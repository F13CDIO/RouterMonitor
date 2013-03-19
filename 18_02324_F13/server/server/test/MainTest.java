package server.test;

public class MainTest {

	/**
	 * @param args
	 */
	
	static String portstr;
	static testTCPClient testServr;
	public static void main(String[] args) 
	{
		try 
		{
			// TCP
			testServr = new testTCPClient();
			testServr.readLine();
			testServr.sendLine("Create\n");
			testServr.readLine();
			portstr = testServr.readLine();
			testServr.sendLine("Start\n");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		testUDPClient testUDP;
		
		try 
		{
			testUDP = new testUDPClient();
			testUDP.setPort(portstr);
			int timer =0;
			while(true)
			{
				timer++;
				testUDP.sendData();
				
				if(timer > 20000)
				{
					testServr.sendLine("test\n");
					timer = 0;
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
