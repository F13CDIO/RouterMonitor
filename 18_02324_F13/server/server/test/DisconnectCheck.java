package server.test;

import java.io.IOException;
import java.net.Socket;

import server.boundary.ConnectedTCPClient;

public class DisconnectCheck extends Thread
{
	private Socket socket;
	private ConnectedTCPClient client;
	private boolean connected, GUIinterrupt, returned;
	private int disconnectCorrection;
	
	public DisconnectCheck(Socket socket, ConnectedTCPClient client)
	{
		this.socket = socket;
		this.client = client;
		connected = true;
		GUIinterrupt = false;
		returned = false;
	}
	
	@Override
	public void run()
	{
		while(connected)
		{
			if(!GUIinterrupt)
			{
				try
				{
					Thread.sleep(5000);
					if((disconnectCorrection = socket.getInputStream().read()) < 0)
					{
						connected = false;
						client.close();
					}
					else
					{
						System.out.println("still connected");
					}
					System.out.println(disconnectCorrection);
				}
				catch (IOException e)
				{
					System.err.println("ERROR IN DISCONNECTCHECK");
					client.close();
					connected = false;
					e.getMessage();
				}
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally
				{
					returned = false;
				}
			}
		}
	}
	
	public char getDisconnectCorrection()
	{
		char correction;
		if(returned == false && disconnectCorrection > 0)
		{
			correction = (char)disconnectCorrection;
		}
		else
		{
			correction = 0;
		}
		returned = true;
		System.out.println("correction: " + correction);
		return correction;
	}

	public void setGUIinterrupt(boolean interrupted)
	{
		GUIinterrupt = interrupted;
	}
}
