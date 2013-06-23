package testPackage;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.data.Data;
import server.data.IData;
import server.function.Function;
import server.function.IFunction;

public class IFunctionTest {

	public String srcIP = "10.16.98.67";
	public String destIP = "69.171.235.16";
	public String url = "0-pct.channel.facebook.com", host = "facebook.com", subHost = "channel";
	
	public String userAgentString = "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31", userAgent = "Chrome";
	
	public String dataString = srcIP + "\t" + destIP + "\t" + url + "\t" + userAgentString;
	
	public String wrongUserAgent = "ERROR";
	public String errorDataString = srcIP + "\t" + destIP + "\t" + url + "\t" + wrongUserAgent;
	
	public IData data = new Data();
	public IFunction function = new Function(data);
	
	@Before
	public void beforeTest()
	{
		function.parse(dataString);
	}
	
	
	@Test
	public void testScrIP() {
		assertEquals(Function.getDatalayer().getDataPackage().getScourceIP(), srcIP);
	}

	@Test
	public void testDestIP() {
		assertEquals(Function.getDatalayer().getDataPackage().getDestinationIP(), destIP);
	}

	@Test
	public void testHost() {
		assertEquals(Function.getDatalayer().getDataPackage().getHost(), host);
	}

	@Test
	public void testSubHost() {
		assertEquals(Function.getDatalayer().getDataPackage().getSubHost(), subHost);
	}

	@Test
	public void testUserAgent() {
		assertEquals(Function.getDatalayer().getDataPackage().getUserAgent(), userAgent);
	}

	@Test
	public void testWrongUserAgent() {
		Function.getDatalayer().getDataPackage();
		function.parse(errorDataString);
		assertTrue(Function.getDatalayer().isEmpty());
	}
}
