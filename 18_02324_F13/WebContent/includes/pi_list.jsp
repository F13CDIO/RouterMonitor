	<jsp:useBean id="piControl" class="server.boundary.TCPclientCommandBean" />
	
		<div id="pi_list_title">
			<span class="pi_num">#</span>
			<span class="pi_info">MAC Address</span>
			<span class="pi_info">Status</span>
			<span class="pi_sel">Selected</span>
		</div>

<% 
	String[] piList;
	String[] piStatus;
	int i;
	
	/* Get list of Pi's */
	try {
		piList = piControl.getClients();
	}
	catch (Exception e) {
	    out.print("<p id=\"pi_empty\">Sorry, the Pi-server is currently unavailible, or no Pi's are connected...</p>");
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
	                piStatus[i] = "Stopped";
	            }
	        }
	        catch (Exception e) {
	            piStatus[i] = "Unknown";
	        }
	    }

        /* Print list of Pi's */
        for(i=0; i<piList.length; i++) {
            out.println("<label class=\"pi_line\">");
            out.println("<span class=\"pi_num\">" + (i+1) + "</span>");
            out.println("<span class=\"pi_info\">" + piList[i] + "</span>");
            out.println("<span class=\"pi_info\">" + piStatus[i] + "</span>");
            out.print("<input name=\"selectedPi\" value=\"" + piList[i] + "\" type=\"radio\"");
            if(i==0) {
                out.print(" checked");
            }
            out.println(" />");
            out.println("</label>");
        }
	}
        
%>