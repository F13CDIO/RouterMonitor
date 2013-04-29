package rPi.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import rPi.connectors.Connector;

public class MenuHandler {
	
	TerminalExecutor tc = new TerminalExecutor();
	public BufferedReader br = null;
	BufferedReader inputFromServer = null;
	ConnectionController cc;
	
	public MenuHandler() throws Exception {
		ConnectionController cc = new ConnectionController();		
	}
	
	public void handleCommand(BufferedReader inputFromServer, Connector con) throws Exception{
		this.inputFromServer = inputFromServer;
		try {
			String command = inputFromServer.readLine();
			Command cmd = Command.valueOf(command);
			switchMenu(cmd);
		} catch (IOException e) {
			System.out.println("Could not read command from server");
			e.printStackTrace();
		}
		
	}
	
	private enum Command {
		start, stop, scanNetworks;
	}
	
	private void switchMenu(Command cmd) throws Exception{
		switch (cmd) {
		case start: // start sniffing
			int portnr = extractPortNumber();
			System.out.println("extracted port nr : " + portnr);
			cc.initAndSendUDP(startSniffing(), portnr);
			break;
		case stop:
			stopSniffing();
			break;
		case scanNetworks:
			//scanNetworks();
			break;
		}
		
	}
	
	// the following command requires tshark opened in sudoers file or the java program executed with su rights
	// the script saves the tshark process id in process1.pid for later implementation of multiple tshark processes
	//the command executed in startScript is "/usr/local/bin/tshark -T fields -e ip.src -e ip.dst -e http.host -e http.user_agent -i en0 -I -R http.request tcp port 80 and ip &";
	public BufferedReader startSniffing(){
		String startScript = "bash startScript.sh";
		br = tc.exec(startScript);
		return br; 
	}
	
	public void stopSniffing(){
		// stopping the process is easy to do in one line, so here it goes with some bash-fu (linux/osx compatible)
		tc.exec("kill $(cat process1.pid)");
		// here is osx versionx
		tc.exec("/System/Library/PrivateFrameworks/Apple80211.framework/Versions/Current/Resources/airport -s");
		
	}
	
	// This method extracts the UDP port the  server listens on
	private int extractPortNumber() throws IOException{
		String command = inputFromServer.readLine();
		System.out.println(command);
		int port = 0;

		String portStr = inputFromServer.readLine();
		port = Integer.parseInt(portStr);
	
		return port;
	}	
}
