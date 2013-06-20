package server.function;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import server.data.DataPackage;
import server.data.mySqlDataAccessObjects.DataPackageDAO;
import server.data.IData;

public class SaveToDB extends Thread
{
	private IData data;
	private DataPackage dataPackage = null;
	private DataPackageDAO dataPackageDAO;	
	private long startTimeout;
	private long stopTimeout;
	private List<DataPackage> dataPackets;
	
	//private Queue<DataPackage> dataPackets = new LinkedList<DataPackage>();
	
	
		
	
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
			dataPackets = new ArrayList<DataPackage>();
			
			while(data.isEmpty())
			{
				try{ Thread.sleep(1); }
				catch (InterruptedException e) { System.out.println(e.getMessage()); }
			}
			
			try 
			{
				dataPackageDAO.openConnection();
				System.out.println("mySQL: Start adding packets.");
			} 
			
			catch (SQLException e1) { System.out.println(e1.getMessage()); 	}
			

			startTimeout = System.currentTimeMillis();
			stopTimeout = System.currentTimeMillis();
			
			
			while(!data.isEmpty())
			{
				startTimeout = System.currentTimeMillis();
				stopTimeout = System.currentTimeMillis();
				
				long timeOut = stopTimeout - startTimeout;
				
				while((timeOut < 3000) && !data.isEmpty())
				{
					dataPackage = data.getDataPackage();
					dataPackets.add(dataPackage);
					stopTimeout = System.currentTimeMillis();
					dataPackage = null;
				}
				

				try
				{	
					dataPackageDAO.addMultipleDataPackets(dataPackets);
				}
				
				catch(Exception e) { System.out.println(e.getMessage()); }	
			}

			dataPackets = null;
			System.out.println("mySQL: Stop adding packets.");
			dataPackageDAO.closeConnection();
		}
	}
}