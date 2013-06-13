<%@include file="./includes/top.jsp" %>

<%
    boolean fileExists = false;
    boolean useStdPage = false;
    int i, chartNum = 0;
    String path, savedData;
    String[] temp, layout, selectedCharts;
    
    /* Check if customize-file exists */
    path = application.getRealPath("/") + "customized_charts.txt";
    java.io.File file = new java.io.File(path);
    if(file.exists()) {
        fileExists = true;
    }
    
    /* Check if user wants std. page */
    if("true".equals(request.getParameter("std"))) {
        useStdPage = true;
    }
    
    if (fileExists && !useStdPage) {
        /* Reads from txt-file */
        java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(path));
        savedData = reader.readLine();

        /* Insert into arrays */
        temp = savedData.split(";");
        layout = temp[0].split(",");
        selectedCharts = temp[1].split(",");
        
        out.println("This is your custom statistics page. You can always acces the standard page");
        out.println("<a href=\"./statistics.jsp?std=true\">here</a>, or customize your page");
        out.println("<a href=\"./customize_selection.jsp\">here</a>.");
        
        /* Print layout with charts */
        for (i = 0; i < 5; i++) {
            if ("1".equals(layout[i])) {
                out.println("<div class=\"statistics_big\">");
                out.println(CustomCharts.insertChartFromName(selectedCharts[chartNum]));
                out.println("</div>");
                
                chartNum++;
            }
            else if ("2".equals(layout[i])) {
                out.println("<div class=\"statistics_small\">");
                out.println(CustomCharts.insertChartFromName(selectedCharts[chartNum]));
                out.println("</div>");
                
                chartNum++;
                
                out.println("<div class=\"statistics_small\">");
                out.println(CustomCharts.insertChartFromName(selectedCharts[chartNum]));
                out.println("</div>");
                
                chartNum++;
            }
        }
        
        out.println("<div class=\"clear\"></div>");
    }
    else {
%>

<div id="statistics_top_right"><% out.println(CustomCharts.top10Pie1Month()); %></div>
This is the main page for network statistics. We have picked out some charts,
that we think might interest most of our users. However, if you have a login for
our service, it is also possible for you to costomize this page.
<br /><br />
You can add additional charts, as well as rearrange the entire page.
<br /><br />
The customization page can be found <a href="./customize_selection.jsp">here.</a>
<div class="clear"><% out.println(CustomCharts.top10Bar1Month()); %></div>

<%
    }
%>

<%@include file="./includes/bottom.jsp" %>