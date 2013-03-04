package Data;

import java.util.*;
import Data.Data.DataPackage;

public interface IData 
{
	void addDataset(Date date, String inIP, String outIP, String host, String subHost, String userAgent);
	void setInIP(String IP, int index); //Skal der kastes en NoDataException, eller noget lignende, i tilf�lde af manglende data?
	void setOutIP(String IP, int index);
	void setHost(String host, int index);
	void setSubHost(String subHost, int index);
	void setUserAgent(String userAgent, int index);
	String getInIP(int index);
	String getOutIP(int index);
	String getHost(int index);
	String getSubHost(int index);
	String getUserAgent(int index);
	Date getTimeStamp(int index);
	List<DataPackage> getDataList();
}
