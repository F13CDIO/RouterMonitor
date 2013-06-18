package server.function;

import java.sql.SQLException;
import server.data.DataPackage;
import server.data.mySqlDataAccessObjects.DataPackageDAO;
import server.data.IData;

public class SaveToDB implements Runnable
{
	private IData data;
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
			while(data.isEmpty())
			{
				try
				{
					Thread.sleep(5); 
				}
				
				catch (InterruptedException e) { System.out.println(e.getMessage()); }
			}
			
			try 
			{
				dataPackageDAO.openConnection();
				System.out.println("mySQL: Start adding packets.");
			} 
			
			catch (SQLException e1) { System.out.println(e1.getMessage()); 	}
			
			while(!data.isEmpty())
			{
				try
				{
					dataPackage= data.getDataPackage();
					dataPackageDAO.addDataPackage(dataPackage);
				}
				
				catch(Exception e) { e.getMessage(); }
			}
			
			System.out.println("mySQL: Stop adding packets.");
			dataPackageDAO.closeConnection();
		}
	}
}