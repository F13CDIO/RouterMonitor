package logbean;

public class LoginBean {
	
	private static LoginBean singletonInst;
	
	public LoginBean()
	{
		
	}

	public static LoginBean getInstance()
	{
		if (singletonInst == null)
		{
			singletonInst = new LoginBean();
		}
		return singletonInst;
	}
	
	String username;
	String password;
	String test="HESTE henning";
	
	public void setUsername(String username) {
		this.username = username;
		System.out.println("det satte username i loginbean er:"+username);
	}
	
	public String getUsername() {
		System.out.println("nu blev username gettet:"+username);
		return username;
	}
	
	public void setPassword(String password) {
		this.password = password;
		System.out.println("det satte password i loginbean er:"+password);
	}
	
	public String getPassword() {
		System.out.println("nu blev password gettet:"+password);
		return password;
	}
	
	public String getTest()
	{
		return test;
	}
	
}