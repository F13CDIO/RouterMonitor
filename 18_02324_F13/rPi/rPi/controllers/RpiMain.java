
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

		MainController mc = new MainController();
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
