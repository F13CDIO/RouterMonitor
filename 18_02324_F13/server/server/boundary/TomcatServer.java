package server.boundary;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;


public class TomcatServer
{
	
	public static void start()
	{
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);
		tomcat.getHost().setDeployOnStartup(true);
        tomcat.getHost().setAutoDeploy(true);
        tomcat.getHost().setAppBase("WebContent");
		tomcat.enableNaming();
		
		File base = new File("WebContent");
		
		tomcat.setBaseDir(base.getAbsolutePath());
		
		Connector httpsConnector = new Connector();
		
		httpsConnector.setPort(443);
		httpsConnector.setSecure(true);
		httpsConnector.setScheme("https");
		httpsConnector.setAttribute("clientAuth", false);
		httpsConnector.setAttribute("sslProtocol", "TLS");
		httpsConnector.setAttribute("protocol", "org.apache.coyote.http11.Http11NioProtocol");
		httpsConnector.setAttribute("SSLEnabled", true);
		httpsConnector.setAttribute("keystoreFile", base.getAbsolutePath()+"/grp18.cert");
		httpsConnector.setAttribute("keystorePass", "123123");
		
		Service tomcatService = tomcat.getService();
		tomcatService.addConnector(httpsConnector);
		
		Connector defaultConnector = tomcat.getConnector();
		defaultConnector.setRedirectPort(443);
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
