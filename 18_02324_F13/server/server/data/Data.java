package server.data;

import java.util.*;
/**
 * 
 * @author Mads
 *
 */

public class Data implements IData
{
	private List<DataPackage> dataPackets;
	
	public Data()
	{
		dataPackets = new ArrayList<DataPackage>();
	}

	@Override
	public void addDataset(Date date, String sourceIP, String destinationIP, String host, String subHost, String userAgent) 
	{
		dataPackets.add(new DataPackage(date ,sourceIP, destinationIP, host, subHost, userAgent));
	}
	
	@Override
	public DataPackage getDataPackage()
	{
		DataPackage dataPacket = dataPackets.get(dataPackets.size()-1);
		dataPackets.remove(dataPacket);
		return dataPacket;
	}
	
	@Override
	public boolean isEmpty()
	{
		return dataPackets.isEmpty();
	}
}
