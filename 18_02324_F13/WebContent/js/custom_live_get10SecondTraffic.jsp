<%@page import="java.util.Calendar, server.data.mySqlConnect"%><%

    /* IMPORTANT! Loads the driver for mySQL interface. Not needed in Java, but IS needed in JSP! */
    Class.forName("com.mysql.jdbc.Driver");

    /* A Java Calender, so the starttime can be set and sent as "Date"-type */
    Calendar cal = Calendar.getInstance();

    /* Get date from the GET-parameter, "date", converts to Long, and sets the Calendar date from this */
    try {
        Long input = new Long(request.getParameter("date"));
        cal.setTimeInMillis(input);
    } catch(Exception e) {
        return;
    }

    /* Connects to mySQL database, to be able to get data */
    mySqlConnect mySql = new mySqlConnect();
    mySql.connect();

    /* Gets data and prints it directly in the document, without any formating */
    out.print(mySql.get10SecondTraffic(cal.getTime(), ""));

    /* Close mySQL database connection */
    mySql.closeConnection();
%>