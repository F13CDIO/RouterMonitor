<jsp:useBean id="DAO" class="server.data.mySqlDataAccessObjects.DataPackageDAO" />

<%
	String username, role, output;

	username = request.getParameter("username");
	role = request.getParameter("role");

	try {
		DAO.openConnection();
		
		DAO.editUser(username, null, role);
		
		output = "Set '" + username + "' to '" + role + "'. User updated successfully.";
	}
	catch (Exception e) {
		output = "Database error. Could not delete user!<br />Error: " + e;
	}
	finally {
		DAO.closeConnection();
	}
	
	session.setAttribute("message", output);
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Network Monitoring System - Saving data...</title>
        <meta http-equiv="refresh" content="0; url=./user_management.jsp">
    </head>
    
    <body>
        <% out.println(output); %>
        See the result <a href="./user_management.jsp">here.</a>
    </body>
</html>