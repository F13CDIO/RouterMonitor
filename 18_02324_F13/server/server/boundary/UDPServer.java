package server.boundary;
import java.io.*;
import java.net.*;

import Funktion.IFunction;

public class UDPServer extends Thread
{
	private static int availableUDPPort = 8001;
	private int udpPort;
	private byte[] bufferSize = new byte[1024];
	private int bufferLength = bufferSize.length;
	private DatagramPacket incommingPacket;
	private DatagramSocket udpSocket;
	private IFunction function = UI.getFunctionInstance();
	
	public UDPServer() throws IOException
	{
		this.udpPort = availableUDPPort;
		UDPServer.availableUDPPort++;
	}
	
	public int getPort()
	{
		return udpPort;
	}
	

	public void run() // Start thread 
	{
		try 
		{
			udpSocket = new DatagramSocket(udpPort);
			incommingPacket = new DatagramPacket(bufferSize, bufferLength);
		} 
		
		catch (SocketException e1) 
		{
			System.out.println(e1.getMessage());
		}
			
		// Keep listening
		while (true)
		{
			try
			{
				//String testData = "10.16.99.136:55751 -> 69.171.235.16:80 [AP]\nGET /ping?partition=236&cb=gks9 HTTP/1.1..Host: 3-pct.channel.facebook.com..Connection: keep-alive..User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/";
				
				incommingPacket = new DatagramPacket(new byte[1024], bufferLength);
				
				udpSocket.receive(incommingPacket);
				String data = new String(incommingPacket.getData());
				//function.parse(data);
				System.out.println(data);
			}
			
			catch (IOException e)
			{
				System.out.println("IOException: " + e);
			}
		}
	}
}