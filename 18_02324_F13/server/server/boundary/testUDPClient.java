package server.boundary;

import java.io.BufferedReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class testUDPClient 
{
	private int destinationPort = 8001;
	String destinationIpAddress = "127.0.0.1";
	DatagramSocket serverSocket;
	byte[] sendData = new byte[1024];
	byte[] buf = new byte[1024];
	
	public testUDPClient() throws Exception
	{
		serverSocket = new DatagramSocket();
	}
	
	public void sendData() throws Exception
	{
		assert serverSocket != null;
		while(true)
		{
			sendData = "test".getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(destinationIpAddress), destinationPort);
			serverSocket.send(sendPacket);
			Thread.sleep(1000L);
		}
	}
		
}
