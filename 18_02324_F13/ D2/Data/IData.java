package Data;

public interface IData 
{
	void setInIP(String IP); //Skal der kastes en NoDataException, eller noget lignende, i tilfælde af manglende data?
	
	void setOutIP(String IP);
	void setHost(String host);
	void setSubHost(String subHost);
	void setUserAgent(String userAgent);
	String getInIP();
	String getOutIP();
	String getHost();
	String getSubHost();
	String getUserAgent();
}
