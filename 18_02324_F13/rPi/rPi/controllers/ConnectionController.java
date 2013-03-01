package rPi.controllers;

public class ConnectionController {

	//This class will contain methods for pining, connection to the internet.
	//an other sutiable methods 
	
	private static int PING_TIMEOUT = 10; // timeout in seconds
	private static String SERVER_IP = ""; //Hard code IP
	
	MainController mc = new MainController();
	
	public void connectToNetwork(String SSID)
	{
		//Sprøger lasse om hvordan man køre script... 
		
	}
	private String scanLocalNetworks()
	{
		return "";
	}
	
	private boolean pingServer(String SERVER_IP)
	{
		mc.exec("ping " + SERVER_IP);
		return true;
		
		
//		try {
//			streamParser.parsePing(br.readLine());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}	
	}
	
}
