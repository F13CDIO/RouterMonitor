package server.function;

import server.data.IData;
import server.data.mySqlConnect;

public class SaveToDB implements Runnable
{
	private IData data;
	private mySqlConnect mySQL;
	private boolean connected = false;
	
	public SaveToDB()
	{
		data = Function.getDatalayer();
		mySQL = new mySqlConnect();
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			//System.out.println(data.isEmpty());
			if(!data.isEmpty())
			{
				System.out.println("isempty");
				if(!connected)
				{
					System.out.println("not connected");
					mySQL.connect();
					connected = true;
				}
				mySQL.addDataPackage(data.getDataPackage().getInIP(), data.getDataPackage().getOutIP(), data.getDataPackage().getHost(), data.getDataPackage().getSubHost(), data.getDataPackage().getUserAgent());
				System.out.println(data.getDataPackage().toString());
			}
			else if(connected)
			{
				mySQL.closeConnection();
				connected = false;
			}
		}
	}
	
}
