package UI;

import java.io.IOException;

import Funktion.IFunction;

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
		System.out.println("TCP startet");
		try 
		{
			tcpServer = new TCPServer();
			
			
			try 
			{
				tcpServer.start();
			} 
			
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				tcpServer.Close();
			}
		} 
		
		catch (IOException e1) 
		
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	}
	
	
}
