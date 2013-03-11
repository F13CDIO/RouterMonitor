package UI;
import java.io.*;
import java.net.*;

import Funktion.IFunction;

public class UDPServer implements Runnable 
{
	private static int availableUDPPort = 8001;
	private int portUDP;
	private byte[] buf = new byte[1024];
	private int lenBuf = buf.length;
	private DatagramPacket inPacketUDP;
	private DatagramSocket inSocketUDP;
	private IFunction function = UI.getFunctionInstance();
	//IParseUdpPackage IParser = new ParseUdpPackage();


	public UDPServer() throws IOException
	{
		portUDP = availableUDPPort;
		availableUDPPort++;
		
		inPacketUDP = new DatagramPacket(buf, lenBuf);
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
		while (true)
		{
			try
			{
				String data = "10.16.99.136:55751 -> 69.171.235.16:80 [AP]\nGET /ping?partition=236&cb=gks9 HTTP/1.1..Host: 3-pct.channel.facebook.com..Connection: keep-alive..User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/";
				
				inSocketUDP.receive(inPacketUDP);
				System.out.println("Vi parser");
				function.parse(data);
			}
			
			catch (IOException e)
			{
				System.out.println("IOException: " + e);
			}
		}
	}
}
