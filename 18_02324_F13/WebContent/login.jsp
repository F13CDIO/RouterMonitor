<%@include file="./includes/top.jsp" %>

<%
	Object logout_message;

	logout_message = session.getAttribute("logout_message");

	if("error".equals(request.getParameter("message"))) {
	    out.println("<p id=\"message\">Unable to login.</p>");
	}
	else if(logout_message != null) {
		out.println("<p id=\"message\">" + logout_message + "</p>");
		request.getSession().removeAttribute("logout_message");
	}
%>

<br />
<form action="j_security_check" method="post">
    <div class="form_description">Username:</div>
    <div class="form_input"><input size="70" name="j_username" type="text" /></div>
    <div class="form_description">Password:</div>
    <div class="form_input"><input size="70" name="j_password" type="password" /></div>
    
    <input class="form_submit" type="submit" value="login" />
</form>

<div class="clear"></div>
<br /><br />

Herunder er angivet testbrugere med forskellige rettigheder, som kan bruges til test...<br />
<br />
Admin:<br />
Username = hans<br />
Password = 1212<br />
<br />
User:<br />
Username = flemming<br />
Password = 1313<br />

<%@include file="./includes/bottom.jsp" %>