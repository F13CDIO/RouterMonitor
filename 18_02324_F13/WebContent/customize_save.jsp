<jsp:useBean id="DAO" class="server.data.mySqlDataAccessObjects.DataPackageDAO" />

<%
    int i;
    String userName, combinedData = "";
    String[] layout = new String[5];
    String[] selectedCharts = new String[10];
    
    /* Reads the selected layout into array */
    for (i = 0; i < 5; i++) {
        layout[i] = request.getParameter("row" + i);
    }
    
    /* Reads the selected charts into array */
    for (i = 0; i < 10; i++) {
        selectedCharts[i] = request.getParameter("chart" + i);
    }
    
    
    /* Combines all the data to one big string, so it is easy to save */
    for(i=0; i<5; i++) {
        combinedData = combinedData + layout[i] + ",";
    }
    
    combinedData = combinedData + ";";
    
    for(i=0; i<10; i++) {
        combinedData = combinedData + selectedCharts[i] + ",";
    }
    
    
    /* Saves data to database */
    userName = request.getRemoteUser();
    if(userName != null) {
    	DAO.openConnection();
    	
    	if(DAO.hasUserSettings(userName)) {
    		DAO.editUserSettings(userName, combinedData);
    	}
    	else {
    		DAO.addUserSettings(userName, combinedData);
    	}
    	
    	DAO.closeConnection();
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Network Monitoring System - Saving data...</title>
        <meta http-equiv="refresh" content="0; url=./statistics.jsp">
    </head>
    
    <body>
        Your data is saved. See the result <a href="./statistics.jsp">here.</a>
    </body>
</html>