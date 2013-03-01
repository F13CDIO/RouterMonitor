package rPi.connectors;

import java.io.BufferedReader;

public interface IConnector {
	BufferedReader initTCPClient(String message);
	void listenForTcp(short port);
	void initUDPServer(BufferedReader output);
}
