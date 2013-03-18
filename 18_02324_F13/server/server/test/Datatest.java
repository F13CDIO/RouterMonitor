package server.test;

import java.io.*;

import server.data.*;
import server.function.*;


public class Datatest 
{
	public static void main(String[] args) {
		IData data1 = new Data();
		IFunction function = new Function(data1);
		IData data = Function.getData();
		IParseUdpPackage parse = new ParseUdpPackage();
		BufferedReader reader = null;

		long startTime = System.nanoTime();
		try
		{
			//ret filepath
			FileInputStream fstream = new FileInputStream("C:/Users/Mads/Dropbox/Grp 18/http_example_more_data.txt");
			DataInputStream in = new DataInputStream(fstream);
			reader = new BufferedReader(new InputStreamReader(in));
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
			reader.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		long calcTime =((System.nanoTime() - startTime)/ (long) Math.pow(10, 6));
		
		for(int i = 0; i < data.getDataList().size(); i++)
		{
			System.out.println(data.getDataList().get(i).toString() + "\n");
		}

		System.out.println(data.getDataList().size() + " packages");
		System.out.println("Time to real the data: " + calcTime + " ms.");
		
	}
}
