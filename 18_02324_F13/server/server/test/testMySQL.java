package server.test;
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
			
			System.out.println("10 seconds");
			JSONObject jo = dataPackageDAO.get10SecondTraffic(cal.getTime(), "");
			System.out.println("JSON: " + jo);
			
			System.out.println("10 minutes");
			JSONObject jo5 = dataPackageDAO.get10Minute(cal.getTime(), "");
			System.out.println("JSON: " + jo5);
			
			System.out.println();
			System.out.println("1 hour");
			JSONObject jo2 = dataPackageDAO.get1HourTraffic(cal.getTime(), "");
			System.out.println("JSON: " + jo2);
			
			System.out.println();
			System.out.println("1 day");
			JSONObject jo3 = dataPackageDAO.get1DayTraffic(cal.getTime(), "");
			System.out.println("JSON: " + jo3);
			
			System.out.println();
			System.out.println("1 month");
			JSONObject jo4 = dataPackageDAO.get1MonthTraffic(cal.getTime(), "");
			System.out.println("JSON: " + jo4);
			
			
		} 
		
		catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) 
		{
			System.out.println(e.getMessage());
		}
	}
		
		
		


}
