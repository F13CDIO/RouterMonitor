package server.boundary;

/**
 * Boundary to the GUI
 * @author Group 18
 *
 */
public class TCPclientCommandBean
{
	private int channelNumber = -1;
	
	/**
	 * Asks a client for ist wifi status
	 * @param mac The identifyer for the client
	 * @return A string containing alle the data the client gives when requested for wifi status
	 */
	public String getWifiStatus(String mac)
	{
		return TCPServer.doClientCommand(mac, "getWifiStatus", channelNumber);
	}
	
	/**
	 * Changes the channel of the specified client
	 * @param mac The identifyer for the client
	 * @param channelNumber The Channelnumber to change to
	 */
	public void setChannel(String mac, int channelNumber)
	{
		TCPServer.doClientCommand(mac, "setChannel", channelNumber);
	}
	
	/**
	 * Scans for wireless networks at the clients position
	 * @param mac The identifyer for the client
	 * @return The networks at the client
	 */
	public String scanNetworks(String mac)
	{
		return TCPServer.doClientCommand(mac, "scanNetworks", channelNumber);
	}
	
	public void iterateOverChannels(String mac)
	{
		TCPServer.doClientCommand(mac, "iterate", channelNumber);
	}
	
	/**
	 * Stops the sending of UDP data from the client
	 * @param mac The identifyer for the client
	 */
	public void stopUDPSocket(String mac)
	{
		TCPServer.doClientCommand(mac, "stop", channelNumber);
	}
	
	/**
	 * Starts the sending of UDP dat from the client
	 * @param mac The identifyer for the client
	 */
	public void startUDPSocket(String mac)
	{
		TCPServer.doClientCommand(mac, "start", channelNumber);
	}
	
	public boolean isUDPactive(String mac)
	{
		return TCPServer.isUDPactive(mac);
	}
	
	/**
	 * Gets a list of connected clients, by theire mac's
	 * @return A lisf of mac's for alle the connected clients
	 */
	public String[] getClients()
	{
		return TCPServer.getClients();
	}
}
