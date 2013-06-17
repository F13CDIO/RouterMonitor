
package rPi.controllers;

import java.io.IOException;
import java.util.InputMismatchException;

/**
 * This is the main method of the rPi project
 * 
 * @author Niclas
 *
 */

public class RpiMain {
	public static void main(String[] args) throws Exception {

		// Take server ip in as argument when program is run in terminal
		String server_ip = 	args[0];
		System.out.println("ip entered : " + server_ip);
		
		MainController mc = new MainController(server_ip);
		mc.connectToServer();
		while (true){
			try {			
				mc.handleCommand();	
			} catch (IOException e) {
				e.printStackTrace();
				mc.connectToServer(); // reconnect if we had an IOException
			} catch (InputMismatchException e) {
				e.printStackTrace();
			}
		}
	}
}
