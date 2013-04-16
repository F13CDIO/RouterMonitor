package rPi.controllers;

import java.io.BufferedReader;

public class DataHandler {
	
	TerminalExecutor tc = new TerminalExecutor();
	// The command run in the script is "ngrep -q '^(GET|POST|HEAD|CONNECT)' 'tcp'"
	// the following command requires ngrep opened in sudoers file or the java program executed with su rights
	String command = "/opt/local/bin/ngrep -q &";
	public BufferedReader br = null;
	
	public BufferedReader startSniffing(){
		System.out.println("starting to sniff real bad");
		br = tc.exec(command);
		return br;  
	}
	
	public void stopSniffing(){
		// TODO
	}
	
}
