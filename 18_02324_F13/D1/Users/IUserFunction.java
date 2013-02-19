package Users;

import java.util.List;

import Exceptions.DALException;

public interface IUserFunction 
{
	/**
	 * Returns the user from the database
	 * @param userID The ID of the user
	 * @return The userobject
	 * @throws DALException
	 */
	User getUser(int userID) throws DALException;
	/**
	 * Returns the Arraylist of the users in the database
	 * @return The Arraylist of the users in the database
	 * @throws DALException
	 */
	List<User> getUserList() throws DALException;
	/**
	 * Creates a new user in the database
	 * @param user A new user to be created
	 * @throws DALException
	 */
	void createUser(User user) throws DALException;
	/**
	 * Updates the password for a user
	 * @param user The user to be updated
	 * @throws DALException
	 */
	void updateUser(User user) throws DALException;
}
