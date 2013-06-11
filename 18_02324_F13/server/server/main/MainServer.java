package server.main;

import server.boundary.GUIsim;


import server.boundary.Boundary;
import server.data.Data;
import server.data.IData;
import server.function.*;

public class MainServer 
{

	public static void main(String[] args) 
	{
		new GUIsim().main(args);
		
		IData data = new Data();
		IFunction function = new Function(data);
		Boundary ui = new Boundary(function);
		ui.startServer();
	}

}
