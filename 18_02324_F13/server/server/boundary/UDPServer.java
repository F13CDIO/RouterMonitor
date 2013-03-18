package server.boundary;
import java.io.*;
import java.net.*;

import server.function.IFunction;


public class UDPServer extends Thread
{
	private static int availableUDPPort = 8001;
	private int udpPort;
	private DatagramPacket incommingPacket;
	private DatagramSocket udpSocket;
	private IFunction function;
	private boolean keepRunning;
	
	public UDPServer() throws IOException
	{
		function  = Boundary.getInstanceOfFunction();
		keepRunning  = true;
		this.udpPort = UDPServer.availableUDPPort;
		UDPServer.availableUDPPort++;
	}
	
	public int getPort()
	{
		return udpPort;
	}
	
	public void stopThread()
	{
		keepRunning = false;
		udpSocket = null;
	}
	

	public void run() // Start thread 
	{
		try 
		{
			udpSocket = new DatagramSocket(udpPort);
		} 
		
		catch (SocketException e1) 
		{
			System.out.println(e1.getMessage());
		}
			
		// Keep listening
		while (keepRunning)
		{
			try
			{		
				incommingPacket = new DatagramPacket(new byte[1024], 1024);
				udpSocket.receive(incommingPacket);
				String data = new String(incommingPacket.getData());
				function.parse(data);
			}
			
			catch (IOException e)
			{
				System.out.println("IOException: " + e);
			}
		}
	}
}