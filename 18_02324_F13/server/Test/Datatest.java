package Test;

import java.io.*;

import Funktion.*;
import Data.*;

public class Datatest 
{
	public static void main(String[] args) {
		DataLayer data = new DataLayer(new Data());
		IParseUdpPackage parse = new ParseUdpPackage();

		long startTime = System.nanoTime();
		try
		{
			//ret filepath
			FileInputStream fstream = new FileInputStream("C:/Users/Gronex/Dropbox/Grp 18/http_example_more_data.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String str;
			String parseString = "";
			while((str = reader.readLine()) != null)
			{
				if(str.substring(0, 1).equals("T") || str.substring(0, 3).equals("  G"))
				{
					parseString += str+"\n";
					//System.out.println(parseString);
				}
				else
				{
					parse.parse(parseString);
					//System.out.println(parseString);
					parseString = "";
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		long calcTime =((System.nanoTime() - startTime)/ (long) Math.pow(10, 6));
		
		for(int i = 0; i < data.getData().getDataList().size(); i++)
		{
			System.out.println(data.getData().getDataList().get(i).toString() + "\n");
		}

		System.out.println(data.getData().getDataList().size() + " packages");
		System.out.println("Time to real the data: " + calcTime + " ms.");
		
	}
}
