package rPi.controllers;

import java.io.BufferedReader;

import java.io.IOException;


public class StreamParser 
{
	String PORT_NUMBER;
	String trigger = "T";
	byte NUMBER_OF_LINES = 2;


	// Method for parsing the command from server currently "start\nPORT_NUMBER" to start sending network traffic over udp and "stop" to stop
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

	// port number getter
	public String getPort(){
		return (PORT_NUMBER != null) ? PORT_NUMBER : "" ;
	}


	public String parseNetworkData(String str)
	{
		return str;
	}

	//	public String parsePing(String str) 
	//	{
	//		return str;
	//	}

}
