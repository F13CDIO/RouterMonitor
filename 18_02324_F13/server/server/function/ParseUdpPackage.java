package server.function;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import server.data.*;

public class ParseUdpPackage implements IParseUdpPackage, IFunction {

	//A number defining where in the string we are looking
    private IData data;
    private String userAgent;
    private String host;
    private String subHost;
    private String distinationIP;
    private String sourceIP;
    private RegexFromFile fileReader = new RegexFromFile();
    //reads the first line from the file "userAgent"
    private String userAgenrRegex = fileReader.ReadLine("userAgent"); 
    

    public ParseUdpPackage() 
    {
    	data = Function.getDatalayer();
    }

    public void parse(String input) 
    {
        Calendar cal = Calendar.getInstance();
        String[] sniffedData = input.split("\t");
        
        if (sniffedData.length <= 1)
        {
        	return;
        }
        
        host = formatHost(sniffedData[2]);
        subHost = formatSubHost(sniffedData[2]);
        sourceIP = sniffedData[0];
        distinationIP = sniffedData[1];
        
        Pattern agentPattern = Pattern.compile(userAgenrRegex);
        Matcher agentMatcher = agentPattern.matcher(sniffedData[3]);
        if(agentMatcher.find())
        {	
        	//userAgent = sniffedData[3].replaceAll("\0", "");
        	userAgent = agentMatcher.group();
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
		String[] domains = hostString.split("\\."); //splits the host string om dots

		int index = domains.length-1;
		try
		{
			if(domains[index].equals("uk")) // removes .??.uk
			{
				if(domains[index-2].equals("www")) //if the subhost is www we set it to null
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
			//in case of no subhost
			return null;
		}
	}

	private String formatHost(String hostString)
	{
		hostString = hostString.toLowerCase();
		String[] domains = hostString.split("\\.");
		String domain = "";
		int index = domains.length-1;
		
		try
		{
			if(domains[index].equals("uk")) // removes .??.uk
			{
				domain += domains[index -2] + "." +domains[index -1] + "."  + domains[index];
				return domain;
			}
			else
			{
				domain += domains[index -1] + "."  + domains[index];
				return domain;
			}
		}
		catch(ArrayIndexOutOfBoundsException e){return null;} //in case of no dots
	}
}
