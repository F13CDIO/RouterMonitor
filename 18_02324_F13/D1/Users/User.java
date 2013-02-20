package Users;

public class User implements IUser
{
	private int oprID;
	private String oprName;
	private String studyNr;
	private String password;
	private Rights rights;
	private PassGen passGen;

	public enum Rights
	{
		Admin, Teacher, Student;
	}
	/**
	 * Der er to constructor
	 */
	public User(int oprID, String oprName, String studyNr, Rights rights)
	{
		passGen = new PassGen();
		this.oprID = oprID;
		this.oprName = oprName;
		this.studyNr = studyNr;
		this.password = passGen.genPassword();
		this.rights = rights;
	}
	
	public User(int oprID, String oprName, String studyNr, String password, Rights rights)
	{
		this.oprID = oprID;
		this.oprName = oprName;
		this.studyNr = studyNr;
		this.password = password;
		this.rights = rights;
	}

	public int getID()
	{
		return oprID;
	}

	public String getName()
	{
		return oprName;
	}

	public String getStudyNr()
	{
		return studyNr;
	}

	public int getRights()
	{
		switch(rights){
			case Admin:
				return 0;
			case Student:
				return 1;
			case Teacher:
				return 2;
		}
		return -1;
	}

	public void setRights(Rights rights)
	{
		this.rights = rights;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;  
	}
	
	public String toString()
	{
		return "ID: "+ oprID + ", Name: " + oprName + ", Rights: " + rights;
	}
	/**
	 * returns all the user data in a string array
	 */
	public String[] getUserData(){
		String[] userData = new String[4];
		userData[0] = Integer.toString(getID());
		userData[1] = getName();
		userData[2] = getStudyNr();
		userData[3] = Integer.toString(getRights());
		return userData;
		
		
	}
}
