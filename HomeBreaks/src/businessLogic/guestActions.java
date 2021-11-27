package businessLogic;

import java.sql.*;

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
	
	public static ResultSet showBookings(User user) {
		user = (Guest) user;
		//get pdID
		int pdID = Database.getID("PdID","Pdetails","Email",user.getEmail());
		//get guest id
		int guestID = Database.getID("GuestID","Guests","PdID",String.valueOf(pdID));
		ResultSet result = Database.getValue("*", "Bookings", "GuestID", String.valueOf(guestID));
		return result;
	}
	
	public static ResultSet showHostDetails(int propertyID) {
		int hostID = Database.getID("HostID", "Properties", "PropertyID",String.valueOf(propertyID));
		// get pdID
		int pdID = Database.getID("PdID", "Hosts", "HostID", String.valueOf(hostID));
		//select Pdetails.*,Hosts.Username from Pdetails,Hosts where Hosts.PdID=3 && Pdetails.PdID=3;
		String conditions = "Hosts.PdID = " + pdID + " && Pdetails.PdID = " + pdID;
		ResultSet result = Database.allInfo("Pdetails.*,Hosts.Username","Pdetails,Hosts",conditions);
		return result;
		
	}
	//shows if booking request is accepted
	public static boolean isAccepted(int guestID) {
		int isAccepted = 0;
		try {
			ResultSet result = Database.getValue("IsAccepted", "Bookings", "GuestID", String.valueOf(guestID));
			while(result.next()) {
				 isAccepted = result.getInt("IsAccepted");
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return isAccepted == 1;
	}
}
