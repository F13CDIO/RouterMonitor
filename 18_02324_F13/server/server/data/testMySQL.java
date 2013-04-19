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
		//JSONArray test = mySql.getTop10();
		
		
				Calendar cal = Calendar.getInstance();
				cal.set(2013, 03, 17, 14, 15, 27);
			 
				String host = "facebook.com";
				JSONArray test = mySql.get10SecondTraffic(cal.getTime(), "");
				System.out.println(test);
				//JSONArray test = mySql.getTop10();
		mySql.closeConnection();
		
		
	}

}
