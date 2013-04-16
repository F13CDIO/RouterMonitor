package rPi.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class TerminalExecutor {
	private static int BUFFER_SIZE = 10; // Buffer size in bytes
	Process thisProcess = null;
	StreamParser streamParser = new StreamParser();
	BufferedReader br = null;
	OutputStream out = null;

	// This method executes a command in the terminal and takes either the command as argument or a path to a shellscript
	public BufferedReader exec(String pathToScriptOrTheCommand)
	{
		
		try {
			thisProcess = Runtime.getRuntime().exec(pathToScriptOrTheCommand);
			br = convertStdoutToBufferedReader();
		} catch (IOException e) {
			System.out.println("Could not execute command");
			e.printStackTrace();
		} 
		return br;
	}
	
	// This method is called from exec and "listens" to the terminal output
	private BufferedReader convertStdoutToBufferedReader()
	{
		out = thisProcess.getOutputStream();
		return new BufferedReader(new InputStreamReader(thisProcess.getInputStream()), BUFFER_SIZE);
	}
}
