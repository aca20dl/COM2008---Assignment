package classCode;
import java.time.LocalDate;
import java.util.*;

public class Property{
	//variables for each property - public stuff
	Address address;
	private String name;
	private String description;
	private String genLoc;
	boolean breakfast;
	private int maxGuest;
	private int numBed;
	private int numBedroom;
	private int numBathroom;
	private Sleeping sleeping;
	private Bathing bathing;
	private Kitchen kitchen;
	private Living living;
	private Utility utility;
	private Outdoor outdoor;
	private ArrayList<ChargeBand> bands; // list of charge bands, maybe for different periods in the year?
	private ArrayList<Review> reviews; // list of reviews for each property
	
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
	
	public int getNumBed() {
		return numBed;
	}
	
	public int getNumBedroom() {
		return numBedroom;
	}
	
	public int getNumBathroom() {
		return numBathroom;
	}
	
	public Sleeping getSleeping() {
		return sleeping;
	}
	
	public Bathing getBathing() {
		return bathing;
	}
	
	public Kitchen getKitchen() {
		return kitchen;
	}
	
	public Living getLiving() {
		return living;
	}
	
	public Utility getUtility() {
		return utility;
	}
	
	public Outdoor getOutdoor() {
		return outdoor;
	}
	
	public ArrayList<ChargeBand> getBands(){
		return bands;
	}
	
	public ArrayList<Review> getReviews(){
		return reviews;
	}
	
	// constructor for property
	public Property(Address ad, String n, String d, String gl,boolean bf,Sleeping s,Bathing b,
			Kitchen k , Living l, Utility u, Outdoor o, 
			ArrayList<ChargeBand>cb,ArrayList<Review> r) {
		address = ad;
		name = n;
		description = d;
		genLoc = gl;
		breakfast = bf;
		maxGuest = s.getTotalSleepers();
		numBed = s.getTotalBeds();
		numBedroom = s.getTotalBedrooms();
		numBathroom = b.getTotalBathrooms();
		sleeping = s;
		bathing = b;
		kitchen = k;
		living = l;
		utility = u;
		outdoor = o;
		bands = cb;
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
				numBed +" No. bedrooms: " + numBedroom + "  No. bathrooms: " 
				+ numBathroom + "\nNo. reviews:" + reviews.size();
	}
	
	public static void main (String [] args) {
		// make addresss
		Address ad = new Address("25","Green ln", "Sheffield", "s109ju");
		
		//make reviews
		Review myRev = new Review("Happy",4,4,5,2,5,4);
		Review myRev2 = new Review("Upset",1,2,1,1,1,2);
		
		ArrayList<Review> reviews = new ArrayList<>();
		reviews.add(myRev);
		reviews.add(myRev2);
		
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
		
		//chargebands
		ChargeBand band1 = new ChargeBand(LocalDate.of(2021, 11, 01), LocalDate.of(2021, 12, 01),10,25,5);
		ChargeBand band2 = new ChargeBand(LocalDate.of(2021, 02, 01), LocalDate.of(2021, 11, 01),7,5,5);
		ArrayList<ChargeBand> bands = new ArrayList<>();
		bands.add(band1);
		bands.add(band2);
		
		
		Property myProp = new Property(ad,"small house", "long description",
				"sheffield",false,sleeping,bathing,kitchen,living
				,utility,outdoor,bands,reviews);
		
		System.out.println(myProp);
		System.out.println(myProp.avrgReview());
	}
}