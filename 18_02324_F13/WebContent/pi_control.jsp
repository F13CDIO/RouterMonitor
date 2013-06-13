<%@include file="./includes/top.jsp" %>

<jsp:useBean id="piControl" class="server.boundary.TCPclientCommandBean" />

<%
    String[] piList;
    String[] piStatus;
    int i;
    
    /* Get list of Pi's */
    try {
        piList = piControl.getClients();
    }
    catch (Exception e) {
        out.print("Sorry, the Pi-server is currently unavailible");
        piList = null;
    }
    
    /* Everything only runs if the Pi-server is availible */
    if(piList != null) {
        /* Pi status array is created with the same length as piList */
        piStatus = new String[piList.length];
        
        /* Get status of all the Pi's */
        for(i=0; i<piList.length; i++) {
            try {
                if(piControl.isUDPactive(piList[i])) {
                    piStatus[i] = "Running";
                }
                else {
                    piStatus[i] = "Stoped";
                }
            }
            catch (Exception e) {
                piStatus[i] = "Unknown";
            }
        } 
%>
        
        <br />
        Availible Pi's:
        <form action="./actions/pi_action.jsp" method="post">
        <div id="pi_list">
        <div id="pi_list_title">
        <span class="pi_num">#</span>
        <span class="pi_info">MAC Address</span>
        <span class="pi_info">Status</span>
        <span class="pi_sel">Selected</span>
        </div>
        
<% 
        /* Print list of Pi's */
        for(i=0; i<piList.length; i++) {
            out.println("<label class=\"pi_line\">");
            out.println("<span class=\"pi_num\">" + i + "</span>");
            out.println("<span class=\"pi_info\">" + piList[i] + "</span>");
            out.println("<span class=\"pi_info\">" + piStatus[i] + "</span>");
            out.println("<input name=\"selectedPi\" value=\"" + piList[i] + "\" type=\"radio\" />");
            out.println("</label>");
        }
        
%>
        
        </div>

        <input class="pi_control_submit" type="submit" name="action" value="Start" />
        <input class="pi_control_submit" type="submit" name="action" value="Stop" />
        <input class="pi_control_submit" type="submit" name="action" value="Get WiFi status" />
        <input class="pi_control_submit" type="submit" name="action" value="Scan networks" />
        <input class="pi_control_submit" type="submit" name="action" value="Set channel" />

        </form>
        
<%   
    }
%>

<%@include file="./includes/bottom.jsp" %>