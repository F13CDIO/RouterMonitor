package Funktion;

import Data.*;
import java.util.Calendar;

public class ParseUdpPackage implements IParseUdpPackage
{
    private int index;
    private IData data = new DataLayer().getData();
    
    public ParseUdpPackage()
    {
    }
	
    public void parse(String input) 
    {
    	Calendar cal = Calendar.getInstance();
        String ip1 = getIP1(input);
        String ip2 = getIP2(input);
        
        String fullHost = getFullHost(input);
        String host = getHost(fullHost);
        String subHost = getSubHost(fullHost);
        String userAgent = getUserAgent(input);
        
        if(ip1 != null && ip2 != null && host != null && userAgent != null)
        {
        	data.addDataset(cal.getTime(), ip1, ip2, host, subHost, userAgent);
        }
    }

    private String getIP1(String input)
    {
        index = input.indexOf(":", 9);
        if(index < 0)
        {
        	return null;
        }
        else
        {
        	String ip = input.substring(2, index);
            return ip;
        }
    }

    private String getIP2(String input)
    {
        index = input.indexOf(" ", index);
        int pos = input.indexOf(":", (index) + 11);
        if(pos < 0 || index < 0)
        {
        	return null;
        }
        else
        {
        	String ip = input.substring(index + 4, pos);
            return ip;
        }
    }
    
    private String getFullHost(String input)
    {
        index = input.indexOf("Host: ", index);
        int pos = input.indexOf("..", index);
        if(pos < 0 || index < 0)
        {
        	return " ";
        }
        else
        {
        	String host = input.substring(index + 6, pos);
            return host;
        }
    }
    
    private String getHost(String fullHost)
    {
        int pos = fullHost.lastIndexOf(".");
        index = fullHost.lastIndexOf(".", pos-1);
        if(pos < 0 )
        {
        	return null;
        }
        else
        {
            String host = fullHost.substring(index+1);
            return host;
        }
    }
    
    private String getSubHost(String fullHost)
    {
        int pos = fullHost.lastIndexOf(".", index-1);
        if(pos < 0)
        {
        	return null;
        }
        else
        {
	        String subHost = fullHost.substring(pos+1, index);
	        return subHost;
        }
    }
    
    public String getUserAgent(String input){
    	index = input.indexOf("User-Agent", index);
    	if(index < 0)
    	{
    		return null;
    	}
    	else
    	{
	    	int endIndex = input.indexOf(")", index);
	    	String userAgent = input.substring(index+12,endIndex+1);
	    	return userAgent;
    	}
    }
}
