package server.function;

import server.data.Data.DataPackage;
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
			try
			{
				Thread.sleep(1); //sleep for at lade køen kunne følge med
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!data.isEmpty())
			{
				//System.out.println("test");
				if(!connected)
				{
					//System.out.println("not connected");
					mySQL.connect();
					connected = true;
				}
				DataPackage dataPackage = data.getDataPackage();
				mySQL.addDataPackage(dataPackage.getInIP(), dataPackage.getOutIP(), dataPackage.getHost(), dataPackage.getSubHost(), dataPackage.getUserAgent());
				System.out.println(dataPackage.toString());
			}
			else if(connected)
			{
				mySQL.closeConnection();
				connected = false;
			}
		}
	}
	
}
