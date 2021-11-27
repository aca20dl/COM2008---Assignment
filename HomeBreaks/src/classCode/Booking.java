package classCode;

import java.time.LocalDate;
import java.util.*;

public class Booking{
	private LocalDate start;
	private LocalDate end;
	private int numNights;
	private int pricePerNight;
	private int serviceCharge;
	private int cleaningCharge;
	private int totalCharge;
	
	public LocalDate getStart() {
		return start;
	}
	
	public LocalDate getEnd() {
		return end;
	}
	
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
	
	//makes booking from list of charge bands
	public static Booking makeBooking(LocalDate start,LocalDate end, ArrayList<ChargeBand> c) {
		ArrayList<LocalDate> dates = ChargeBand.getDatesBetween(start, end);
		int numNights = dates.size();
		ChargeBand main = ChargeBand.getMain(start, end, c);
		
		//gets average service charge and cleaning charge
		int sc = 0;
		int cc = 0;
		for(ChargeBand cb : c ) {
			sc = sc + cb.getServiceCharge();
			cc = cc + cb.getCleaningCharge();
		}
		sc = sc/c.size();
		cc = cc/c.size();
		
		int ppn = 0;
		for(int i = 0; i < dates.size(); i++) {
			ppn = ChargeBand.getMain(dates.get(i), dates.get(i), c).pricePerNight;
		}
		ppn = ppn/c.size();
		return new Booking(start,end,ppn,sc,cc);
	}
	
	public Booking(LocalDate s, LocalDate e, int ppn, int sc, int cc) {
		start = s;
		end = e;
		numNights = ChargeBand.getDatesBetween(s, e).size();
		pricePerNight = ppn;
		serviceCharge = sc;
		cleaningCharge = cc;
		totalCharge = ppn * numNights + sc + cc;
	}
	
	public String toString() {
		return "Start: " + start.toString() + "\nEnd: " + end.toString() + "\nNumber of Nights: " + numNights + "\nPricePerNight: " +
	            pricePerNight + "\nServiceCharge: " + serviceCharge +
	            "\nCleaning Charge: " + cleaningCharge + "\nTotal Charge: " 
	            + totalCharge;
	}
	
	public static void main (String [] args) {
		Booking booking = new Booking(LocalDate.of(2021, 10, 01),LocalDate.of(2021, 10, 20),12,10,10);
		System.out.println(booking);
	}
}