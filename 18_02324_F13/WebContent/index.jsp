
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%session.removeAttribute("username"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Jeg er en Boss!</title>
<link rel="stylesheet" href="styles.css" type="text/css" />
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>
				<a href="index.jsp">Team_18</a>
			</h1>
			<h2>We rock!</h2>
			<div class="clear"></div>
		</div>
		<div id="nav">
			<ul>
				<li class="selected"><a href="index.jsp">Home</a></li>
				<li><a href="contactpre.jsp">Contact</a></li>
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

					<p>Dette kan køre på det meste</p>


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
				<jsp:include page="loginForm.jsp"></jsp:include>
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