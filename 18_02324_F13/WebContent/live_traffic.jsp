<%@include file="./includes/top.jsp" %>

<!-- Uncomment this for debug info: <p id="live_debug">Senest modtaget datas�t: ...</p> -->
<% out.println(CustomCharts.liveTraffic()); %>

<%@include file="./includes/bottom.jsp" %>