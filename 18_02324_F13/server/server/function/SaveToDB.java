package server.function;

import server.data.Data.DataPackage;
import server.data.IData;
import server.data.mySqlConnect;

public class SaveToDB implements Runnable
{
	private IData data;
	private mySqlConnect mySQL;
	private boolean connected = false;
	private DataPackage dataPackage = null;
	
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
				if(!connected)
				{
					mySQL.connect();
					connected = true;
				}
				try
				{
				dataPackage= data.getDataPackage();
				mySQL.addDataPackage(dataPackage.getInIP(), dataPackage.getOutIP(), dataPackage.getHost(), dataPackage.getSubHost(), dataPackage.getUserAgent(), dataPackage.getTimeStamp());
				System.out.println(dataPackage.toString());
				}
				catch(Exception e)
				{
					System.out.println(e.getStackTrace());
					System.out.println(e.getMessage());
					System.out.println(mySQL);
					System.out.println(dataPackage);
				}
			}
			else if(connected)
			{
				mySQL.closeConnection();
				connected = false;
			}
		}
	}
}