package rPi.connectors;

import java.io.BufferedReader;
import java.net.InetAddress;

public class Connector implements IConnector {
	
	private TCPClient tcp;
	private UDPClient udp;
		
	@Override
	public BufferedReader initTCPClient(String message)
	{
		tcp = new TCPClient();
		try {
			return tcp.createConnection(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	@Override
	public void initUDPServer(int portToSendFrom, int destinationPort, InetAddress destinationIP)
	{
		udp = new UDPClient();
		try {
			udp.initUDPServer(portToSendFrom, destinationPort, destinationIP);
		} catch (Exception e){
			// TO be handled
		}
	}
	public void sendUDP(BufferedReader bf)
	{
		if (udp != null){
			try {
				udp.sendData(bf);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// udp not initialized or broken
		}
	}
}
