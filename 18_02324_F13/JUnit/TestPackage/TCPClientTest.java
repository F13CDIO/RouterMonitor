package TCPTest;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import server.boundary.ConnectedTCPClient;
import server.boundary.TCPServer;

/*
 * Tests interactions between TCPServer.java and connectedTCPClient.java.
 * This is done by initializing a TCPServer object which initializes it's own connectedTCPClient object.
 * It is tested if TCPServer receives and saves connectedTCPClient object in it's list of connectedTCPClient based on the mac address the client sends.  
 */
public class TCPClientTest {



	public static String macAddress = "This is MacAddress"; 
	private static String newMacAddress = "New Mac address";
	private static String incorrectMac = "incorrectMac";

	public TCPServer tcpServer = new TCPServer();
	public Socket acceptSocket;
	public static DataOutputStream outToServer;
	public static Socket clientSocket;
	public static ServerSocket serverSocket;
	public static ConnectedTCPClient client;
	public static Runnable r;
	public static int serverNum = 9000;

	/*
	 * Setup Thread that has TCPServer object.
	 * Client and server has to run in seperate threads.
	 * This method is only run once before all tests run.
	 */
	@BeforeClass
	public static void init()
	{
		// anonymous inner class in separate thread for setting up TCPServer
		r = new Runnable()
		{
			@Override
			public void run() 
			{
				try {
					TCPServer.startServer(9000);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}		
		};
		new Thread(r).start();

		//Setting up client part of test. 
		try {
			//Sleep is used to ensure server in separate thread is ready
			Thread.sleep(300);	 
			clientSocket = new Socket("127.0.0.1", 9000);
			outToServer = new DataOutputStream(clientSocket.getOutputStream());
			//Server saves a client in it's list of connectedTCPClient objects.
			outToServer.writeBytes("Mac\n" + macAddress + "\n");	
			outToServer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	/*
	 * Tests if TCPServer has saved a client with the correct 
	 * mac address in it's hashmap of clients 
	 */
	@Test
	public void testSavedClient()
	{
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertTrue(TCPServer.hasClient(macAddress));
	}

	/*
	 * Tests that TCPServer has a client in it's Client array with the mac address macAddress 
	 */
	@Test
	public void testCorrectMac() 
	{
		assertEquals(TCPServer.getClients()[0], macAddress);
	}

	/*
	 * Tests that the same place in the array of clients is not saved with the incorrectMac respond positive to all requests.  
	 */
	@Test
	public void testIncorrectMac() 
	{
		assertFalse(TCPServer.getClients()[0].equals(incorrectMac));

	}

	/*
	 * Tests that a client is not saved if the first line is not mac.
	 */
	@Test
	public void testInvalidCommand()
	{
		try 
		{
			outToServer.writeBytes("Invalid command\n" + newMacAddress + "\n");
			outToServer.flush();
			assertFalse(TCPServer.getClients()[0].equals(newMacAddress));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	/*
	 * Cleans up after test environment setup by closing the ramainin socket. 
	 * This method is only run once after all tests have been run.
	 */
	@AfterClass
	public static void closeTest()
	{
		try {
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
