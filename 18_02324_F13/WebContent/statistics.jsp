<jsp:useBean id="DAO" class="server.data.mySqlDataAccessObjects.DataPackageDAO" />

<%@include file="./includes/top.jsp" %>

<%
    boolean useCustom = false;
    boolean useStdPage = false;
    int i, chartNum = 0;
    String path, savedData = null;
    String[] temp, layout, selectedCharts;
    
    /* Check if user wants std. page */
    if("true".equals(request.getParameter("std"))) {
        useStdPage = true;
    }
    
    /* Check if customize-data exists, and gets it if it does */
    try {
	    DAO.openConnection();
	    if(userName != null && !useStdPage && DAO.hasUserSettings(userName)) {
	    	savedData = DAO.getUserSettings(userName);
	    	useCustom = true;
	    }
    }
    catch (Exception e) {
    	out.print("<p id=\"message\">Database error. Could not get customized layout.</p>");
    	useCustom = false;
    }
    finally {
    	DAO.closeConnection();
    }
    
    
    if (useCustom && savedData != null) {
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