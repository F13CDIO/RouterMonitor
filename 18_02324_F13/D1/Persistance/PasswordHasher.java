package Persistance;
import java.security.spec.KeySpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.SecretKeyFactory;
import java.util.Random;

/*
 *  2012 Niclas Falck-Andersen
 *  This class creates a secret key spec that we use to validate the user. The static method
 *  generateSalt() is needed when a new user is created. Existing users need their salt from the db
 */
public class PasswordHasher {
	
	private static String salt;
    private int iterationCount = 1024;
    private int keyStrength = 128;
    private SecretKey key;
    private static int SALT_LENGTH = 32;
    
    public PasswordHasher(String passPhrase, String salt) throws Exception {
    	this.salt = salt;
    	SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    	KeySpec spec = new PBEKeySpec(passPhrase.toCharArray(), salt.getBytes(), iterationCount, keyStrength);
    	SecretKey tmp = factory.generateSecret(spec);
        key = new SecretKeySpec(tmp.getEncoded(), "AES");
    }
    public String getSalt(){
    	return salt;
    }
    public String getKey(){
    	return key.toString();
    }
    
    // static so you can call the method before creating a new password that takes a salt in constructor
    public static String generateSalt(){
    	Random rnd = new Random(System.currentTimeMillis());
    	byte[] tmpSalt = new byte[SALT_LENGTH];
    	rnd.nextBytes(tmpSalt);
    	String str = tmpSalt.toString().substring(3); // 3 is the weird offset that gets printed when array gets converted
    	return str;
    }    	
}

    

