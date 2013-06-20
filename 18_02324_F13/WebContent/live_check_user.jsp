<jsp:useBean id="DAO" class="server.data.mySqlDataAccessObjects.DataPackageDAO" /><%    
    String userName = request.getParameter("username");

	if(userName != null) {
		try {
			DAO.openConnection();
	    	out.print(DAO.userExists(userName));
		}
		catch (Exception e) {
			out.print("error");
		}
		finally {
			DAO.closeConnection();
		}
	}
	else {
		out.print("empty");
	}
%>