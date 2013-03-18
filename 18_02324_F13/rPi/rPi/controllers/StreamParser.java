package rPi.controllers;

import java.io.BufferedReader;
import java.io.IOException;


public class StreamParser 
{
	String PORT_NUMBER;
	String trigger = "T";
	byte NUMBER_OF_LINES = 2;

	public String parseString(BufferedReader br)
	{
		String lines = "";
		String line;
		try 
		{
			line = br.readLine();
			while(line != null)
			{
				if(line.startsWith(trigger))
				{
					for (int i = 0; i < NUMBER_OF_LINES; i++) 
					{
						lines = line+lines+"\n";
					}
				}
			}


		} catch (IOException e) 
		{
			e.printStackTrace();
		} 

		return lines;

	}

	// Method for parsing the command from server currently "start\nPORT_NUMBER" to start sending network traffic over udp and "stop" to stop
	public String parseTCPCommand(BufferedReader br) throws Exception
	{
		String command;
		command = br.readLine();
		if(command.contains("Start"))
		{
			PORT_NUMBER = command.substring(command.indexOf("\\n"));
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

	// This method assumes that, parseTCPCommand had already been called.
	public String praseTCP_PORT()
	{
		if(PORT_NUMBER == null)
		{
			return "No port";
		}
		else
		{
			return PORT_NUMBER;
		}
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
