package Main;

import UI.UI;
import Data.Data;
import Data.IData;
import Funktion.*;

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
