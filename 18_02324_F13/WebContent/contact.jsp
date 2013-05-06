
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
				<li><a href="home.jsp">Home</a></li>
				<li><a href="examples.jsp">Examples</a></li>
				<li class="selected"><a href="contact.jsp">Contact</a></li>
			</ul>
		</div>
		<div id="body">
			<div id="content">
				<h1>Page content</h1>
				<div class="box">
					<h2>Introduction</h2>
					<h3>How are we</h3>
					<p>Vi er gruppe 18 fra Diplom IT blah blah blah</p>

					<p>We are:</p>


					<ul class="styledlist">

						<li>Mig</li>
						<li>Dig</li>
						<li>Os</li>
						<li>Vi</li>
					</ul>

					<h3>What are we doing</h3>



					<p>BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH
						BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH
						BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH BLAH</p>

					<h3>Ohh snap</h3>

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
							<li><a href="examples.jsp">Examples</a></li>
							<li><a href="contact.jsp">Contact</a></li>
						</ul>
					</li>
					<li><jsp:include page="welcomeForm.jsp"></jsp:include></li>
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