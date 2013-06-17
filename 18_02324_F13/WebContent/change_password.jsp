<%@include file="./includes/top.jsp" %>
<%
	Object changeMessage;
	
	changeMessage = session.getAttribute("changeMessage");
	if(changeMessage != null) {
	    out.println("<p id=\"message\">" + changeMessage + "</p>");
	    request.getSession().removeAttribute("changeMessage");
	}
%>
<br />

<form action="./change_password_action.jsp" method="post">
    <div class="form_description_long">Old password:</div>
    <div class="form_input"><input size="70" name="old_password" type="password" /></div>
    <div class="form_description_long">New password:</div>
    <div class="form_input"><input size="70" name="new_password" type="password" /></div>  
    
    <input class="form_submit" type="submit" value="Save" />
</form>

<%@include file="./includes/bottom.jsp" %>