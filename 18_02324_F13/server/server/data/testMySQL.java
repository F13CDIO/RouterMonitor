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
		cal.set(2013, 03, 29, 14, 34, 23);
		 
		
		//JSONObject test = mySql.get10SecondTraffic(cal.getTime(), "");
		//JSONObject test = mySql.get1MinuteTraffic(cal.getTime(), "");
//		JSONObject test = mySql.get10MinuteTraffic(cal.getTime(), "");
		//JSONObject test = mySql.get1HourTraffic(cal.getTime(), "");
		//JSONObject test = mySql.get1DayTraffic(cal.getTime(), "");
		//JSONObject test = mySql.get1MonthTraffic(cal.getTime(), "");
		JSONArray test = mySql.getTop10(cal.getTime());
		
				
		//System.out.println(test.get("2013-04-17 14:15:36.0"));
		System.out.println(test);
		
		cal.set(2013, 03, 17, 14, 15, 29);
		//System.out.println(test.get(cal.getTime().toString()));
		
		
		mySql.closeConnection();
		
		
	}

}
