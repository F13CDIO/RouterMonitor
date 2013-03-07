package UI;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer
{
	private ServerSocket serverSocket;
	private String clientRequest;
    private String capitalizedSentence;
    private Socket connectionSocket;

    public TCPServer() throws IOException 
    {
    	serverSocket = new ServerSocket(9000);
    	connectionSocket = serverSocket.accept();
    }
    
	public void start() throws IOException
	{
		while(true)
		{
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
}
