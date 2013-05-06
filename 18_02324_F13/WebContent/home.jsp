<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Jeg er en Boss!</title>
<link rel="stylesheet" href="styles.css" type="text/css" />
</head>
<body>
<jsp:useBean id="sql" class="logbean.sqlConn" scope="session"/>
<jsp:useBean id="test" class="logbean.LoginBean" scope="session"/>
	<div id="container">
		<div id="header">
			<h1>
				<a href="home.jsp">Team_18</a>
			</h1>
			<h2>We rock!</h2>
			<div class="clear"></div>
		</div>
		<div id="nav">
			<ul>
				<li class="selected"><a href="home.jsp">Home</a></li>
				<li><a href="chart.jsp">Chart</a></li>
				<li><a href="examples.jsp">Examples</a></li>
				<li><a href="contact.jsp">Contact</a></li>
			</ul>
		</div>
		<div id="body">
			<div id="content">
				<h1>Page content</h1>
				<div class="box">
					<h2>Introduction</h2>
					<p>BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH
						BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH
						BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH
						BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH</p>

					<p>Dette kan køre på det meste
						og TEST = <%=test.getTest() %>
						Og BOM = <%=sql.requset() %>
						
						
						her: <% test.setUserName(request.getParameter("username")); %>
						<%=test.getUserName()%>
					</p>


					<ul class="styledlist">

						<li><jsp:include page="classTest.jsp"></jsp:include></li>
						<li>Opera</li>
						<li>IE 6, 7, 8 and 9</li>
						<li>Chrome</li>
					</ul>

					<h3>Buy unbranded</h3>



					<p>BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH
						BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH
						BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH</p>

					<h3>Lorem lipsum</h3>

					<p>BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH
						BLAH BLAH BLAH BLAH</p>

				</div>
			</div>

			<div class="sidebar">
				<ul>
					<li>
						<h4>
							<span>Navigate</span>
						</h4>
						<ul class="blocklist">
							<li><a href="home.jsp">Home</a></li>
							<li><a href="chart.jsp">Chart</a></li>
							<li><a href="examples.jsp">Examples</a></li>
							<li><a href="contact.jsp">Contact</a></li>
						</ul>
					</li>
					<li>
						<h4>Velkommen</h4>
						<fieldset>
							<ul>
								<li>
									<p>
									<%
									/*String un = request.getParameter("username");
									String pw = request.getParameter("password");
									if(session.getAttribute("username") == null)
									{
										
										session.setAttribute("username", un);
										out.println(un);
									}
									else
									{*/
										out.println(session.getAttribute("username"));
									//}
									
									%>
									</p>
									<form action="index.jsp" method="POST">
										<p>
											<button type="submit" value="logout">Logout</button>
										</p>
									</form>
								</li>
							</ul>
						</fieldset>
					</li>
					<li>
						<h4>About</h4>
						<ul>
							<li>
								<p style="margin: 0;">Skriv hvem vi er her ;).</p>
							</li>
						</ul>
					</li>
				</ul>
			</div>
			<div class="clear"></div>
		</div>
		<div id="footer">
			<div class="footer-bottom">
				<p>&copy; Nu er det mit!</p>
			</div>
		</div>
	</div>
</body>
</html>