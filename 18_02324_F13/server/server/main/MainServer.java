package server.main;

import server.boundary.Boundary;
import server.data.Data;
import server.data.IData;
import server.function.*;
/**
 * Where the server is started, and starts all the other components
 * @author 
 *
 */
public class MainServer 
{
	public static void main(String[] args) 
	{
		IData data = new Data();
		IFunction function = new Function(data);
		Boundary ui = new Boundary(function);
		ui.startServer();
	}

}
