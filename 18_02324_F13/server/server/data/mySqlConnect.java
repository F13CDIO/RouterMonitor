package server.data;

import java.sql.*;
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
		ResultSet mySqlOutput = executeQuery("SELECT host, COUNT(*) as count FROM dataPackages GROUP BY host ORDER BY count DESC LIMIT 10");
		return parseResultsetToJSONArray(mySqlOutput);
	}
	
	public JSONArray getTop10(Date dateFrom)
	{
		java.sql.Timestamp mySqlFrom = new java.sql.Timestamp(dateFrom.getTime());		
		ResultSet mySqlOutput = executeQuery("SELECT host, COUNT(*) as count FROM dataPackages WHERE timestamp > '" + mySqlFrom + "'GROUP BY host ORDER BY count DESC LIMIT 10");
		return parseResultsetToJSONArray(mySqlOutput);
	}
		

	public JSONObject get10SecondTraffic(Date date, String host)
	{
		if(!host.equals(""))
			host = "and host = '"+host+"'";
		
		java.sql.Timestamp mySqlTimestampTo = new java.sql.Timestamp(date.getTime());
		ResultSet mySqlOutput = executeQuery("SELECT timestamp, COUNT(*) AS count FROM dataPackages WHERE timestamp BETWEEN ('"+mySqlTimestampTo+"' - INTERVAL 10 SECOND) and '"+mySqlTimestampTo+"' "+host+" GROUP BY UNIX_TIMESTAMP(timestamp) DIV 1 LIMIT 10");
		return parseResultsetToJSONObject("traffic", mySqlOutput);
	}
	
	public JSONObject get1MinuteTraffic(Date date, String host)
	{
		if(!host.equals(""))
			host = "and host = '"+host+"'";
		
		java.sql.Timestamp mySqlTimestampTo = new java.sql.Timestamp(date.getTime());
		ResultSet mySqlOutput = executeQuery("SELECT timestamp, COUNT(*) AS count FROM dataPackages WHERE timestamp BETWEEN ('"+mySqlTimestampTo+"' - INTERVAL 1 MINUTE) and '"+mySqlTimestampTo+"' "+host+" GROUP BY UNIX_TIMESTAMP(timestamp) DIV 1 LIMIT 60");
		return parseResultsetToJSONObject("traffic", mySqlOutput);
	}
	
	public JSONObject get10MinuteTraffic(Date date, String host)
	{
		if(!host.equals(""))
			host = "and host = '"+host+"'";
		
		java.sql.Timestamp mySqlTimestampTo = new java.sql.Timestamp(date.getTime());
		ResultSet mySqlOutput = executeQuery("SELECT timestamp, COUNT(*) AS count FROM dataPackages WHERE timestamp BETWEEN ('"+mySqlTimestampTo+"' - INTERVAL 10 MINUTE) and '"+mySqlTimestampTo+"' "+host+" GROUP BY UNIX_TIMESTAMP(timestamp) DIV 60 LIMIT 10");
		return parseResultsetToJSONObject("traffic", mySqlOutput);
	}
	
	public JSONObject get1HourTraffic(Date date, String host)
	{
		if(!host.equals(""))
			host = "and host = '"+host+"'";
		
		java.sql.Timestamp mySqlTimestampTo = new java.sql.Timestamp(date.getTime());
		ResultSet mySqlOutput = executeQuery("SELECT FROM_UNIXTIME(ROUND(UNIX_TIMESTAMP(timestamp)/(60))*(60)) as timestamp, COUNT(*) AS count FROM dataPackages WHERE timestamp BETWEEN ('"+mySqlTimestampTo+"' - INTERVAL 1 HOUR) and '"+mySqlTimestampTo+"' "+host+" GROUP BY UNIX_TIMESTAMP(timestamp) DIV 60 LIMIT 60");
		return parseResultsetToJSONObject("traffic", mySqlOutput);
	}
	
	public JSONObject get1DayTraffic(Date date, String host)
	{
		if(!host.equals(""))
			host = "and host = '"+host+"'";
		
		java.sql.Timestamp mySqlTimestampTo = new java.sql.Timestamp(date.getTime());
		ResultSet mySqlOutput = executeQuery("SELECT FROM_UNIXTIME(ROUND(UNIX_TIMESTAMP(timestamp)/(3600))*(3600)) as timestamp, COUNT(*) AS count FROM dataPackages WHERE timestamp BETWEEN ('"+mySqlTimestampTo+"' - INTERVAL 1 DAY) and '"+mySqlTimestampTo+"' "+host+" GROUP BY UNIX_TIMESTAMP(timestamp) DIV 3600 LIMIT 24");
		return parseResultsetToJSONObject("traffic", mySqlOutput);
	}
	
	public JSONObject get1MonthTraffic(Date date, String host)
	{
		if(!host.equals(""))
			host = "and host = '"+host+"'";
		
		java.sql.Timestamp mySqlTimestampTo = new java.sql.Timestamp(date.getTime());
		ResultSet mySqlOutput = executeQuery("SELECT FROM_UNIXTIME(ROUND(UNIX_TIMESTAMP(timestamp)/(86400))*(86400)) as timestamp, COUNT(*) AS count FROM dataPackages WHERE timestamp BETWEEN ('"+mySqlTimestampTo+"' - INTERVAL 1 MONTH) and '"+mySqlTimestampTo+"' "+host+" GROUP BY UNIX_TIMESTAMP(timestamp) DIV 86400 LIMIT 31");
		return parseResultsetToJSONObject("traffic", mySqlOutput);
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
			sqlStatement.setString(5, "");//skal fjernes?
			sqlStatement.setString(6, userAgent);
			sqlStatement.setTimestamp(7, mySqlTimestamp);
			sqlStatement.executeUpdate();
			
			System.out.println(sqlStatement.toString());
		} 
		catch (SQLException e) { System.out.println(e.getMessage()); }
	}
	

	private JSONArray parseResultsetToJSONArray(ResultSet mySqlOutput) 
	{
		JSONArray hosts = new JSONArray();
		
		try 
		{
			while (mySqlOutput.next())
			{			
				JSONObject jo = new JSONObject();
				jo.put("rank", mySqlOutput.getRow());
				jo.put("count", mySqlOutput.getInt("count"));
				jo.put("host", mySqlOutput.getString("host"));
				hosts.add(jo);
			}
		} 
		
		catch (SQLException e) 
		
		{
			System.out.println(e.getMessage());
		}
		
		return hosts;
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject parseResultsetToJSONObject(String function, ResultSet mySqlOutput)
	{
		JSONObject jsonObject = new JSONObject();
		
		try 
		{
			while (mySqlOutput.next())
			{			
				switch(function)
				{
					case "top10":
						jsonObject.put(mySqlOutput.getInt("rank"), mySqlOutput.getString("host"));
						break;
						
					case "traffic":
						jsonObject.put(mySqlOutput.getString("timestamp"),mySqlOutput.getInt("count"));
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
		return jsonObject;
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
}