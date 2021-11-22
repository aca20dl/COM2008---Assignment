package classCode;

import java.time.*;

public class ChargeBand{
	LocalDate start; // (yyyy-mm-dd) format
	LocalDate end;
	int pricePerNight;
	int serviceCharge;
	int cleaningCharge;
	
	public LocalDate getStart() {
		return start;
	}
	
	public LocalDate getEnd() {
		return end;
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
	
	public ChargeBand(LocalDate s, LocalDate e, int ppn, int sc, int cc) {
		start = s;
		end = e;
		pricePerNight = ppn;
		serviceCharge = sc;
		cleaningCharge = cc;
	}
	
	public String toString() {
		return "Start Date: " + start + "\nEnd date: " + end + 
	            "\nPrice per night: " + pricePerNight + "\nService Charge: " +
				serviceCharge + "\nCleaningCharge" + cleaningCharge;
	}
	
	public static void main (String [] args ) {
		LocalDate start = LocalDate.of(2021, 11, 01);
		LocalDate end = LocalDate.of(2021, 12, 30);
		ChargeBand band1 = new ChargeBand(start,end,15,5,10);
		System.out.println(band1);
	}
}