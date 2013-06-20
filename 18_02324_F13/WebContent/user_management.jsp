<%@include file="./includes/top.jsp" %>

<%@page import="org.json.simple.*"%>

<jsp:useBean id="DAO" class="server.data.mySqlDataAccessObjects.DataPackageDAO" />

<%
	int i;
	Object message; 
	
	/* Connects to mySQL database, to be able to get data */
	DAO.openConnection();
	
	/* Get data */
	JSONArray data = DAO.getAllUsers();
	JSONObject temp;
	
	/* Close mySQL database connection */
	DAO.closeConnection();
	
	/* Creates array with size according to actual number of pages in top10 */
	String[] userNames = new String[data.size()];
	String[] userRoles = new String[data.size()];
	
	for (i = 0; i < data.size(); i++) {
	    temp = (JSONObject) data.get(i);
	
	    userNames[i] = temp.get("userName").toString();
	    userRoles[i] = temp.get("userRole").toString();
	}
	
	message = session.getAttribute("message");
	if(message != null) {
	    out.println("<p id=\"message\">" + message + "</p>");
	    request.getSession().removeAttribute("message");
	}
%>		
		<br />
		Registered users:
		<form action="./user_edit.jsp" method="post">
        <div id="pi_list_wide">
        <div id="pi_list_title_wide">
        <span class="pi_num">#</span>
        <span class="pi_info_wide">Username</span>
        <span class="pi_info">Role</span>
        <span class="pi_sel">Selected</span>
        </div>
        
<% 
        /* Print list of Pi's */
        for(i=0; i<userNames.length; i++) {
            out.println("<label class=\"pi_line_wide\">");
            out.println("<span class=\"pi_num\">" + (i+1) + "</span>");
            out.println("<span class=\"pi_info_wide\">" + userNames[i] + "</span>");
            out.println("<span class=\"pi_info\">" + userRoles[i] + "</span>");
            out.print("<input name=\"selectedUser\" value=\"" + userNames[i] + "\" type=\"radio\"");
            if(i==0) {
                out.print(" checked");
            }
            out.println(" />");
            out.println("<input name=\"" + userNames[i] + "_role\" value=\"" + userRoles[i] + "\" type=\"hidden\" />");
            out.println("</label>");
        }
        
%>
        </div>
        
        <div class="clear"></div>
		
		<input class="pi_control_submit_wide" type="submit" name="action" value="Edit selected user" />
        <input class="pi_control_submit_wide" type="submit" name="action" value="Delete selected user" />
        
        </form>

<%@include file="./includes/bottom.jsp" %>