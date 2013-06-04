package rPi.controllers;

import java.io.BufferedReader;

import java.io.IOException;

/**
 * This class reads the server input from tcp (out-of-band connection)
 * and 'listens' for the trigger
 * 
 * @author Niclas
 *
 */

public class StreamParser 
{
	String PORT_NUMBER;
	String trigger = "T";
	byte NUMBER_OF_LINES = 2;


	/**
	 *  Method for parsing the command from server currently "start\nPORT_NUMBER" to start sending network traffic over udp and "stop" to stop
	 * @param br The BufferedReader with commandstream from server
	 * @return the command from server
	 * @throws Exception
	 */		
	public String parseTCPCommand(BufferedReader br) throws Exception
	{
		String command;
		command = br.readLine();
		System.out.println(command);
		if(command.contains("start"))
		{
			
			PORT_NUMBER = br.readLine();
			System.out.println("port nr " + PORT_NUMBER);
			
			return "start";
		}
		else if (command.contains("stop"))
		{
			return "stop";
		}
		else 
		{
			return "Wrong message";
		}

	}

	/**
	 * This method returns the class variable PORT_NUMBER if not <code>null</code> else an empty string
	 * 
	 * @return the port number as saved in the class from previous method
	 */
	public String getPort(){
		return (PORT_NUMBER != null) ? PORT_NUMBER : "" ;
	}
}
