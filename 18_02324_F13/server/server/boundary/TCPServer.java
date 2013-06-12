/**
 * TCP server with support for multiple clients. 
 */

package server.boundary;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class TCPServer
{
	
	private static ServerSocket serverSocket;
	private static int serverPort;
	private static HashMap<String, ConnectedTCPClient> clients;	
	
	/**
	 * Get all client MAC addresses (identifier)
	 * @return String[]
	 */
	public static String[] getClients()
	{
		return clients.keySet().toArray(new String[0]);
	}
	
	/**
	 * Add client to list of clients
	 * @param ConnectedTCPClient object
	 */
	public static void addClient(ConnectedTCPClient client)
	{
		clients.put(client.getMac(), client);
	}
	
	/**
	 * Remove a specific client from list of clients
	 * @param ConnectedTCPClient object
	 */
	public static void removeClient(ConnectedTCPClient client) 
	{
		clients.remove(client.getMac());
	}
	
	/**
	 * Get client object from specific MAC address
	 * @param String mac
	 * @return ConnectedTCPClient client
	 */
	private static ConnectedTCPClient getClient(String mac)
	{
		return clients.get(mac);
	}
	
	/**
	 * Writes command via TCP connection to client.
	 * 
	 * @param String mac
	 * @param String command
	 * @param int channelNumber
	 * @return
	 */
	public static String doClientCommand(String mac, String command, int channelNumber)
	{
		System.out.println("Command: " + command);
		String data = "";
		
		ConnectedTCPClient client = getClient(mac);
		client.interrupt();
		
		try 
		{			
			client.write(command);
			
			if (command.equals("setChannel"))
			{
				client.write("" + channelNumber);
			}
		
			else if(command.equals("start"))
			{
				client.write(""+client.getUDPport());
			}
			
			data = client.readData();
		} 
		
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
		if(!data.equals(""))
		System.out.println("data returned: \n" + data);
		
		return data;
	}
	
	/**
	 * Initializes and starts server. Keeps listening (looping) for new clients. For each client connect, a client object is created to deal with the 
	 * specific traffic between server and client.  
	 * @param int serverPortNum (Set server TCP port number)
	 * @throws IOException
	 */
    public static void startServer(int serverPortNum) throws IOException
    {
    	serverPort = serverPortNum;
		clients = new HashMap<String, ConnectedTCPClient>();
        serverSocket = new ServerSocket(serverPort);
        
        System.out.println("TCP server online on ip: " + Inet4Address.getLocalHost().getHostAddress());
 
        while(true) 
        {
        	try
			{
				Thread.sleep(1);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            Socket socket = serverSocket.accept(); 
            if (socket != null)
            {
               ConnectedTCPClient client = new ConnectedTCPClient(socket);               
               System.out.println("Client at "+ client.getIpAddress() +":" + client.getPort()+" connected ");
               client.start();
            }
        }
    }
    
    /**
     * Checks if mac already exists in clientlist
     * @param mac 
     * @return True if mac exists, false if not
     */
    public static boolean hasClient(String mac)
    {
    	return clients.containsKey(mac);
    }
}