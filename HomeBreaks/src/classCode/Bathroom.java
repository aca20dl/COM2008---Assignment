package classCode;

public class Bathroom{
	private boolean hasToilet;
	private boolean hasBath;
	private boolean hasShower;
	private boolean isShared;
	
	public boolean getHasToilet() {
		return hasToilet;
	}
	
	public boolean getHasBath() {
		return hasBath;
	}
	
	public boolean getHasShower() {
		return hasShower;
	}
	
	public boolean getIsShared() {
		return isShared;
	}
	
	public Bathroom(boolean t, boolean b, boolean shower, boolean shared) {
		hasToilet = t;
		hasBath = b;
		hasShower = shower;
		isShared = shared;
	}
	
	public String toString() {
		return "Has Toilet: " + hasToilet + "\nHas Bath: " + hasBath
				+ "\nHas Shower: " + hasShower + "\nIs Shared with host: " + isShared;
	}
}