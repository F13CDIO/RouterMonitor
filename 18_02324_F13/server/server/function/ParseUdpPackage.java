package server.function;

import java.util.Calendar;

import server.data.*;

public class ParseUdpPackage implements IParseUdpPackage, IFunction {

	//A number defining where in the string we are looking
    private IData data;
    private int dataAdded;

    public ParseUdpPackage() 
    {
    	data = Function.getDatalayer();
    	dataAdded = 0;
    }

    public void parse(String input) 
    {
        Calendar cal = Calendar.getInstance();
        String[] sniffedData = input.split("\t");
        
        /* Different 'get'-methods for the different types of info.
         * It is important that methods run in the right order, since they share
         * the same masterIndex variable, for better performance */
        String host;
        if(!sniffedData[2].toLowerCase().contains("dropbox"))
        	host = sniffedData[2];
        else
        	return;
        String sourceIP = sniffedData[0];
        String distinationIP = sniffedData[1];
        String userAgent = sniffedData[3].replaceAll("\0", "");

        /* Only save data if all the "important" information was found.
         * Useragent and subhost is not considered "important" */
        if (sourceIP != null && distinationIP != null && host != null) {
            data.addDataset(cal.getTime(), sourceIP, distinationIP, host, userAgent);
            //System.out.println(data.getDataPackage().toString());
            dataAdded++;
            //System.out.println("Package count: " + dataAdded);
            
        }
    }
}
