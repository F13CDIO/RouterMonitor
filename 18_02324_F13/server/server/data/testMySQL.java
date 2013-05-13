package server.data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import server.data.Data.DataPackage;
import server.data.mySQLConnector.MySQLConnector;
import server.data.mySqlDataAccessObjects.DataPackageDAO;

public class testMySQL 
{

	public static void main(String[] args) 
	{
		// Calendar
		Calendar cal = Calendar.getInstance();	
		cal.set(2013, 04, 06, 16, 34, 23);

//		Add packet
//		Data data = new Data();
//		data.addDataset(cal.getTime(), "src", "dst", "host", "sub", "user");
//		DataPackage dp = data.getDataPackage();
		
		// Packet DAO
		DataPackageDAO dataPackageDAO = new DataPackageDAO();
		
		
		// Test
		try 
		{
			MySQLConnector.connect();
			//JSONObject jo = dataPackageDAO.get1MonthTraffic(cal.getTime(), "");
			
			//JSONObject jo = dataPackageDAO.get1HourTraffic(cal.getTime(), "");
			//JSONObject jo = dataPackageDAO.get1DayTraffic(cal.getTime(), "");
			JSONObject jo = dataPackageDAO.get1MonthTraffic(cal.getTime(), "");
			System.out.println("\nJSON: " + jo);
		} 
		
		catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) 
		{
			System.out.println(e.getMessage());
		}
	}
		
		
		


}
