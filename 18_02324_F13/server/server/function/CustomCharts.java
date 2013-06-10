package server.function;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.data.mySQLConnector.MySQLConnector;
import server.data.mySQLInterfaces.IDataPackageDAO;
import server.data.mySqlDataAccessObjects.DataPackageDAO;

public class CustomCharts {
    /* The Javascript files for jQuery, Highchart etc. can be placed in a subfolder */
    private static String SCRIPT_PATH = "./chart/";
    
    /* The DAO is used by almost all of the methods, so it is instantiated once, globally */
    private IDataPackageDAO DAO = new DataPackageDAO();
    
    /* An integer to increment for each new chart, to make multiple charts possible */
    private int uniqeHtmlId = 0;
    
    
    public String importScripts() {
        String begin, end;
        
        begin = "<script type=\"text/javascript\" src=\"";
        end = "\"></script>\n  ";
        
        return begin + SCRIPT_PATH + "jquery.min.js" + end + begin + SCRIPT_PATH + "highcharts.js" + end + begin + SCRIPT_PATH + "modules/exporting.js" + end;
    }
    
    public String generateJspLiveFeed(String timestamp) {
        try {
            /* A Java Calender, so the starttime can be set and sent as "Date"-type */
            Calendar cal = Calendar.getInstance();

            /* Get date from the timestamp string, converts to Long, and sets the Calendar date from this */
            Long input = new Long(timestamp);
            cal.setTimeInMillis(input);

            /* Connects to mySQL database, to be able to get data */
            MySQLConnector.connect();

            /* Gets data and saves in temp var, so the MySQL connection can be closed before return */
            String output = DAO.get10SecondTraffic(cal.getTime(), "").toString();

            /* Close mySQL database connection */
            MySQLConnector.closeConnection();

            return output;
        }
        catch (Exception e) {
            return "";
        }
        finally {
            MySQLConnector.closeConnection();
        }
    }
    
    
    public String top10Bar10Minute() {
        String TITLE = "Top 10 most visited sites the last 10 minutes";
        
        /* A Java Calender, so the starttime can be set and sent as "Date"-type */
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, -10);
        
