package rPi.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

	private String parseInputStream()
	{
		try {
			return streamParser.parseString(br);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	private String get_IP()
	{
		return "";
	}
	
}
