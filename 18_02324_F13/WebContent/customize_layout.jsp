<%@include file="./includes/top.jsp" %>

<%
    String[] availableCharts = {
        "Top 10 Bar-chart for the last 10 minutes",
        "Top 10 Bar-chart for the last hour",
        "Top 10 Bar-chart for the last day",
        "Top 10 Bar-chart for the last month",
        "Top 5 Line-chart for the last 10 minutes",
        "Top 5 Line-chart for the last hour",
        "Top 5 Line-chart for the last day",
        "Top 5 Line-chart for the last month",
        "Top 10 Pie-chart for the last 10 minutes",
        "Top 10 Pie-chart for the last hour",
        "Top 10 Pie-chart for the last day",
        "Top 10 Pie-chart for the last month",
        "Live traffic"
    };
    
    int i, i2, chartNum = 0, numOfSelectedCharts = 0;
    String[] layout = new String[5];
    String[] selectedCharts = new String[availableCharts.length];

    /* Reads the selected layout into array */
    for (i = 0; i < 5; i++) {
        layout[i] = request.getParameter("row" + i);
    }
    
    /* Reads the selected charts into array */
    for (i=0; i<availableCharts.length; i++) {
        if("on".equals( request.getParameter("chart"+i) )) {
            selectedCharts[numOfSelectedCharts] = availableCharts[i];
            numOfSelectedCharts++;
        }
    }
%>


<form action="./actions/customize_action.jsp" method="post">

    <%
        for (i = 0; i < 5; i++) {
            if ("1".equals(layout[i])) {
                out.println("<div class=\"layout_big\">");
                out.println("<input name=\"row" + i + "\" value=\"" + layout[i] + "\" type=\"hidden\" />");
                out.println("Select the chart to display here:<br />");
                out.println("<select name=\"chart" + chartNum + "\">");

                for(i2=0; i2<numOfSelectedCharts; i2++) {
                    out.println("<option value=\"" + selectedCharts[i2] + "\">" + selectedCharts[i2] + "</option>");
                }
                
                out.println("</select>");
                out.println("</div>");
                
                chartNum++;
            }
            else if ("2".equals(layout[i])) {
                out.println("<div class=\"layout_small\">");
                out.println("<input name=\"row" + i + "\" value=\"" + layout[i] + "\" hidden=\"true\" />");
                out.println("Select the chart to display here:<br />");
                out.println("<select name=\"chart" + chartNum + "\">");

                for (i2 = 0; i2 < numOfSelectedCharts; i2++) {
                    out.println("<option value=\"" + selectedCharts[i2] + "\">" + selectedCharts[i2] + "</option>");
                }

                out.println("</select>");
                out.println("</div>");
                
                chartNum++;
                
                out.println("<div class=\"layout_small\">");
                out.println("Select the chart to display here:<br />");
                out.println("<select name=\"chart" + chartNum + "\">");

                for (i2 = 0; i2 < numOfSelectedCharts; i2++) {
                    out.println("<option value=\"" + selectedCharts[i2] + "\">" + selectedCharts[i2] + "</option>");
                }

                out.println("</select>");
                out.println("</div>");
                
                chartNum++;
            }
        }
    %>

    <input class="form_submit" type="submit" value="Save changes" />
</form>
<div class="clear"></div>

<%@include file="./includes/bottom.jsp" %>