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
	
	private static InetAddress SERVER_IP; // Hard code IP for now
	private static short DEFAULT_PORT = 0; // the port the server listens on
	
	//=================================================//
	
	Connector con = new Connector();
	BufferedReader inputFromServer = con.initTCPClient(SERVER_IP, DEFAULT_PORT);
	
	
	
	MainController mc = new MainController();
	
	public void connectToNetwork(String SSID)
	{
		//Sprøger lasse om hvordan man køre script... 
		
	}
	private String scanLocalNetworks()
	{
		mc.exec("iw wlan0 scan");
		return "";
	}
}
