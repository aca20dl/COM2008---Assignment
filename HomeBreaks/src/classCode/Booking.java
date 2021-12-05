package classCode;

import java.time.LocalDate;
import java.util.*;

public class Booking{
	private LocalDate start;
	private LocalDate end;
	private double numNights;
	private double pricePerNight;
	private double serviceCharge;
	private double cleaningCharge;
	private double totalCharge;
	
	public LocalDate getStart() {
		return start;
	}
	
	public LocalDate getEnd() {
		return end;
	}
	
	public double getNumNights() {
		return numNights;
	}
	
	public double getPricePerNight() {
		return pricePerNight;
	}
	
	public double getServiceCharge() {
		return serviceCharge;
	}
	
	public double getCleaningCharge() {
		return cleaningCharge;
	}
	
	public double getTotalCharge() {
		return totalCharge;
	}
	
	//makes booking from list of charge bands
	public static Booking makeBooking(LocalDate start,LocalDate end, ArrayList<ChargeBand> c) {
		ArrayList<LocalDate> dates = ChargeBand.getDatesBetween(start, end);
		double numNights = dates.size();
		ChargeBand main = ChargeBand.getMain(start, end, c);
		
		//gets average service charge and cleaning charge
		double sc = 0;
		double cc = 0;
		for(ChargeBand cb : c ) {
			sc = sc + cb.getServiceCharge();
			cc = cc + cb.getCleaningCharge();
		}
		sc = sc/c.size();
		cc = cc/c.size();
		
		double ppn = 0;
		for(int i = 0; i < dates.size(); i++) {
			ppn = ppn + ChargeBand.getMain(dates.get(i), dates.get(i), c).pricePerNight;
		}
		ppn = ppn/dates.size();
		return new Booking(start,end,ppn,sc,cc);
	}
	
	public Booking(LocalDate s, LocalDate e, double ppn, double sc, double cc) {
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
}