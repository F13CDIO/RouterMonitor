package rPi.connectors;
import java.io.*;
import java.net.*;

/*
 *  Class to send the truncated network traffic that the rPi has scanned to the server through UDP
 *  written 01-03-2013 by Niclas Falck
 */

class UDPServer
{
	private int destinationPort;
	InetAddress destinationIpAddress;
	DatagramSocket serverSocket;
	byte[] sendData = new byte[1024];
	byte[] buf = new byte[1024];
	
	public void initUDPServer(int portToSendFrom, int destinationPort, InetAddress destinationIpAddress) throws Exception
	{
		serverSocket = new DatagramSocket(portToSendFrom);
		this.destinationPort = destinationPort;
		this.destinationIpAddress = destinationIpAddress;
	}
	
	public void sendData(BufferedReader outputFromPi) throws Exception
	{
		assert serverSocket != null;
		while(true)
		{
			String thisLine = outputFromPi.readLine();
			sendData = thisLine.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, destinationIpAddress, destinationPort);
			serverSocket.send(sendPacket);
		}
	}
			

 
}