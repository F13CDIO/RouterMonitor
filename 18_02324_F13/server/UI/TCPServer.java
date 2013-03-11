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
	private List<Socket> socketList= new ArrayList<Socket>();
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
	    	socketList.add(connectionSocket);
	    	System.out.println(connectionSocket.getInetAddress() + " has connected");
			
	       BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
	       DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
	           
	       String clientRequest = inFromClient.readLine();
	       
	       if (clientRequest.equals("Ready"))
	       {
	    	   UDPServer udpServer = new UDPServer();
	    	   Thread udpServerThread = new Thread(udpServer);
	    	   udpServerThread.start();
	    	   int grantedPortNumber = udpServer.getPort();
		       outToClient.writeBytes("Port granted = " + grantedPortNumber + "\n");
	       }
		}
	}
	
	public void Close()
	{
		try {
			connectionSocket.close();
			System.out.println("Connection closed");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("close error");
		}
	}
}
