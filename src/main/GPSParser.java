package main;

import java.io.*;
import java.util.*;

public class GPSParser {

	public void getFile() 
	{
		String path = "res/gps.txt";
		File file;
		try 
		{
			file = new File(path);
			Scanner reader = new Scanner(file);
			readFile(reader);
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}

	}

	public String readFile(Scanner reader) throws IOException 
	{
		String line = null;
		while (reader.hasNext()) 
		{
			line = reader.nextLine();
			parseLine(line);
		}
		return line;
	}
	
	public void parseLine(String line)
	{
		String date = null;
		String time = null;
		String latitude = null;
		String longitude = null;
		String[] tokens = line.split(","); 
		if(line.contains("GPGGA"))
		{
			if(tokens.length == 15)
			{
				 time = tokens[1];
				 try
				 {
					 latitude = (Double.parseDouble(tokens[2])/100) + tokens[3];
					 longitude = (Double.parseDouble(tokens[4])/100) + tokens[5]; 
				 }
				 catch(NumberFormatException e)
				 {
					 
				 }
				
			}		
		}
		
		if(line.contains("GPZDA"))
		{
			if(tokens.length == 7)
			{
				for(int i=0; i < tokens.length; i++)
				{
					System.out.println(tokens[i]);
				}

			}
			
		}
	}

}
