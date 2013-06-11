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
			if(client.readLine().equals("start"))
			{
				System.out.println("start");
				System.out.println(client.readLine());
				client.sendLine("\0");
			}
			else if(client.readLine().equals("stop"))
			{
				System.out.println("stop");
				client.sendLine("\0");
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
