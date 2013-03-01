package rPi.connectors;

import java.io.BufferedReader;

public class Connector implements IConnector {
		
	@Override
	public BufferedReader initTCPClient(String message)
	{
		TCPClient tcp = new TCPClient();
		try {
			return tcp.createConnection(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	@Override
	public void listenForTcp(short port)
	{
			// TODO Auto-generated catch block	
	}	
	public void initUDPServer(BufferedReader output)
	{
		UDPServer udp = new UDPServer();
	}
}
