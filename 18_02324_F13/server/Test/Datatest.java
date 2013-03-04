package Test;

import java.util.*;
import java.io.*;

import Funktion.*;
import Data.*;

public class Datatest 
{
	public static void main(String[] args) {
		DataLayer data = new DataLayer(new Data());
		IParseUdpPackage parse = new ParseUdpPackage();
		
		try
		{
		FileInputStream fstream = new FileInputStream("C:\\Users\\Gronex\\Dropbox\\Grp 18\\http_example.txt");
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String str;
		String parseString = "";
		while(true)
		{
			str = reader.readLine();
			if(str.substring(0, 1).equals("T") || str.substring(0, 3).equals("  G"))
			{
				parseString += str+"\n";
				System.out.println(parseString);
			}
			if(str.length() == 0)
			{
				System.out.println("jdgkjfdngj");
				parse.parse(parseString);
				parseString = "";
			}
		}
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		List list = data.getData().getDataList();
		System.out.println(list.size());
		for(int i = 0; i < list.size(); i++)
		{
			System.out.println("jdfh");
			System.out.println(list.get(i).toString());
		}
		
		
	}
}
