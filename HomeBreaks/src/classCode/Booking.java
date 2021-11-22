package classCode;

public class Booking{
	private int numNights;
	private int pricePerNight;
	private int serviceCharge;
	private int cleaningCharge;
	private int totalCharge;
	
	public int getNumNights() {
		return numNights;
	}
	
	public int getPricePerNight() {
		return pricePerNight;
	}
	
	public int getServiceCharge() {
		return serviceCharge;
	}
	
	public int getCleaningCharge() {
		return cleaningCharge;
	}
	
	public int getTotalCharge() {
		return totalCharge;
	}
	
	public Booking(int n, int ppn, int sc, int cc) {
		numNights = n;
		pricePerNight = ppn;
		serviceCharge = sc;
		cleaningCharge = cc;
		totalCharge = ppn * n + sc + cc;
	}
	
	public String toString() {
		return "Number of Nights: " + numNights + "\nPricePerNight: " +
	            pricePerNight + "\nServiceCharge: " + serviceCharge +
	            "\nCleaning Charge: " + cleaningCharge + "\nTotal Charge: " 
	            + totalCharge;
	}
	
	public static void main (String [] args) {
		Booking booking = new Booking(14,12,10,10);
		System.out.println(booking);
	}
}