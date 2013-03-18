package server.function;

import server.data.IData;

public class Function implements IFunction
{
	private ParseUdpPackage parseUdpPackage = new ParseUdpPackage(); 
	private static IData data = null;
	
	public Function(IData data)
	{
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
