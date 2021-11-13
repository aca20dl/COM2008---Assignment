package classCode;
import java.util.*;

public class Property{
	//variables for each property - public stuff
	Address address;
	private String name;
	private String description;
	private String genLoc;
	boolean breakfast;
	int maxGuest;
	int beds;
	int bedrooms;
	int bathrooms;
	ArrayList<Review> reviews; // list of reviews for each property
	
	//get methods for variables
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
	
	public int getBeds() {
		return beds;
	}
	
	public int getBedrooms() {
		return bedrooms;
	}
	
	public int getBathrooms() {
		return bathrooms;
	}
	
	public ArrayList<Review> getReviews(){
		return reviews;
	}
	
	// constructor for property
	public Property(Address ad, String n, String d, String gl,boolean bf,
			int mg, int bds, int bedr, int bath, ArrayList<Review> r) {
		address = ad;
		name = n;
		description = d;
		genLoc = gl;
		breakfast = bf;
		maxGuest = mg;
		beds = bds;
		bedrooms = bedr;
		bathrooms = bath;
		reviews = r;
	}
	
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
	
	
	public String toString() {
		return address + "\nName: " + name + "\nDescription: " + description + 
				"\nGeneral location: " + genLoc + "\nBreakfast incl: " + 
				breakfast + "\nMax Guests: " + maxGuest + " No. beds: " + 
				beds +" No. bedrooms: " + bedrooms + " + No. bathrooms: " 
				+ bathrooms + "\nNo. reviews:" + reviews.size();
	}
	
	public static void main (String [] args) {
		Address ad = new Address("25","Green ln", "Sheffield", "s109ju");
		
		Review myRev = new Review("Happy",4,4,5,2,5,4);
		Review myRev2 = new Review("Upset",1,2,1,1,1,2);
		
		ArrayList<Review> reviews = new ArrayList<>();
		reviews.add(myRev);
		reviews.add(myRev2);
		
		Property myProp = new Property(ad,"small house", "long description",
				"sheffield",false,6,2,3,2,reviews);
		
		System.out.println(myProp);
		System.out.println(myProp.avrgReview());
	}
}