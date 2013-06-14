<%@include file="./includes/top.jsp" %>

<jsp:useBean id="piControl" class="server.boundary.TCPclientCommandBean" />

<%
    String[] piList;
    String[] piStatus;
    Object pi_response;
    int i;
    
    /* Get list of Pi's */
    try {
        piList = piControl.getClients();
    }
    catch (Exception e) {
        out.print("Sorry, the Pi-server is currently unavailible, or no Pi's are connected...");
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
        
        pi_response = session.getAttribute("pi_response");
        if(pi_response != null) {
            out.println("<p id=\"pi_response\">" + pi_response + "</p>");
            request.getSession().removeAttribute("pi_response");
        }
%>
        
        <br />
        <span id="pi_title">Availible Pi's:</span>
        Change channel:
        <form action="./pi_action.jsp" method="post">
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
            out.print("<input name=\"selectedPi\" value=\"" + piList[i] + "\" type=\"radio\"");
            if(i==0) {
                out.print(" checked");
            }
            out.println(" />");
            out.println("</label>");
        }
        
%>
        
        </div>
        
        <div id="pi_channel_box">
            To change channel for the selected Pi, you need to select the channel from the list below
            <br /><br />
            <select name="channel">
                <option value="1">Channel 1</option>
                <option value="2">Channel 2</option>
                <option value="3">Channel 3</option>
                <option value="4">Channel 4</option>
                <option value="5">Channel 5</option>
                <option value="6">Channel 6</option>
                <option value="7">Channel 7</option>
                <option value="8">Channel 8</option>
                <option value="9">Channel 9</option>
                <option value="10">Channel 10</option>
                <option value="11">Channel 11</option>
                <option value="12">Channel 12</option>
                <option value="13">Channel 13</option>
                <option value="36">Channel 36</option>
                <option value="40">Channel 40</option>
                <option value="44">Channel 44</option>
                <option value="48">Channel 48</option>
            </select>
        </div>

        <div class="clear"></div>

        <input class="pi_control_submit" type="submit" name="action" value="Start" />
        <input class="pi_control_submit" type="submit" name="action" value="Stop" />
        <input class="pi_control_submit" type="submit" name="action" value="Get WiFi status" />
        <input class="pi_control_submit" type="submit" name="action" value="Scan networks" />
        <input class="pi_control_submit pi_submit_last" type="submit" name="action" value="Set channel" />

        </form>
        
<%   
    }
%>

<%@include file="./includes/bottom.jsp" %>