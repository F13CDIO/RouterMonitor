package server.function;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class RegexFromFile
{
	/**
	 * 
	 * @param file Can be "userAgent" or "host" 
	 * @return Returns the first line of the file
	 */
	public String ReadLine(String file)
	{
		String line = null;
		try
		{
			FileInputStream stream = new FileInputStream("RegEx files/"+file);
			DataInputStream in = new DataInputStream(stream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			line = br.readLine(); //reads the first line
			br.close();
		}
		catch(Exception e)
		{
			System.out.println("file " +file+ ".txt not found");
		}
		return line;
	}
}
