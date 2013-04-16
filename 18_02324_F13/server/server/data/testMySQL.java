package server.data;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class testMySQL 
{

	public static void main(String[] args) 
	{
		mySqlConnect mySql = new mySqlConnect();
		mySql.connect();
		JSONArray test = mySql.getTop10();
		mySql.closeConnection();
		
		System.out.println(test);
	}

}
