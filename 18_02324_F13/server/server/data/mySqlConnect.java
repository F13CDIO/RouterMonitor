package server.data;

import java.sql.*;
import java.util.Calendar;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class mySqlConnect 
{
	private String sqlURL = "jdbc:mysql://bjqrn88.cloudns.org:3306/";
	private String sqlDbName = "grp18";
	private String sqlUser = "grp18"; 
	private String sqlPassword = "123123";
	
	private Connection sqlConnection = null;
	private PreparedStatement sqlStatement = null;
	private boolean connected = false;
	
	final int second = 1;
	final int minute = 2;
	final int hour = 3;
	final int day = 4;
	final int week = 5;
	final int month = 6;
	
	public JSONArray getTop10()
	{
		ResultSet mySqlOutput = executeQuery("SELECT host, timestamp, COUNT(*) FROM dataPackages GROUP BY host ORDER BY COUNT(*) DESC LIMIT 10;");
		return parseResultsetToJSONArray("top10", mySqlOutput);
	}
	
	public JSONArray get10SecondTraffic(Date date, String host)
	{
		JSONArray counts = new JSONArray();
		counts = getData(date, second, host);
		return counts;
	}
	
	public JSONArray get1MinuteTraffic(Date date, String host)
	{
		JSONArray counts = new JSONArray();
		counts = getData(date, minute, host);
		return counts;
	}
	
	public JSONArray get1HourTraffic(Date date, String host)
	{
		JSONArray counts = new JSONArray();
		counts = getData(date, hour, host);
		return counts;
	}
	
	public JSONArray get1DayTraffic(Date date, String host)
	{
		JSONArray counts = new JSONArray();
		counts = getData(date, day, host);
		return counts;
	}
	
	public JSONArray get1WeekTraffic(Date date, String host)
	{
		JSONArray counts = new JSONArray();
		counts = getData(date, week, host);
		return counts;
	}
	
	public JSONArray get1MonthTraffic(Date date, String host)
	{
		JSONArray counts = new JSONArray();
		counts = getData(date, month, host);
		return counts;
	}
	
	public JSONArray getData(Date date, int choice, String host) 
	{	
		JSONArray counts = new JSONArray();
		int numIterate = 0;
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date);
		cal1.set(Calendar.MILLISECOND,0);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date);
		cal2.set(Calendar.MILLISECOND,0);
		
		
		if (!host.equals(""))
			host = "host = '" +host+ "' AND ";
	
	
	
		numIterate = setIterate(choice);

		for (int i = 0; i < numIterate; i++) 
		{
			cal2 = incrementCalendar(choice, cal2);
			
			java.sql.Timestamp mySqlTimestamp1 = new java.sql.Timestamp(cal1.getTime().getTime()); // Get time from Date instance)
			java.sql.Timestamp mySqlTimestamp2 = new java.sql.Timestamp(cal2.getTime().getTime()); 
			
			System.out.println("SELECT timestamp, COUNT(timestamp) AS count FROM dataPackages WHERE "+host+" timestamp >= '" + mySqlTimestamp1 + "' AND timestamp < '" + mySqlTimestamp2 + "'");
			ResultSet mySqlOutput = executeQuery("SELECT timestamp, COUNT(timestamp) AS count FROM dataPackages WHERE "+host+" timestamp >= '" + mySqlTimestamp1 + "' AND timestamp < '" + mySqlTimestamp2 + "'");
			
			try 
			{
				while (mySqlOutput.next())
				{
					int countValue = mySqlOutput.getInt("count");
					JSONObject count = new JSONObject();
					count.put("count", countValue);
					count.put("timestamp", mySqlTimestamp1);
					counts.add(count);
					System.out.println(countValue);
				}
			} 
			
			catch (SQLException e1){System.out.println(e1.getMessage());}
			cal1 = incrementCalendar(choice, cal1);
		}
		return counts;
	}
	
	
	private int setIterate(int choice) 
	{
		int numIterate = 0;
		
		if (choice == second)
			numIterate = 10;
		
		else if (choice == minute)
			numIterate = 60;
		
		else if (choice == hour)
			numIterate = 60;
		
		else if (choice == day)
			numIterate = 24;
		
		else if (choice == week)
			numIterate = 7;
		
		else if (choice == month)
			numIterate = 31;
		
		return numIterate;
	}

	public void addDataPackage(String sourceIP, String destinationIP, String host, String subHost, String userAgent, Date timestamp)
	{		
		java.sql.Timestamp mySqlTimestamp = new java.sql.Timestamp(timestamp.getTime());
		
		try 
		{
			sqlStatement = sqlConnection.prepareStatement("INSERT INTO dataPackages VALUES (?, ?, ?, ?, ? , ?, ?)");
			sqlStatement.setString(1, "0");
			sqlStatement.setString(2, sourceIP);
			sqlStatement.setString(3, destinationIP);
			sqlStatement.setString(4, host);
			sqlStatement.setString(5, subHost);
			sqlStatement.setString(6, userAgent);
			sqlStatement.setTimestamp(7, mySqlTimestamp);
			sqlStatement.executeUpdate();
		} 
		catch (SQLException e) { System.out.println(e.getMessage()); }
	}
	
	private ResultSet executeQuery(String query) 
	{
		ResultSet output = null;
		
		if (connected)
		{
			try 
			{
				sqlStatement = sqlConnection.prepareStatement(query);
				output = sqlStatement.executeQuery();
			} 
			catch (SQLException e) { System.out.println(e.getMessage()); }
		}
		return output;
	}
	
	private Calendar incrementCalendar(int choice, Calendar cal1)
	{
		Calendar cal = Calendar.getInstance();
		cal = cal1;
		
		if (choice == second)
			cal1.add(Calendar.SECOND, 1);

		else if (choice == minute)
			cal1.add(Calendar.SECOND, 1);
			
		else if (choice == hour)
			cal1.add(Calendar.MINUTE, 1);
			
		else if (choice == day)
			cal1.add(Calendar.HOUR, 1);
				
		else if (choice == week)
			cal1.add(Calendar.HOUR, 24);
			
		else if (choice == month)
			cal1.add(Calendar.HOUR, 24);
		
		return cal;
	}
	
	public void connect()
	{
		try 
		{
			sqlConnection = DriverManager.getConnection(sqlURL + sqlDbName, sqlUser, sqlPassword);
			System.out.println("Connected to mySql database");
			connected =  true;
		} 
		
		catch (SQLException e1) 
		{
			System.out.println(e1.getMessage());
			connected =  false;
		}
	}
	
	public void closeConnection()
	{
		try 
		{
			if (sqlConnection != null)
			{
				if (!sqlConnection.isClosed())
				{
					sqlConnection.close();
					System.out.println("Disconnected from mySql database");
					connected = false;
				}
			}
		} 
		catch (Exception e) { System.out.println(e.getMessage()); }
	}
	
	private JSONArray parseResultsetToJSONArray(String function, ResultSet mySqlOutput)
	{
		JSONArray jsonObjects = new JSONArray();
		int count = 1;
		
		try 
		{
			while (mySqlOutput.next())
			{				
				switch(function)
				{
					case "top10":
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("host", mySqlOutput.getString("host"));
						jsonObject.put("rank", count);
						jsonObjects.add(jsonObject);
						count++;
						break;
					
					default:
						break;
				}
			}
		} 
		
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
		}
		return jsonObjects;
	}

}