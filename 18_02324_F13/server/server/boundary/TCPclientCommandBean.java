package server.boundary;

public class TCPclientCommandBean
{
	private String[] command = {"getWifiStatus", "setChannel", "scanNetworks"};
	private int channelNumber = -1;
	
	public String getWikiStatus(String mac)
	{
		return TCPServer.doClientCommand(mac, command[0], channelNumber);
	}
	
	public String setChannel(String mac, int channelNumber)
	{
		return TCPServer.doClientCommand(mac, command[1], channelNumber);
	}
	
	public String scanNetwirks(String mac)
	{
		return TCPServer.doClientCommand(mac, command[2], channelNumber);
	}
}
