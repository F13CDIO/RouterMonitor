<!DOCTYPE html>

<html>
 <head>
  <title>Graf eksempel...</title>
  
  <%-- Disse to linjer skal indsættes i "<head>" på hjemmesiden. Den første linje opretter "bønnen",
  som benyttes til at kalde alle metoder. Den anden linje printer import af scripts der bruges af highcharts. --%>
  <jsp:useBean id="CustomCharts" class="server.function.CustomCharts" /> 
  <% out.print(CustomCharts.importScripts()); %>
  
 </head>

 <body>
     
<%-- Herefter kan grafer indsættes ved at kalde metoden for den type graf man ønsker.
Grafen indsættes i HTML der, hvor metoden kaldes. Herunder eksempler på de forskellige muligheder. --%>

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