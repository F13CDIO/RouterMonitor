package rPi.controllers;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetAddress;

import rPi.connectors.Connector;

/*
 * This class has the responsibility for networking and "calling home"
 *
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
	
	public ConnectionController() throws Exception{
		SERVER_IP = InetAddress.getByName("10.16.171.97");
		
		
		
		
		
		
		
	}
	public BufferedReader connectToServer(){
		// from here we get the command from the C&C server
		inputFromServer = con.initTCPClient(SERVER_IP, DEFAULT_PORT); 
		
		System.out.println("her sendes create"); // useless shit protocol HAVE TO BE MITIGATED!!! 
		con.sendTCP("create\n");
		con.sendTCP("start\n");
		return inputFromServer;
	}
	public void initAndSendUDP(BufferedReader outputStream, int portnr){
		con.initUDP(UDP_PORT_TO_SEND_FROM, portnr,SERVER_IP);  // extractportnumber skal fejltjekkes for port 0
		
		con.sendUDP(outputStream); // The start sniffing method returns a buffered reader with output from ngrep
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
