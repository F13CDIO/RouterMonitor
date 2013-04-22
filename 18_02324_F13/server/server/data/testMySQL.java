package server.data;
import java.util.Calendar;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class testMySQL 
{

	public static void main(String[] args) 
	{
		mySqlConnect mySql = new mySqlConnect();
		mySql.connect();
		
		
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 03, 17, 14, 15, 27);
		 
		String host = "facebook.com";
		JSONObject test = mySql.get10SecondTraffic(cal.getTime(), "");
				
		//System.out.println(test.get("2013-04-17 14:15:36.0"));
		System.out.println(test);
		
		cal.set(2013, 03, 17, 14, 15, 29);
		System.out.println(test.get(cal.getTime().toString()));
		mySql.closeConnection();
		
		
	}

}
