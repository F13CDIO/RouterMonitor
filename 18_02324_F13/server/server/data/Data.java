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
}
