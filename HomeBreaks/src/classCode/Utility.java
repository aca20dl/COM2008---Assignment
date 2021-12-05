package classCode;

public class Utility{
	private boolean hasHeating;
	private boolean hasWashingMachine;
	private boolean hasDryingMachine;
	private boolean hasFireExtinguisher;
	private boolean hasSmokeAlarm;
	private boolean hasFirstAidKit;
	
	public boolean getHasHeating() {
		return hasHeating;
	}
	
	public boolean getHasWashingMachine() {
		return hasWashingMachine;
	}
	
	public boolean getHasDryingMachine() {
		return hasDryingMachine;
	}
	
	public boolean getHasFireExtinguisher() {
		return hasFireExtinguisher;
	}
	
	public boolean getHasSmokeAlarm() {
		return hasSmokeAlarm;
	}
	
	public boolean getHasFirstAidKit() {
		return hasFirstAidKit;
	}
	
	public Utility(boolean h, boolean w, boolean d, boolean f,
			boolean s, boolean first) {
		hasHeating = h;
		hasWashingMachine = w;
		hasDryingMachine = d;
		hasFireExtinguisher = f;
		hasSmokeAlarm = s;
		hasFirstAidKit = first;
	}
	
	public String toString() {
		return "Has Heating: " + hasHeating + "\nHas a Washing Machine: " + hasWashingMachine + 
				"\nHas a Drying Machine: " + hasDryingMachine + "\nHas a Fire Extinguisher: " + 
				hasFireExtinguisher + "\nHas a Smoke alarm: " + hasSmokeAlarm
				+ "\nHas a First Aid Kit: " + hasFirstAidKit;
	}
}