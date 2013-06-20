package rPi.connectors;

import java.io.BufferedReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

/*
 * Implementation of the controller that handles our network connections through use of TCPCLient and UDPCLient
 */

public class Connector implements IConnector {
	
	private TCPClient tcp;
	private UDPClient udp;
		
	@Override
	public BufferedReader initTCPClient(InetAddress serverAddress, short serverPort)
	{
		tcp = new TCPClient();
		try {
			System.out.println("trying to connect to server over tcp");
			return tcp.createConnection(serverAddress, serverPort);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to init tcp client");
			e.printStackTrace();
		} 
		return null;
	}
	@Override
	public void sendTCP(String message)
	{
		assert(tcp != null);	
		
		tcp.sendLine(message);
		
	}
	@Override
	public void initUDP(int portToSendFrom, int destinationPort, InetAddress destinationIP)
	{
		udp = new UDPClient();
		

		try {
			udp.initUDP(portToSendFrom, destinationPort, destinationIP);
		} catch (Exception e){
			// TO be handled
		}
	}
	public void sendUDP(BufferedReader bf)
	{
		if (udp != null){
			System.out.println("Setting udp buff reader in Connector.java");
			udp.setBufferedReader(bf);
			try {
				udp.start();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// udp not initialized or broken
		}
	}
	public void stopUDP(){
		if(udp != null)
			udp.stopUDP();
		else
			System.out.println("there is no UDP connection to terminate");
	}
}
