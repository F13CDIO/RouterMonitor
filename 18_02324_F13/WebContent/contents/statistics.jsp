<jsp:useBean id="CustomCharts" class="server.function.CustomCharts" />
<% out.println(CustomCharts.top10Pie1Month()); %>
<br /><br />
<% out.println(CustomCharts.top10Bar1Month()); %>