package server.boundary;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
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
        System.out.println("TCP server online on ip: " + Inet4Address.getLocalHost().getHostAddress());
 
        while(true) // Keep listening for new clients
        {
            Socket socket = serverSocket.accept(); // Create new client if new connection is established
            if (socket != null)
            {
               client = new Client(socket);
               System.out.println("Client at "+ client.getIpAddress() +":" + client.getPort()+" connected ");
               client.start(); // Thread start
               //client.write("Welcome");
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
				{
					read();
				}
        }
        
        private void read() 
        {
        	try
            {    
                dataFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String clientRequest = dataFromClient.readLine();
                System.out.println("Pi command: " + clientRequest);
                
                switch(clientRequest.toLowerCase())
                    {            		
                    	case "create": // -----------------------------------------------------------------------------
                    	if (linkedUDPServer == null)
                		{
                			linkedUDPServer = new UDPServer();
                    		write("start\n" + linkedUDPServer.getPort());
                    		System.out.println("Server: Start on UDP port " + linkedUDPServer.getPort());
                		}
                    	break;
                    	

                    	case "start": // -----------------------------------------------------------------------------
                    	if (linkedUDPServer != null)
                    	{
                    		linkedUDPServer.start();
                    		write("UDP server started");
                    		
                    		// test comm with pi
                    		write("scanNetworks\n");
                    		System.out.println("Server: Scan networks");
                    		while (dataFromClient.read() > 0)
                    		{
                    			System.out.println("Pi: " + dataFromClient.readLine());
                    		}
                    		
                    		Thread.sleep(1000);
                    		
                    		write("getWifiStatus");
                    		System.out.println("Server: Get wifi status");
                    		while (dataFromClient.read() > 0)
                    		{
                    			System.out.println("Pi: " + dataFromClient.readLine());
                    		}
                    		Thread.sleep(1000);
                    		                    		
                    		write("setChannel");
                    		write("5");
                    		System.out.println("Server: Set channel 5");
                    		
                    		while (dataFromClient.read() > 0)
                    			System.out.println("Pi: " + dataFromClient.readLine());
                    		
                    		write("getWifiStatus");
                    		System.out.println("Command to rPi: Get wifi status");
                    		while (dataFromClient.read() > 0)
                    		{
                    			System.out.println("Pi: " + dataFromClient.readLine());
                    		}
                    		// End test

                    	}
                    		
                    	else
                    		write("A UDP server hasn't been created yet");
                    	break;
                    	
                    	
                    	case "stop": // -----------------------------------------------------------------------------
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
                    	
                    	default: // -----------------------------------------------------------------------------
                    		write("Invalid command");
                    		
                    	break;
                    }
            }
            
        	catch(Exception e) // IO and null pointer
            {
        		System.out.println(client.ipAddress + ": " + client.port + " disconnected");
                stopThread(); 
            	if (linkedUDPServer != null)
            		linkedUDPServer.stopThread(); // Stop linked UDP server thread
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