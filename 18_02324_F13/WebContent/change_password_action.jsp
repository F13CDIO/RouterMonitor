<jsp:useBean id="DAO" class="server.data.mySqlDataAccessObjects.DataPackageDAO" />
<jsp:useBean id="mysql" class="server.data.mySQLConnector.MySQLConnector" />

<%
	String oldPass, newPass, user, output, role;

	/* Get parameters */
	oldPass = request.getParameter("old_password");
	newPass = request.getParameter("new_password");
	user = request.getRemoteUser();
	
	/* Find userrole */
	if(request.isUserInRole("admin")) {
		role = "admin";
	}
	else if(request.isUserInRole("user")) {
		role = "user";
	}
	else {
		role = null;
	}
	
	/* If all parameters are given, attempt to connect to database and update user */
	if(oldPass != null && !"".equals(oldPass) && newPass != null && !"".equals(newPass) && user != null && role != null) {
		try {
			mysql.connect();
			
			/* Check if old password is correct */
			if(DAO.loginValid(user, oldPass)) {
				
				DAO.editUser(user, newPass, role);
				output = "Your password was changed successfully.";
			}
			else {
				output = "The specified OLD password is wrong!";
			}
		}
		catch (Exception e) {
			output = "Database error. Your password could not be changed.<br />Error: " + e;
		}
		finally {
			mysql.closeConnection();
		}
	}
	else {
		output = "Missing requered parameters!";
	}
	
	session.setAttribute("changeMessage", output);
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Network Monitoring System - Saving data...</title>
        <meta http-equiv="refresh" content="0; url=./change_password.jsp">
    </head>
    
    <body>
		<a href="./change_password.jsp">Continue...</a>
    </body>
</html>