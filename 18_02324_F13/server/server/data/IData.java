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
//	/**
//	 * Sets the ip of a spesifik element in the list
//	 * @param IP the ip
//	 * @param index the index to change at
//	 */
//	void setSourceIP(String IP, int index);
//	/**
//	 * Sets the ip of a spesifik element in the list
//	 * @param IP the ip
//	 * @param index the index to change at
//	 */
//	void setDestinationIP(String IP, int index);
//	/**
//	 * Sets the host of a spesifik element in the list
//	 * @param host the host
//	 * @param index the index to change at
//	 */
//	void setHost(String host, int index);
//	/**
//	 * Sets the subHost of a spesifik element in the list
//	 * @param subHost the subhost
//	 * @param index the index to change at
//	 */
//	void setSubHost(String subHost, int index);
//	/**
//	 * Sets the ip of a spesifik element in the list
//	 * @param userAgent the useragent
//	 * @param index the index to change at
//	 */
//	void setUserAgent(String userAgent, int index);
//	/**
//	 * Gets the ip of the element
//	 * @param index the element to get from
//	 * @return The ip
//	 */
//	String getSourceIP(int index);
//	/**
//	 * Gets the ip of the element
//	 * @param index the element to get from
//	 * @return The ip
//	 */
//	String getDistinationIP(int index);
//	/**
//	 * Gets the host of the element
//	 * @param index the element to get from
//	 * @return The host
//	 */
//	String getHost(int index);
//	/**
//	 * Gets the subhost of the element
//	 * @param index the element to get from
//	 * @return The subhost
//	 */
//	String getSubHost(int index);
//	/**
//	 * Gets the useragent of the element
//	 * @param index the element to get from
//	 * @return The useragent
//	 */
//	String getUserAgent(int index);
//	/**
//	 * Gets the timestamp of the element
//	 * @param index the element to get from
//	 * @return The timestamp
//	 */
//	Date getTimeStamp(int index);
//	/**
//	 * Gets the entire list of DataPackages
//	 * @return The list of datapackages
//	 */
//	Queue<DataPackage> getDataList();
}
