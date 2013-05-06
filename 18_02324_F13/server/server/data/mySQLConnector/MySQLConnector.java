package server.data.mySQLConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MySQLConnector 
{
	private static String sqlURL = "jdbc:mysql://bjqrn88.cloudns.org:3306/";
	private static String sqlDbName = "grp18";
	private static String sqlUser = "grp18"; 
	private static String sqlPassword = "123123";
	
	private static Connection sqlConnection = null;
	private static PreparedStatement sqlPreparedStatement = null;
	private static Statement sqlStatement = null;
	
	public MySQLConnector() throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		connect();
	}
	
	public static ResultSet execQuery(String query) throws SQLException 
	{		  
		  return sqlStatement.executeQuery(query);
	}
	
	public static int update(String cmd) throws SQLException
	{
		return sqlStatement.executeUpdate(cmd);
	}
	
	public static boolean connect() throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{			
		try 
		{
			sqlConnection = DriverManager.getConnection(sqlURL + sqlDbName, sqlUser, sqlPassword);
			sqlStatement = sqlConnection.createStatement();
			return true;
		} 
		
		catch (SQLException e1) 
		{
			System.out.println(e1.getMessage());
			return false;
		}
	}
	
	public static void closeConnection()
	{
		try 
		{
			if (sqlConnection != null)
			{
				if (!sqlConnection.isClosed())
				{
					sqlConnection.close();
					System.out.println("Disconnected from mySql database");
				}
			}
		} 
		catch (Exception e) { System.out.println(e.getMessage()); }
	}
}