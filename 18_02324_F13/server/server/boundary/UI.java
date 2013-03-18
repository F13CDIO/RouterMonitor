package server.boundary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import server.boundary.TCPServer.Client;
import server.function.IFunction;



public class UI
{
	private static IFunction function;
	private TCPServer tcpServer;
		
	public UI()
	{
		
	}
	
	public UI(IFunction function)
	{
		this.function = function;
	}
	
	public static IFunction getFunctionInstance()
	{
		return function;
	}

	public void initialize() 
	{
			try 
			{
				tcpServer = new TCPServer(9000);		
				tcpServer.go();
			} 
			
			catch (IOException e) 
			{
				System.out.println(e.getMessage());
			}
		} 
}