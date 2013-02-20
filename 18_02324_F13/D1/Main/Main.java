package Main;
import Exceptions.DALException;
import Users.*;
import Users.User.Rights;

public class Main 
{
	
	//TODO Der skal tilføjes menu interfacen.. 
	
	public static void main(String[] args)
	{
		IUserFunction users = new UserFunction();
		try
		{ 
			users.createUser(new User(11, "John", "S111754", "12BAasAS12", Rights.Student));
			System.out.println(users.getUser(10));
			System.out.println(users.getUser(11));
			System.out.println(users.getUserList());
			//users.updateUser(new User(10, "", "", "KLiohtdbgd", Rights.Teacher));
			//users.updateUserPassword(new User(10, "", "", "ReRFdf+hH", Rights.Admin));
		}
		catch(DALException e)
		{
			System.out.println(e.getMessage());
		}
	}
}
