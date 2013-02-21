package Users;

import java.util.*;

import Exceptions.DALException;
import Persistance.PasswordChecker;

/**
 * 
 * @author Gruppe 18
 *
 */

public class UserFunction implements IUserFunction
{
	private List<IUser> users;
	
	public UserFunction()
	{
		users = new ArrayList<IUser>();
		createAdmin();
	}
	
	//Creates the admin at index 0 with standard info
	private void createAdmin()
	{
		users.add(new User(10, "Administrator", "a000001", "02324it!", 0));
	}
	
	@Override
	public String createUser(int userType, String name, String sNr) throws DALException 
	{
		User user = new User(name, sNr,  userType);
		users.add(user);
		return user.getPassword();
		
	}
	

		
	@Override
	public void updateUser(int id, String name, String sNr) throws DALException 
	{
		IUser user = getUser(id);
		user.setName(name);
		user.setStudyNr(sNr);

	}
	
	public void updateUser(int id, String username, String sNr, String password)
	throws DALException
	{
		IUser user = getUser(id);
		PasswordChecker passCheck = new PasswordChecker();
		//Index out of bounds
		if(user.getID()-10 < users.size() && user.getID() > 9)
		{
			//wrong password
			if(passCheck.isPasswordStrongEnough(password))
			{
				user.setPassword(password);
				user.setName(username);
				user.setStudyNr(sNr);
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
	
	public IUser getUser(int userID) throws DALException
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
	
	public Object[][] getUserList() throws DALException
	{
		Object[][] userData = new String[users.size()][4];
		//arraylist exists check
		if(users != null)
		{
			//inserts all the userdata in the arraylist
			for(int i = 0; i < users.size(); i++){
				String[] data = users.get(i).getUserData();
				userData[i] = data;
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

	@Override
	public void deleteUser(int userID) throws DALException {
		IUser user = getUser(userID);
		users.remove(user);
		
	}
	
	public boolean validPass(int id, String password) throws DALException{
		IUser user = getUser(id);
		return password.equals(user.getPassword());
	}

	

	
	
	
}
