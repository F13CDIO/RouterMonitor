package rPi.connectors;
import java.io.*;
import java.net.*;

/*
 *  Class to connect the rPi to the server to await commands
 *  written by Niclas Falck 01-03-2013
 */

class TCPClient
{
	private Socket clientSocket;
	private BufferedReader inFromServer;
	private DataOutputStream outToServer;

	public BufferedReader createConnection(InetAddress serverAddress, short serverPort) throws Exception
	{
		clientSocket = new Socket(serverAddress, serverPort);
		inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));	
		return inFromServer;
	}
	public void sendLine(String message)
	{
		try {
			outToServer = new DataOutputStream(clientSocket.getOutputStream());
			outToServer.writeBytes(message);
			outToServer.flush();
			System.out.println("hejsa jeg flsuher");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
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