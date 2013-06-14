package server.boundary;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;


public class TomcatServer
{
	
	public static void start()
	{
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);
		File base = new File("WebContent");
		
		tomcat.setBaseDir(base.getAbsolutePath());
		
		Context rootCtx = tomcat.addContext("", base.getAbsolutePath());
		
		Tomcat.initWebappDefaults(rootCtx);
		
			try
			{
				tomcat.start();
				System.out.println(tomcat.getServer().getAddress());
			}
			catch (LifecycleException e)
			{
				System.out.println("Server not started");
				e.printStackTrace();
			}
	}

}
