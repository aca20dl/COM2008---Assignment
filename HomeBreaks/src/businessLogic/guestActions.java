package businessLogic;

import classCode.*;
import database.*;

public class guestActions{
	
	//lets user add a Prov booking
	public static void addBooking(User user, int propertyID, Booking b) {
		user = (Guest) user;
		// get guest ID
		int pdID = Database.getID("PdID","Pdetails","Email",user.getEmail());
		int guestID = Database.getID("GuestID", "Guests", "PdID", String.valueOf(pdID));
		
		int nn = b.getNumNights();
		int ppn = b.getPricePerNight();
		int sc = b.getServiceCharge();
		int cc = b.getCleaningCharge();
		int total = b.getTotalCharge();
		
		//insert into bookings table
		String columns = "NumNights,PricePerNight,ServiceCharge,"
				+ "CleaningCharge,TotalCharge,GuestID,PropertyID";
		String values = nn + "," + ppn + "," + sc + ","+ cc + "," +
				total + "," + guestID + "," + propertyID;
		Database.insertValues("Booking", columns, values);
	}
	
	public static void addReview(User user, int propertyID, Review r) {
		user = (Guest) user;
		
		String sat = r.getSatisfaction();
		int clean = r.getCleanliness();
		int com = r.getCommunication();
		int ch = r.getCheckIn();
		int acc = r.getAccuracy();
		int loc = r.getLocation();
		int val = r.getValue();
		
		String columns = "Satisfaction,Cleanliness,Communication"
				+ ",CheckIn,Accuracy,Location,Value,PropertyID";
		String values = "'" + sat + "'," + clean + "," + com +
				"," + ch + "," + acc + "," + loc + "," + val;
		
		Database.insertValues("Reviews", columns, values);
	}
}
