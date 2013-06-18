
package rPi.controllers;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This is the main method of the rPi project
 * 
 * @author Niclas
 *
 */

public class RpiMain {
	public static void main(String[] args) throws Exception {
<<<<<<< HEAD
		
		MainController mc = new MainController();
=======

		String server_ip;
		// Take server ip in as argument when program is run in terminal
		if (args.length > 1){
			server_ip = args[0];
		} else {
			System.out.println("Please enter the server ip as it wasn't specified as arg to the program");
			Scanner scan = new Scanner(System.in);
			server_ip = scan.next();
		}
		System.out.println("ip entered : " + server_ip);
		
		MainController mc = new MainController(server_ip);
>>>>>>> 8a847b5fa12853aa953ad8764818a4223c9d6425
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
