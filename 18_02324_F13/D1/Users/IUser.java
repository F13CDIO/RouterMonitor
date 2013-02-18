package Users;

import Users.User.Rights;

public interface IUser 
{
	int getID();
	String getName();
	String getStudyNr();
	String getPassword();
	Rights getRights();
	void setRights(Rights rights);
	void setPassword(String password);
	
}
