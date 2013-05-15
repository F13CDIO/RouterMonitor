<!DOCTYPE html>

<html>
 <head>
  <title>Graf eksempel...</title>
  <jsp:useBean id="CustomCharts" class="server.function.CustomCharts" /> 
  <% out.print(CustomCharts.importScripts()); %>
 </head>

 <body>
     
<%
  //out.println(CustomCharts.top10Bar10Minute());
  //out.println(CustomCharts.top10Bar1Hour());
  //out.println(CustomCharts.top10Bar1Day());
  out.println(CustomCharts.top10Bar1Month());
%>
  
<hr />
<br />
 
<%
  //out.println(CustomCharts.top5Line10Minute());
  //out.println(CustomCharts.top5Line1Hour());
  //out.println(CustomCharts.top5Line1Day());
  out.println(CustomCharts.top5Line1Month());
%>

<hr />
<br />

<%
  //out.println(CustomCharts.top10Pie10Minut());  
  //out.println(CustomCharts.top10Pie1Hour());
  //out.println(CustomCharts.top10Pie1Day());
  out.println(CustomCharts.top10Pie1Month());
%>

<hr />
<br />

<%  
  out.println(CustomCharts.liveTraffic());
%>

 </body>

</html> 