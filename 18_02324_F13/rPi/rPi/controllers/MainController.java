package rPi.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainController {
	
	
	@SuppressWarnings("unused")
	private String get_IP()
	{
		String IP = "";
		try 
		{
			IP = InetAddress.getLocalHost().toString();
		} 
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		char backslash = '/';
		String nyIP = "";
		for(int i = 0; i <IP.length()-1;i++)
		{
			char temp = IP.charAt(i);
			if(temp == backslash)
			{
				nyIP = IP.substring(i+1);
				break;
			}
		}
		
		return nyIP;
		
	}
	
}
