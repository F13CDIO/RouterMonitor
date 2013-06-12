package server.test;

import java.util.Scanner;
import server.boundary.TCPclientCommandBean;


public class GUIsim extends Thread
{
	Scanner scanner = new Scanner(System.in);
	boolean test = true;
	TCPclientCommandBean bean = new TCPclientCommandBean();
	
	public void run()
	{
		sleep();
		System.out.println("GUI!!!!!!!");
		while(test)
		{
			if(bean.getClients().length > 0)
			{
				sleep();
				for(String client : bean.getClients())
				{
					System.out.println("Client: " + client);
					
					System.err.println("START");
					bean.startUDPSocket(client);
					sleep();
					System.err.println("STOP");
					bean.stopUDPSocket(client);
					sleep();
					System.err.println("Scan networks");
					bean.scanNetwirks(client);
					sleep();
					System.err.println("Set channel");
					bean.setChannel(client, 5);
					sleep();
					System.err.println("Get wifi status");
					bean.getWifiStatus(client);
					
					System.out.println("Slut");
					
					
					
					
					
					test = false;
				}
				
				
			}
		}
	}
	
	private void sleep()
	{
		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
