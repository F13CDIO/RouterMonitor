package Users;

import Users.User.Rights;

public interface IUser 
{
	int getID();
	String getName();
	String getStudyNr();
	String getPassword();
	int getRights();
	void setRights(Rights rights);
	void setPassword(String password);
	String toString();	
	String[] getUserData();
}
