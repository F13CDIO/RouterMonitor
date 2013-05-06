<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


	<jsp:setProperty property="*" name="login"></jsp:setProperty>
	<jsp:useBean id="login" class="logbean.LoginBean" scope="session"></jsp:useBean>
	<jsp:useBean id="sql" class="logbean.sqlConn" scope="session" ></jsp:useBean>

</head>
<body>

	<% 
		/*
		String un = request.getParameter("username");
		String pw = request.getParameter("password");

		if (un == null)
		{ 
			response.sendRedirect("contact.jsp");
		}
		if (pw == null)
		{
			response.sendRedirect("contactpre.jsp");
		}
		*/
			
		System.out.println("login username:"+login.getUsername());		
		System.out.println("login password:"+login.getPassword());
		
		if(sql.valid())
		{
			System.out.println("valid/ name er sat");
			response.sendRedirect("home.jsp");
		}
		else
		{
			System.out.println("ikke valid/ name er NULL");
			out.println(sql.req());
			//response.sendRedirect("index.jsp");
		}
		
	%>

</body>
</html>