package server.function;

import server.data.IData;

public class Function implements IFunction
{
	private ParseUdpPackage parseUdpPackage; 
	private static IData data;
	
	public Function(IData data)
	{
		this.data = data;
		parseUdpPackage = new ParseUdpPackage();
	}

	@Override
	public void parse(String input) 
	{
		parseUdpPackage.parse(input);
	}

	public static IData getData() {
		return data;
	}
}
