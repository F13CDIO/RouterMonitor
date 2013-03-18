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
				outToServer.writeBytes(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		public void readLine()
		{
			try {
				String input = inFromServer.readLine();
				System.out.println(input);
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
	
	
	

