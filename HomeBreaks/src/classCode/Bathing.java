package classCode;

import java.util.*;

public class Bathing {
	private boolean hasHairDryer;
	private boolean hasShampoo;
	private boolean hasToiletPaper;
	private ArrayList<Bathroom> bathrooms;
	private int totalBathrooms;
	
	public boolean getHasHairDryer() {
		return hasHairDryer;
	}
	
	public boolean getHasShampoo() {
		return hasShampoo;
	}
	
	public boolean getHasToiletPaper() {
		return hasToiletPaper;
	}
	
	public ArrayList<Bathroom> getBathrooms(){
		return bathrooms;
	}
	
	public int getTotalBathrooms() {
		return totalBathrooms;
	}
	
	public Bathing(boolean h, boolean s, boolean t, ArrayList<Bathroom> b) {
		hasHairDryer = h;
		hasShampoo = s;
		hasToiletPaper = t;
		bathrooms = b;
		totalBathrooms = b.size();
	}
	
	public String toString() {
		return "Has Hairdryer: " + hasHairDryer + "\nHas Shampoo: "
				+ hasShampoo +  "\nHas toilet paper: " +
				hasToiletPaper + "\nTotal Bathrooms: " + totalBathrooms;
	}
	
	public static void main (String [] args) {
		Bathroom room1 = new Bathroom(true,false,false,true);
		Bathroom room2 = new Bathroom(false,false,true,true);
		
		ArrayList<Bathroom> rooms = new ArrayList<>();
		rooms.add(room1);
		rooms.add(room2);
		
		Bathing bathing = new Bathing(true,true,false,rooms);
		System.out.println(bathing);
	}
}