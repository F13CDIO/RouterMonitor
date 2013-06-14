package server.data.mySQLInterfaces;

import java.sql.SQLException;
import java.util.Date;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.data.Data;
import server.data.Data.DataPackage;

public interface IDataPackageDAO 
{
	JSONArray getTop10() throws SQLException ;
	JSONArray getTop10(Date dateFrom) throws SQLException ;
	JSONObject get10SecondTraffic(Date date, String host) throws SQLException ;
	JSONObject get10Minute(Date date, String host) throws SQLException ;
	JSONObject get1HourTraffic(Date date, String host) throws SQLException ;
	JSONObject get1DayTraffic(Date date, String host) throws SQLException ;
	JSONObject get1MonthTraffic(Date date, String host) throws SQLException;
	void addDataPackage(DataPackage dataPackage) throws SQLException ;
	void addUser(String email, String password, String role)throws SQLException ;
	boolean userExists(String email) throws SQLException ;
	void editUser(String email, String newPassword, String newRole) throws SQLException;
	void deleteUser(String email) throws SQLException;
	boolean loginValid(String email, String password) throws SQLException;
	JSONArray getAllUsers() throws SQLException;
}
