package rPi.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * This class consists only of the terminal executor method exec() which takes 
 * a string or script as parameter and returns the buffered reader with
 * the output from the process from stdout
 * 
 * @author Niclas
 */

public class TerminalExecutor {
	private static int BUFFER_SIZE = 10; // Buffer size in bytes
	Process thisProcess = null;
	BufferedReader br = null;
	OutputStream out = null;

	/**
	 * This method executes a command in the terminal and takes either the command as argument or a path to a shellscript
	 * 
	 * @param The string to be executed or the path to the script
	 * @throws TerminalExecutionException if the execution failed
	 * @throws ReadFromStdOutException if the piping from stdout to java console failed
	 * @return BufferedReader with the output from the executed command/script
	 */
	public BufferedReader exec(String pathToScriptOrTheCommand)
	{
		assert(pathToScriptOrTheCommand.length() > 0);
		
		try {
			System.out.println("execute " + pathToScriptOrTheCommand);
			thisProcess = Runtime.getRuntime().exec(pathToScriptOrTheCommand);
		} catch (IOException e) {
			System.out.println("could not execute script or command: " + pathToScriptOrTheCommand);
			e.printStackTrace();
		}
		try 
		{
			br = new BufferedReader(new InputStreamReader(thisProcess.getInputStream()), BUFFER_SIZE);
		} catch (Exception e)
		{
			System.out.println("Could not read from stdout");
		}
		return br;
		
	}
	
	/*
	class TerminalExecutionException extends IOException {
		// declare  fields that will be passed on to handlers
		private String script;
		
		public TerminalExecutionException(String script){
			super("Script execution failed");
			this.script = script;
		}
		// override toString
		public String toString(){
			return "The script : \"" + script + "\" could not be executed";
		}
	}*/
}
