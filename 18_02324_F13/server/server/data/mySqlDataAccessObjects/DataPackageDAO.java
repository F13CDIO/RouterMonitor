package server.data.mySqlDataAccessObjects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import server.data.DataPackage;
import server.data.mySQLConnector.MySQLConnector;
import server.data.mySQLInterfaces.IDataPackageDAO;

public class DataPackageDAO implements IDataPackageDAO
{
	private MySQLConnector mySQLConnector = new MySQLConnector();
	
	public DataPackageDAO()
	{
		
	}

	@Override
	public JSONArray getTop10() throws SQLException 
	{
		ResultSet mySqlOutput = mySQLConnector.execQuery("SELECT host, COUNT(*) as count FROM dataPackages GROUP BY host ORDER BY count DESC LIMIT 10");
		return parseResultsetToJSONArray("hosts", mySqlOutput);
	}

	@Override
	public JSONArray getTop10(Date dateFrom) throws SQLException 
	{
		java.sql.Timestamp mySqlFrom = new java.sql.Timestamp(dateFrom.getTime());		
		ResultSet mySqlOutput = mySQLConnector.execQuery("SELECT host, COUNT(*) as count FROM dataPackages WHERE timestamp > '" + mySqlFrom + "'GROUP BY host ORDER BY count DESC LIMIT 10");
		return parseResultsetToJSONArray("hosts", mySqlOutput);
	}

	@Override
	public JSONObject get10SecondTraffic(Date date, String host) throws SQLException 
	{
		if(!host.equals(""))
			host = "and host = '"+host+"'";
		
		java.sql.Timestamp mySqlTimestampTo = new java.sql.Timestamp(date.getTime());
		ResultSet mySqlOutput = mySQLConnector.execQuery("SELECT FROM_UNIXTIME(ROUND(UNIX_TIMESTAMP(timestamp)/(10))*(10)) as timestamp, COUNT(*) AS count FROM dataPackages WHERE timestamp BETWEEN ('"+mySqlTimestampTo+"' - INTERVAL 10 SECOND) and '"+mySqlTimestampTo+"' "+host+" GROUP BY UNIX_TIMESTAMP(timestamp) DIV 1 LIMIT 10");
		return parseResultsetToJSONObject("traffic", mySqlOutput);
	}
	
	@Override
	public JSONObject get10Minute(Date date, String host) throws SQLException 
	{
		if(!host.equals(""))
			host = "and host = '"+host+"'";
		
		java.sql.Timestamp mySqlTimestampTo = new java.sql.Timestamp(date.getTime());
		ResultSet mySqlOutput = mySQLConnector.execQuery("SELECT FROM_UNIXTIME(ROUND(UNIX_TIMESTAMP(timestamp)/(60))*(60)) as timestamp, COUNT(*) AS count FROM dataPackages WHERE timestamp BETWEEN ('"+mySqlTimestampTo+"' - INTERVAL 10 MINUTE) and '"+mySqlTimestampTo+"' "+host+" GROUP BY UNIX_TIMESTAMP(timestamp) DIV 60 LIMIT 10");
		return parseResultsetToJSONObject("traffic", mySqlOutput);
	}

	@Override
	public JSONObject get1HourTraffic(Date date, String host) throws SQLException 
	{
		if(!host.equals(""))
			host = "and host = '"+host+"'";
		
		java.sql.Timestamp mySqlTimestampTo = new java.sql.Timestamp(date.getTime());
		ResultSet mySqlOutput = mySQLConnector.execQuery("SELECT FROM_UNIXTIME(ROUND(UNIX_TIMESTAMP(timestamp)/(60))*(60)) as timestamp, COUNT(*) AS count FROM dataPackages WHERE timestamp BETWEEN ('"+mySqlTimestampTo+"' - INTERVAL 1 HOUR) and '"+mySqlTimestampTo+"' "+host+" GROUP BY UNIX_TIMESTAMP(timestamp) DIV 60 LIMIT 60");
		return parseResultsetToJSONObject("traffic", mySqlOutput);
	}

	@Override
	public JSONObject get1DayTraffic(Date date, String host) throws SQLException 
	{
		if(!host.equals(""))
			host = "and host = '"+host+"'";
		
		java.sql.Timestamp mySqlTimestampTo = new java.sql.Timestamp(date.getTime());
		ResultSet mySqlOutput = mySQLConnector.execQuery("SELECT FROM_UNIXTIME(ROUND(UNIX_TIMESTAMP(timestamp)/(3600))*(3600)) as timestamp, COUNT(*) AS count FROM dataPackages WHERE timestamp BETWEEN ('"+mySqlTimestampTo+"' - INTERVAL 1 DAY) and '"+mySqlTimestampTo+"' "+host+" GROUP BY UNIX_TIMESTAMP(timestamp) DIV 3600 LIMIT 24");
		return parseResultsetToJSONObject("traffic", mySqlOutput);
	}

