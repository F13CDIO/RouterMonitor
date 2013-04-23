package rPi.controllers;

import java.io.BufferedReader;

public class DataHandler {
	
	TerminalExecutor tc = new TerminalExecutor();
	// The command run in the script is "ngrep -q '^(GET|POST|HEAD|CONNECT)' 'tcp'"
	// the following command requires ngrep opened in sudoers file or the java program executed with su rights
	String command = "/Applications/Wireshark.app/Contents/Resources/bin/tshark -T fields -e ip.src -e ip.dst -e http.host -e http.user_agent -i en1 -I tcp port 80 and ip";
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
