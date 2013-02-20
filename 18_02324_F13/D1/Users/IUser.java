package Users;


public interface IUser 
{
	int getID();
	String getName();
	void setName(String name);
	String getStudyNr();
	void setStudyNr(String nr);
	String getPassword();
	int getRights();
	void setRights(int rights);
	void setPassword(String password);
	String toString();	
	String[] getUserData();
}
