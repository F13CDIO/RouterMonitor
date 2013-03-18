package server.main;

import server.boundary.UI;
import server.data.Data;
import server.data.IData;
import server.function.*;

public class MainServer 
{

	public static void main(String[] args) 
	{
		IData data = new Data();
		IFunction function = new Function(data);
		UI ui = new UI(function);
		ui.initialize();
	}

}
