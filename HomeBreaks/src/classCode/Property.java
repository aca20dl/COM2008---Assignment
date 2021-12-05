package classCode;
import java.time.LocalDate;
import java.util.*;

public class Property{
	//variables for each property - public stuff
	private Address address;
	private String name;
	private String description;
	private String genLoc;
	boolean breakfast;
	private int maxGuest;
	private int numBed;
	private int numBedroom;
	private int numBathroom;
	private double averageRating;
	
	//get methods for variables
	public Address getAddress() {
		return address;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getGenLoc() {
		return genLoc;
	}
	
	public boolean getBreakfast() {
		return breakfast;
	}
	
	public int getMaxGuest() {
		return maxGuest;
	}
	
	public int getNumBed() {
		return numBed;
	}
	
	public int getNumBedroom() {
		return numBedroom;
	}
	
	public int getNumBathroom() {
		return numBathroom;
	}

	public double getAverageRating() {
		return averageRating;
	}
	
	// constructor for property
	public Property(Address ad, String n, String d, String gl,boolean bf,int mg,int beds
			,int bedrooms,int bathrooms,double ar) {
		address = ad;
		name = n;
		description = d;
		genLoc = gl;
		breakfast = bf;
		maxGuest = mg;
		numBed = beds;
		numBedroom = bedrooms;
		numBathroom = bathrooms;
		averageRating = ar;
	}
	
	public String toString() {
		return address + "\nName: " + name + "\nDescription: " + description + 
				"\nGeneral location: " + genLoc + "\nBreakfast incl: " + 
				breakfast + "\nMax Guests: " + maxGuest + " No. beds: " + 
				numBed +" No. bedrooms: " + numBedroom + "  No. bathrooms: " 
				+ numBathroom;
	}
}