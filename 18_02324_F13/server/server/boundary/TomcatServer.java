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
		tomcat.setPort(80);
		tomcat.getHost().setDeployOnStartup(true);
        tomcat.getHost().setAutoDeploy(true);
        tomcat.getHost().setAppBase("WebContent");
		tomcat.enableNaming();
		
		File base = new File("WebContent");
		
		tomcat.setBaseDir(base.getAbsolutePath());
		try
		{
			Context ctx = tomcat.addWebapp("/", base.getAbsolutePath());
			File configFile = new File("WebContent/META-INF/context.xml");
	        ctx.setConfigFile(configFile.toURI().toURL());
			
		}
		catch (Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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
