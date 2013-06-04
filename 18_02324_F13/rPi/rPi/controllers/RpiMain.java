
package rPi.controllers;

/**
 * This is the main method of the rPi project
 * 
 * @author Niclas
 *
 */

public class RpiMain {
	public static void main(String[] args) throws Exception {

		MenuHandler mh = new MenuHandler();
		while (true){
			mh.handleCommand();	
		}
	}
}
