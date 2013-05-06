<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% String un = request.getParameter("username");%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="styles.css" type="text/css" />
</head>
<body>
	<%
		String URL = "jdbc:mysql://bjqrn88.cloudns.org:3306/login";
		String Driver = "com.mysql.jdbc.Driver";
		String username = "grp18";
		String password = "123123";
		String status = "";
		Connection conn = null;

		Statement statement = null;
		ResultSet rs = null;

		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(URL, username, password);
			if (!conn.isClosed()) {

			}
		} catch (Exception e) {
			out.println(e.getMessage());
		}

		statement = conn.createStatement();
		rs = statement
				.executeQuery("SELECT password FROM login WHERE username ='"
						+ session.getAttribute("thename") + "'");
		while (rs.next()) {
			out.println(rs.getString("password"));
		}

		rs.close();
		conn.close();
	%>
</body>
</html>