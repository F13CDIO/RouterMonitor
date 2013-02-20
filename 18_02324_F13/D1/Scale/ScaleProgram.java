package Scale;

public class ScaleProgram {
		
	int tara = 0;
	int brutto = 0;
	int netto = 0;
	
	/**
	 * Input tara weight in KG
	 * @param taraIKg
	 */
	public void setTara(int taraIKg)
	{
		tara = taraIKg;
	}
	/**
	 * input brutto weight in KG
	 * @param bruttoiKg
	 */
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
	/**
	 * This methode returns the netto weight 
	 * Netto = brutto - tara 
	 * @return Netto weight in
	 */
	public int getNetto()
	{
		netto = brutto - tara;
		return netto;
	}
		
}
