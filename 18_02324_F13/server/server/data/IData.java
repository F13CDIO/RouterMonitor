package server.data;

import java.util.*;
import server.data.DataPackage;

/**
 * 
 * @author Mads
 *
 */

public interface IData 
{
	/**
	 * For adding an entire dataset into the list, it is possible to let fields stay as null if they are not important
	 * @param date
	 * @param scourceIP 
	 * @param destinationIP
	 * @param host
	 * @param subHost
	 * @param userAgent 
	 */
	void addDataset(Date date, String scourceIP, String destinationIP, String host, String subHost, String userAgent);
	
	/**
	 * Removes the Package from the list, and returns it
	 * @return Returns the entire package from the list
	 */
	DataPackage getDataPackage();
	
	/**
	 * 
	 * @return Returns true if the list is empty
	 */
	boolean isEmpty();
}
