package UI;
import java.io.*;
import java.net.*;

import Funktion.IFunction;

public class UDPServer implements Runnable 
{
	private static int availableUDPPort = 8001;
	private int portUDP;
	private DatagramPacket inPacketUDP;
	private DatagramSocket inSocketUDP;
	private IFunction function = UI.getFunctionInstance();
	//IParseUdpPackage IParser = new ParseUdpPackage();


	public UDPServer() throws IOException
	{
		portUDP = availableUDPPort;
		availableUDPPort++;
		
		inSocketUDP = new DatagramSocket(portUDP);
	}
	
	public int getPort()
	{
		return portUDP;
	}


	//	inSocketUDP.close();



	//fill inPacketUDP buf with packetData from socket
	public void run() 
	{
		try
		{
			while (true)
			{
				//String data = "10.16.99.136:55751 -> 69.171.235.16:80 [AP]\nGET /ping?partition=236&cb=gks9 HTTP/1.1..Host: 3-pct.channel.facebook.com..Connection: keep-alive..User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/";
				
				inPacketUDP = new DatagramPacket(new byte[1024], 1024);
				if(Thread.interrupted())
				{
					break;
				}
				inSocketUDP.receive(inPacketUDP);
				
				String data = new String(inPacketUDP.getData());
				function.parse(data);
			}
			
		}
		catch(SocketException se)
		{
			System.out.println(se.getMessage());
			inSocketUDP.close();
		}			
		catch (IOException e)
		{
			System.out.println("IOException: " + e);
			inSocketUDP.close();
		}
		finally
		{
			System.out.println("finally!!");
			inSocketUDP.close();
		}
	}
	
	public void close()
	{
		inSocketUDP.close();
		System.out.println("close");
	}
}
