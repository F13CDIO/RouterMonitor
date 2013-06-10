package server.boundary;

import java.io.IOException;
import server.function.IFunction;

public class Boundary
{
	private static IFunction function;
		
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
			TCPServer.startServer(9000);
		} 
			
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}
	} 
}