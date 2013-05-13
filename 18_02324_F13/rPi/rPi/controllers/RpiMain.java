
package rPi.controllers;

public class RpiMain {
	public static void main(String[] args) throws Exception {

		MenuHandler mh = new MenuHandler();
		while (true){
			mh.handleCommand();	
		}
	}
}
