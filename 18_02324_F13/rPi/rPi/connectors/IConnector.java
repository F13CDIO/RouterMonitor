package rPi.connectors;

import java.io.BufferedReader;
import java.net.InetAddress;

/**
 * The 'Connector.Java' conforms to this interface which gives a quick overview of our boundary methods which is called from ConnectionController
 * 
 * @author Niclas
 *
 */

public interface IConnector {
	BufferedReader initTCPClient(InetAddress serverAddress, short serverPort);
	void sendTCP(String message);
	void initUDP(int portToSendFrom, int destinationPort, InetAddress destinationIP);
	void sendUDP(BufferedReader bf);
	void stopUDP();
}
