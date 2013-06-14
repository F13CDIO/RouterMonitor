/**
 * Boundary layer of 3-tier model.
 */
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
	
	/**
	 * Get functionality layer object 
	 * @return Function object
	 */
	public static IFunction getInstanceOfFunction()
	{
		return function;
	}

	/**
	 * Start server
	 */
	public void startServer() 
	{
		try 
		{		
			TomcatServer.start();
			TCPServer.startServer(9000);		
		} 
			
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}
	} 
}