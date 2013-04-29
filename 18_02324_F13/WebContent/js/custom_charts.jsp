<%-- Includes the javascript libraries used to generate the charts --%>
<script type="text/javascript" src="./js/jquery.min.js"></script>
<script src="./js/highcharts.js"></script>
<script src="./js/modules/exporting.js"></script>

<%-- Import mySqlConnect etc --%>
<%@ page import="java.util.Calendar, server.data.mySqlConnect" %>

<%-- Generates a basic pie chart with top10 data from last 2 hours --%>
<%!
public String chart_pie2h() {
    /* Pie chart data */
    double[] pieValues = {45.0, 26.8, 12.8, 8.5, 6.2, 0.7};
    String[] pieCategories = {"Firefox", "IE", "Chrome", "Safari", "Opera", "Others"};
    
    
    /* IMPORTANT! Loads the driver for mySQL interface. Not needed in Java, but IS needed in JSP! */
    try {
        Class.forName("com.mysql.jdbc.Driver");
    }
    catch(Exception e) {
        return null;
    }

    /* A Java Calender, so the starttime han be set and sent as "Date"-type */
    Calendar cal = Calendar.getInstance();

    /* Connects to mySQL database, to be able to get data */
    mySqlConnect mySql = new mySqlConnect();
    mySql.connect();
    
    /* Get data */

    
    
    /* Close mySQL database connection */
    mySql.closeConnection();
    
    return chart_pie(pieCategories, pieValues);
}
%>

<%-- Generates a top10 chart --%>
<%!	public String chart_top10(String[] mainCategories, double[] mainValues, String[][] subCategories, double[][] subValues) {
	int i, iSub;

	/* Constants */
	String TITLE = "Top 10 most visited sites";
	String SUB_TITLE = "Click the columns to view sub-sites. Click again to view main-sites.";
	String Y_AXIS_DESCRIPTION = "Number of hits";
	String DATA_LABEL_UNIT = "";
	String HTML_ID = "top10_container";
	String MIN_WIDTH = "400px";
	String HEIGHT = "400px";
	String MAIN_PAGE_DESCRIPTION = "main-sites";
	String SUB_PAGES_DESCRIPTION = "sub-sites";
	String VALUE_DESCRIPTION = "hits";
	

	/* Generate HTML data-part */
	String htmlCategories = "'" + mainCategories[0] + "'";
	for(i=1; i<mainValues.length; i++) {
		htmlCategories = htmlCategories + ", '" + mainCategories[i] + "'";
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
	String script = "<script type=\"text/javascript\">$(function () {var colors = Highcharts.getOptions().colors, categories = [" + htmlCategories + "], name = '" + MAIN_PAGE_DESCRIPTION + "', data = [" + data + "]; function setChart(name, categories, data, color) {chart.xAxis[0].setCategories(categories, false); chart.series[0].remove(false); chart.addSeries({name: name, data: data, color: color || 'white'}, false); chart.redraw();} var chart = $('#" + HTML_ID + "').highcharts({chart: {type: 'column'}, title: {text: '" + TITLE + "'}, subtitle: {text: '" + SUB_TITLE + "'}, xAxis: {categories: categories}, yAxis: {title: {text: '" + Y_AXIS_DESCRIPTION + "'} }, plotOptions: {column: {cursor: 'pointer', point: {events: {click: function() {var drilldown = this.drilldown; if (drilldown) {setChart(drilldown.name, drilldown.categories, drilldown.data, drilldown.color);} else {setChart(name, categories, data);} } } }, dataLabels: {enabled: true, color: colors[0], style: {fontWeight: 'bold'}, formatter: function() {return this.y + ' " + DATA_LABEL_UNIT + "'; } } } }, legend: {enabled: false},tooltip: {formatter: function() {var point = this.point, s = this.x +': <b>'+ this.y +' " + VALUE_DESCRIPTION + "</b><br/>'; if (point.drilldown) {s += 'Click to view '+ point.category +' " + SUB_PAGES_DESCRIPTION + "'; } else {s += 'Click to return to " + MAIN_PAGE_DESCRIPTION + "'; } return s; } }, series: [{name: name, data: data, color: 'white'}], exporting: {enabled: true} }) .highcharts(); }); </script>";

	return script + "\n" + container;
}
%>

<%-- Generates basic pie chart --%>
<%!	public String chart_pie(String[] categories, double[] values) {
	int i;
	
	/* Constants */
	String TITLE = "Top 10 websites the last hour";
	String HTML_ID = "pie_container";
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
	String script = "<script type=\"text/javascript\"> $(function () {$('#" + HTML_ID + "').highcharts({chart: {plotBackgroundColor: null,plotBorderWidth: null,plotShadow: false},title: {text: '" + TITLE + "'},tooltip: {pointFormat: '{series.name}: <b>{point.percentage}%</b>',percentageDecimals: 1},plotOptions: {pie: {allowPointSelect: true,cursor: 'pointer',dataLabels: {enabled: true,color: '#000000',connectorColor: '#000000',formatter: function() {return '<b>'+ this.point.name +'</b>: '+ this.percentage +' %';}}}},series: [{type: 'pie',name: 'Browser share',data: [" + data + "]}]});});</script>";
	String container = "<div id=\"" + HTML_ID + "\" style=\"min-width: " + MIN_WIDTH + "; height: " + HEIGHT + "; margin: 0 auto\"></div>";
	
	return script + "\n" + container;
}
%>

<%-- Generates basic line chart --%>
<%! public String chart_line(String categories[], String series[], double values[][]) {
	int i, iSub;
	
	/* Constants */
	String TITLE = "Monthly Average Temperature";
	String SUB_TITLE = "Source: WorldClimate.com";
	String Y_AXIS_DESCRIPTION = "Temperature (°C)";
	String HTML_ID = "line_container";
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
	String script = "<script type=\"text/javascript\"> $(function () { $('#" + HTML_ID + "').highcharts({ chart: { type: 'line', marginRight: 130, marginBottom: 25 }, title: { text: '" + TITLE + "', x: -20}, subtitle: { text: '" + SUB_TITLE + "', x: -20 }, xAxis: { categories: [" + htmlCategories + "] }, yAxis: { title: { text: '" + Y_AXIS_DESCRIPTION + "' }, plotLines: [{ value: 0, width: 1, color: '#808080' }] }, tooltip: { valueSuffix: '" + UNIT + "' }, legend: { layout: 'vertical', align: 'right', verticalAlign: 'top', x: -10, y: 100 }, series: [" + data + "] }); });   </script>";
	String container = "<div id=\"" + HTML_ID + "\" style=\"min-width: " + MIN_WIDTH + "; height: " + HEIGHT + "; margin: 0 auto\"></div>";

	return script + "\n" + container;
}
%>

<%-- Generates Live trafic line chart --%>
<%! public String chart_live() {
    String script = "<script src=\"./js/custom_live_line.js\"></script>";
    String container = "<div id=\"container\" style=\"min-width: 400px; height: 400px; margin: 0 auto\"></div>";
    
    return script + "\n" + container;
}
%>