package main;

import java.util.*;

public class Unit {
	
	public String time;
	public String date;
	public String latitude;
	public String longitude;
	
	
	public Unit()
	{
		
	}
	
	public Unit(String time, String date, String latitude, String longitude)
	{
		this.time = time;
		this.date = date;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public void setTime(String time)
	{
		this.time = time;
	}
	
	public void setDate(String date)
	{
		this.date = date;
	}
	
	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}
	
	public void setLongitude(String longitude)
	{
		this.longitude = longitude;
	}
	
	public String toString()
	{
		return "Time: " + time + " Date: " + date + " Lat: " + latitude +" Long: " + longitude;
	}
	
}
