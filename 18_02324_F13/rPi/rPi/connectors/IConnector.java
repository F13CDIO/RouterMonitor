package rPi.connectors;

import java.io.BufferedReader;
import java.net.InetAddress;

public interface IConnector {
	BufferedReader initTCPClient(InetAddress serverAddress, short serverPort);
	void sendTCP(String message);
	void initUDP(int portToSendFrom, int destinationPort, InetAddress destinationIP);
	void sendUDP(BufferedReader bf);
}
