package server.function;

import java.util.Calendar;

import server.data.*;

public class ParseUdpPackage implements IParseUdpPackage, IFunction {

	//A number defining where in the string we are looking
    private IData data;
    private String userAgent;
    private String host;
    private String subHost;
    private String distinationIP;
    private String sourceIP;

    public ParseUdpPackage() 
    {
    	data = Function.getDatalayer();
    }

    public void parse(String input) 
    {
        Calendar cal = Calendar.getInstance();
        String[] sniffedData = input.split("\t");
        
        /* Different 'get'-methods for the different types of info.
         * It is important that methods run in the right order, since they share
         * the same masterIndex variable, for better performance */
        if(!sniffedData[2].toLowerCase().contains("dropbox")) //fjerner dropbox
        	host = formatHost(sniffedData[2]);
        else
        	return;
        
        subHost = formatSubHost(sniffedData[2]);
        sourceIP = sniffedData[0];
        distinationIP = sniffedData[1];
        
    	//removes avast and steam, and more?
        if(!sniffedData[3].toLowerCase().contains("Avast")||!sniffedData[3].toLowerCase().contains("steam")) //Optimize
        {	
        	userAgent = sniffedData[3].replaceAll("\0", "");
        	if(userAgent.length() > 300)
        	{
        		return;
        	}
        }
        else
        {
        	return;
        }

        /* Only save data if all the "important" information was found.
         * Useragent and subhost is not considered "important" */
        if (sourceIP != null && distinationIP != null && host != null) {
            data.addDataset(cal.getTime(), sourceIP, distinationIP, host, subHost, userAgent);
            
        }
    }

	private String formatSubHost(String hostString)
	{
		hostString = hostString.toLowerCase();
		String[] domains = hostString.split("\\.");

		int index = domains.length-1;
		try
		{
			if(domains[index].equals("uk")) // removes .??.uk
			{
				if(domains[index-2].equals("www"))
					return null;
				return domains[index -3];
			}
			else
			{
				if(domains[index-2].equals("www"))
					return null;
				return domains[index-2];
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			return null;
		}
	}

	private String formatHost(String hostString)
	{
		hostString = hostString.toLowerCase();
		String[] domains = hostString.split("\\.");

		int index = domains.length-1;
		if(domains[index].equals("uk")) // removes .??.uk
		{
			return domains[index -2];
		}
		else
		{
			return domains[index-1];
		}
	}
}
