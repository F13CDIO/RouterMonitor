<%
	String output, user;

	user = request.getParameter("user");

	if(request.getUserPrincipal() == null) {
		output = "User \"" + user + "\" was logged out successfully.";
	}
	else {
		output = "Error. Unable to log user out.";
	}
	
	session.setAttribute("logout_message", output);
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Network Monitoring System - Saving data...</title>
        <meta http-equiv="refresh" content="0; url=./login.jsp">
    </head>
    
    <body>
    	<% out.println(output); %>
        Continue to login-page <a href="./login.jsp">here.</a>
    </body>
</html>