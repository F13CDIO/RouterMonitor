package server.function;

import java.util.Calendar;

import server.data.*;

public class ParseUdpPackage implements IParseUdpPackage, IFunction {

	//A number defining where in the string we are looking
    private int masterIndex;
    private IData data;

    public ParseUdpPackage() 
    {
    	data = Function.getData();
    }

    public void parse(String input) 
    {
        Calendar cal = Calendar.getInstance();

        /* Different 'get'-methods for the different types of info.
         * It is important that methods run in the right order, since they share
         * the same masterIndex variable, for better performance */
        String sourceIP = getSourceIP(input);
        String distinationIP = getDistinationIP(input);
        String subHost = getSubHost(input);
        String host = getHost(input);
        String userAgent = getUserAgent(input);

        /* Only save data if all the "important" information was found.
         * Useragent and subhost is not considered "important" */
        if (sourceIP != null && distinationIP != null && host != null) {
            data.addDataset(cal.getTime(), sourceIP, distinationIP, host, subHost, userAgent);
            System.out.println(data.getDataList().toString());
            System.out.println("Package count: " + data.getDataList().size());
        }
    }

    private String getSourceIP(String input) {
        masterIndex = input.indexOf(":", 9);

        if (masterIndex < 2) {
        	//if there is no ip
            return null;
        }
        else {
            return input.substring(2, masterIndex);
        }
    }

    private String getDistinationIP(String input) {
        int endIndex;

        masterIndex = input.indexOf(" ", masterIndex);
        endIndex = input.indexOf(":", (masterIndex) + 11);

        if (endIndex < 0 || masterIndex < 0 || (masterIndex + 4) > endIndex) {
            return null;
        }
        else {
            return input.substring(masterIndex + 4, endIndex);
        }
    }

    private String getSubHost(String input) {
        int hostIndex, dotIndex;

        hostIndex = input.indexOf("Host: ", masterIndex);
        masterIndex = input.indexOf("..", hostIndex);
        masterIndex = input.lastIndexOf(".", masterIndex - 1);
        masterIndex = input.lastIndexOf(".", masterIndex - 1);

        /* If there is no dot between the index if "Host" and the index of
         * the last dot in the domain, there is no subdomain, so return null.
         * Else find the index of the last dot in that range */
        if (masterIndex < hostIndex || hostIndex < 0) {
            masterIndex = hostIndex + 5;
            return null;
        }
        else {
            dotIndex = input.lastIndexOf(".", masterIndex - 1);

            /* If there is no dot between "Host" and the main domain, then
             * the index to cut from, will just be the index of the end of "Host" */
            if (hostIndex > dotIndex) {
                dotIndex = hostIndex + 5;
            }

            return input.substring(dotIndex + 1, masterIndex);
        }
    }

    private String getHost(String input) {
        int endIndex;

        endIndex = input.indexOf("..", masterIndex);

        if (masterIndex - 5 < 0 || endIndex < 0 || (masterIndex + 1) > endIndex) {
            return null;
        }
        else {
            return input.substring(masterIndex + 1, endIndex);
        }
    }

    private String getUserAgent(String input) {
        int endIndex;

        masterIndex = input.indexOf("User-Agent", masterIndex);
        endIndex = input.indexOf(")", masterIndex);

        if (masterIndex < 0 || endIndex < 0 || (masterIndex + 12) > (endIndex + 1)) {
            return null;
        }
        else {
            return input.substring(masterIndex + 12, endIndex + 1);
        }
    }
}
