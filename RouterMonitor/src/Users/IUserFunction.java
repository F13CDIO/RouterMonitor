package Users;

import java.util.List;

import Exceptions.DALException;

public interface IUserFunction 
{
	User getUser(int userID) throws DALException;
	List<User> getUserList() throws DALException;
	void createUser(User user) throws DALException;
	void updateUser(User user) throws DALException;
	void updateUserPassword(User user) throws DALException;
	
	//TODO Evt tilføje en updatePassword Exception + funktion
}
