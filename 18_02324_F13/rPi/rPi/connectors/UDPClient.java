package rPi.connectors;
import java.io.*;
import java.net.*;

/*
 *  Class to send the truncated network traffic that the rPi has scanned to the server through UDP
 *  written 01-03-2013 by Niclas Falck
 */

class UDPClient extends Thread
{
	private int destinationPort;
	InetAddress destinationIpAddress;
	DatagramSocket serverSocket;
	byte[] sendData = new byte[2048];
	byte[] buf = new byte[2048];
	
	BufferedReader outputFromPi;
	boolean RUNNING;
	
	public void run(){
		while (RUNNING)
			try {
				sendData();
			} catch (Exception e) {
				System.out.println("problem in udp client");
				e.printStackTrace();
			}
	}
	
	public void stopUDP(){
		this.RUNNING = false;
	}
	
	public void setBufferedReader(BufferedReader outputFromPi){
		this.outputFromPi = outputFromPi;
	}
	
	public void initUDP(int portToSendFrom, int destinationPort, InetAddress destinationIpAddress) throws Exception
	{
		RUNNING = true;
		System.out.println("dest port"+ destinationPort);
		serverSocket = new DatagramSocket(portToSendFrom);
		this.destinationPort = destinationPort;
		this.destinationIpAddress = destinationIpAddress;
	}
	
	public void sendData() throws Exception
	{
		assert serverSocket != null;
		while(true)
		{
			String line = outputFromPi.readLine();
			if(line == null)
				System.out.println("output in sendData is null");
			if(line != null){
				System.out.println(line);
					//break;
				sendData =line.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, destinationIpAddress, destinationPort);
				if(sendPacket != null){
				serverSocket.send(sendPacket);
				
				}
			}
		}
	} 
}