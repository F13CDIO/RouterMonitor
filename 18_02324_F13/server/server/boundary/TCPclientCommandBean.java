package server.boundary;

import java.util.Scanner;

public class TCPclientCommandBean
{
	Scanner scanner = new Scanner(System.in);

	public void test()
	{
		System.out.println("venter p� test");
		scanner.nextLine();
		
		TCPServer.testLortet();
	}
	
}
