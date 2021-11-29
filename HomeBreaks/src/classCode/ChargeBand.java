package classCode;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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
	
	public boolean isBetween(LocalDate d) {
		if(d.isBefore(end) && d.isAfter(start))
			return true;
		else
			return false;
	}
	
	// returns all dates, end date inclusive that are between 2 dates
	public static ArrayList<LocalDate> getDatesBetween(LocalDate start, LocalDate end) {
		int diff = (int) ChronoUnit.DAYS.between(start,end);
		ArrayList<LocalDate> dates = new ArrayList<>();
		for(int i = 0; i < diff + 1; i++) {
			LocalDate date = start.plusDays(i);
			dates.add(date);
		}
		return dates;
	}
	
	// returns the charge band dates fall in the most
	public static int datesWithin(ArrayList<LocalDate> dates,ChargeBand c) {
		int count = 0;
		for(int i = 0; i < dates.size(); i++) {
			if(c.isBetween(dates.get(i)))
				count += 1;
		}
		return count;
	}
	
	//checks if the dates overlap, assuming that s is before e and s1 is before e1
	public static boolean overlaps(LocalDate s, LocalDate e, LocalDate s1, LocalDate e1) {
		boolean overlaps = false;
		if(((s1.isBefore(e) || s.equals(e)) && (s1.isAfter(s) || s1.equals(s))) || 
				((s.isBefore(e1) || s.equals(e1)) && (s.isAfter(s1) || s.isEqual(s1))))
			overlaps = true;
		return overlaps;
	}
	
	// get main charge band associated with a start and end date
	public static ChargeBand getMain(LocalDate start, LocalDate end, ArrayList<ChargeBand> c) {
		ArrayList<LocalDate> datesBetween = getDatesBetween(start,end);
		ArrayList<Integer> numDayswithin = new ArrayList<>();
		
		for(int i = 0; i < c.size(); i ++) {
			numDayswithin.add(datesWithin(datesBetween,c.get(i)));
		}
		int maxIndex = numDayswithin.indexOf(Collections.max(numDayswithin));
		
		return c.get(maxIndex);
	}
	
	//constructor
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
		LocalDate start = LocalDate.of(2021, 9, 01);
		LocalDate end = LocalDate.of(2021, 10, 01);
		ChargeBand band = new ChargeBand(start,end,10,10,10);
		
		LocalDate start1 = LocalDate.of(2021, 11, 03);
		LocalDate end1 = LocalDate.of(2021, 11, 03);
		ChargeBand band1 = new ChargeBand(start1,end1,20,20,20);
		
		ArrayList<ChargeBand> bands = new ArrayList<>();
		bands.add(band);
		bands.add(band1);
		
		LocalDate sDate = LocalDate.of(2021, 9, 20);
		LocalDate eDate = LocalDate.of(2021, 11, 02);
		
		//System.out.println(getDatesBetween(sDate,eDate));
		System.out.println(Booking.makeBooking(sDate, eDate, bands));
		LocalDate test = LocalDate.parse("2002-04-01");
		System.out.println(test);
		System.out.println(overlaps(start1,end1,sDate,eDate));
		}
}