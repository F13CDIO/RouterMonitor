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
	//Sets all the datavariabels, it is legal to set any or all of them as null
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
	
	
	public class DataPackage
	{
		private String sourceIP;
		private String destinationIP;
		private String host;
		private String subHost; //skal slettes
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
		
		public void setInIP(String IP) {
			this.sourceIP = IP;
			
		}
	
		public void setOutIP(String IP) {
			this.destinationIP = IP;
			
		}
	
		public void setHost(String host) {
			this.host = host;
			
		}
	
		public void setSubHost(String subHost) {
			this.subHost = subHost;
			
		}
	
		public void setUserAgent(String userAgent) {
			this.userAgent = userAgent;
			
		}
	
		public String getInIP() {
			return sourceIP;
		}
	
		public String getOutIP() {
			return destinationIP;
		}
	
		public String getHost() {
			return host;
		}
	
		public String getSubHost() {
			return subHost;
		}
	
		public String getUserAgent() {
			return userAgent;
		}
		
		public Date getTimeStamp()
		{
			return timeStamp;
		}
		
		public String toString()
		{
			return "Time: " + this.getTimeStamp()+
					"\nHost: " + this.getHost() +
					"\nSubhost: " + this.getSubHost() +
					"\nScource IP: " + this.getInIP() + 
					"\nDestination IP: " + this.getOutIP() +
					"\nUser - Agent: " + this.getUserAgent();
		}
	}

}
