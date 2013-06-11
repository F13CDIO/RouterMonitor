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
					System.out.println(client);
					System.out.println("før start");
					bean.startUDPSocket(client);
					System.out.println("efter start");
					bean.stopUDPSocket(client);
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
