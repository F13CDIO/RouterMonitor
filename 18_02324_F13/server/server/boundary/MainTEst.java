package server.boundary;

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
//			testServr = new testTCPClient();
//			Thread.sleep(2000);
//			testServr.sendLine("Create");
//			Thread.sleep(2000);
//			testServr.sendLine("Start");
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
