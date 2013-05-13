package rPi.controllers;

public class Main {
	public static void main(String[] args) throws Exception {

		MenuHandler mh = new MenuHandler();
		while (true){
			mh.handleCommand();	
		}
	}
}
