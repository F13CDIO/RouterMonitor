package rPi.connectors;
import java.io.*;
import java.net.*;

/*
 *  Class to connect the rPi to the server to await commands
 *  written by Niclas Falck 01-03-2013
 */

class TCPClient
{
	private static short DEFAULT_PORT = 0;
	private Socket clientSocket;
	private BufferedReader inFromServer;
	private DataOutputStream outToServer;

	public BufferedReader createConnection(String messageFromPi) throws Exception
	{
		clientSocket = new Socket("localhost", DEFAULT_PORT);
		outToServer = new DataOutputStream(clientSocket.getOutputStream());
		inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		outToServer.writeBytes(messageFromPi);	
		return inFromServer;
	}
	
	public void closeConnection() throws Exception
	{
		try {
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}