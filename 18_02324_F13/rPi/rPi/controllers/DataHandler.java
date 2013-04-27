package rPi.controllers;

import java.io.BufferedReader;

public class DataHandler {
	
	TerminalExecutor tc = new TerminalExecutor();
	
	// the following command requires tshark opened in sudoers file or the java program executed with su rights
	// the script saves the tshark process id in process1.pid for later implementation of multiple tshark processes
	String startScript = "bash startScript.sh";
	//the command executed in startScript is "/usr/local/bin/tshark -T fields -e ip.src -e ip.dst -e http.host -e http.user_agent -i en0 -I -R http.request tcp port 80 and ip &";
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
