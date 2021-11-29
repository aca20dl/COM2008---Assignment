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
	private Outdoor outdoor;
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
	
	/*
	//gets average review rating for 1 property
	public double avrgReview() {
		double sum,sum1,sum2,sum3,sum4,sum5;
		sum = sum1 = sum2 = sum3 = sum4 = sum5 = 0;
		
		ArrayList<Review> r = this.reviews;
		int size = r.size();
		if(!r.isEmpty()) {
			for(int i = 0; i < r.size(); i++) {
				sum = sum + r.get(i).cleanliness;
				sum1 = sum1 + r.get(i).communication;
				sum2 = sum2 + r.get(i).checkIn;
				sum3 = sum3 + r.get(i).accuracy;
				sum4 = sum4 + r.get(i).location;
				sum5 = sum5 + r.get(i).value;
			}
		}
		double totalSum = sum/size + sum1/size + sum2/size + sum3/size + sum4/size + sum5/size;
		
		return totalSum/5;
	}
	*/
	
	
	public String toString() {
		return address + "\nName: " + name + "\nDescription: " + description + 
				"\nGeneral location: " + genLoc + "\nBreakfast incl: " + 
				breakfast + "\nMax Guests: " + maxGuest + " No. beds: " + 
				numBed +" No. bedrooms: " + numBedroom + "  No. bathrooms: " 
				+ numBathroom;
	}
	
	public static void main (String [] args) {
		// make addresss
		Address ad = new Address("25","Green ln", "Sheffield", "s109ju");
		
		// make sleeping facility
		Bedroom room1 = new Bedroom(new Bed("Double"),new Bed("Single"));
		Bedroom room2 = new Bedroom(new Bed("Bunk"),new Bed("Kingsize"));
		Bedroom room3 = new Bedroom(new Bed("Bunk"),null);
		
		ArrayList<Bedroom> rooms = new ArrayList<>();
		rooms.add(room1);
		rooms.add(room2);
		rooms.add(room3);
		
		Sleeping sleeping = new Sleeping(true,false,rooms);
		
		//make bathing facility
		Bathroom broom1 = new Bathroom(true,false,false,true);
		Bathroom broom2 = new Bathroom(false,false,true,true);
		
		ArrayList<Bathroom> brooms = new ArrayList<>();
		brooms.add(broom1);
		brooms.add(broom2);
		
		Bathing bathing = new Bathing(true,true,false,brooms);
		
		//kitchen facility
		Kitchen kitchen = new Kitchen(true,false,true,true,false,true,true,false);
		
		//living facility
		Living living = new Living(true,false,false,false,true,true);
		
		//Utility facility
		Utility utility = new Utility(true,false,false,true,true,true);
		
		//outdoor facility
		Outdoor outdoor = new Outdoor(false,false,true,true,false);
		
		
		Property myProp = new Property(ad,"small house", "long description",
				"sheffield",false,1,2,3,4,0);
		
		System.out.println(myProp);
	}
}