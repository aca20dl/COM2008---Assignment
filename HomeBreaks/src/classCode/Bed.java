package classCode;

public class Bed {
	public enum BedType {SINGLE,DOUBLE,KINGSIZE,BUNK};
	private BedType type;
	private int numSleepers;

	public BedType getType() {
		return type;
	}
	
	public int getSleepers() {
		return numSleepers;
	}
	
	public int sleepers(BedType b) {
		int sleepers = 0;
		if(b.equals(BedType.SINGLE))
			sleepers = 1;
		else
			sleepers = 2;
		return sleepers;
	}
	
	public Bed(String b) {
		type = BedType.valueOf(b.toUpperCase());
		numSleepers = sleepers(type);
	}
	
	public String toString() {
		return "bedType: " + type.name().toLowerCase() + 
				"\nNo. Sleepers in bed: " + numSleepers;
	}
}