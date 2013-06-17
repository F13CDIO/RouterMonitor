package rPi.controllers;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

import rPi.controllers.MainController.SupportedOS;
import rPi.controllers.MainController.SupportedCommands;


/**
 *  This class handles all the menu options, and is the only class that is OS-aware
 *  
 *  @author Niclas
 */

public class MenuHandler {
	
	private SupportedOS currentOS;
	private ConnectionController cc;
	
	/**
	 * The constructor needs to know host OS
	 * 
	 * @param currentOS
	 */
	public MenuHandler(SupportedOS currentOS, ConnectionController cc) {
		this.currentOS = currentOS;
		this.cc = cc;
	}
				
	TerminalExecutor tc = new TerminalExecutor();
	
	/**
	 *  The obvious switch case with possible commands from server. When a cmd is succesfully executed it returns "\0" over tcp which is our 'end of file' char
	 * @param cmd command from server as enum type
	 * @throws Exception
	 */
	public void switchMenu(SupportedCommands cmd) throws Exception{
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
		case iterate: 
			ChannelHopping hopper = new ChannelHopping("dtu", currentOS);
			(new Thread(hopper)).start();
				
		default: cc.sendStringTCP("ERROR"); // enum might get extended
		}
		
	}
	
	/**
	 *  The following command requires tshark opened in sudoers file or the java program executed with su rights.
	 *  The script saves the tshark process id in process1.pid for later implementation of multiple tshark processes.
	 *  The command executed in startScript is "/usr/local/bin/tshark -T fields -e ip.src -e ip.dst -e http.host -e http.user_agent -i en0 -I -R http.request tcp port 80 and ip &";
	 * 
	 * @param UDP port on server to send to as sniffing uses out-of-band communication
	 */
	public void startSniffing(int portToSendTo){
		// String startScript = "bash /usr/local/bin/tshark -T fields -e ip.src -e ip.dst -e http.host -e http.user_agent -i en0 -I -l -R http.request tcp port 80 and ip";
		String startScript = "bash startScript.sh"; // the startscript automatically appends the right path to tshark
		BufferedReader br = tc.exec(startScript);
		System.out.println("startscript exec'd");
		cc.initAndSendUDP(br, portToSendTo);
	}
	
	/**
	 *  This method requires the start script has been run, and not just tshark, invoked since pid needs to have been saved
	 */
	public void stopSniffing(){
		cc.stopUDP();
		// stopping the process is easy to do in one line, so here it goes with some bash-fu (linux/osx compatible)
		if (this.currentOS == SupportedOS.Mac || this.currentOS == SupportedOS.Linux){
			tc.exec("kill $(cat process1.pid)");	
		} else if (this.currentOS == SupportedOS.Windows){
			System.out.println("windows can't sniff");
		}
	}
	
	/**
	 * This method scans local networks depending on host OS.
	 * 
	 * @return ArrayList with strings from the scan of nearby networks
	 * @throws Exception
	 */
	private ArrayList<String[]> scanNetworks() throws Exception{
		assert(this.currentOS != null);
		
		ArrayList<String[]> networkOutput = null;
		
		// Execute network scanning according to user operating system
		String scanCommand = "";
		if (this.currentOS == SupportedOS.Windows){
			System.out.println("windows not supported");
			scanCommand = "";
		} else if (this.currentOS == SupportedOS.Mac){
			scanCommand = "/System/Library/PrivateFrameworks/Apple80211.framework/Versions/Current/Resources/airport -s";
		} else if (this.currentOS == SupportedOS.Linux){
			System.out.println("executing linux scan");
			scanCommand = "iwlist wlan0 scan";
		}
		BufferedReader br = tc.exec(scanCommand); 
		if (this.currentOS == SupportedOS.Mac){
			networkOutput = parseMacScanNetworks(br);
		} else if (this.currentOS == SupportedOS.Linux){
			networkOutput =  parseLinuxScanNetworks(br);
		} 
		if (networkOutput == null)
			System.out.println("ARRAY IS FUCKING null");
		return networkOutput;
	}
	
	private ArrayList<String[]> parseMacScanNetworks(BufferedReader br){
		ArrayList<String[]> networks = new ArrayList<String[]>();
	    String[] aNetwork; // SSID | BSSID | RSSI | CHANNEL | HT | CC | SECURITY
	    while (true){
	    	try {
	    		String line = br.readLine();
	    		System.out.println(line);
	    		if (line.length() > 0){
	    			aNetwork = line.split("\\s+"); // Split on first non whitespace character
	    			networks.add(aNetwork);
	    		}
	    	} catch (Exception e){
	    		break;
	    	}
	    } // end while
	    return networks;
	}

	// The point is to try and get the messy linux output formatted the same way as mac scan above ^^ SSID | BSSID | RSSI | CHANNEL | HT | CC | SECURITY
	// Country code (cc) and HT does not get outputted by iwlist in linux, so we set it to null
	private ArrayList<String[]> parseLinuxScanNetworks(BufferedReader br){
		ArrayList<String[]> networks = new ArrayList<String[]>();
		String[] aNetwork = new String[7];
		String line;
		
		while (true){
			try{
				line = br.readLine();
				String [] temp = line.split("\\s+");
				if (temp[0].equals("Cell")){
					networks.add(aNetwork); // save current network when 1. line is met
					aNetwork = new String[7];
					
					aNetwork[4] = null; // set HT to null
					aNetwork[5] = null; // set CC to null
					
					aNetwork[1] = temp[4]; // BSSID from temp to aNetwork
				} else if (temp[0].startsWith("Channel")){
					aNetwork[3] = temp[0].split(":")[1]; // CHANNEL from temp to aNetwork
				} else if (temp[0].startsWith("Quality")){
					aNetwork[2] = temp[2].split("=")[1];
				} else if (temp[0].startsWith("Encryption")){
					aNetwork[6] = temp[1].split(":")[1]; // Sec settings not as verbose as mac (on/off)
				} else if (temp[0].startsWith("ESSID")){
					String tempESSID = temp[0].split(":")[1]; // saves ESSID but with quotes around
					aNetwork[0] = tempESSID.substring(1, tempESSID.length()-2); // remove quotes from "ESSID"
				} 
				
			} catch (IOException e){
				e.printStackTrace();
				networks.remove(0); // hack to remove the first entry which is added though it's null
				break;
			}
		}
		return networks;
	}
	
	/**
	 * This method changes the channel of the netcard, by invoking different external programs depending on host OS.
	 * 
	 * @param chan channel to switch the NIC to
	 * @author user Niclas & Jacob
	 */
	private void setChannel(int chan){
		assert(this.currentOS != null);
		
		if (this.currentOS == SupportedOS.Mac){
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
		} else if (this.currentOS == SupportedOS.Windows){
			
		}
	}
	
	/**
	 * This method gets info about current NIC, ie channel, from different external terminal programs depending on host OS.
	 * 
	 * @return ArrayList of strings
	 */
	private ArrayList<String[]> getWifiStatus(){
		assert(this.currentOS != null);
		
		BufferedReader br = null;
		if (this.currentOS == SupportedOS.Mac){
			br = tc.exec("/System/Library/PrivateFrameworks/Apple80211.framework/Versions/Current/Resources/airport --getinfo");
		} else if (this.currentOS == SupportedOS.Windows){
			// TODO
		} else if (this.currentOS == SupportedOS.Linux){
			System.out.println("tried to get linux wifistatus, not supported yet");
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
	
	/**
	 * Method to get host MAC address for identification purposes
	 * 
	 * @return String with MAC addr.
	 * @throws Exception
	 */
	private String getMacAddress() throws Exception{
		assert(this.currentOS != null);
		
		String addr = ""; // the MAC address we wanna return
		BufferedReader br = null;
		
		// MAC
		if (this.currentOS == SupportedOS.Mac){
			br = tc.exec("ifconfig");
			String line;
		    while ( (line = br.readLine()) != null){
		    	System.out.println(line);
		    	if (line.indexOf("ether") >= 0){
		    		addr = line.substring(line.indexOf(" ")+1 );
			   	}
		    }	
		// WIN
		} else if (this.currentOS == SupportedOS.Windows){
			br = tc.exec("ipconfig /all");
			String line;
		    while ( (line = br.readLine()) != null){
		    	System.out.println(line);
		    	if (line.indexOf("Psysical Address") >= 0){
		    		addr = line.substring(line.indexOf(" ")+1 );
			   	}
		    }	
		// NIX
		} else if (this.currentOS == SupportedOS.Linux){
			br = tc.exec("ifconfig -a");
			String line;
			while( (line = br.readLine()) != null){
				System.out.println("line");
				if (line.indexOf("HWaddr") >= 0){
					addr = line.substring(line.indexOf(" ") + 1);
				}
			}
		}
		
		return addr;
	}
	
	/**
	 *  This method extracts the UDP port the  server listens on
	 * @return
	 * @throws IOException
	 */
	private int extractNumber() throws IOException{
		String command = "";
		BufferedReader inputFromServer = null;
		
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
