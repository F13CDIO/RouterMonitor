<%@include file="./includes/top.jsp" %>

<br />
<a class="control_panel_link password" href="./change_password.jsp">Change password</a>
<a class="control_panel_link chart" href="./customize_selection.jsp">Customize statistics</a>
<%
/* Dynamic menu. Only show pi-control etc. if user-role is Admin */
if(request.isUserInRole("admin")) { %>
	<a class="control_panel_link pi" href="./pi_control.jsp">Pi Control</a>
	<a class="control_panel_link user" href="./user_management.jsp">User management</a>
<% }
%>

<%@include file="./includes/bottom.jsp" %>