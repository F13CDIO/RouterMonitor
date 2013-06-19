<jsp:useBean id="piControl" class="server.boundary.TCPclientCommandBean" />

<%  
    String[] availibleActions = {"Start", "Stop", "Iterate channels", "Scan networks", "Set channel"};
    
	int i;

	String[][] wifiInfo;
	String[] splitTemp;
    String selectedPi, action, output, selectedChannel, feedback, wifiNetworks = null;
    
    /* Get post values */
    selectedPi = request.getParameter("selectedPi");
    action = request.getParameter("action");
    selectedChannel = request.getParameter("channel");
    
    /* The feedback for most methods will be html that redirects to the pi-control page, so this i default value */
    feedback = "redirect";
    
    /* Only execute if all parameters are given correctly */
    if(selectedPi == null || action == null) {
        output = "Error - requered parameters missing!";
    }
    else if (availibleActions[4].equals(action) && selectedChannel == null) {
        output = "Error - you have to select a channel to use with the 'Set channel'-command!";
    }
    else {
        /* Insert requested action in output string */
        output = "Action, '" + action + "' on Pi '" + selectedPi + "' returned: ";

        /* Select the correct action to perform */
        try {
            if(availibleActions[0].equals(action)) {
                piControl.startUDPSocket(selectedPi);
                output = output + "OK";
            }
            else if (availibleActions[1].equals(action)) {
                piControl.stopUDPSocket(selectedPi);
                output = output + "OK";
            }
            else if (availibleActions[2].equals(action)) {
            	piControl.iterateOverChannels(selectedPi);
            	output = output + "OK";
            }
            else if (availibleActions[3].equals(action)) {
            	wifiNetworks = piControl.scanNetworks(selectedPi);
            	output = output + "OK";
            	feedback = "show_wifi";
            }
            else if (availibleActions[4].equals(action)) {
                piControl.setChannel(selectedPi, Integer.parseInt(selectedChannel));
                output = output + "OK";
            }
        }
        catch (Exception e) {
            output = output + "Error.";
            feedback = "redirect";
        }
    }
    
    if("redirect".equals(feedback)) {
    	session.setAttribute("pi_response", output);
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Network Monitoring System - Saving data...</title>
        <meta http-equiv="refresh" content="0; url=./pi_control.jsp">
    </head>
    
    <body>
        <% out.println(output); %>
        See the result <a href="./pi_control.jsp">here.</a>
    </body>
</html>

<%
    }
	else if ("show_wifi".equals(feedback)) {
		splitTemp = wifiNetworks.split("\\r?\\n");
		
		wifiInfo = new String[splitTemp.length][8];
		
		for(i=0; i<splitTemp.length; i++) {
			wifiInfo[i] = splitTemp[i].split("\\|");
			
			//out.println("Navn: " + wifiInfo[i][1] + " Mac: " + wifiInfo[i][2] + "<br />");
		}
%>
<%@include file="./includes/top.jsp" %>
<br />
Network overview for pi "<% out.print(selectedPi); %>":
<div id="wifi_list">
	<div class="wifi_line">
		<div class="wifi_item0">MAC Address</div>
		<div class="wifi_item1">SSID</div>
		<div class="wifi_item2">RSSI</div>
		<div class="wifi_item3">Channel</div>
		<div class="wifi_item4">Security (auth/unicast/group)</div>
	</div>
	
<%
	for(i=1; i<splitTemp.length; i++) {
%>
	<div class="wifi_line">
		<div class="wifi_item0"><% out.print(wifiInfo[i][1]); %></div>
		<div class="wifi_item1"><% out.print(wifiInfo[i][0]); %></div>
		<div class="wifi_item2"><% out.print(wifiInfo[i][2]); %></div>
		<div class="wifi_item3"><% out.print(wifiInfo[i][3]); %></div>
		<div class="wifi_item4"><% out.print(wifiInfo[i][6]); %></div>
	</div>
<%
}
%>

</div>
<div class="clear"></div>

<%@include file="./includes/bottom.jsp" %>
<%
	}
%>