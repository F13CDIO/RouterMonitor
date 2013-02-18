package Users;

import java.util.Random;

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

	public Rights getRights()
	{
		return rights;
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



	//Password generator, som overholder DTU's standard, dvs. 6 tegn og tegn fra mindst 3 af de 4 klasser..
	//Skrevet af Chris

	public class PassGen {
		Random r = new Random();

		private int genInt9() {
			return r.nextInt(10);
		}

		private int genInt3() {
			return r.nextInt(4);        
		}

		private char genLetter() {
			return (char)(r.nextInt(26) + 'a');
		}

		private char genCap() {
			return (char)(r.nextInt(26) + 'A');
		}

		private char genSpecial() {
			String avalible = ".-_+!?=";

			return avalible.charAt(r.nextInt(avalible.length()));
		}

		public String genPassword() {
			int[] structure = new int[6];
			int[] numType = new int[4];
			int i, rInt3;
			String output = "";

			while((numType[0] + numType[1] + numType[2] + numType[3]) < 3)
			{
				//Reset type counter
				for(i=0; i<3; i++)
				{
					numType[i] = 0;
				}

				//Generate random structure
				for(i=0; i<6; i++)
				{
					rInt3 = genInt3();
					structure[i] = rInt3;
					numType[rInt3] = 1;
				}
			}

			for(i=0; i<6; i++)
			{
				if(structure[i] == 0)
				{
					output = output + genInt9();
				}
				else if(structure[i] == 1)
				{
					output = output + genLetter();
				}
				else if(structure[i] == 2)
				{
					output = output + genCap();
				}
				else
				{
					output = output + genSpecial();
				}
			}

			return output;
		}
	}

}
