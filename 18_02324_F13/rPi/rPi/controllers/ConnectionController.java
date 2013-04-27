package rPi.controllers;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetAddress;

import rPi.connectors.Connector;

/*
 * This class has the responsibility for networking and "calling home"
 * 2013 Anders & Niclas
 */

public class ConnectionController {

	//====== THE CONSTANTS YOU MIGHT WANNA CHANGE ======//
	private static InetAddress SERVER_IP	; // Hard code IP for now
	private static short DEFAULT_PORT = 9000; // the TCP port the server listens on
	private static int UDP_PORT_TO_SEND_FROM = 15000;
	
	//=================================================//
	
	Connector con = new Connector();
	BufferedReader inputFromServer;
	String fromServer;
	StreamParser parser = new StreamParser();
	DataHandler dHandler;
	
	public ConnectionController() throws Exception{
		SERVER_IP = InetAddress.getByName("10.16.171.97");
		dHandler = new DataHandler(); // this instance executes the sniffing program in a terminal
		
		inputFromServer = con.initTCPClient(SERVER_IP, DEFAULT_PORT); 
		System.out.println("her sendes create");
		
		con.sendTCP("create\n");
		
		con.initUDP(UDP_PORT_TO_SEND_FROM, extractPortNumber(),SERVER_IP);  // extractportnumber skal fejltjekkes for port 0
		con.sendTCP("start\n");
		con.sendUDP(dHandler.startSniffing()); // The start sniffing method returns a buffered reader with output from ngrep
		
	}
	
	// This method extracts the UDP port the  server listens on
	private int extractPortNumber() throws IOException{
		String command = inputFromServer.readLine();
		System.out.println(command);
		int port = 0;
		if(command.startsWith("start")){
			String portStr = inputFromServer.readLine();
			port = Integer.parseInt(portStr);
		}
		return port;
	}
	 
	public void connectToNetwork(String SSID)
	{
		//Spr��ger lasse om hvordan man k��re script... 
		
	}
	private String scanLocalNetworks()
	{
		//following works on linux/android but we need to find solutions to osx and winslows mc.exec("iw wlan0 scan");
		return "";
	}
}
