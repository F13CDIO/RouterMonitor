package Users;

public class User implements IUser
{
	private int oprID;
	private String oprName;
	private String studyNr;
	private String password;
	private int rights;
	private PassGen passGen;
	private static int idCount = 11;

	
	/**
	 * Der er to constructor
	 */
	public User(String oprName, String studyNr, int rights)
	{
		passGen = new PassGen();
		this.oprID = idCount;
		this.oprName = oprName;
		this.studyNr = studyNr;
		this.rights = rights;
		this.password = passGen.genPassword();
		idCount++;
	}
	
	public User(int oprID, String oprName, String studyNr, String password, int rights)
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


	public void setRights(int rights)
	{
		this.rights = rights;
	}
	public int getRights()
	{
		return rights;
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

	@Override
	public void setName(String name) {
		oprName = name;	
	}
	
	@Override
	public void setStudyNr(String nr){
		studyNr = nr;
	}

}

	
