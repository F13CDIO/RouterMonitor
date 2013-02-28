package Data;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Connector {
	
	private static short DEFAULT_PORT = 0;
	
	private ServerSocket ss;
	private Socket skt;
	
	public void listenForTCP(int port) throws Exception
	{
		new ServerSocket(DEFAULT_PORT);
		skt = (ss.accept());
		
	}
	
	public void sendTCP(String message)
	{
		try {
			PrintWriter out = new PrintWriter(skt.getOutputStream(), true);
			out.print(message);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}	
}
