package Funktion;

import Data.*;

public class ParseUdpPackage implements IParseUdpPackage
{
    private int index;
    private IData data;
    
    public ParseUdpPackage()
    {
    	data = new Data();
    }
	
    //Lave til public void parse(String input, IData data) i stedet for?, for at en controller kan bruge samme dataobject 
    public void parse(String input) 
    {
        String ip1 = getIP1(input);
        String ip2 = getIP2(input);
        
        String fullHost = getFullHost(input);
        String host = getHost(fullHost);
        String subHost = getSubHost(fullHost);
        
        if(ip1 != null && ip2 != null && host != null)
        {
        	data.addDataset(ip1, ip2, host, subHost, null);
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
    
    //B�r denne metode v�re der??
    public IData getData()
    {
    	return data;
    }
}
