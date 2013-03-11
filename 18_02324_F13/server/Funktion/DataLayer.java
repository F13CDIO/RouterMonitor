package Funktion;

import Data.*;

public class DataLayer 
{
	private static IData data = null;
	
	public DataLayer(IData data)
	{
		this.data = data;
	}
	
	public DataLayer()
	{
	//for common instantiation
	}
	
	public IData getData()
	{
		return data;
	}
}
