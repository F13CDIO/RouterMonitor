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
	
	public static String[] getClients()
	{
		return clients.keySet().toArray(new String[0]);
	}
	
	public static void addClient(ConnectedTCPClient client)
	{
		clients.put(client.getMac(), client);
	}
	
	public static void removeClient(ConnectedTCPClient client) 
	{
		clients.remove(client.getMac());
	}
	
	private static ConnectedTCPClient getClient(String mac)
	{
		return clients.get(mac);
	}
	
	public static String doClientCommand(String mac, String command, int channelNumber)
	{
		System.out.println("doCommand");
		String data = "";
		
		ConnectedTCPClient client = getClient(mac);
		
		System.out.println(client.getMac());
		try 
		{
			client.write(command);
			
			if (channelNumber != -1)
			{
				client.write("" + channelNumber);
			}
			data = client.readData();
		} 
		
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
		
		System.out.println("data returned: " + data);
		return data;
	}
	
    public static void startServer(int serverPortNum) throws IOException
    {
    	serverPort = serverPortNum;
		clients = new HashMap<String, ConnectedTCPClient>();
        serverSocket = new ServerSocket(serverPort);
        
        System.out.println("TCP server online on ip: " + Inet4Address.getLocalHost().getHostAddress());
 
        while(true) 
        {
            Socket socket = serverSocket.accept(); 
            if (socket != null)
            {
               ConnectedTCPClient client = new ConnectedTCPClient(socket);               
               System.out.println("Client at "+ client.getIpAddress() +":" + client.getPort()+" connected ");
               client.start(); 
            }
        }
    }
}