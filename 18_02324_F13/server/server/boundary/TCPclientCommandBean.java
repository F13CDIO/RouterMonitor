package server.boundary;

public class TCPclientCommandBean
{
	private int channelNumber = -1;
	
	public String getWifiStatus(String mac)
	{
		return TCPServer.doClientCommand(mac, "getWifiStatus", channelNumber);
	}
	
	public String setChannel(String mac, int channelNumber)
	{
		return TCPServer.doClientCommand(mac, "setChannel", channelNumber);
	}
	
	public String scanNetwirks(String mac)
	{
		return TCPServer.doClientCommand(mac, "scanNetworks", channelNumber);
	}
	
	public void stopUDPSocket(String mac)
	{
		TCPServer.doClientCommand(mac, "stop", channelNumber);
	}
	
	public void startUDPSocket(String mac)
	{
		TCPServer.doClientCommand(mac, "start", channelNumber);
	}
	
	public String[] getClients()
	{
		return TCPServer.getClients();
	}
}
