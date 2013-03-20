package server.data;

import java.util.*;
/**
 * 
 * @author Mads
 *
 */

public class Data implements IData
{
	private List<DataPackage> dataPackageList;
	public Data()
	{
		dataPackageList = new ArrayList<DataPackage>();
	}
	
	@Override
	public void setSourceIP(String IP, int index) {
		dataPackageList.get(index).setInIP(IP);
		
	}


	@Override
	public void setDestinationIP(String IP, int index) {
		dataPackageList.get(index).setOutIP(IP);
		
	}


	@Override
	public void setHost(String host, int index) {
		dataPackageList.get(index).setHost(host);
	}


	@Override
	public void setSubHost(String subHost, int index) {
		dataPackageList.get(index).setSubHost(subHost);
	}


	@Override
	public void setUserAgent(String userAgent, int index) {
		dataPackageList.get(index).setUserAgent(userAgent);
	}


	@Override
	public String getSourceIP(int index) {
		return dataPackageList.get(index).getInIP();
	}


	@Override
	public String getDistinationIP(int index) {
		return dataPackageList.get(index).getOutIP();
	}


	@Override
	public String getHost(int index) {
		return dataPackageList.get(index).getHost();
	}


	@Override
	public String getSubHost(int index) {
		return dataPackageList.get(index).getSubHost();
	}


	@Override
	public String getUserAgent(int index) {
		return dataPackageList.get(index).getUserAgent();
	}

	@Override
	public Date getTimeStamp(int index) {
		return dataPackageList.get(index).getTimeStamp();
	}

	@Override
	public List<DataPackage> getDataList() {
		return dataPackageList;
	}

	@Override
	//Sets all the datavariabels, it is legal to set any or all of them as null
	public void addDataset(Date date, String sourceIP, String destinationIP, String host, String subHost, String userAgent) {
		dataPackageList.add(new DataPackage(date ,sourceIP, destinationIP, host, subHost, userAgent));
	}
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