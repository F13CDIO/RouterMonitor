package rPi.controllers;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import rPi.controllers.MainController.SupportedOS;


public class ChannelHopping extends Thread {
	private String SSID;
	private SupportedOS currentOS;
	TerminalExecutor tc;
	
	
	public ChannelHopping(String ssid, SupportedOS currentOS) {
		this.SSID = ssid;
		this.currentOS = currentOS;
		tc = new TerminalExecutor();
	}
	/**
	 * Starts the iteration code in either linux og OS X
	 */
	@Override
	public void run() {
		if(currentOS == SupportedOS.Mac)
			iteratingMac(SSID);
		else if(currentOS == SupportedOS.Linux)
			iteratingLinux(SSID);
		
	}
	
	private void iteratingLinux(String ssid){
		ArrayList<Integer> channels = getRelevantChannelsLinux(ssid);
		tc.exec("bash ./monitor.sh");
		int i = 0;
		while(true){
			tc.exec("iw dev mon0 set channel "+channels.get(i));
			System.out.println("current channel: "+channels.get(i));
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
			if(i >= channels.size())
				i = 0;
		}
	}
	
	private void iteratingMac(String ssid){
		//TODO iterate over channels mac
	}
	/**
	 * This method scans the wireless networks and returns the channels we want to monitor
	 * @param ssid(Name of AP)
	 * @return list of channels it supports
	 */
	private ArrayList<Integer> getRelevantChannelsLinux(String ssid){
		//the iwlist command scans all the AP's detectable and prints their info
		BufferedReader br = tc.exec("iwlist wlan0 scan");
		ArrayList<String> lineList = new ArrayList<String>();
		ArrayList<Integer> channels = new ArrayList<Integer>();
		String line = null;
		int listIndex = 0;
		try {
			//Saves all the lines in a list
			while((line = br.readLine())!= null){
				lineList.add(line);
				System.out.println(line);
				//If the AP we are searching for it will extract its channel
				if(line.contains("\""+ssid+"\"")){
					//4 lines before contains the channel
					String channelLine = lineList.get(listIndex-4);
					//Strips all the whitespace -> format: "channel:x"
					channelLine = channelLine.replaceAll("\\s", "");
					Integer channel = Integer.parseInt(channelLine.split(":")[1]);
					if(!channels.contains(channel)){
						channels.add(channel);
					}
				}
				listIndex++;
			}
		} catch (IOException e) {
			System.out.println("Error reading command output");
			e.printStackTrace();
		}
		return channels;
	}
	
	private ArrayList<Integer> getRelevantChannelsMac(String ssid){
		ArrayList<Integer> channels = new ArrayList<Integer>();
		//TODO implement extraction of channels mac
		return channels;
	}

}
