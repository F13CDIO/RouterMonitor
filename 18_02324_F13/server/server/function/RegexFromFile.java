package server.function;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class RegexFromFile
{
	/**
	 * 
	 * @param file Can be "userAgent" or "host" 
	 * @return Returns the first line of the file
	 * @throws IOException if it can't close in finally
	 */
	public String ReadLine(String file)
	{
		String line = null;
		
		FileInputStream stream = null;
		DataInputStream in = null;
		BufferedReader br = null;
		try
		{
			stream = new FileInputStream("RegEx files/"+file);
			in = new DataInputStream(stream);
			br = new BufferedReader(new InputStreamReader(in));
			line = br.readLine(); //reads the first line
		}
		catch(IOException e)
		{
			System.out.println("file " +file+ ".txt not found");
		}
		finally
		{
			try//try for at lukke?? alternativt er throws, men så skal der try længere oppe
			{
				br.close();
				in.close();
				stream.close();
			}
			catch(IOException e)
			{
				System.out.println(e.getMessage());
			}
		}
		return line;
	}
}
