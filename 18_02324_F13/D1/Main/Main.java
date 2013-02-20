package Main;
import Exceptions.DALException;
import Users.*;
import Users.User.Rights;
import Scale.ScaleProgram;

public class Main 
{
	
	//TODO Der skal tilføjes menu interfacen.. 
	
	public static void main(String[] args)
	{
		IUserFunction users = new UserFunction();
		ScaleProgram scale = new ScaleProgram();
		try
		{ 
			users.createUser(new User(11, "John", "S111754", "12BAasAS12", Rights.Student));
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
