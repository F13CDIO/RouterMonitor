package server.data;

import java.sql.*;

public class mySqlConnect 
{
	private String sqlURL = "jdbc:mysql://db4free.net:3306/";
	private String sqlDbName = "dalgaard";
	private String sqlUser = "redlaz"; 
	private String sqlPassword = "kodetilsql";
	
	private Connection sqlConnection = null;
	private PreparedStatement sqlStatement = null;
	private ResultSet sqlOutput = null;
	
	public void getAll()
	{	
		executeQuery("SELECT * FROM dataPackages");
	}
	
	public void addDataPackage(String sourceIP, String destinationIP, String host, String subHost, String userAgent)
	{
		connect();
		
		try 
		{
			sqlStatement = sqlConnection.prepareStatement("INSERT INTO dataPackages VALUES (?, ?, ?, ?, ? , ?)");
			sqlStatement.setString(1, "0");
			sqlStatement.setString(2, sourceIP);
			sqlStatement.setString(3, destinationIP);
			sqlStatement.setString(4, host);
			sqlStatement.setString(5, subHost);
			sqlStatement.setString(6, userAgent);
			sqlStatement.executeUpdate();
		} 
		
		catch (SQLException e) { System.out.println(e.getMessage()); }
		
		closeConnection();
	}
	
	private void executeQuery(String query) 
	{
		
		if (connect())
		{
			try 
			{
				sqlStatement = sqlConnection.prepareStatement(query);
				sqlOutput = sqlStatement.executeQuery();
			
				while (sqlOutput.next())
				{
					System.out.println(sqlOutput.getString("host") + ", " + sqlOutput.getString("sourceIP"));
				}
			} 
			
			catch (SQLException e) { System.out.println(e.getMessage()); }
		}
				
		closeConnection();
	}
	
	private boolean connect()
	{
		try 
		{
			sqlConnection = DriverManager.getConnection(sqlURL + sqlDbName, sqlUser, sqlPassword);
			System.out.println("Connected to mySql database");
			return true;
		} 
		
		catch (SQLException e1) 
		{
			System.out.println(e1.getMessage());
			return false;
		}
	}
	
	private boolean closeConnection()
	{
		try 
		{
			if (sqlConnection != null)
			{
				if (!sqlConnection.isClosed())
				{
					sqlConnection.close();
					System.out.println("Disconnected from mySql database");
					return true;
				}
			}
		} 
		 
		catch (Exception e) { System.out.println(e.getMessage()); }
		return false;
	}
}
