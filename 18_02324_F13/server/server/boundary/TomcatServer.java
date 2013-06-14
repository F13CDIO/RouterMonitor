package server.boundary;

import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import server.main.MainServer;


public class TomcatServer
{
	
	public static void start()
	{
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);
		tomcat.enableNaming();
		
		File base = new File("WebContent");
		
		tomcat.setBaseDir(base.getAbsolutePath());
		try
		{
			tomcat.addWebapp("/", base.getAbsolutePath()).getDocBase();
		}
		catch (ServletException e1)
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
