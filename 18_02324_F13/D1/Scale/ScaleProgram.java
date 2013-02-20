package Scale;

public class ScaleProgram {
	
	String userID = "";
	String password = "";
	
	public ScaleProgram(String userID, String password)
	{
		this.userID = userID;
		this.password = password;
	}
	
	int tara = 0;
	int brutto = 0;
	int netto = 0;
	
	
	public void setTara(int taraIKg)
	{
		tara = taraIKg;
	}
	
	public void setBrutto(int bruttoiKg)
	{
		brutto = bruttoiKg;
	}
	
	public int getTara()
	{
		return tara;
	}
	
	public int getBrutto()
	{
		return brutto;
	}
	
	public int getNetto()
	{
		netto = brutto - tara;
		return netto;
	}
	
	

	
	
}
