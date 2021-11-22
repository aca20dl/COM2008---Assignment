package classCode;

public class Kitchen{
	private boolean hasFridge;
	private boolean hasMicrowave;
	private boolean hasOven;
	private boolean hasStove;
	private boolean hasDishwasher;
	private boolean hasTableware;
	private boolean hasCookware;
	private boolean hasBasicProv;
	
	public boolean getHasFridge() {
		return hasFridge;
	}
	
	public boolean getHasMicrowave() {
		return hasMicrowave;
	}
	
	public boolean getHasOven() {
		return hasOven;
	}
	
	public boolean getHasStove() {
		return hasStove;
	}
	
	public boolean getHasDishwasher() {
		return hasDishwasher;
	}
	
	public boolean getHasTableware() {
		return hasTableware;
	}
	
	public boolean getHasCookware() {
		return hasCookware;
	}
	
	public boolean getHasBasicProv() {
		return hasBasicProv;
	}
	
	public Kitchen(boolean f, boolean m, boolean o, boolean s,
			boolean d , boolean t, boolean c, boolean bp) {
		hasFridge = f;
		hasMicrowave = m;
		hasOven = o;
		hasStove = s;
		hasDishwasher = d;
		hasTableware = t;
		hasCookware = c;
		hasBasicProv = bp;
	}
	 public String toString() {
		 return "Has Fridge: " + hasFridge + "\nHas Microwave: " + hasMicrowave + 
				 "\nHas Oven: " + hasOven + "\nHas Stove: " + hasStove + 
				 "\nHas Dishwasher: " + hasDishwasher + "\nHas Tableware: " + hasTableware +
				 "\nHas Cookware: " + hasCookware + "\nHas basic provisions: " + hasBasicProv;
	 }
	 
	 public static void main (String [] args ) {
		 Kitchen kitchen = new Kitchen(true,false,true,true,false,true,true,false);
		 System.out.println(kitchen);
	 }
}