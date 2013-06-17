<%
String user;

user = request.getRemoteUser();

session.invalidate();
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Network Monitoring System - Saving data...</title>
        <meta http-equiv="refresh" content="0; url=./logout_check.jsp?user=<% out.print(user); %>">
    </head>
    
    <body>
        Continue to logout-check <a href="./logout_check.jsp?user=<% out.print(user); %>">here.</a>
    </body>
</html>