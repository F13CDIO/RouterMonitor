package server.boundary;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ConnectedTCPClient extends Thread
{
    private Socket socket = null;
    private BufferedReader dataFromClient = null;
    private DataOutputStream dataToClient = null;
    private boolean clientDisconnected = false;
    private UDPServer linkedUDPServer = null;
    private String ipAddress;
    private int port;
    private String macAddress = "";

    public ConnectedTCPClient(Socket socket) throws IOException
    {
        this.socket = socket;
        this.ipAddress = this.socket.getInetAddress().toString().replace("/", "");
        this.port = this.socket.getPort();
    }
    
    public String getMac()
    {
    	return macAddress;
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
    
    
    
    public String read() 
    {
    	String clientRequest = "";
    	
    	try
        {    
            dataFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            while (dataFromClient.read() > 0)
            {
            	clientRequest += dataFromClient.readLine();
            }
            
            
            
            switch(clientRequest.toLowerCase())
                {            
            
                	case "mac":
                		System.out.println("Pi command: " + clientRequest);
                		macAddress = dataFromClient.readLine();
                		System.out.println("Pi MAC: " + macAddress);
                		
                		break;
                		
                	case "create": // -----------------------------------------------------------------------------
                	if (linkedUDPServer == null && !macAddress.equals(""))
            		{
            			linkedUDPServer = new UDPServer();
                		write("start\n" + linkedUDPServer.getPort());
                		System.out.println("Pi command: " + clientRequest);
                		System.out.println("Server: Start on UDP port " + linkedUDPServer.getPort());
                		System.out.println("MAC: " + macAddress);
                		
                		TCPServer.addClient(this);
            		}
                	break;
                	

                	case "start": // -----------------------------------------------------------------------------
                	
                	System.out.println("Pi command: " + clientRequest);
                	if (linkedUDPServer != null)
                	{
                		linkedUDPServer.start();
                		write("UDP server started");
                	}
                		
                	else
                	{
                		write("A UDP server hasn't been created yet");
                	}
                	break;
                	
                	
                	case "stop": // -----------------------------------------------------------------------------
                	System.out.println("Pi command: " + clientRequest);
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
                		//write("Invalid command");
                		break;
                }
        }
        
    	catch(Exception e) // IO and null pointer
        {
    		System.out.println(this.ipAddress + ": " + this.port + " disconnected");
            stopThread();
            System.out.println("MAC REMOVED FROM GUI");
        	if (linkedUDPServer != null)
        		linkedUDPServer.stopThread(); // Stop linked UDP server thread
        }
    	
   	return clientRequest;
    }
    
    public void write(String message) throws IOException
    {
    	dataToClient = new DataOutputStream(socket.getOutputStream());
        dataToClient.writeBytes(message + "\n");
        dataToClient.flush();
    }    
    
    private void stopThread()
    {
    	TCPServer.removeClient(this);
    	clientDisconnected = true;
    }
}