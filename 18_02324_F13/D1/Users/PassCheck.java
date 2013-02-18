
package Users;

//v1.1
//Tjekker om et password overholder DTU's standard.
//Skrevet af Chris

public class PassCheck {
    public boolean Check(String input)
    {
        int active;
        int i, numType = 0, letterType = 0, capType = 0, specialType = 0;
        
        for(i=0; i<input.length(); i++)
        {
            active = input.charAt(i);
            
            if(active >= 97 && active <= 97+25)
            {
                letterType = 1;
            }
            else if(active >= 65 && active <= 65+25)
            {
                capType = 1;
            }
            else if(active >= 48 && active <= 49+9)
            {
                numType = 1;
            }
            else if(active == 46 ||
                    active == 45 ||
                    active == 95 ||
                    active == 43 ||
                    active == 33 ||
                    active == 63 ||
                    active == 61)
            {
                specialType = 1;
            }
            else
            {
                //Hvis et tegn ikke tilhører nogen af ovenstående grupper, må det ikke bruges i password.
                return false;
            }
        }
        
        if((numType + letterType + capType + specialType) >= 3 && input.length() >= 6)
        {
            return true;
        }
        else
        {
            //Hvis passwordet ikke indeholder tegn fra mindst 3 grupper og er mindst 6 tegn langt, må det ikke bruges
            return false;
        }
    }
}
