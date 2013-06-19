package server.data.mySQLConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MySQLConnector 
{
	private String sqlURL = "jdbc:mysql://bjqrn88.cloudns.org:3306/";
	private String sqlDbName = "grp18";
	private String sqlUser = "grp18"; 
	private String sqlPassword = "123123";
	
	private Connection sqlConnection = null;
	private Statement sqlStatement = null;
	
	public MySQLConnector() //throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		
	}
	
	public ResultSet execQuery(String query) throws SQLException 
	{		  
		//System.out.println(query);
		return sqlStatement.executeQuery(query);
	}
	
	public int update(String cmd) throws SQLException
	{
		//System.out.println(cmd);
		return sqlStatement.executeUpdate(cmd);
	}
	
	public void insertBatch(String[] queries) throws SQLException 
	{
		
		for (String query : queries) 
		{
		    sqlStatement.addBatch(query);
		}
		
		sqlStatement.executeBatch();
	}
	
	public void connect() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{		
		Class.forName("com.mysql.jdbc.Driver"); 

			sqlConnection = DriverManager.getConnection(sqlURL + sqlDbName, sqlUser, sqlPassword);
			sqlStatement = sqlConnection.createStatement();
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
				}
			}
		} 
		catch (Exception e) { System.out.println(e.getMessage()); }
	}
}