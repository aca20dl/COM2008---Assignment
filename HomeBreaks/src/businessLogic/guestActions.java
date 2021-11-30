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
		
		String start = b.getStart().toString();
		String end = b.getEnd().toString();
		int nn = b.getNumNights();
		int ppn = b.getPricePerNight();
		int sc = b.getServiceCharge();
		int cc = b.getCleaningCharge();
		int total = b.getTotalCharge();
		
		//insert into bookings table
		String columns = "StartDate,EndDate,NumNights,PricePerNight,ServiceCharge,"
				+ "CleaningCharge,TotalCharge,GuestID,PropertyID";
		String values = "'" + start + "','" + end + "'," + nn + "," + ppn + "," + sc + ","+ cc + "," +
				total + "," + guestID + "," + propertyID;
		Database.insertValues("Booking", columns, values);
	}
	
	public static void addReview(User user, int propertyID, Review r) {
		user = (Guest) user;
		int pdID = Database.getID("PdID","Pdetails","Email",user.getEmail());
		int guestID = Database.getID("GuestID","Guests","PdID",String.valueOf(pdID));
		
		String sat = r.getSatisfaction();
		int clean = r.getCleanliness();
		int com = r.getCommunication();
		int ch = r.getCheckIn();
		int acc = r.getAccuracy();
		int loc = r.getLocation();
		int val = r.getValue();
		
		String columns = "Satisfaction,Cleanliness,Communication"
				+ ",CheckIn,Accuracy,Location,Value,GuestID,PropertyID";
		String values = "'" + sat + "'," + clean + "," + com +
				"," + ch + "," + acc + "," + loc + "," + val + "," +
				guestID + "," + propertyID ;
		
		Database.insertValues("Reviews", columns, values);
		//update property and host average value
		Houses.updateProperty(propertyID);
		Houses.updateHost(propertyID);
	}
	
	public static boolean reviewExists(int guestID, int propertyID) {
		String conditions = "GuestID = " + guestID + " && PropertyID = " + propertyID;
		boolean exists = false;
		try {
			ResultSet result = Database.allInfo("*", "Reviews", conditions);
			while(result.next()) {
				exists = true;
			}
			result.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exists;
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
	
	public static String [] hostPubInfo(int propertyID) {
		int hostID = Database.getID("HostID", "Properties", "PropertyID",String.valueOf(propertyID));
		String username = "";
		String sh = "";
		try {
			ResultSet result = Database.getValue("Username,IsSuperHost", "Hosts", "HostID", String.valueOf(hostID));
			while(result.next()) {
				username = result.getString("Username");
				sh = String.valueOf(result.getInt("IsSuperHost"));
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String [] values = {username,sh};
		return values;
	}
}
