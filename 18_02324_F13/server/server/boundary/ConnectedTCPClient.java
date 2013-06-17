package server.boundary;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import server.function.DisconnectCheck;

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
    private boolean UDPactive = false;
    private DisconnectCheck disconnectControl;

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
        disconnectControl = new DisconnectCheck(socket, this);
    }
    
    /**
     * The identifyer of the client is it's macaddress
     * @return Returns the identifyer of the client
     */
    public String getMac()
    {
    	return macAddress;
    }
     
    public void setUDPactive(boolean active)
    {
    	UDPactive = active;
    }
    
    public boolean isUDPactive()
    {
    	return UDPactive;
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
    	disconnectControl.start();
			while(!clientDisconnected)
			{
				if (!GUIInterrupt)
				{
					readCommand();
				}
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
    	
    	while(!dataFromClient.ready() && timeout < 300)
    	{
    		timeout++;
    		try
			{
				Thread.sleep(50);
			}
			catch (InterruptedException e)
			{
				System.err.println("Sleeping exception");
			}
    	}
    	while(dataFromClient.ready())
		{
			data += readLine() + "\n";
		}
		
    	disconnectControl.setGUIinterrupt(false);
		GUIInterrupt = false;
		return data;
    }
    
    private String readLine()
    {
    	try
    	{
    		char temp;
	    	if((temp = disconnectControl.getDisconnectCorrection()) > 0)
			{
				return temp + dataFromClient.readLine();
			}
			else
			{
				return dataFromClient.readLine();
			}
    	}
    	catch(IOException e)
    	{
    		System.err.println(e.getMessage());
    		return "ERROR";
    	}
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
				clientCommand = readLine();
	            System.out.println("rPi command: " + clientCommand);
	            switch(clientCommand.toLowerCase())
	            {            	
	            	case "mac":
	            		macAddress = readLine();
	            		if(!TCPServer.hasClient(macAddress))
	            		{
	                		System.out.println("rPi Connected: " + macAddress);
	            			linkedUDPServer = new UDPServer();
	            			System.out.println("Server: Start on UDP port " + linkedUDPServer.getPort());
	            			TCPServer.addClient(this);
	            			linkedUDPServer.start();
	            		}
	            		else
	            		{
	            			System.out.println("rPi ignored: " + macAddress);
	            		}
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
    		disconnectControl.setGUIinterrupt(true);
    	}
    	catch(Exception e) 
        {
    		close();
        }
    }
    
    public void close()
    {
    	
    	{	
    		try
			{
	    		System.out.println(this.ipAddress + ": " + this.port + " disconnected");
	    		clientDisconnected = true;
				socket.close();
	    		stopThread();
	            System.out.println("MAC REMOVED FROM GUI");
	        	if (linkedUDPServer != null)
	        	{
	        		// Stop linked UDP server thread
	        		linkedUDPServer.stopThread();
	        	}
	        	TCPServer.removeClient(this);
        	}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
     * Gets the UDP port
     * @return The UDP port
     */
    public int getUDPport()
    {
    	return linkedUDPServer.getPort();
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