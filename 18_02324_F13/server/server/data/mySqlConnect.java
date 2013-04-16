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
	
	public JSONArray getTop10()
	{
		ResultSet mySqlOutput = executeQuery("SELECT host, COUNT(*) FROM dataPackages GROUP BY host ORDER BY COUNT(*) DESC LIMIT 10;");
		return parseResultsetToJSONArray("top10", mySqlOutput);
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
	
	@SuppressWarnings("unchecked")
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
						jsonObject.put("numRequests", new Integer(mySqlOutput.getInt("COUNT(*)")));
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