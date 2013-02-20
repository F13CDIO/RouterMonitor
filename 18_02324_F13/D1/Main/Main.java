package Main;
import Exceptions.DALException;
import Users.*;
import Presentation.FrameMain;
import Scale.ScaleProgram;

public class Main 
{
	
	//TODO Der skal tilføjes menu interfacen.. 
	
	public static void main(String[] args)
	{
		IUserFunction users = new UserFunction();
		ScaleProgram scale = new ScaleProgram();
		FrameMain gui = new FrameMain(users);
		//IUser user = new User(11, "Johnny", "S111754", "12BAasAS12", 1);
		IUser user = new User(11, "Johnny", "S111754", "12345678lK", 1);
		
		gui.setVisible(true);
		try
		{ 
			users.createUser(user);
			System.out.println(users.getUser(10));
			System.out.println(users.getUser(11));
			System.out.println(users.getUserList());
			scale.setBrutto(100);
			scale.setTara(10);
			System.out.println(scale.getNetto());
			
			//users.updateUser(new User(10, "", "", "KLiohtdbgd", Rights.Teacher));
			//users.updateUserPassword(new User(10, "", "", "ReRFdf+hH", Rights.Admin));
		}
		catch(DALException e)
		{
			System.out.println(e.getMessage());
		}
	}
}
