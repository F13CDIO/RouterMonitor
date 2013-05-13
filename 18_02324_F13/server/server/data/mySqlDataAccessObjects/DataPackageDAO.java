package server.data.mySqlDataAccessObjects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import server.data.Data;
import server.data.Data.DataPackage;
import server.data.IData;
import server.data.mySQLConnector.MySQLConnector;
import server.data.mySQLInterfaces.IDataPackageDAO;

public class DataPackageDAO implements IDataPackageDAO
{

	@Override
	public JSONArray getTop10() throws SQLException 
	{
		ResultSet mySqlOutput = MySQLConnector.execQuery("SELECT host, COUNT(*) as count FROM dataPackages GROUP BY host ORDER BY count DESC LIMIT 10");
		return parseResultsetToJSONArray(mySqlOutput);
	}

	@Override
	public JSONArray getTop10(Date dateFrom) throws SQLException 
	{
		java.sql.Timestamp mySqlFrom = new java.sql.Timestamp(dateFrom.getTime());		
		ResultSet mySqlOutput = MySQLConnector.execQuery("SELECT host, COUNT(*) as count FROM dataPackages WHERE timestamp > '" + mySqlFrom + "'GROUP BY host ORDER BY count DESC LIMIT 10");
		return parseResultsetToJSONArray(mySqlOutput);
	}

	@Override
	public JSONObject get10SecondTraffic(Date date, String host) throws SQLException 
	{
		if(!host.equals(""))
			host = "and host = '"+host+"'";
		
		java.sql.Timestamp mySqlTimestampTo = new java.sql.Timestamp(date.getTime());
		ResultSet mySqlOutput = MySQLConnector.execQuery("SELECT timestamp, COUNT(*) AS count FROM dataPackages WHERE timestamp BETWEEN ('"+mySqlTimestampTo+"' - INTERVAL 10 SECOND) and '"+mySqlTimestampTo+"' "+host+" GROUP BY UNIX_TIMESTAMP(timestamp) DIV 1 LIMIT 10");
		return parseResultsetToJSONObject("traffic", mySqlOutput);
	}

	@Override
	public JSONObject get1HourTraffic(Date date, String host) throws SQLException 
	{
		if(!host.equals(""))
			host = "and host = '"+host+"'";
		
		java.sql.Timestamp mySqlTimestampTo = new java.sql.Timestamp(date.getTime());
		ResultSet mySqlOutput = MySQLConnector.execQuery("SELECT FROM_UNIXTIME(ROUND(UNIX_TIMESTAMP(timestamp)/(60))*(60)) as timestamp, COUNT(*) AS count FROM dataPackages WHERE timestamp BETWEEN ('"+mySqlTimestampTo+"' - INTERVAL 1 HOUR) and '"+mySqlTimestampTo+"' "+host+" GROUP BY UNIX_TIMESTAMP(timestamp) DIV 60 LIMIT 60");
		return parseResultsetToJSONObject("traffic", mySqlOutput);
	}

	@Override
	public JSONObject get1DayTraffic(Date date, String host) throws SQLException 
	{
		if(!host.equals(""))
			host = "and host = '"+host+"'";
		
		java.sql.Timestamp mySqlTimestampTo = new java.sql.Timestamp(date.getTime());
		ResultSet mySqlOutput = MySQLConnector.execQuery("SELECT FROM_UNIXTIME(ROUND(UNIX_TIMESTAMP(timestamp)/(3600))*(3600)) as timestamp, COUNT(*) AS count FROM dataPackages WHERE timestamp BETWEEN ('"+mySqlTimestampTo+"' - INTERVAL 1 DAY) and '"+mySqlTimestampTo+"' "+host+" GROUP BY UNIX_TIMESTAMP(timestamp) DIV 3600 LIMIT 24");
		return parseResultsetToJSONObject("traffic", mySqlOutput);
	}

	@Override
	public JSONObject get1MonthTraffic(Date date, String host) throws SQLException 
	{
		if(!host.equals(""))
			host = "and host = '"+host+"'";
		
		java.sql.Timestamp mySqlTimestampTo = new java.sql.Timestamp(date.getTime());
		ResultSet mySqlOutput = MySQLConnector.execQuery("SELECT FROM_UNIXTIME(ROUND(UNIX_TIMESTAMP(timestamp)/(86400))*(86400)) as timestamp, COUNT(*) AS count FROM dataPackages WHERE timestamp BETWEEN ('"+mySqlTimestampTo+"' - INTERVAL 1 MONTH) and '"+mySqlTimestampTo+"' "+host+" GROUP BY UNIX_TIMESTAMP(timestamp) DIV 86400 LIMIT 31");
		return parseResultsetToJSONObject("traffic", mySqlOutput);
	}

	@Override
	public void addDataPackage(DataPackage dataPackage) throws SQLException 
	{
		java.sql.Timestamp mySqlTimestamp = new java.sql.Timestamp(dataPackage.getTimeStamp().getTime());
		
		String query = "INSERT INTO dataPackages VALUES(";
		query += "0, '";
		query += dataPackage.getInIP() + "', '";
		query += dataPackage.getOutIP() + "', '";
		query += dataPackage.getHost() + "', '";
		query += "sub','";
		query += dataPackage.getUserAgent() + "', '";
		query += mySqlTimestamp + "');";
		
		System.out.println(query);
		MySQLConnector.update(query);		
	}
	
	@SuppressWarnings("unchecked")
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

}
