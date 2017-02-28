package main;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		Unit unit = new Unit();
		while (reader.hasNext()) 
		{
			line = reader.nextLine();
			parseLine(line, unit);
			
			//insert into database or write to file instead of
			//print out
			System.out.println(unit.toString());
		}
		return line;
	}
	
	public void parseLine(String line, Unit unit)
	{
		
		String[] tokens = line.split(","); 

		if(line.contains("GPGGA"))
		{
			if(tokens.length == 15)
			{
				 try
				 {
					 String time = tokens[1];
					 String latitude = (Double.parseDouble(tokens[2])/100) + tokens[3];
					 String longitude = (Double.parseDouble(tokens[4])/100) + tokens[5];
					 
					 unit.setTime(time);
					 unit.setLatitude(latitude);
					 unit.setLongitude(longitude);
				 }
				 catch(NumberFormatException e)
				 {
					 //leave empty so no error message occurs at end of file
				 }
			
			}		
		}
		
		if(line.contains("GPZDA"))
		{
			if(tokens.length == 7)
			{
				String date = tokens[3] + "-" + tokens[2]+"-" + tokens[4];
				unit.setDate(date);
			}
		}
		
	}

}
