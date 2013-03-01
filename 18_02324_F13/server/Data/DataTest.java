package Data;

import Funktion.*;

public class DataTest 
{
	IData data = new Data();
	IParseUdpPackage parse  = new ParseUdpPackage();;
	public static void main(String[] args)
	{
		DataTest testClass = new DataTest();
		testClass.funktionTest();
	}
	
	public void funktionTest()
	{
		parse.parse("T 10.16.99.136:56086 -> 173.194.65.139:80 [AP] \n"+
				 " POST /safebrowsing/downloads?pver=2.2&client=Safari&appver=6.0.2 HTTP/1.1..Host: safebrowsing.clients.google.com..User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/536.26.17 (KHTML, li");
	}
}
