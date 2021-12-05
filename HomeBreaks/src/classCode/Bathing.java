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

}