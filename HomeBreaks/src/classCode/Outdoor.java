package classCode;

public class Outdoor{
	private boolean hasFreeParking;
	private boolean hasRoadParking;
	private boolean hasPaidCarPark;
	private boolean hasPatio;
	private boolean hasBarbeque;
	
	public boolean getHasFreeParking() {
		return hasFreeParking;
	}
	
	public boolean getHasRoadParking() {
		return hasRoadParking;
	}
	
	public boolean getHasPaidCarPark() {
		return hasPaidCarPark;
	}
	
	public boolean getHasPatio() {
		return hasPatio;
	}
	
	public boolean getHasBarbeque() {
		return hasBarbeque;
	}
	
	public Outdoor(boolean fp, boolean rp, boolean pp, 
			boolean p, boolean b) {
		hasFreeParking = fp;
		hasRoadParking = rp;
		hasPaidCarPark = pp;
		hasPatio = p;
		hasBarbeque = b;
	}
	
	public String toString() {
		return "Has Free on sight parking: " + hasFreeParking +
				"\nHas on road parking: " + hasRoadParking + "\nHas paid car park: " 
				+ hasPaidCarPark + "\nHas a Patio: " + hasPatio + "\nHas a barbeque: " +
				hasBarbeque;
	}
	
}