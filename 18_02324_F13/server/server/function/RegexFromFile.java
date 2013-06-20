package server.function;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Reads the first line from a file 
 * in the folder RegEx files
 * @author Mads
 *
 */
public class RegexFromFile
{
	/**
	 * 
	 * @param file Can be "userAgent" or "host" 
	 * @return Returns the first line of the file
	 */
	public static String ReadLine(String file)
	{
		String line = null;
		try
		{
			String path = "/RegEx files/" + file;
			InputStream inputStream = RegexFromFile.class.getResourceAsStream(path);
			Scanner scanner = new Scanner(inputStream);
			line = scanner.nextLine();
			scanner.close();
		}
		catch(Exception e)
		{
			System.out.println("file " +file+ " not found");
		}
		return line;
	}
}
