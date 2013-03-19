package server.test;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class testUDPClient 
{
	private int destinationPort = 8001;
	String destinationIpAddress = "10.16.135.44";
	DatagramSocket serverSocket;
	byte[] sendData = new byte[1024];
	byte[] buf = new byte[1024];
	
	String testPackage = "T 10.16.99.136:55751 -> 69.171.235.16:80 [AP]\n  GET /ping?partition=236&cb=gks9 HTTP/1.1..Host: 3-pct.channel.facebook.com..Connection: keep-alive..User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/";
	
	public void setPort(String port)
	{
		destinationPort = Integer.parseInt(port);
	}
	public testUDPClient() throws Exception
	{
		serverSocket = new DatagramSocket();
	}
	
	public void sendData() throws Exception
	{
		assert serverSocket != null;
			sendData = testPackage.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(destinationIpAddress), destinationPort);
			serverSocket.send(sendPacket);
			//Thread.sleep(1L);
		
	}
		
}
