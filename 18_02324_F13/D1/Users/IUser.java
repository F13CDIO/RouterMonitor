package Users;


public interface IUser 
{
	int getID();
	String getName();
	String getStudyNr();
	String getPassword();
	int getRights();
	void setRights(int rights);
	void setPassword(String password);
	String toString();	
	String[] getUserData();
}