        return top10Bar(cal.getTime(), TITLE);
    }
    
    public String top10Bar1Hour() {
        String TITLE = "Top 10 most visited sites the last hour";
        
        /* A Java Calender, so the starttime can be set and sent as "Date"-type */
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, -1);
        
        return top10Bar(cal.getTime(), TITLE);
    }
    
    public String top10Bar1Day() {
        String TITLE = "Top 10 most visited sites the last day";
        
        /* A Java Calender, so the starttime can be set and sent as "Date"-type */
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        
        return top10Bar(cal.getTime(), TITLE);
    }
    
    public String top10Bar1Month() {
        String TITLE = "Top 10 most visited sites the last month";
        
        /* A Java Calender, so the starttime can be set and sent as "Date"-type */
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        
        return top10Bar(cal.getTime(), TITLE);
    }
    
    
    public String top5Line10Minute() {
        String TITLE = "Top 5 pages the last 10 minuts, usage over time";
        int NUMBER_OF_POINTS = 10;
        int SECONDS_BACK = 600;
        
        return top5Line(TITLE, NUMBER_OF_POINTS, SECONDS_BACK);
    }
    
    public String top5Line1Hour() {
        String TITLE = "Top 5 pages the last 1 hour, usage over time";
        int NUMBER_OF_POINTS = 60;
        int SECONDS_BACK = 3600;
        
        return top5Line(TITLE, NUMBER_OF_POINTS, SECONDS_BACK);
    }
    
    public String top5Line1Day() {
        String TITLE = "Top 5 pages the last day, usage over time";
        int NUMBER_OF_POINTS = 24;
        int SECONDS_BACK = 86400;
        
        return top5Line(TITLE, NUMBER_OF_POINTS, SECONDS_BACK);
    }
    
    public String top5Line1Month() {
        String TITLE = "Top 5 pages the last month, usage over time";
        int NUMBER_OF_POINTS = 31;
        int SECONDS_BACK = 2678400;
        
        return top5Line(TITLE, NUMBER_OF_POINTS, SECONDS_BACK);
    }
    
    
    public String top10Pie10Minute() {
        String TITLE = "Top 10 websites the last 10 minuts";
        
        /* A Java Calender, so the starttime can be set and sent as "Date"-type */
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, -10);
        
        return top10Pie(cal.getTime(), TITLE);
    }
    
    public String top10Pie1Hour() {
        String TITLE = "Top 10 websites the last hour";
        
        /* A Java Calender, so the starttime can be set and sent as "Date"-type */
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, -60);
        
        return top10Pie(cal.getTime(), TITLE);
    }
    
    public String top10Pie1Day() {
        String TITLE = "Top 10 websites the last day";
        
        /* A Java Calender, so the starttime can be set and sent as "Date"-type */
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        
        return top10Pie(cal.getTime(), TITLE);
    }
    
    public String top10Pie1Month() {
        String TITLE = "Top 10 websites the last month";
        
        /* A Java Calender, so the starttime can be set and sent as "Date"-type */
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        
        return top10Pie(cal.getTime(), TITLE);
    }
    
    
    public String liveTraffic() {
        String script = "<script src=\"" + SCRIPT_PATH + "custom_live_line.js\"></script>";
        String container = "<div id=\"container\" style=\"min-width: 400px; height: 400px; margin: 0 auto\"></div>";

        return script + "\n" + container;
    }
    
    
    private String top10Bar(Date offset, String title) {
        try {
            int i;

            /* Connects to mySQL database, to be able to get data */
            MySQLConnector.connect();

            /* Get data */
            JSONArray data = DAO.getTop10(offset);
            JSONObject temp;
            
            /* Creates array with size according to actual number of pages in top10 */
            String[] mainValues = new String[data.size()];
            String[] mainCategories = new String[data.size()];
            String[][] subValues = new String[data.size()][10]; //Not implementet
            String[][] subCategories = new String[data.size()][10]; //Not implementet

            for (i = 0; i < data.size(); i++) {
                temp = (JSONObject) data.get(i);

                mainValues[i] = temp.get("count").toString();
                mainCategories[i] = temp.get("host").toString();
            }

            /* Close mySQL database connection */
            MySQLConnector.closeConnection();

            return generateBar(mainCategories, mainValues, subCategories, subValues, title);
        }
        catch (Exception e) {
            return "Database error occurred. <br /><br />Error: " + e.getMessage() + "<br />";
        }
        finally {
            MySQLConnector.closeConnection();
        }
    } //OBS - subhost not implemented
    
    private String generateBar(String[] mainCategories, String[] mainValues, String[][] subCategories, String[][] subValues, String title) {
	int i, iSub;

	/* Constants */
	String TITLE = title;
	String SUB_TITLE = "Click the columns to view sub-sites. Click again to view main-sites.";
	String Y_AXIS_DESCRIPTION = "Number of hits";
	String DATA_LABEL_UNIT = "";
	String HTML_ID = "top10_container_" + uniqeHtmlId++;
	String MIN_WIDTH = "400px";
	String HEIGHT = "400px";
	String MAIN_PAGE_DESCRIPTION = "main-sites";
	String SUB_PAGES_DESCRIPTION = "sub-sites";
	String VALUE_DESCRIPTION = "hits";
	

	/* Generate HTML data-part */
	String htmlCategories = "";
	for(i=0; i<mainValues.length; i++) {
            if(i != 0) {
                htmlCategories = htmlCategories + ", ";
            }
            
            htmlCategories = htmlCategories + "'" + mainCategories[i] + "'";
	}
	
	String htmlSubCategories;
	String htmlSubValues;
	String data = "";
	for(i=0; i<mainValues.length; i++) {
		if(i != 0) {
			data = data + ", ";
		}
		
		htmlSubCategories = "'" + subCategories[i][0] + "'";
		for(iSub=1; iSub<subValues[i].length; iSub++) {
			htmlSubCategories = htmlSubCategories + ", '" + subCategories[i][iSub] + "'";
		}
		
		htmlSubValues = "" + subValues[i][0];
		for(iSub=1; iSub<subValues[i].length; iSub++) {
			htmlSubValues = htmlSubValues + ", " + subValues[i][iSub];
		}
		
		data = data + "{y: " + mainValues[i] + ", color: colors[" + i + "], drilldown: {name: '" + mainCategories[i] + " " + SUB_PAGES_DESCRIPTION + "', categories: [" + htmlSubCategories + "], data: [" + htmlSubValues + "], color: colors[" + i + "]}}";
	}
	

	/* Generate HTML main-part */
	String container = "<div id=\"" + HTML_ID + "\" style=\"min-width: " + MIN_WIDTH + "; height: " + HEIGHT + "; margin: 0 auto\"></div>";
	String script = "<script type=\"text/javascript\">$(function () {var colors = Highcharts.getOptions().colors, categories = [" + htmlCategories + "], name = '" + MAIN_PAGE_DESCRIPTION + "', data = [" + data + "]; function setChart(name, categories, data, color) {chart.xAxis[0].setCategories(categories, false); chart.series[0].remove(false); chart.addSeries({name: name, data: data, color: color || 'white'}, false); chart.redraw();} var chart = $('#" + HTML_ID + "').highcharts({chart: {type: 'column'}, title: {text: '" + TITLE + "'}, subtitle: {text: '" + SUB_TITLE + "'}, xAxis: {categories: categories, labels: {rotation: -20, align: 'right'} }, yAxis: {title: {text: '" + Y_AXIS_DESCRIPTION + "'} }, plotOptions: {column: {cursor: 'pointer', point: {events: {click: function() {var drilldown = this.drilldown; if (drilldown) {setChart(drilldown.name, drilldown.categories, drilldown.data, drilldown.color);} else {setChart(name, categories, data);} } } }, dataLabels: {enabled: true, color: colors[0], style: {fontWeight: 'bold'}, formatter: function() {return this.y + ' " + DATA_LABEL_UNIT + "'; } } } }, legend: {enabled: false},tooltip: {formatter: function() {var point = this.point, s = this.x +': <b>'+ this.y +' " + VALUE_DESCRIPTION + "</b><br/>'; if (point.drilldown) {s += 'Click to view '+ point.category +' " + SUB_PAGES_DESCRIPTION + "'; } else {s += 'Click to return to " + MAIN_PAGE_DESCRIPTION + "'; } return s; } }, series: [{name: name, data: data, color: 'white'}], exporting: {enabled: true} }) .highcharts(); }); </script>";

	return script + "\n" + container;
}
    
    private String top5Line(String title, int numberOfPoints, int secondsBack) {
        try {
            int NUMBER_OF_DOMAINS = 5;

            /* Declarations */
            int i, iSub, interval, actualDomains, numberOfZeros;
            JSONArray top10Data;
            JSONObject temp;
            Calendar dateOffset, dateNow;
            String dateString;
            String categories[] = new String[numberOfPoints];
            String series[];
            String values[][];
            
            /* Sets dates */
            dateNow = Calendar.getInstance();
            dateOffset = Calendar.getInstance();
            dateOffset.add(Calendar.SECOND, -secondsBack);
            
            /* Sets interval for iteration and numberOfZeros for zeroing of date */
            interval = secondsBack / numberOfPoints;
            numberOfZeros = numberOfZeros(secondsBack);

            /* Connect to database */
            MySQLConnector.connect();

            /* Get top10 data */
            top10Data = DAO.getTop10(dateOffset.getTime());
            System.out.println(top10Data);
            
            /* Check if the top10 has enough elements to get the requested number of domains */
            if(top10Data.size() < NUMBER_OF_DOMAINS) {
                actualDomains = top10Data.size();
            }
            else {
                actualDomains = NUMBER_OF_DOMAINS;
            }
            
            /* Declares the value-arrays whith size according to the acutal number of domains */
            series = new String[actualDomains];
            values = new String[actualDomains][numberOfPoints];

            for (i = 0; i < actualDomains; i++) {
                /* Get series name */
                temp = (JSONObject) top10Data.get(i);
                series[i] = temp.get("host").toString();

                /* Get series data */
                temp = getTraffic(secondsBack, dateNow.getTime(), series[i]);
                System.out.println(temp);
                
                /* Process series data */
                for(iSub = 0; iSub < numberOfPoints; iSub++) {
                    /* Sets categories if its the first run*/
                    if(i == 0) {
                        categories[iSub] = categoriesDate(dateOffset);
                    }
                    /* Generate date-key-string */
                    dateString = javaDateString(dateOffset, numberOfZeros);
                    System.out.println(dateString);
                    
                    /* Get data from date-key */
                    if(temp.get(dateString) != null) {
                        values[i][iSub] = temp.get(dateString).toString();
                    }
                    else {
                        /* If no data read, set to 0 */
                        values[i][iSub] = "0";
                    }
                    
                    /* Add seconds according to interval, to get next datapoint */
                    dateOffset.add(Calendar.SECOND, interval);
                }
                
                /* Sets back the offset-date to what it was before the iteration */
                dateOffset.add(Calendar.SECOND, -secondsBack);
            }

            /* Close mySQL database connection */
            MySQLConnector.closeConnection();

            return generateLine(categories, series, values, title);
        }
        catch (Exception e) {
            return "Database error occurred. <br /><br />Error: " + e.getMessage() + "<br />";
        }
        finally {
            MySQLConnector.closeConnection();
        }
    }
    
    private String generateLine(String categories[], String series[], String values[][], String title) {
	int i, iSub;
	
	/* Constants */
	String TITLE = title;
	String SUB_TITLE = "";
	String Y_AXIS_DESCRIPTION = "Number of visits";
	String HTML_ID = "line_container_" + uniqeHtmlId++;
	String MIN_WIDTH = "400px";
	String HEIGHT = "400px";
	String UNIT = "";
	
	
	/* Generate HTML data-part */
	String htmlCategories = "";	
	for(i=0; i<categories.length; i++) {
		if(i != 0) {
			htmlCategories = htmlCategories + ", ";
		}
		htmlCategories = htmlCategories + "'" + categories[i] + "'";
	}
	
	String data = "";
	for(i=0; i<series.length; i++) {
		if(i != 0) {
			data = data + ", ";
		}
		
		data = data + "{name: '" + series[i] + "', data: [";
		
		for(iSub=0; iSub<categories.length; iSub++) {
			if(iSub != 0) {
				data = data + ", ";
			}
			
			data = data + values[i][iSub];
		}
		
		data = data + "]}";
	}
	
	
	/* Generates HTML main part */
	String script = "<script type=\"text/javascript\"> $(function () { $('#" + HTML_ID + "').highcharts({ chart: { type: 'line', marginRight: 130, marginBottom: 75 }, title: { text: '" + TITLE + "', x: -20}, subtitle: { text: '" + SUB_TITLE + "', x: -20 }, xAxis: { categories: [" + htmlCategories + "], labels: {rotation: -45, align: 'right' } }, yAxis: { title: { text: '" + Y_AXIS_DESCRIPTION + "' }, plotLines: [{ value: 0, width: 1, color: '#808080' }] }, tooltip: { valueSuffix: '" + UNIT + "' }, legend: { layout: 'vertical', align: 'right', verticalAlign: 'top', x: -10, y: 100 }, series: [" + data + "] }); });   </script>";
	String container = "<div id=\"" + HTML_ID + "\" style=\"min-width: " + MIN_WIDTH + "; height: " + HEIGHT + "; margin: 0 auto\"></div>";

	return script + "\n" + container;
}
    
    private String top10Pie(Date offset, String title) {
        try {
            int i;

            /* Connects to mySQL database, to be able to get data */
            MySQLConnector.connect();

            /* Get data */
            JSONArray data = DAO.getTop10(offset);
            JSONObject temp;
            
            /* Creates array with size according to actual number of pages in top10 */
            String[] pieValues = new String[data.size()];
            String[] pieCategories = new String[data.size()];


            for (i = 0; i < data.size(); i++) {
                temp = (JSONObject) data.get(i);

                pieValues[i] = temp.get("count").toString();
                pieCategories[i] = temp.get("host").toString();
            }

            /* Close mySQL database connection */
            MySQLConnector.closeConnection();

            return generatePie(pieCategories, pieValues, title);
        }
        catch (Exception e) {
            return "Database error occurred. <br /><br />Error: " + e.getMessage() + "<br />";
        }
        finally {
            MySQLConnector.closeConnection();
        }
    }
    
    private String generatePie(String[] categories, String[] values, String title) {
	int i;
	
	/* Constants */
	String TITLE = title;
	String HTML_ID = "pie_container_" + uniqeHtmlId++;
	String MIN_WIDTH = "400px";
	String HEIGHT = "400px";
	
	/* Generate HTML data-part */
	String data = "";
	for(i=0; i<values.length; i++) {
		if(i != 0) {
			data = data + ", ";
		}
		
		data = data + "['" + categories[i] + "', " + values[i] + "]";
	}
	
	/* Generate HTML main part */
	String script = "<script type=\"text/javascript\"> $(function () {$('#" + HTML_ID + "').highcharts({chart: {plotBackgroundColor: null,plotBorderWidth: null,plotShadow: false},title: {text: '" + TITLE + "'},tooltip: {formatter: function() {return this.point.name +'<br />Percentage of visits: <b>'+ this.percentage.toFixed(2) +' %</b><br />Number of packages: <b>' + this.y + '</b>';}},plotOptions: {pie: {allowPointSelect: true,cursor: 'pointer',dataLabels: {enabled: true,color: '#000000',connectorColor: '#000000',formatter: function() {return '<b>'+ this.point.name +'</b>: '+ this.percentage.toFixed(2) +' %';}}}},series: [{type: 'pie',name: 'Browser share',data: [" + data + "]}]});});</script>";
	String container = "<div id=\"" + HTML_ID + "\" style=\"min-width: " + MIN_WIDTH + "; height: " + HEIGHT + "; margin: 0 auto\"></div>";
	
	return script + "\n" + container;
}
    
    
    private JSONObject getTraffic(int secondsBack, Date date, String host) throws SQLException {
        if(secondsBack == 10) {
            return DAO.get10SecondTraffic(date, host);
        }
        else if(secondsBack == 60) {
            //get1MinuteTraffic er fjernet det nye interface?!?
            //return DAO.get1MinuteTraffic(date, host);
            return DAO.get1HourTraffic(date, host);
        }
        else if(secondsBack == 600) {
            //get10MinuteTraffic er fjernet det nye interface?!?
            //return DAO.get10MinuteTraffic(date, host);
            return DAO.get1HourTraffic(date, host);
        }
        else if(secondsBack == 3600) {
            return DAO.get1HourTraffic(date, host);
        }
        else if(secondsBack == 86400) {
            return DAO.get1DayTraffic(date, host);
        }
        else if(secondsBack == 2678400) {
            return DAO.get1MonthTraffic(date, host);
        }
        
        return null;
    }
    
    private int numberOfZeros(int seconds) {
        if(seconds > 24*60*60) {
            return 3;
        }
        else if(seconds > 60*60) {
            return 2;
        }
        else if(seconds > 60) {
            return 1;
        }
        
        return 0;
    }
    
    private String categoriesDate(Calendar inputDate) {
        int month = inputDate.get(Calendar.MONTH)+1;
        int day = inputDate.get(Calendar.DAY_OF_MONTH);
        String hour = String.format("%02d", inputDate.get(Calendar.HOUR_OF_DAY));
        String minute = String.format("%02d", inputDate.get(Calendar.MINUTE));
        
        return day+"/"+month+" "+hour+":"+minute;
    }
    
    private String javaDateString(Calendar inputDate, int zeros) {
        String[] date = new String[6];
        
        /* Year */
        date[5] = Integer.toString(inputDate.get(Calendar.YEAR));
        
        /* Month - adding zero if less than 2 characters */
        date[4] = String.format("%02d", inputDate.get(Calendar.MONTH)+1);
        
        /* Day - adding zero if less than 2 characters */
        date[3] = String.format("%02d", inputDate.get(Calendar.DAY_OF_MONTH));
        
        /* Hour - adding zero if less than 2 characters */
        date[2] = String.format("%02d", inputDate.get(Calendar.HOUR_OF_DAY));
        
        /* Minute - adding zero if less than 2 characters */
        date[1] = String.format("%02d", inputDate.get(Calendar.MINUTE));
        
        /* Second - adding zero if less than 2 characters */
        date[0] = String.format("%02d", inputDate.get(Calendar.SECOND));
        
        /* Zero the specified number of places */
        if(zeros > 5) {
            return null;
        }
        
        zeros--;
        while(zeros >= 0) {
            date[zeros] = "00";
            
            /* Fix for the "02" bug in Hours */
            if(zeros == 2) {
                date[2] = "02";
            }
            
            zeros--;
        }
        
        /* Return complete date-string */
        return date[5]+"-"+date[4]+"-"+date[3]+" "+date[2]+":"+date[1]+":"+date[0]+".0";
    } //OBS - remember the "02" fix
}
