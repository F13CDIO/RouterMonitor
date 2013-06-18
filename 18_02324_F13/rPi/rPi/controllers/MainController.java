package rPi.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.InputMismatchException;

public class MainController {
	
	private final SupportedOS currentOS;
	private final String server_ip;
	
	TerminalExecutor tc = new TerminalExecutor();
	public BufferedReader br = null;
	BufferedReader inputFromServer = null;
	ConnectionController cc;
	
	public MainController(String server_ip) throws Exception {
		this.server_ip = server_ip;
		this.currentOS = checkOS();
		cc = new ConnectionController();		
	}
	
	public void connectToServer() throws IOException {
		inputFromServer = cc.connectToServer(server_ip);
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
		System.out.println("your OS:"+ OSstring);
		if (OSstring.indexOf("win") >= 0){
			OS = SupportedOS.Windows;
		} else if (OSstring.indexOf("mac") >= 0){
			OS = SupportedOS.Mac;
		} else if (OSstring.indexOf("inux") >= 0){
			System.out.println("DU HAR LIIIIINUUUUUX");
			OS = SupportedOS.Linux;
		}
		return OS;
	}
	
	/**
	 *  Supported commands from server as enumerated type
	 */
	public enum SupportedCommands {
		nop, start, stop, scanNetworks, setChannel, getWifiStatus, getMacAddress, iterate; //TODO iterate skal implementeres paa serveren
	}
	
	/**
	 *  Get the command from C&C server
	 * @throws IOException 
	 * @throws Exception
	 */
	public void handleCommand() throws IOException
	{
		MenuHandler mh = new MenuHandler(currentOS, cc, inputFromServer);
		
		SupportedCommands thisCommand = SupportedCommands.nop;
		String commandStringFromServer = inputFromServer.readLine();
		System.out.println("command :" + commandStringFromServer);
			
		// check if command is valid
		for (SupportedCommands c : SupportedCommands.values()){
			System.out.println("command recieved : " + commandStringFromServer);
			if (c.name().equals(commandStringFromServer)){
				thisCommand = SupportedCommands.valueOf(commandStringFromServer); 
			} else {
				System.out.println(commandStringFromServer);
				//throw new InputMismatchException("command recieved : " + commandStringFromServer);
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
