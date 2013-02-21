package Main;

import Exceptions.DALException;
import Users.*;
import Presentation.FrameMain;

public class Main 
{

	public static void main(String[] args)
	{
		// 3 layer
		IUserFunction userFunction = new UserFunction();
		FrameMain gui = new FrameMain(userFunction);
		gui.setVisible(true);
		
		//Admin login: id = 10, password = 02324it!
	}
}
