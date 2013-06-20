/**
 * UDP server for each client.
 */

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
	
	/**
	 * Get granted UDP port
	 * @return int udpPort
	 */
	public int getPort()
	{
		return udpPort;
	}
	
	/**
	 * Kill thread
	 */
	public void stopThread()
	{
		keepRunning = false;
		udpSocket = null;
	}
	
	/**
	 * Start thread by running start method. Keep listening for UDP packet on granted UDP port. 
	 */
	public void run()  
	{
		try { udpSocket = new DatagramSocket(udpPort); } 
		catch (SocketException e1) { System.out.println(e1.getMessage()); }
			
		while (keepRunning)
		{
			try
			{
				Thread.sleep(1);
				incommingPacket = new DatagramPacket(new byte[1024], 1024);
				udpSocket.receive(incommingPacket);
				String data = new String(incommingPacket.getData());
				function.parse(data);
			}
			
			catch (IOException | InterruptedException e) { System.out.println("IOException: " + e); }
		}
	}
}