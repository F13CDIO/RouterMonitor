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
			client.sendLine("create");
			System.out.println(client.readLine());
			System.out.println(client.readLine());
			client.sendLine("start");
			System.out.println(client.readLine());
			System.out.println(client.readLine());
			client.sendLine("data");
			client.sendLine("lsidfsd,jbflasdfn\nalsiunasldfnasldifn");
			client.sendLine("\0");
			client.sendLine("1");
			System.out.println("1" + client.readLine());
			client.sendLine("strt");
			System.out.println("2" + client.readLine());
			client.sendLine("st");
			System.out.println("3" + client.readLine());
			client.sendLine("strt");
			System.out.println("4" + client.readLine());
			client.sendLine("tart");
			System.out.println("5" + client.readLine());
			client.sendLine("srt");
			System.out.println("6" + client.readLine());
			client.sendLine("stat");
			System.out.println("7" +client.readLine());
			
			
			while(true)
			{
				//System.out.println("...");
			}
			
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
