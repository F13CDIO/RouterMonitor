<jsp:useBean id="piControl" class="server.boundary.TCPclientCommandBean" />

<%  
    String[] availibleActions = {"Start", "Stop", "Get WiFi status", "Scan networks", "Set channel"};
    
    String selectedPi, action;
    
    /* Get post values */
    selectedPi = request.getParameter("selectedPi");
    action = request.getParameter("action");
    
    /* Print what we are trying to do */
    out.println("Performing '" + action + "' to Pi with MAC-address: " + selectedPi);
    
    /* Select the correct action to perform */
    if(availibleActions[0].equals(action)) {
        piControl.startUDPSocket(selectedPi);
    }
    else if (availibleActions[1].equals(action)) {
        piControl.stopUDPSocket(selectedPi);
    }
    else if (availibleActions[2].equals(action)) {
        piControl.getWifiStatus(selectedPi);
    }
    else if (availibleActions[3].equals(action)) {
        piControl.scanNetworks(selectedPi);
    }
    else if (availibleActions[4].equals(action)) {
        piControl.setChannel(selectedPi, 1);
    } 
%>