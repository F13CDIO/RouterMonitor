package Users;

import java.util.*;

import Exceptions.DALException;
import Persistance.PasswordChecker;
import Users.User.Rights;

/**
 * 
 * @author Gruppe 18
 *
 */

public class UserFunction implements IUserFunction
{
	private List<User> users;
	
	public UserFunction()
	{
		users = new ArrayList<User>();
		createAdmin();
	}
	
	//Creates the admin at index 0 with standard info
	private void createAdmin()
	{
		users.add(new User(10, "Administrator", "a000001", "02324it!", Rights.Admin));
	}
	
	public void createUser(User user) throws DALException
	{	
		int oprID = 10 + users.size();
		if(oprID < 100 && oprID > 10)
		{
			//add a new user to the arraylist
			users.add(new User(oprID, user.getName(), user.getStudyNr(), user.getRights()));
		}
		else
		{
			//out of bounds
			throw new DALException(oprID);
		}
	}
	
//	public void updateUser(User user) throws DALException
//	{
//		
//		if(user.getID()-10 < users.size() && user.getID() > 9)
//		{	
//			if(user.getRights() == Rights.Admin || user.getRights() == Rights.Student || user.getRights() == Rights.Teacher)
//			{
//				users.get(user.getID() - 10).setRights(user.getRights());
//			}
//			else 
//			{
//				throw new DALException(user.getRights());
//			}
//		
//		}
//		else
//		{
//			throw new DALException(user.getID());
//		}
//	}
		
	
	public void updateUser(User user)
	throws DALException
	{
		PasswordChecker passCheck = new PasswordChecker();
		//Index out of bounds
		if(user.getID()-10 < users.size() && user.getID() > 9)
		{
			//wrong password
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
	
	public User getUser(int userID) throws DALException
	{ 
		//Index out of bounds check
		if(userID > 9 && users.size() > (userID - 10))
		{
			return users.get(userID - 10);
		}
		else
		{
			throw new DALException(userID);
		}
	}
	
	public List<String[]> getUserList() throws DALException
	{
		List<String[]> userData = new ArrayList<String[]>();
		//arraylist exists check
		if(users != null)
		{
			//inserts all the userdata in the arraylist
			for(int i = 0; i < users.size(); i++){
				userData.add(users.get(i).getUserData());
			}
			return userData;
		}
		else
		{
			throw new DALException();
		}
	}
	
	public int checkLogin(int id, String password) throws DALException{
		IUser checkUser = getUser(id);
		if(checkUser.getPassword().equals(password))
			return checkUser.getRights();
		else
			return -1;
		
	}
	
	public String[] userData(int id) throws DALException{
		IUser user = getUser(id);
		return user.getUserData();
	}
	
	
}
