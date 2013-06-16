package rPi.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.InputMismatchException;

public class MainController {
	
	private final SupportedOS currentOS;
	
	TerminalExecutor tc = new TerminalExecutor();
	public BufferedReader br = null;
	BufferedReader inputFromServer = null;
	ConnectionController cc;
	
	public MainController() throws Exception {
		this.currentOS = checkOS();
		cc = new ConnectionController();		
	}
	
	public void connectToServer() throws IOException {
		inputFromServer = cc.connectToServer();
	}
	
	/**
	 *  Supported OS'es. Type-safety wins.
	 */
	public enum SupportedOS {
		Windows, Mac, Linux
	}
	
	/**
	 *  We need to invoke different terminal programs for different OS'es, so this
	 *  method checks current OS.
	 * @return Current OS of enum type 'supportedOS'
	 */
	private SupportedOS checkOS(){
		SupportedOS OS = null;
		// Get current OS
		final String OSstring = System.getProperty("os.name").toLowerCase(); // Name of OS
		if (OSstring.indexOf("win") >= 0){
			OS = SupportedOS.Windows;
		} else if (OSstring.indexOf("mac") >= 0){
			OS = SupportedOS.Mac;
		} else if (OSstring.indexOf("nix") >= 0){
			OS = SupportedOS.Linux;
		}
		return OS;
	}
	
	/**
	 *  Supported commands from server as enumerated type
	 */
	public enum SupportedCommands {
		nop, start, stop, scanNetworks, setChannel, getWifiStatus, getMacAddress, iterate;//TODO iterate skal implementeres paa serveren
	}
	
	/**
	 *  Get the command from C&C server
	 * @throws IOException 
	 * @throws Exception
	 */
	public void handleCommand() throws IOException
	{
		MenuHandler mh = new MenuHandler(currentOS);
		
		SupportedCommands thisCommand = SupportedCommands.nop;
		String commandStringFromServer = inputFromServer.readLine();
		System.out.println("command :" + commandStringFromServer);
			
		// check if command is valid
		for (SupportedCommands c : SupportedCommands.values()){
			if (c.name().equals(commandStringFromServer)){
				thisCommand = SupportedCommands.valueOf(commandStringFromServer); 
			} else {
				throw new InputMismatchException("command recieved : " + commandStringFromServer);
			}
		}
		
		try {
			// call the menu handler
			mh.switchMenu(thisCommand);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	} // end handleCommand()
	
} // end class 
