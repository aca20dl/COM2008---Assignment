package classCode;

import java.util.*;

public class Sleeping{ // sleeping facility
	private boolean hasBedLinen;
	private boolean hasTowels;
	private ArrayList<Bedroom> bedrooms; // list of bedrooms, must have 1 or more
	private int totalBedrooms;
	private int totalBeds;
	private int totalSleepers;
	
	public boolean getHasBedLinen() {
		return hasBedLinen;
	}
	
	public boolean getHasTowels() {
		return hasTowels;
	}
	
	public ArrayList<Bedroom> getBedrooms(){
		return bedrooms;
	}
	
	public int getTotalBedrooms() {
		return totalBedrooms;
	}
	
	public int getTotalBeds() {
		return totalBeds;
	}
	
	public int getTotalSleepers() {
		return totalSleepers;
	}
	
	public Sleeping(boolean bl, boolean t, ArrayList<Bedroom> b) {
	hasBedLinen = bl;
	hasTowels = t;
	bedrooms = b;
	totalBedrooms = b.size();
	//getting total beds + sleepers for each bedroom in list
	int countBeds = 0;
	int countSleepers = 0;
	for(int i = 0; i < b.size(); i++) {
		countBeds = countBeds + b.get(i).getNumBeds();
		countSleepers = countSleepers + b.get(i).getNumSleepers();
	}
	totalBeds = countBeds;
	totalSleepers = countSleepers;
	}
	
	public String toString() {
		return "Has bedlinen: " + hasBedLinen + "\nHas Towels: " + hasTowels
				+ "\nTotal bedrooms: " + bedrooms.size() + "\nTotal Beds: " + 
				totalBeds + "\nTotal Sleepers: " + totalSleepers;
	}
	public static void main (String [] args) {
		Bedroom room1 = new Bedroom(new Bed("Double"),new Bed("Single"));
		Bedroom room2 = new Bedroom(new Bed("Bunk"),new Bed("Kingsize"));
		Bedroom room3 = new Bedroom(new Bed("Bunk"),null);
		
		ArrayList<Bedroom> rooms = new ArrayList<>();
		rooms.add(room1);
		rooms.add(room2);
		rooms.add(room3);
		
		Sleeping sleeping = new Sleeping(true,false,rooms);
		System.out.println(sleeping);
	}
}