	@Override
	public JSONObject get1MonthTraffic(Date date, String host) throws SQLException 
	{
		if(!host.equals(""))
			host = "and host = '"+host+"'";
		
		java.sql.Timestamp mySqlTimestampTo = new java.sql.Timestamp(date.getTime());
		ResultSet mySqlOutput = mySQLConnector.execQuery("SELECT FROM_UNIXTIME(ROUND(UNIX_TIMESTAMP(timestamp)/(86400))*(86400)) as timestamp, COUNT(*) AS count FROM dataPackages WHERE timestamp BETWEEN ('"+mySqlTimestampTo+"' - INTERVAL 1 MONTH) and '"+mySqlTimestampTo+"' "+host+" GROUP BY UNIX_TIMESTAMP(timestamp) DIV 86400 LIMIT 31");
		return parseResultsetToJSONObject("traffic", mySqlOutput);
	}

	@Override
	public void addDataPackage(DataPackage dataPackage) throws SQLException 
	{
		java.sql.Timestamp mySqlTimestamp = new java.sql.Timestamp(dataPackage.getTimeStamp().getTime());
		
		String query = "INSERT INTO dataPackages VALUES(";
		query += "0, '";
		query += dataPackage.getScourceIP() + "', '";
		query += dataPackage.getDestinationIP() + "', '";
		query += dataPackage.getHost() + "', '";
		query += dataPackage.getSubHost() + "','";
		query += dataPackage.getUserAgent() + "', '";
		query += mySqlTimestamp + "');";
		
		System.out.println(query);
		mySQLConnector.update(query);		
	}
	
	@SuppressWarnings("unchecked")
	private JSONArray parseResultsetToJSONArray(String function, ResultSet mySqlOutput) 
	{
		JSONArray jsonArray = new JSONArray();
		
		try 
		{
			while (mySqlOutput.next())
			{		
				JSONObject jo = new JSONObject();
				switch (function)
				{
					case "hosts":
						jo.put("rank", mySqlOutput.getRow());
						jo.put("count", mySqlOutput.getInt("count"));
						jo.put("host", mySqlOutput.getString("host"));
						jsonArray.add(jo);
						break;
						
					case "users":
						jo.put("userName", mySqlOutput.getString("userNameCol"));
						jo.put("userRole", mySqlOutput.getString("roleNameCol"));
						jsonArray.add(jo);
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
		
		return jsonArray;
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

	@Override
	public void addUser(String email, String password, String role) throws SQLException 
	{
	
		String query = "INSERT INTO userTable VALUES('"+email+"', '"+password+"')";
		System.out.println(query);
		mySQLConnector.update(query);
		
		query = "INSERT INTO userRoleTable VALUES('"+email+"', '"+role+"')";
		System.out.println(query);
		mySQLConnector.update(query);
	}

	@Override
	public boolean userExists(String email) throws SQLException 
	{
		ResultSet mySqlOutput = mySQLConnector.execQuery("SELECT userNameCol FROM userTable WHERE userNameCol = '"+email+"'");
		return mySqlOutput.first();
	}

	@Override
	public boolean loginValid(String email, String password) throws SQLException
	{
		ResultSet mySqlOutput = mySQLConnector.execQuery("SELECT * FROM userTable WHERE userNameCol ='"+email+"' AND userCredCol = '"+password+"'");
		return mySqlOutput.first();
	}

	@Override
	public void deleteUser(String email) throws SQLException 
	{
		String query = "DELETE FROM userTable WHERE userNameCol = '"+email+"'";
		mySQLConnector.update(query);
		query = "DELETE FROM userRoleTable WHERE userNameCol = '"+email+"'";
		mySQLConnector.update(query);
	}

	@Override
	public void editUser(String email, String newPassword, String newRole)throws SQLException 
	{
		String query = "";
		if (newPassword != null)
		{
			query = "UPDATE userTable SET userCredCol = '"+newPassword+"' WHERE userNameCol ='"+email+"'";
			mySQLConnector.update(query);
		}
		
		if (newRole != null)
		{
			query = "UPDATE userRoleTable SET roleNameCol = '"+newRole+"' WHERE userNameCol ='"+email+"'";
			mySQLConnector.update(query);
		}
	}

	@Override
	public JSONArray getAllUsers() throws SQLException 
	{
		ResultSet mySqlOutput = mySQLConnector.execQuery("SELECT * FROM userRoleTable ORDER BY userNameCol ASC");
		return parseResultsetToJSONArray("users", mySqlOutput);
	}

	@Override
	public boolean openConnection() throws SQLException 
	{
		try {
			mySQLConnector.connect();
			return true;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}

	@Override
	public void closeConnection() 
	{
		mySQLConnector.closeConnection();
	}

	

}
