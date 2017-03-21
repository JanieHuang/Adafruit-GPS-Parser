package main;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class GPSParser {

	public boolean readDate = false;
	
	public void getFile() 
	{
		String path = "res/gps.txt";
		//String path = "res/gpslog.txt";
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
			readDate = getLatitude(tokens, unit);
		}
		
		else if(line.contains("GPZDA") && readDate == true)
		{
			if(tokens.length == 7)
			{
				String date = tokens[3] + "-" + tokens[2]+"-" + tokens[4];
				unit.setDate(date);
			}
		}
		
	}
	
	
	private boolean getLatitude(String[] tokens,Unit unit)
	{
		if(!tokens[2].equals(""))
		{
			 try
			 {
				 String time = tokens[1];
				 DecimalFormat f = new DecimalFormat("##.00000");
				 double preLat = Double.parseDouble(tokens[2])/100;
				 double preLong = Double.parseDouble(tokens[4])/100;
				 preLat = Math.round(preLat * 100000.0) / 100000.0;
				 preLong = Math.round(preLong * 100000.0) / 100000.0;
				 String latitude = Double.toString(preLat);
				 String longitude = Double.toString(preLong);
				 if(tokens[3].equals("S"))
				 {
					 latitude = "-" + latitude;
				 }
				 
				 if(tokens[5].equals("W"))
				 {
					 longitude = "-" + longitude;
				 }
				 
				 unit.setTime(time);
				 unit.setLatitude(latitude);
				 unit.setLongitude(longitude);
			 }
			 catch(NumberFormatException e)
			 {
				 //leave empty so no error message occurs at end of file
			 }
			 return true;
		}
		return false;
	}

}
