<%@include file="./includes/top.jsp" %>

<p id="live_debug">Senest modtaget datasæt: ...</p>
<% out.println(CustomCharts.liveTraffic()); %>

<%@include file="./includes/bottom.jsp" %>