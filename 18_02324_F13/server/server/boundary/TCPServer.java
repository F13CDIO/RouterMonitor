package server.boundary;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * @author Lasse Dalgaard and Jesper Baltzersen (March 2013)
 * 
 * Multithreaded TCP server. 
 * String commands: 
 * 		"Ready": An exclusive UDP port is granted for incoming data stream.
 * 		"Start": Start receiving UDP data stream.
 * 		"Stop": Stop receiving UDP data stream.
 */

public class TCPServer
{
	private Client client;
	private ServerSocket serverSocket;
	private int serverPort;
	
	
	/**
	 * Constructor. Takes wanted server port as argument.
	 * @param serverPort
	 */
	public TCPServer(int serverPort)
	{
		this.serverPort = serverPort;
	}
	
	/**
	 * Initializes and starts TCP server. This has to be done manually as the server has to be granted an Observer externally.
	 * The server loops listening for new connections. If a new client connects to the server a new Client object is created 
	 * for individually handling.  
	 * @throws IOException
	 */
    public void go() throws IOException  
    {
        serverSocket = new ServerSocket(serverPort);
        System.out.println("TCP server online");
 
        while(true)
        {
        	// Create new client for every new connection
            Socket socket = serverSocket.accept();
            if (socket != null)
            {
               client = new Client(socket);
                System.out.println("Client at "+ client.getIpAddress() +": " + client.getPort()+" connected ");
                client.start(); // Thread start
                client.write("Welcome");
            }
        }
    }
    
      /**
     * Private class. A new client object is created for each client connected to the TCP server. Each client 
     * connection has its own thread. This is how multiple clients are able to connect to the shared server port.
     */
    public class Client extends Thread
    {
        private Socket socket = null;
        private BufferedReader dataFromClient = null;
        private DataOutputStream dataToClient = null;
        private boolean disconnected = false;
        private UDPServer linkedUDPServer = null;
        private String ipAddress;
        private int port;
     
        /**
         * Constructor
         * @param socket
         * @throws IOException
         */
        public Client(Socket socket) throws IOException
        {
            this.socket = socket;
            this.ipAddress = this.socket.getInetAddress().toString().replace("/", "");
            this.port = this.socket.getPort();
            
        }
        
        /**
         * 
         * @return
         */
        public String getIpAddress()
        {
        	return this.ipAddress;
        }
        
        /**
         * 
         * @return
         */
        public int getPort()
        {
        	return this.port;
        }
        
             
        /**
         * Keep listening for TCP commands as long as connected to client. If disconnected then stop the client 
         * thread and destroy client object.
         */
        public void run()
        {
				while(!disconnected)
					read();
				 
				if (linkedUDPServer != null)
					linkedUDPServer.stop();
					
					this.stop();
        }
        
        /**
         * Read client command. 
         * "Ready": Creation of new UDP server thread
         * "Start": Start UDP server thread 
         * "Stop": Stop UDP server thread
         */
        private void read()
        {
        	try
            {    
                dataFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String clientRequest = dataFromClient.readLine();
                
                if (clientRequest != null)
                {        	
                	if (clientRequest.equals("Create"))
                	{
                		if (linkedUDPServer == null)
                		{
                			linkedUDPServer = new UDPServer();
                    		write("Start\n" + linkedUDPServer.getPort());
                		}
                			
                		else
                			write("UDP server is already created");
                		
                	}
                	
                	else if (clientRequest.equals("Start"))
                	{
                		if (linkedUDPServer != null)
                		{
                			linkedUDPServer.start();
                			write("UDP server started");
                		}
                		
                		else
                			write("A UDP server hasn't been created yet");

                	}
                	
                	else if (clientRequest.equals("Stop"))
                	{
                		if (linkedUDPServer != null)
                		{
                			linkedUDPServer.stop();
                			linkedUDPServer = null;
                			write("UDP server terminated");
                		}
                		
                		else
                		{
                			write("No UDP server is running");
                		}
                	}
                	
                	else 
                		write("Invalid command");
                }
                
                else
                {
                	disconnected = true;
                	System.out.println("Client at "+ ipAddress +" disconnected ");
                }
            }
            
            catch(IOException e)
            {
                System.out.println("Error: " + e);
            }
        }
        
        /**
         * Sends message with granted UDP port number to client.
         * @param message
         * @throws IOException
         */
        private void write(String message) throws IOException
        {
        	dataToClient = new DataOutputStream(socket.getOutputStream());
            dataToClient.writeBytes(message + "\n");
            dataToClient.flush();
        }    
    }
}