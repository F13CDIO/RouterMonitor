<%@include file="./includes/top.jsp" %>

<p id="live_debug">Senest modtaget datas�t: ...</p>
<% out.println(CustomCharts.liveTraffic()); %>

<%@include file="./includes/bottom.jsp" %>