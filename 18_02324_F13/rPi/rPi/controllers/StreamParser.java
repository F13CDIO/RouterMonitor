package rPi.controllers;

import java.io.BufferedReader;



public class StreamParser {
	
	String trigger = "T";
	
	public String parseString(String str)
	{
		
		String lines = "";
		
		while(str != null)
		{
			if(str.startsWith(trigger))
			{
				for (int i = 0; i < 2; i++) 
				{
					lines = str+lines+"\n";
				}
			}
		}
		
		return str;
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
