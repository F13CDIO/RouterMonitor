package rPi.controllers;

import java.io.BufferedReader;
import java.io.IOException;



public class StreamParser 
{

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
	public String parseNetworkData(String str)
	{
		


		return str;
	}

	//	public String parsePing(String str) 
	//	{
	//		return str;
	//	}

}
