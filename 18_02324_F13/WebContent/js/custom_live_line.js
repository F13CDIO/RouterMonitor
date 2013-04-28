var bufferIndex = -1;
var activeBuffer = 1;
var liveTrafficBuffer = [[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]];
var currentDate = new Date();

/* Set starttime. Even when running "live", there should be af few seconds
 * delay to allow the data to reach the database, before beeing requested */
addSeconds(-5);

/* Takes care of the "static" datapoint as well as the first 10 seconds of live data */
fillBuffers();

function fillBuffers() {
    /* When the chart loads, it will display 20 "static" points immediately. For this to make
     * sense, it has to be the 20 seconds leading up to "now", så we add -20 seconds to the current time. */
    addSeconds(-20);

    /* Runs the get-function twice to fill both buffers */
    get10SecondTraffic("fill1");
}

function javaDateString() {
    var year = currentDate.getFullYear();
    var month = ('0' + (currentDate.getMonth()+1)).slice(-2);
    var day = ('0' + currentDate.getDate()).slice(-2);
    var hour = ('0' + currentDate.getHours()).slice(-2);
    var minute = ('0' + currentDate.getMinutes()).slice(-2);
    var second = ('0' + currentDate.getSeconds()).slice(-2);
        
    return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second + ".0";
}

function addSeconds(seconds) {
    currentDate.setSeconds(currentDate.getSeconds() + seconds);
}

function getInactiveBuffer() {
    return activeBuffer*-1+1;
}

function startUpdate(input) {
    var index = 0, active = 0;
    
    /* Translates the recieved index from the "-19 to 0", to two sets of
     * "0 to 9", as requered to use the two buffers */
    if(input <= -10) {
        index = input + 19;
        active = 0;
    }
    else {
        index = input + 9;
        active = 1;
    }
    
    return liveTrafficBuffer[active][index];
    }

function liveUpdate() {
    /* Sikrer at der itereres gennem værdierne */
    bufferIndex++;
    if(bufferIndex >= 10) {
        bufferIndex = 0;
        activeBuffer = getInactiveBuffer();
    }
    /* Fylder bufferen igen, hvis den er ved at være tom */
    else if (bufferIndex === 7) {
        get10SecondTraffic(currentDate);
    }
    
    return liveTrafficBuffer[activeBuffer][bufferIndex];
}

function get10SecondTraffic(doOnFinish) {
    /* Get string with JSON Object from JSP page */
    $.get('js/custom_live_get10SecondTraffic.jsp?date=' + currentDate.getTime(), function(data) {
        var i;
        
        /* Parse input string to JSON Object */
        data = eval( '(' + data + ')' );
        
        /* The loop adds 1 second 10 times, so we need to subtract them first */
        addSeconds(-10);
        
        /* Iterates through the recieved dataset */
        for(i=0; i<10; i++) {
            addSeconds(1);
            
            /* If the value of the current key is 'undefined', it will be set to 0 */
            if(typeof data[javaDateString()] == 'undefined') {
                liveTrafficBuffer[getInactiveBuffer()][i] = 0;
            }
            else {
                liveTrafficBuffer[getInactiveBuffer()][i] = data[javaDateString()];
            }
        }
        
        /*Adds 10 seconds when a new dataset have been saved */
        addSeconds(10);
        
        if(doOnFinish === "fill1") {
            activeBuffer = getInactiveBuffer();
            get10SecondTraffic("fill2");
        }
        else if(doOnFinish === "fill2") {
            drawChart();
            get10SecondTraffic("switch");
        }
        else if(doOnFinish === "switch") {
            activeBuffer = getInactiveBuffer();
        }
    });
}

function drawChart() {
/* This is the Highchart example code. We only change the input data */
$(function () {
    $(document).ready(function() {
        Highcharts.setOptions({
            global: {
                useUTC: false
            }
        });
    
        var chart;
        $('#container').highcharts({
            chart: {
                type: 'spline',
                marginRight: 10,
                events: {
                    load: function() {
    
                        // set up the updating of the chart each second
                        var series = this.series[0];
                        setInterval(function() {
                            var x = (new Date()).getTime(), // current time
                                y = liveUpdate();
                            series.addPoint([x, y], true, true);
                        }, 1000);
                    }
                }
            },
            title: {
                text: 'Live random data'
            },
            xAxis: {
                type: 'datetime',
                tickPixelInterval: 150
            },
            yAxis: {
                title: {
                    text: 'Value'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                formatter: function() {
                        return '<b>'+ this.series.name +'</b><br/>'+
                        Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+
                        Highcharts.numberFormat(this.y, 2);
                }
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
            },
            series: [{
                name: 'Random data',
                data: (function() {
                    // generate an array of random data
                    var data = [],
                        time = (new Date()).getTime(),
                        i;
    
                    for (i = -19; i <= 0; i++) {
                        data.push({
                            x: time + i * 1000,
                            y: startUpdate(i)
                        });
                    }
                    return data;
                })()
            }]
        });
    });
    
});

}