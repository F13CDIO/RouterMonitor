package Data;

import java.util.*;

public class Data implements IData
{
	private List<DataPackage> dataList;
	public Data()
	{
		dataList = new ArrayList<DataPackage>();
	}
	
	@Override
	public void setInIP(String IP, int index) {
		dataList.get(index).setInIP(IP);
		
	}


	@Override
	public void setOutIP(String IP, int index) {
		dataList.get(index).setOutIP(IP);
		
	}


	@Override
	public void setHost(String host, int index) {
		dataList.get(index).setHost(host);
	}


	@Override
	public void setSubHost(String subHost, int index) {
		dataList.get(index).setSubHost(subHost);
	}


	@Override
	public void setUserAgent(String userAgent, int index) {
		dataList.get(index).setUserAgent(userAgent);
	}


	@Override
	public String getInIP(int index) {
		return dataList.get(index).getInIP();
	}


	@Override
	public String getOutIP(int index) {
		return dataList.get(index).getOutIP();
	}


	@Override
	public String getHost(int index) {
		return dataList.get(index).getHost();
	}


	@Override
	public String getSubHost(int index) {
		return dataList.get(index).getSubHost();
	}


	@Override
	public String getUserAgent(int index) {
		return dataList.get(index).getUserAgent();
	}


	@Override
	public List<DataPackage> getDataList() {
		return dataList;
	}


	@Override
	public void addDataset(String inIP, String outIP, String host, String subHost, String userAgent) {
		dataList.add(new DataPackage(inIP, outIP, host, subHost, userAgent));
	}
	
	public class DataPackage
	{
		private String inIP;
		private String outIP;
		private String host;
		private String subHost;
		private String userAgent;
		
		private DataPackage(String inIP, String outIP, String host, String subHost, String userAgent)
		{
			this.inIP = inIP;
			this.outIP = outIP;
			this.host = host;
			this.subHost = subHost;
			this.userAgent = userAgent;
		}
		
		public void setInIP(String IP) {
			this.inIP = IP;
			
		}
	
		public void setOutIP(String IP) {
			this.outIP = IP;
			
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
			return inIP;
		}
	
		public String getOutIP() {
			return outIP;
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
	}
}
