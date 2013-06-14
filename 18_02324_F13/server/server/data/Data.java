package server.data;

import java.util.*;
/**
 * 
 * @author Mads
 *
 */

public class Data implements IData
{
	private Queue<DataPackage> dataPackageList;
	public Data()
	{
		dataPackageList = new LinkedList<DataPackage>();
	}

	@Override
	public void addDataset(Date date, String sourceIP, String destinationIP, String host, String subHost, String userAgent) {
		dataPackageList.add(new DataPackage(date ,sourceIP, destinationIP, host, subHost, userAgent));
	}
	
	@Override
	public DataPackage getDataPackage()
	{
		return dataPackageList.poll();
	}
	
	@Override
	public boolean isEmpty()
	{
		return dataPackageList.isEmpty();
	}
	
	/**
	 * Inner class for containing all the data of one package
	 * @author Mads
	 *
	 */
	public class DataPackage
	{
		private String sourceIP;
		private String destinationIP;
		private String host;
		private String subHost;
		private String userAgent;
		private Date timeStamp;
		
		/**
		 * Sets all the values in the package at once
		 * @param timeStamp
		 * @param sourceIP
		 * @param destinationIP
		 * @param host
		 * @param subHost
		 * @param userAgent
		 */
		private DataPackage(Date timeStamp, String sourceIP, String destinationIP, String host, String subHost, String userAgent)
		{
			this.timeStamp = timeStamp;
			this.sourceIP = sourceIP;
			this.destinationIP = destinationIP;
			this.host = host;
			this.subHost = subHost;
			this.userAgent = userAgent;
		}
		
		/**
		 * Sets the ScourceIP of a package
		 * @param IP ScourceIP to set
		 */
		public void setScourceIP(String IP) {
			this.sourceIP = IP;
			
		}
	
		/**
		 * Sets the destinationIP of a package
		 * @param IP DestinationIP to set
		 */
		public void setDestinationIP(String IP) {
			this.destinationIP = IP;
			
		}
	
		/**
		 * Sets the domain of a package
		 * @param host
		 */
		public void setHost(String host) {
			this.host = host;
			
		}
	
		/**
		 * Sets the subhost of a package
		 * @param subHost
		 */
		public void setSubHost(String subHost) {
			this.subHost = subHost;
			
		}
	
		/**
		 * Sets the useragent of a package
		 * @param userAgent The useragent, preferably only the name and not the entire useragent string
		 */
		public void setUserAgent(String userAgent) {
			this.userAgent = userAgent;
			
		}
	
		/**
		 * 
		 * @return Returns the ScourceIP
		 */
		public String getScourceIP() {
			return sourceIP;
		}
	
		/**
		 * 
		 * @return Returns the DestinationIP
		 */
		public String getDestinationIP() {
			return destinationIP;
		}
	
		public String getHost() {
			return host;
		}
	
		/**
		 * 
		 * @return Returns the subhost
		 */
		public String getSubHost() {
			return subHost;
		}
	
		/**
		 * 
		 * @return Returns the useragent
		 */
		public String getUserAgent() {
			return userAgent;
		}
		
		/**
		 * 
		 * @return Returns the Timestamp of the object
		 */
		public Date getTimeStamp()
		{
			return timeStamp;
		}
		
		public String toString()
		{
			return "Time: " + this.getTimeStamp()+
					"\nHost: " + this.getHost() +
					"\nSubhost: " + this.getSubHost() +
					"\nScource IP: " + this.getScourceIP() + 
					"\nDestination IP: " + this.getDestinationIP() +
					"\nUser - Agent: " + this.getUserAgent();
		}
	}

}
