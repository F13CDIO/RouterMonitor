<!DOCTYPE html>

<html>
 <head>
  <title>Graf eksempel...</title>
  <jsp:useBean id="customCharts" class="server.function.customCharts" /> 
  <% out.print(customCharts.importScripts()); %>
 </head>

 <body>
     
<%
  //out.println(customCharts.top10Bar10Minute());
  //out.println(customCharts.top10Bar1Hour());
  //out.println(customCharts.top10Bar1Day());
  out.println(customCharts.top10Bar1Month());
%>
  
<hr />
<br />
 
<%
  //out.println(customCharts.top5Line10Minute());
  //out.println(customCharts.top5Line1Hour());
  //out.println(customCharts.top5Line1Day());
  out.println(customCharts.top5Line1Month());
%>

<hr />
<br />

<%
  //out.println(customCharts.top10Pie10Minut());  
  //out.println(customCharts.top10Pie1Hour());
  //out.println(customCharts.top10Pie1Day());
  out.println(customCharts.top10Pie1Month());
%>

<hr />
<br />

<%  
  out.println(customCharts.liveTraffic());
%>

 </body>

</html> 