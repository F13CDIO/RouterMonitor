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
	
	public static void addClient(ConnectedTCPClient client)
	{
		clients.put(client.getMac(), client);
	}
	
	public static void removeClient(ConnectedTCPClient client) 
	{
		clients.remove(client.getMac());
	}
	
//	// test comm with pi
//	write("scanNetworks\n");
//	System.out.println("Server: Scan networks");
//	while (dataFromClient.read() > 0)
//	{
//		System.out.println("Pi: " + dataFromClient.readLine());
//	}
//	
//	Thread.sleep(1000);
//	
//	write("getWifiStatus");
//	System.out.println("Server: Get wifi status");
//	while (dataFromClient.read() > 0)
//	{
//		System.out.println("Pi: " + dataFromClient.readLine());
//	}
//	Thread.sleep(1000);
//	                    		
//	write("setChannel");
//	write("5");
//	System.out.println("Server: Set channel 5");
//	
//	while (dataFromClient.read() > 0)
//		System.out.println("Pi: " + dataFromClient.readLine());
//	
//	write("getWifiStatus");
//	System.out.println("Command to rPi: Get wifi status");
//	while (dataFromClient.read() > 0)
//	{
//		System.out.println("Pi: " + dataFromClient.readLine());
//	}
//	// End test
	
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
				client.write("" + channelNumber);
			
			data = client.read();
		} 
		
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}
		
		System.out.println("data returned: " + data);
		return data;
	}
	
	private static ConnectedTCPClient getClient(String mac)
	{
		return clients.get(mac);
	}
	
    public static void startServer(int serverPortNum) throws IOException
    {
    	serverPort = serverPortNum;
		clients = new HashMap<String, ConnectedTCPClient>();
        serverSocket = new ServerSocket(serverPort);
        
        System.out.println("TCP server online on ip: " + Inet4Address.getLocalHost().getHostAddress());
 
        while(true) // Keep listening for new clients
        {
            Socket socket = serverSocket.accept(); // Create new client if new connection is established
            if (socket != null)
            {
               ConnectedTCPClient client = new ConnectedTCPClient(socket);               
               System.out.println("Client at "+ client.getIpAddress() +":" + client.getPort()+" connected ");
               client.start(); // Thread start
            }
        }
    }

	public static void testLortet() 
	{
		doClientCommand("1234", "scanNetworks\n", -1);
	}
}