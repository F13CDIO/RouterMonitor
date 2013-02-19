
package Users;

import java.util.Random;
 

/**
 * Password generator - Generates passwords of desired length, 
 * that complies with DTU's requirements for passwords
 *  (3 of 4 "character groups", min 6 characters)
 * @author Chris Johansen 2013
 *
 */

public class PassGen {
    private static int PASS_LENGTH = 8;
    private static int REQUIRED_GROUPS = 3;
    private static String AVALAIBLE_SPECIALS = ".-_+!?=";
    
    Random r = new Random();
    
    /**
     * 
     * @return Generate random lowercase letter
     */
    private char genLower() {
        return (char)(r.nextInt(26) + 'a');
    }
    
    /**
     * 
     * @return Generate random uppercase letter
     */
    private char genUpper() {
        return (char)(r.nextInt(26) + 'A');
    }
    
    /**
     * 
     * @return Generate random character from the specified "special characters"
     */
    private char genSpecial() {
        return AVALAIBLE_SPECIALS.charAt(r.nextInt(AVALAIBLE_SPECIALS.length()));
    }
    
	
    /**
     * Generate the password
     * @return The password
     */
    public String genPassword() {
        int[] structure = new int[PASS_LENGTH];
        int[] numType = new int[4];
        int i, rInt3;
        String output = "";
        
        //Generates random sequences of "character-types", until a random sequence of at least 3 types is found
        while((numType[0] + numType[1] + numType[2] + numType[3]) < REQUIRED_GROUPS)
        {
            //Reset character-type counter
            for(i=0; i<4; i++)
            {
                numType[i] = 0;
            }

            //Generate random sequences
            for(i=0; i<PASS_LENGTH; i++)
            {
                rInt3 = r.nextInt(4);
                structure[i] = rInt3;
                numType[rInt3] = 1;
            }
        }
        
        for(i=0; i<PASS_LENGTH; i++)
        {
            //Adds random character according to the created sequence, to the final password string (output)
            if(structure[i] == 0)
            {
                output = output + r.nextInt(10);
            }
            else if(structure[i] == 1)
            {
                output = output + genLower();
            }
            else if(structure[i] == 2)
            {
                output = output + genUpper();
            }
            else
            {
                output = output + genSpecial();
            }
        }
        
        return output;
    }
}
