<jsp:useBean id="CustomCharts" class="server.function.CustomCharts" /><%    
    String timestamp = request.getParameter("date");   
    out.print(CustomCharts.generateJspLiveFeed(timestamp));
%>