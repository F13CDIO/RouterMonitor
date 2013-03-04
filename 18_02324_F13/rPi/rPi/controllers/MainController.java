package rPi.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainController {
	
	
	private static int BUFFER_SIZE = 10; // Buffer size in bytes
	Process thisProcess = null;
	BufferedReader br = null;
	StreamParser streamParser = new StreamParser();

	public void exec(String pathToScriptOrTheCommand)
	{
		try {
			thisProcess = Runtime.getRuntime().exec(pathToScriptOrTheCommand);
			convertStdoutToBufferedReader();
		} catch (IOException e) {
			System.out.println("Could not execute command");
			e.printStackTrace();
		}
	}
	// This method is called from exec and "listens" to the terminal output
	private BufferedReader convertStdoutToBufferedReader()
	{
		br = new BufferedReader(
					new InputStreamReader(thisProcess.getInputStream() ), BUFFER_SIZE);
		return br;
	}

//	private String parseInputStream()
//	{
//
//		try {
//			return streamParser.parseString(br);
//		} catch (IOException e) {
//			e.printStackTrace();
//			return "";
//		}
//	}
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
