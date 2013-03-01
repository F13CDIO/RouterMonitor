package Data;

import Funktion.*;

public class DataTest 
{
	private IParseUdpPackage parse  = new ParseUdpPackage();;
	public static void main(String[] args)
	{
		DataTest testClass = new DataTest();
		testClass.funktionTest();
	}
	
	public void funktionTest()
	{
		String test1 = "T 10.1699.13655751 -> 69.171.235.1680 [AP]\n GET /ping?partition=236&cb=gks9 HTTP/1.1..Hot: 3-pct.channel.facebook.com..Connection: keep-alive..User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/";
		String test2 = "T 10.1699.136:55751 -> 69.171.235.16:80 [AP]\n GET /ping?partition=236&cb=gks9 HTTP/1.1..Host: 3-pct.channel.facebook.com..Connection: keep-alive..User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/";
		parse.parse(test2);
		parse.parse(test1);
		
        System.out.println("IP1 = " + parse.getData().getInIP(0));
        System.out.println("IP2 = " + parse.getData().getOutIP(0));
        System.out.println("Host = " + parse.getData().getHost(0));
        System.out.println("Subhost = " + parse.getData().getSubHost(0));
	}
}
