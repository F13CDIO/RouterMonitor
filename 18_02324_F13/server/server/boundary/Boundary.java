package server.boundary;

import java.io.IOException;
import server.function.IFunction;

public class Boundary
{
	private static IFunction function;
	private TCPServer tcpServer;
		
	public Boundary(){}
	
	public Boundary(IFunction function)
	{
		Boundary.function = function;
	}
	
	public static IFunction getInstanceOfFunction()
	{
		return function;
	}

	public void startServer() 
	{
		try 
		{
			tcpServer = new TCPServer(9000);		
			tcpServer.startServer();
		} 
			
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}
	} 
}