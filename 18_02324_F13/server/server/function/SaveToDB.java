package server.function;

import java.sql.SQLException;

import server.data.DataPackage;
import server.data.mySQLConnector.MySQLConnector;
import server.data.mySqlDataAccessObjects.DataPackageDAO;
import server.data.IData;
//import server.data.mySqlConnect;

public class SaveToDB implements Runnable
{
	private IData data;
	private boolean connected = false;
	private DataPackage dataPackage = null;
	private DataPackageDAO dataPackageDAO;
	
	
	
	
	public SaveToDB()
	{
		data = Function.getDatalayer();
		dataPackageDAO = new DataPackageDAO();
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			try
			{
				Thread.sleep(1); 
			}
			catch (InterruptedException e)
			{
				System.out.println(e.getMessage());
			}
			
			if(!data.isEmpty())
			{
				if(!connected)
				{
					try 
					{
						MySQLConnector.connect();
						connected = true;
					} 
					
					catch (InstantiationException e) {e.printStackTrace();} 
					catch (IllegalAccessException e) {e.printStackTrace();} 
					catch (ClassNotFoundException e) {e.printStackTrace();}
					catch (SQLException e) {e.printStackTrace();}
					
				}
				
				try
				{
					dataPackage= data.getDataPackage();
					dataPackageDAO.addDataPackage(dataPackage);
					//mySQL.addDataPackage(dataPackage.getInIP(), dataPackage.getOutIP(), dataPackage.getHost(), dataPackage.getSubHost(), dataPackage.getUserAgent(), dataPackage.getTimeStamp());
					System.out.println(dataPackage.toString());
				}
				
				catch(Exception e)
				{
					System.out.println(e.getStackTrace());
					System.out.println(e.getMessage());
					System.out.println(dataPackageDAO);
					System.out.println(dataPackage);
				}
			}
			else if(connected)
			{
				MySQLConnector.closeConnection();
				connected = false;
			}
		}
	}
}