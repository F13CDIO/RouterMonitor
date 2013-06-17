<%@include file="./includes/top.jsp" %>

<%	
	Object createMessage;

	createMessage = session.getAttribute("createMessage");
	if(createMessage != null) {
	    out.println("<p id=\"message\">" + createMessage + "</p>");
	    request.getSession().removeAttribute("createMessage");
	}
%>

<br />

<form action="./create_user_action.jsp" method="post">
    <div class="form_description">Email:</div>
    <div class="form_input"><input size="70" name="mail" type="email" /></div>
    <div class="form_description">Password:</div>
    <div class="form_input"><input size="70" name="password" type="password" /></div>  
    
    <input class="form_submit" type="submit" value="Create user" />
</form>

<%@include file="./includes/bottom.jsp" %>