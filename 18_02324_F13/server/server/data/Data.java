package server.data;

import java.util.*;

public class Data implements IData
{
	private List<DataPackage> dataPackageList;
	public Data()
	{
		dataPackageList = new ArrayList<DataPackage>();
	}
	
	@Override
	public void setScourceIP(String IP, int index) {
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
	public String getInIP(int index) {
		return dataPackageList.get(index).getInIP();
	}


	@Override
	public String getOutIP(int index) {
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
	public void addDataset(Date date, String inIP, String outIP, String host, String subHost, String userAgent) {
		dataPackageList.add(new DataPackage(date ,inIP, outIP, host, subHost, userAgent));
	}

	
	public class DataPackage
	{
		private String scourceIP;
		private String distinationIP;
		private String host;
		private String subHost;
		private String userAgent;
		private Date timeStamp;
		
		private DataPackage(Date timeStamp, String inIP, String outIP, String host, String subHost, String userAgent)
		{
			this.timeStamp = timeStamp;
			this.scourceIP = inIP;
			this.distinationIP = outIP;
			this.host = host;
			this.subHost = subHost;
			this.userAgent = userAgent;
		}
		
		public void setInIP(String IP) {
			this.scourceIP = IP;
			
		}
	
		public void setOutIP(String IP) {
			this.distinationIP = IP;
			
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
			return scourceIP;
		}
	
		public String getOutIP() {
			return distinationIP;
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
