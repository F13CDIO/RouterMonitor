<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
	<h4>Velkommen</h4>
	<fieldset>
		<ul>
			<li>
				<p>
					<%=session.getAttribute("username") %>
				</p>
				<form action="index.jsp" method="POST">
					<p>
						<button type="submit" value="logout">Logout</button>
					</p>
				</form>
			</li>
		</ul>
	</fieldset>
</body>
</html>