<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>

	<ul>
		<li>
			<h4>Log in</h4>
			<fieldset>
				<ul>
					<li>
						<form action="connect" method=post id="login" >
							<p>
								<label>User:</label> <input name="username" type="text"
									placeholder="Type username"  />
									
							</p>
							<p>
								<label>Password:</label> <input name="password" type="password"
									placeholder="Type password" />

							</p>
							<p>
						
								<input type="submit" value="login" >
						
								</p>
						</form>
						
					</li>
				</ul>
			</fieldset>
		</li>
	</ul>
</body>
</html>