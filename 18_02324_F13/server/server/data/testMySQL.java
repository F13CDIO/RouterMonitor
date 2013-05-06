package server.data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import server.data.Data.DataPackage;
import server.data.mySQLConnector.MySQLConnector;
import server.data.mySqlDataObjects.DataPackageDAO;

public class testMySQL 
{

	public static void main(String[] args) 
	{
//		mySqlConnect mySql = new mySqlConnect();
//		mySql.connect();
//		
//		
Calendar cal = Calendar.getInstance();	
cal.set(2013, 03, 29, 14, 34, 23);
//		 
//		
//		JSONArray test = mySql.getTop10(cal.getTime());
//		
//				
//		//System.out.println(test.get("2013-04-17 14:15:36.0"));
//		System.out.println(test);
//		
//		cal.set(2013, 03, 17, 14, 15, 29);
//		//System.out.println(test.get(cal.getTime().toString()));
//		
//		
//		mySql.closeConnection();
		
		DataPackageDAO dat = new DataPackageDAO();
		
		
		IData data = new Data();
		data.addDataset(cal.getTime(), "src", "dst", "host", "sub", "user");
		DataPackage dp = data.getDataPackage();
		try 
		{
			MySQLConnector.connect();
			//JSONObject test = dat.get10SecondTraffic(cal.getTime(), "");
			dat.addDataPackage(dp);
			//System.out.println(test);
		} 
		
		catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) 
		{
			System.out.println(e.getMessage());
		}
	}
		
		
		


}
