package rPi.connectors;

import java.io.BufferedReader;
import java.net.InetAddress;

public interface IConnector {
	BufferedReader initTCPClient(String message);
	void initUDPServer(int portToSendFrom, int destinationPort, InetAddress destinationIP);
	void sendUDP(BufferedReader bf);
}
