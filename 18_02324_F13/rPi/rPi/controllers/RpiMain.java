
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

		MenuHandler mh = new MenuHandler();
		mh.connectToServer();
		while (true){
			try {			
				mh.handleCommand();	
			} catch (IOException e) {
				e.printStackTrace();
				mh.connectToServer(); // reconnect if we had an IOException
			} catch (InputMismatchException e) {
				e.printStackTrace();
			}
		}
	}
}
