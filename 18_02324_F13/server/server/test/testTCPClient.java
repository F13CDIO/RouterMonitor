package server.test;
	
	import java.io.*;
	import java.net.*;

	

	class testTCPClient
	{
		private Socket clientSocket;
		private BufferedReader inFromServer;
		private DataOutputStream outToServer;
		private String serverAddress = "127.0.0.1";
		private int serverPort = 9000;

		public testTCPClient() throws Exception
		{
			clientSocket = new Socket(serverAddress, serverPort);
			inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		}
		
		public void sendLine(String message)
		{
			try {
				outToServer = new DataOutputStream(clientSocket.getOutputStream());
				outToServer.writeBytes(message + "\n");
				outToServer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		public void send(String message)
		{
			try {
				outToServer = new DataOutputStream(clientSocket.getOutputStream());
				outToServer.writeBytes(message);
				outToServer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		public String readLine()
		{
			try {
				String input = inFromServer.readLine();
				return input;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
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
	
	
	

