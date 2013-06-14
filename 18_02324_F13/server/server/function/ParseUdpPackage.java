package server.function;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import server.data.*;

/**
 * Saves the raw inputdata into dataPackage objects
 * 
 * @author Gruppe 18
 * 
 */
public class ParseUdpPackage implements IParseUdpPackage, IFunction
{

	private IData data;
	private String userAgent;
	private String host;
	private String subHost;
	private String distinationIP;
	private String sourceIP;
	private String userAgenrRegex;

	public ParseUdpPackage()
	{
		data = Function.getDatalayer();
		// reads the first line from the file "userAgent"
		userAgenrRegex = RegexFromFile.ReadLine("userAgent");
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
		if (agentMatcher.find())
		{
			userAgent = agentMatcher.group();
			if (userAgent.length() > 300)
			{
				return;
			}
		}
		else
		{
			return;
		}

		/*
		 * Only save data if all the "important" information was found.
		 * Useragent and subhost is not considered "important"
		 */
		if (sourceIP != null && distinationIP != null && host != null)
		{
			data.addDataset(cal.getTime(), sourceIP, distinationIP, host,
					subHost, userAgent);

		}
	}

	/**
	 * Extracts the subhost from the URL
	 * 
	 * @param hostString
	 *            The URL string
	 * @return Returns the subhost, or null depending if there is any. www does
	 *         not count as a subhost
	 */
	private String formatSubHost(String hostString)
	{
		hostString = hostString.toLowerCase();
		// splits the host string on dots
		String[] domains = hostString.split("\\.");

		int index = domains.length - 1;
		try
		{
			// removes .??.uk
			if (domains[index].equals("uk"))
			{
				// if the subhost is www we set it to null
				if (domains[index - 2].equals("www"))
					return null;
				return domains[index - 3];
			}
			else
			{
				if (domains[index - 2].equals("www"))
					return null;
				return domains[index - 2];
			}
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			// in case of no subhost
			return null;
		}
	}

	/**
	 * Extracts the host from the URL
	 * 
	 * @param hostString
	 *            The URL string
	 * @return Returns the domain of the webpage (if it is a .uk it will remove
	 *         what is in front of uk as well
	 */
	private String formatHost(String hostString)
	{
		hostString = hostString.toLowerCase();
		String[] domains = hostString.split("\\.");
		String domain = "";
		int index = domains.length - 1;

		try
		{
			// removes .??.uk
			if (domains[index].equals("uk"))
			{
				domain += domains[index - 2] + "." + domains[index - 1] + "." + domains[index];
				return domain;
			}
			else
			{
				domain += domains[index - 1] + "." + domains[index];
				return domain;
			}
		}
		// in case of no dots
		catch (ArrayIndexOutOfBoundsException e)
		{
			return null;
		}
	}
}
