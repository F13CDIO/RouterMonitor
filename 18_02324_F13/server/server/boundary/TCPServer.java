package server.boundary;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer
{
	private Client client;
	private ServerSocket serverSocket;
	private int serverPort;
	
	public TCPServer(int serverPort)
	{
		this.serverPort = serverPort;
	}
	
    public void startServer() throws IOException  
    {
        serverSocket = new ServerSocket(serverPort);
        System.out.println("TCP server online");
 
        while(true) // Keep listening for new clients
        {
            Socket socket = serverSocket.accept(); // Create new client if new connection is established
            if (socket != null)
            {
               client = new Client(socket);
               System.out.println("Client at "+ client.getIpAddress() +": " + client.getPort()+" connected ");
               client.start(); // Thread start
               client.write("Welcome");
            }
        }
    }
    
    private class Client extends Thread
    {
        private Socket socket = null;
        private BufferedReader dataFromClient = null;
        private DataOutputStream dataToClient = null;
        private boolean clientDisconnected = false;
        private UDPServer linkedUDPServer = null;
        private String ipAddress;
        private int port;
     

        public Client(Socket socket) throws IOException
        {
            this.socket = socket;
            this.ipAddress = this.socket.getInetAddress().toString().replace("/", "");
            this.port = this.socket.getPort();
        }
        
        public String getIpAddress()
        {
        	return this.ipAddress;
        }
        
        public int getPort()
        {
        	return this.port;
        }
        
             
        public void run() // Thread start method
        {
				while(!clientDisconnected)
					read();
        }
        
        private void read() 
        {
        	try
            {    
                dataFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String clientRequest = dataFromClient.readLine();
                
           
                if (clientRequest != null)
                {
                	switch(clientRequest)
                    {            		
                		// 1. Create UDP server -----------------------------------------------------------------------------
                	
                    	case "Create":
                    	if (linkedUDPServer == null)
                		{
                			linkedUDPServer = new UDPServer();
                    		write("Start\n" + linkedUDPServer.getPort());
                		}
                    	break;
                    	
                    	// 2. Stop UDP server -----------------------------------------------------------------------------
                    
                    	case "Start":
                    	if (linkedUDPServer != null)
                    	{
                    		linkedUDPServer.start();
                    		write("UDP server started");
                    	}
                    		
                    	else
                    		write("A UDP server hasn't been created yet");
                    	break;
                    	
                    	
                    	// 3. Stop UDP server -----------------------------------------------------------------------------
                    	
                    	case "Stop":
                    	if (linkedUDPServer != null)
                    	{
                    		linkedUDPServer.stopThread();
                    		linkedUDPServer = null;
                    		write("UDP server terminated");
                    	}

                    	else
                    	{
                    		write("No UDP server is running");
                    	}
                    	
                    	break;
                    	
                    	default:
                    	write("Invalid command");
                    		break;
                    	//  ----------------------------------------------------------------------------------------------
                    }
                }
                
                
                else 
                {
                	stopThread(); // Stop client thread
                	if (linkedUDPServer != null)
                		linkedUDPServer.stopThread(); // Stop linked UDP server thread
                	
                	System.out.println("Client at "+ ipAddress +" disconnected ");
                }
            }
            
            catch(IOException e)
            {
                System.out.println("Error: " + e);
            }
        }
        
        private void write(String message) throws IOException
        {
        	dataToClient = new DataOutputStream(socket.getOutputStream());
            dataToClient.writeBytes(message + "\n");
            dataToClient.flush();
        }    
        
        private void stopThread()
        {
        	clientDisconnected = true;
        }
    }
}