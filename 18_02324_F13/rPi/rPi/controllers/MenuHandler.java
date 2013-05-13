package rPi.controllers;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import rPi.connectors.Connector;


/*
 *  This class has the main menu plus some OS differentiation as this is the only class that invokes external programs
 *  Niclas 2013
 */

public class MenuHandler {
	
	private final supportedOS currentOS;
	
	TerminalExecutor tc = new TerminalExecutor();
	public BufferedReader br = null;
	BufferedReader inputFromServer = null;
	ConnectionController cc;
	
	public MenuHandler() throws Exception {
		this.currentOS = checkOS();
		System.out.println("");
		cc = new ConnectionController();		
		inputFromServer = cc.connectToServer();
	}
	
	// Supported OS'es. Type-safety wins
	private enum supportedOS {
		Windows, Mac, Linux
	}
	
	// We need to invoke different terminal programs for different OS'es
	private supportedOS checkOS(){
		supportedOS OS = null;
		final String OSstring = System.getProperty("os.name").toLowerCase(); // Name of OS
		if (OSstring.indexOf("win") >= 0){
			OS = supportedOS.Windows;
		} else if (OSstring.indexOf("mac") >= 0){
			OS = supportedOS.Mac;
		} else if (OSstring.indexOf("nix") >= 0){
			OS = supportedOS.Linux;
		}
		return OS;
	}
	
	// Get the command from 2C server
	public void handleCommand() throws Exception{
		try {
			boolean validCommand = false;
			
			
			String command = inputFromServer.readLine();
			System.out.println("command :" + command);
			
			// check if command is valid
			for (Command c : Command.values()){
				if (c.name().equals(command)){
					validCommand = true;
				}
			}
			// execute command
			if (validCommand)
			{
				Command cmd = Command.valueOf(command); // enum to avoid mistakes, see below
				System.out.println("command recieved : " + cmd);
				switchMenu(cmd);
			}
		} catch (IOException e) {
			System.out.println("Could not read command from server");
			e.printStackTrace();
		}	
	}
	
	// Supported commands
	private enum Command {
		start, stop, scanNetworks, setChannel, getWifiStatus, getMacAddress;
	}
	// The obvious switch case
	private void switchMenu(Command cmd) throws Exception{
		switch (cmd) {
		case start: // start sniffing
			int portnr = extractNumber();
			System.out.println("extracted port nr : " + portnr);
			startSniffing(portnr);
			System.out.println("started sniffing");
			break;
		case stop:
			System.out.println("stopping sniffer");
			stopSniffing();
			break;
		case scanNetworks:
			System.out.println("scanning networks");
			cc.sendArrayTCP(scanNetworks());
			break;
		case setChannel:
			System.out.println("setting channel");
			int chan = extractNumber();
			setChannel(chan);
			break;
		case getWifiStatus:
			System.out.println("");
			cc.sendArrayTCP(getWifiStatus());
			break;
		case getMacAddress:
			cc.sendStringTCP( getMacAddress() );
			break;
		}
		
	}
	
	// the following command requires tshark opened in sudoers file or the java program executed with su rights
	// the script saves the tshark process id in process1.pid for later implementation of multiple tshark processes
	//the command executed in startScript is "/usr/local/bin/tshark -T fields -e ip.src -e ip.dst -e http.host -e http.user_agent -i en0 -I -R http.request tcp port 80 and ip &";
	public void startSniffing(int portToSendTo){
		String startScript = "bash /usr/local/bin/tshark -T fields -e ip.src -e ip.dst -e http.host -e http.user_agent -i en0 -I -l -R http.request tcp port 80 and ip";
		//String startScript = "bash startScript.sh"; // the startscript automatically appends the right path to tshark
		br = tc.exec(startScript);
		System.out.println("startscript exec'd");
		cc.initAndSendUDP(br, portToSendTo);
	}
	
	// This method requires the start script is run, and not just tshark invoked since pid needs to be saved
	public void stopSniffing(){
		cc.stopUDP();
		// stopping the process is easy to do in one line, so here it goes with some bash-fu (linux/osx compatible)
		if (this.currentOS == supportedOS.Mac || this.currentOS == supportedOS.Linux){
			tc.exec("kill $(cat process1.pid)");	
		} else if (this.currentOS == supportedOS.Windows){
			
		}
	}
	
