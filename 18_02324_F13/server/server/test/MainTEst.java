package server.test;

public class MainTEst {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		testTCPClient testServr;
		try 
		{
			// TCP
			testServr = new testTCPClient();
			testServr.readLine();
			testServr.sendLine("Create\n");
			testServr.readLine();
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
			testUDP.sendData();
		} 
		
		catch (Exception e) 
		
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
