<jsp:useBean id="DAO" class="server.data.mySqlDataAccessObjects.DataPackageDAO" />

<%
	/* Get parameters */
	String selectedUser, action, output;

	selectedUser = request.getParameter("selectedUser");
	action = request.getParameter("action");

	
	if("Delete selected user".equals(action)) { 
		try {
			DAO.openConnection();
			
			DAO.deleteUser(selectedUser);
			
			if(DAO.userExists(selectedUser)) {
				output = "Error. Could not delete user!";
			}
			else {
				output = "User '" + selectedUser + "' was deleted successfully.";
			}
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
<% 
	}
	else if("Edit selected user".equals(action)) {
		
%>

<%@include file="./includes/top.jsp" %>

<br />
<form action="./user_edit_action.jsp" method="post">
    <div class="form_description">Username:</div>
    <div class="form_input"><input size="50" name="username" type="text" value="<% out.print(selectedUser); %>" readonly /></div>
    <div class="form_description">Role:</div>
    <div class="form_input">
	<select name="role">
		<option value="admin">admin</option>
		<option value="user">user</option>		
	</select>
	</div>
	<div class="clear"></div>
	<br />
	<input type="submit" value="Save" class="form_submit" />
</form>


<%@include file="./includes/bottom.jsp" %>

<%
	}
%>