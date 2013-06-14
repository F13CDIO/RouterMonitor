package server.test;

public class cmdTest
{
	public static void main(String args[])
	{
		new cmdTest().test();
	}
	
	private void test()
	{
		try
		{
			testTCPClient client = new testTCPClient();
			client.sendLine("mac");
			client.sendLine("1234");
			
			while(true)
			{
				String command = ""; 
				command = client.readLine();
				
				if(command.equals("start"))
				{
					System.out.println("start");
					System.out.println(client.readLine());
				}
				
				else if(command.equals("stop"))
				{
					System.out.println("stop");
					
				}
				
				else if(command.equals("getWifiStatus"))
				{
					System.out.println("Get wifi status");
					client.sendLine("Dette er wifi status\nailfdnaskdfhbaskudjfb\nasilnslkdjn");
					
				}
				
				else if(command.equals("setChannel"))
				{
					System.out.println("Set channel");
					
				}
				
				else if(command.equals("scanNetworks"))
				{
					System.out.println("Scan networks");
					client.sendLine("Networks retur\nlinje 2");
					
				}
			}
			
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
