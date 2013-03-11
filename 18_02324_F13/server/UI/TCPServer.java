package UI;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class TCPServer
{
	private UDPServer udpServer;
	private Thread udpServerThread;
	private ServerSocket serverSocket;
	private String clientRequest;
    private String capitalizedSentence;
    private Socket connectionSocket;

    public TCPServer() throws IOException 
    {
    	serverSocket = new ServerSocket(9000);
    }
    
	public void start() throws IOException
	{
		while(true)
		{			
			connectionSocket = serverSocket.accept();
	    	System.out.println(connectionSocket.getInetAddress() + " has connected");
			
	       BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
	       DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
	           
	       String clientRequest = inFromClient.readLine();
	       
	       if (clientRequest.equals("Ready"))
	       {
	    	   udpServer = new UDPServer();
	    	   udpServerThread = new Thread(udpServer);
	    	   udpServerThread.start();
	    	   int grantedPortNumber = udpServer.getPort();
		       outToClient.writeBytes("Port granted = " + grantedPortNumber + "\n");
	       }
	       //Den kommer ikke der til, pga readLine højere oppe
	       if(!connectionSocket.isConnected())
	       {
	    	   Close();
	       }
		}
	}
	
	public void Close()
	{
		try {
			connectionSocket.close();
			udpServerThread.interrupt();
			System.out.println("Connection closed");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("close error");
		}
	}
}