	private ArrayList<String[]> scanNetworks() throws Exception{
		assert(this.currentOS != null);
		
		// Execute network scanning according to user operating system
		String scanCommand = "";
		if (this.currentOS == supportedOS.Windows){
			scanCommand = "";
		} else if (this.currentOS == supportedOS.Mac){
			scanCommand = "/System/Library/PrivateFrameworks/Apple80211.framework/Versions/Current/Resources/airport -s";
		} else if (this.currentOS == supportedOS.Linux){
			scanCommand = "iw wlan0 scan";
		}
		br = tc.exec(scanCommand); 
	    
		// This parsing only works for mac
		ArrayList<String[]> networks = new ArrayList<String[]>();
	    String[] aNetwork = new String[7]; // SSID | BSSID | RSSI | CHANNEL | HT | CC | SECURITY
	    for (int i = 0; i < 20; i++){
	    	String line = br.readLine();
	    	if (line.length() > 0){
	    		aNetwork = line.split("^S"); // Split on first non whitespace character
	    		networks.add(aNetwork);
	    	}
	    }
	    return networks;
	}
	
	private void setChannel(int chan){
		assert(this.currentOS != null);
		
		if (this.currentOS == supportedOS.Mac){
			// Dissociate from current network
			tc.exec("/System/Library/PrivateFrameworks/Apple80211.framework/Versions/Current/Resources/airport -z");
			System.out.println("Dissociated from access point");
			// set channel
			String chanCmd = "/System/Library/PrivateFrameworks/Apple80211.framework/Versions/Current/Resources/airport --channel=";
			System.out.println("setting channel to " +chanCmd + chan);
			BufferedReader response = tc.exec(chanCmd + chan);
			try {
				String rootRequired = response.readLine();
				System.out.println(rootRequired);
				if (rootRequired.length() > 0){
					cc.sendStringTCP(rootRequired);
				} else {
					cc.sendStringTCP("\0");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (this.currentOS == supportedOS.Windows){
			
		}
	}
	
	private ArrayList<String[]> getWifiStatus(){
		assert(this.currentOS != null);
		
		if (this.currentOS == supportedOS.Mac){
			br = tc.exec("/System/Library/PrivateFrameworks/Apple80211.framework/Versions/Current/Resources/airport --getinfo");
		} else if (this.currentOS == supportedOS.Windows){
			// TODO
		} else if (this.currentOS == supportedOS.Linux){
			// TODO
		}
		ArrayList<String[]> statusList = new ArrayList<String[]>();
		String[] aLine = new String[2];
		String line;
		try {
			while( (line = br.readLine()) != null){
				aLine = line.split("^S"); // split on whitespaces
				statusList.add(aLine);
			}
		} catch (IOException e) {
			System.out.println("Problem with input when trying to get wifi status on os: " + this.currentOS);
			e.printStackTrace();
		}
		return statusList;
	}
	
	private String getMacAddress() throws Exception{
		assert(this.currentOS != null);
		
		String addr = ""; // the MAC address we wanna return
		
		// MAC
		if (this.currentOS == supportedOS.Mac){
			br = tc.exec("ifconfig");
			String line;
		    while ( (line = br.readLine()) != null){
		    	System.out.println(line);
		    	if (line.indexOf("ether") >= 0){
		    		addr = line.substring(line.indexOf(" ")+1 );
			   	}
		    }	
		// WIN
		} else if (this.currentOS == supportedOS.Windows){
			br = tc.exec("ipconfig /all");
			String line;
		    while ( (line = br.readLine()) != null){
		    	System.out.println(line);
		    	if (line.indexOf("Psysical Address") >= 0){
		    		addr = line.substring(line.indexOf(" ")+1 );
			   	}
		    }	
		// NIX
		} else if (this.currentOS == supportedOS.Linux){
			// TODO
		}
		
		return addr;
	}
	
	// This method extracts the UDP port the  server listens on
	private int extractNumber() throws IOException{
		String command = "";
		// making it more robust because server maight not respect protocol and send several newlines
		while (command.length() < 1){
			command = inputFromServer.readLine();
			System.out.println("Reading number");
		}
		
		System.out.println("port number : " + command);
		int port = 0;
		port = Integer.parseInt(command);
		return port;
	}	
}
