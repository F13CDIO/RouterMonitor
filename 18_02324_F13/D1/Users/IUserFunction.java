package Users;



import Exceptions.DALException;

public interface IUserFunction 
{
	/**
	 * Returns the user from the database
	 * @param userID The ID of the user
	 * @return The userobject
	 * @throws DALException
	 */
	IUser getUser(int userID) throws DALException;
	/**
	 * Returns the Arraylist of the users in the database
	 * @return The Arraylist of the users in the database
	 * @throws DALException
	 */
	Object[][] getUserList() throws DALException;
	
	
	String createUser(int userType, String name, String sNr) throws DALException;
	
	/**
	 * Creates a new user in the database
	 * @param user A new user to be created
	 * @throws DALException
	 */
	void createUser(IUser user) throws DALException;
	/**
	 * Updates the password for a user
	 * @param user The user to be updated
	 * @throws DALException
	 */
	void updateUser(int id, String name,String sNr, String password) throws DALException;
	
	void updateUser(int id, String name,String sNr) throws DALException;
	
	/**
	 * Delete a user
	 * @param userID
	 * @throws DALException
	 */
	void deleteUser(int userID) throws DALException;
	
	/**
	 * Check the user ID and the password of the user
	 * @param id
	 * @param password
	 * @return
	 * @throws DALException
	 */
	int checkLogin(int id, String password) throws DALException;
	
	String[] userData(int id) throws DALException;
	
	boolean validPass(int id, String password) throws DALException;
	
	
}
