package server.data;

import java.util.*;
import server.data.Data.DataPackage;

/**
 * 
 * @author Mads
 *
 */

public interface IData 
{
	/**
	 * For adding an entire dataset into the list, it is possible to let fields stay as null if they are not important
	 */
	void addDataset(Date date, String scourceIP, String destinationIP, String host, String subHost, String userAgent);
	
	DataPackage getDataPackage();
	
	boolean isEmpty();
}
