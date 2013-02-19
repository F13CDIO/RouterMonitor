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
			users.createUser(new User(10, "", "", "", Rights.Student));
			System.out.println(users.getUser(10));
			users.updateUser(new User(10, "", "", "", Rights.Teacher));
			users.updateUserPassword(new User(10, "", "", "ReRFdf+hH", Rights.Admin));
		}
		catch(DALException e)
		{
			System.out.println(e.getMessage());
		}
	}
}
