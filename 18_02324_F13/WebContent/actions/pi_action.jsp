<jsp:useBean id="piControl" class="server.boundary.TCPclientCommandBean" />

<%  
    String[] availibleActions = {"Start", "Stop", "Get WiFi status", "Scan networks", "Set channel"};
    
    String selectedPi, action, output, selectedChannel;
    
    /* Get post values */
    selectedPi = request.getParameter("selectedPi");
    action = request.getParameter("action");
    selectedChannel = request.getParameter("channel");
    
    
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
                output = output + piControl.getWifiStatus(selectedPi);
            }
            else if (availibleActions[3].equals(action)) {
                output = output + piControl.scanNetworks(selectedPi);
            }
            else if (availibleActions[4].equals(action)) {
                piControl.setChannel(selectedPi, Integer.parseInt(selectedChannel));
                output = output + "OK";
            }
        }
        catch (Exception e) {
            output = output + "Error.";
        }
    }
    
    session.setAttribute("pi_response", output);
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Network Monitoring System - Saving data...</title>
        <meta http-equiv="refresh" content="0; url=../pi_control.jsp">
    </head>
    
    <body>
        <% out.println(output); %>
        See the result <a href="./pi_control.jsp">here.</a>
    </body>
</html>