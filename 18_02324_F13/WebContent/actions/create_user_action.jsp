<jsp:useBean id="DAO" class="server.data.mySqlDataAccessObjects.DataPackageDAO" />

<%
	String username, password, fullname, mail, message;

	password = request.getParameter("password");
	mail = request.getParameter("mail");
	
	if(mail != null && password != null) {
		try {
			DAO.openConnection();
			
			/* Check if user exists */
			if(!DAO.userExists(mail)) {
				DAO.addUser(mail, password, "user");
				message = "User created succesfully.";	
			}
			else {
				message = "User already exists!";
			}
			
		}
		catch (Exception e) {
			message = "System error. User not created!<br />Error: " + e;
		}
		finally {
			DAO.closeConnection();
		}
	}
	else {
		message = "Missing requred parameters!";
	}
	
	session.setAttribute("createMessage", message);
%>


<!DOCTYPE html>
<html>
    <head>
        <title>Network Monitoring System - Saving data...</title>
        <meta http-equiv="refresh" content="0; url=../create_user.jsp">
    </head>
    
    <body>
		<% out.println(message); %>
		<a href="../create_user.jsp">Continue...</a>
    </body>
</html>