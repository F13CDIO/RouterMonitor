package rPi.controllers;

import java.io.BufferedReader;

public class DataHandler {
	
	TerminalExecutor tc = new TerminalExecutor();
<<<<<<< HEAD
	
	// the following command requires tshark opened in sudoers file or the java program executed with su rights
	// the script saves the tshark process id in process1.pid for later implementation of multiple tshark processes
	String startScript = "bash startScript.sh";
	//the command executed in startScript is "/usr/local/bin/tshark -T fields -e ip.src -e ip.dst -e http.host -e http.user_agent -i en0 -I -R http.request tcp port 80 and ip &";
=======
	// The command run in the script is "ngrep -q '^(GET|POST|HEAD|CONNECT)' 'tcp'"
	// the following command requires ngrep opened in sudoers file or the java program executed with su rights
	String command = "/Applications/Wireshark.app/Contents/Resources/bin/tshark -T fields -e ip.src -e ip.dst -e http.host -e http.user_agent -i en1 -I tcp port 80 and ip";
>>>>>>> 4654b643d91065ef535d5a98f6c1f46550f048a3
	public BufferedReader br = null;
	
	public BufferedReader startSniffing(){
		br = tc.exec(startScript);
		return br;  
	}
	
	public void stopSniffing(){
		// stopping the process is easy to do in one line, so here it goes with some bash-fu (linux/osx compatible)
		tc.exec("kill $(cat process1.pid)");
	}
	
}
