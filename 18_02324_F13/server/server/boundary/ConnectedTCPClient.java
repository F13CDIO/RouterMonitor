package server.boundary;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * The TCPclient connected to the server
 * @author Group 18
 *
 */

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
    private boolean GUIInterrupt = false;

    /**
     * 
     * @param socket 
     * @throws IOException
     */
    public ConnectedTCPClient(Socket socket) throws IOException
    {
    	dataFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.socket = socket;
        this.ipAddress = this.socket.getInetAddress().toString().replace("/", "");
        this.port = this.socket.getPort();
    }
    
    /**
     * The identifyer of the client is it's macaddress
     * @return Returns the identifyer of the client
     */
    public String getMac()
    {
    	return macAddress;
    }
    
    /**
     * 
     * @return Returns the ipaddress of the client
     */
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
					if (!GUIInterrupt)
						readCommand();				
				
			}
    }
    
    /**
     * Reads data send via TCP, with a timeout of 20 times 50ms    
     * @return The data recieved as one string
     * @throws IOException
     */
    public String readData() throws IOException
    {
    	String data = "";
    	int timeout = 0;
    	
    	while(!dataFromClient.ready() && timeout < 20)
    	{
    		timeout++;
    		try
			{
				Thread.sleep(50);
			}
			catch (InterruptedException e)
			{
				System.err.println("sleeping");
			}
    	}
    	
    	while(dataFromClient.ready())
		{
			data += dataFromClient.readLine();
		}
		
		GUIInterrupt = false;
		return data;
    }
    
    /**
     * Common commands recieved from the client, is interpreted here 
     */
    public void readCommand() 
    {
    	String clientCommand = "";
    	
    	
    	try
        { 
    		Thread.sleep(1);
    		
    		if (dataFromClient.ready())
    		{
            clientCommand = dataFromClient.readLine();
            System.out.println("rPi command: " + clientCommand);
            switch(clientCommand.toLowerCase())
            {            	
            	case "mac":
            		macAddress = dataFromClient.readLine();
            		System.out.println("rPi MAC: " + macAddress);
	        		linkedUDPServer = new UDPServer();
	        		
	            	System.out.println("Server: Start on UDP port " + linkedUDPServer.getPort());
	            	TCPServer.addClient(this);
            		linkedUDPServer.start();
            		break;
            	
            	default: 
            		write("Invalid command");
            		System.out.println("Invalid command");
            		break;
            }
        
    		}
    	}
        
    	catch (InterruptedException ie)
    	{
    		GUIInterrupt = true;
    	}
    	catch(Exception e) 
        {
    		System.out.println(this.ipAddress + ": " + this.port + " disconnected");
            stopThread();
            System.out.println("MAC REMOVED FROM GUI");
        	if (linkedUDPServer != null)
        		linkedUDPServer.stopThread(); // Stop linked UDP server thread
        }
    }
    
    /**
     * Sends a message to the client, ending on a linebreake
     * @param message The message to send
     * @throws IOException
     */
    public void write(String message) throws IOException
    {
    	dataToClient = new DataOutputStream(socket.getOutputStream());
        dataToClient.writeBytes(message + "\n");
        dataToClient.flush();
    }    
    
    /**
     * Stops the client thread
     */
    private void stopThread()
    {
    	TCPServer.removeClient(this);
    	clientDisconnected = true;
    }
}