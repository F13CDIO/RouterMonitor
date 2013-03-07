package UI;
import java.io.*;
import java.net.*;

public class UDPServer implements Runnable 
{
	private static int availableUDPPort = 8001;
	private int portUDP;
	private byte[] buf = new byte[1024];
	private int lenBuf = buf.length;
	private DatagramPacket inPacketUDP;
	private DatagramSocket inSocketUDP;
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
	public void run() {
		while (true){
			try{
			inSocketUDP.receive(inPacketUDP);
			//IParser.parse(new String(inPacketUDP.getData()));
			System.out.println(new String(inPacketUDP.getData()));
			// TODO Auto-generated method stub
			}catch (IOException e){
				System.out.println("IOException: " + e);
			}
		}
	}
}
