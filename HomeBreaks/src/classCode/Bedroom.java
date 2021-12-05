package classCode;

public class Bedroom{
	private Bed bed1;
	private Bed bed2;
	private int numBeds;
	private int numSleepers;
	
	public Bed getBed1() {
		return bed1;
	}
	
	public Bed getBed2() {
		if(bed2 != null) {
			return bed2;
		}
		else
			return null;
	}
	
	public int getNumBeds() {
		return numBeds;
	}
	
	public int getNumSleepers() {
		return numSleepers;
	}
	
	public Bedroom(Bed b1, Bed b2) {
		bed1 = b1;
		bed2 = b2;
		if(bed2 == null) // each room must have a bed1 so only check if bed2 null
			numBeds = 1;
		else 
			numBeds = 2;
		
		int bed2Sleepers = 0;
		if(bed2 != null)
			bed2Sleepers = bed2.getSleepers();
		numSleepers = bed1.getSleepers() + bed2Sleepers;
	}
	
	public String toString() {
		return "bed1\n" + bed1 + "\nBed2\n" + bed2 + "\nNumber of beds in bedroom: " + numBeds
				+ "\nNumber of sleepers in bedroom: " + numSleepers;
	}
}