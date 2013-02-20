package Persistance;

/*
 *  Klasse der checker om en password streng overholder DTU's regler for brug af tegnsaetning
 *  2013 Niclas Falck-Andersen
 */

public class PasswordChecker {

	private int REQUIRED_CHARS = 6;
	private int REQUIRED_CHAR_GROUPS = 3;
	private char[] ALLOWED_SPECIAL_CHARACTERS = {'.', '-', '_', '+', '!', '?', '='};

	int[] passwordGroups = new int [4]; // 4 is the number of char groups

	int passGroupsSatisfied = 0;
	int totalPassGroupChars = 0;
	
	// Den metode du skal kalde
	public boolean isPasswordStrongEnough(String password){
		parsePassword(password);
		groupCheck();
		if (passGroupsSatisfied >= REQUIRED_CHAR_GROUPS && totalPassGroupChars >= REQUIRED_CHARS)
			return true;
		else
			return false;
	}
	
	// help method that parses password
	private void parsePassword(String password){
		for (char c : password.toCharArray() ) {
			if (c >= 'a' && c <= 'z'){ // maybe Character.isLowerCase is more appropriate
				passwordGroups[0] ++;
			}
			if (c >= 'A' && c <= 'Z'){ // maybe Character.isUpperCase is better
				passwordGroups[1] ++;
			}
			if (Character.isDigit(c)){
				passwordGroups[2] ++;
			}
			for (char specialChar : ALLOWED_SPECIAL_CHARACTERS){ // special char check needed though cubic time
				if (c == specialChar){
					passwordGroups[3] ++;
				}
			}
		}
	}	
	
	// help method that counts chars in desired groups and total groups satisfied
	private void groupCheck(){
		for (int group : passwordGroups){
			if (group > 0){
				passGroupsSatisfied++;
				totalPassGroupChars += group;
			}
		}
	}
}
