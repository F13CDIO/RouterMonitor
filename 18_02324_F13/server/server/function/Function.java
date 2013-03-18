package server.function;

import server.data.IData;

public class Function implements IFunction
{
	private ParseUdpPackage parseUdpPackage; 
	private static IData data;
	
	public Function(IData data)
	{
		parseUdpPackage = new ParseUdpPackage();
		this.data = data;
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
