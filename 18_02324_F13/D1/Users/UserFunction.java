package Users;

import java.util.*;

import Exceptions.DALException;
import Persistance.PasswordChecker;
import Users.User.Rights;

public class UserFunction implements IUserFunction
{
	private List<User> users;
	
	public UserFunction()
	{
		users = new ArrayList<User>();
		createAdmin();
	}
	
	private void createAdmin()
	{
		users.add(new User(10, "Administrator", "a000001", "02324it!", Rights.Admin));
	}
	
	public void createUser(User user)
	throws DALException
	{	
		int oprID = 10 + users.size();
		if(oprID < 100 && oprID > 10)
		{
			users.add(new User(oprID, user.getName(), user.getStudyNr(), user.getRights()));
		}
		else
		{
			throw new DALException(oprID);
		}
	}
	
	public void updateUser(User user)
	throws DALException
	{
		if(user.getID()-10 < users.size() && user.getID() > 9)
		{	
			if(user.getRights() == Rights.Admin || user.getRights() == Rights.Student || user.getRights() == Rights.Teacher)
			{
				users.get(user.getID() - 10).setRights(user.getRights());
			}
			else 
			{
				throw new DALException(user.getRights());
			}
		
		}
		else
		{
			throw new DALException(user.getID());
		}
	}
		
	
	public void updateUserPassword(User user)
	throws DALException
	{
		PasswordChecker passCheck = new PasswordChecker();
		//TODO Denne metode skal kunne kaste en exception hvis koden er forkert eller indexOutOfBounds
		//Der bliver oprette en klasse eller en metode til at kontroller dette..
		if(user.getID()-10 < users.size() && user.getID() > 9)
		{
		if(passCheck.isPasswordStrongEnough(user.getPassword()))
		{
			users.get(user.getID() - 10).setPassword(user.getPassword());
		}
		else
		{
			throw new DALException(passCheck.isPasswordStrongEnough(user.getPassword()));
		}
		}
		else
		{
			throw new DALException(user.getID());
		}
	}
	
	public User getUser(int userID)
	throws DALException
	{ 
		if(userID > 9 && users.size() > (userID - 10))
		{
		return users.get(userID - 10);
		}
		else
		{
			throw new DALException(userID);
		}
	}
	
	public List<User> getUserList()
	throws DALException
	{
		if(users != null)
		{
			return users;
		}
		else
		{
			throw new DALException();
		}
	}
	
	
}
