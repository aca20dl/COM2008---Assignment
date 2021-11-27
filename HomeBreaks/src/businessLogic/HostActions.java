package businessLogic;

import java.sql.ResultSet;
import java.sql.SQLException;

import classCode.*;
import database.*;

public class HostActions{
	public static ResultSet showBookings(User user) {
		//select Bookings.* from Bookings,Hosts,Properties where Hosts.HostID = 3 && Properties.HostID = 3;
		user = (Host) user;
		int pdID = Database.getID("PdID","Pdetails","Email",user.getEmail());
		int hostID = Database.getID("HostID","Hosts","PdID",String.valueOf(pdID));
		String conditions = "Hosts.HostID = " + hostID + " && Properties.HostID = " + hostID;
		ResultSet result = Database.allInfo("Bookings.*", "Bookings,Hosts,Properties",conditions);
		return result;
	}
	
	public static ResultSet showGuestDetails(int guestID) {
		int pdID = Database.getID("PdID","Guests","GuestId",String.valueOf(guestID));
		//select Pdetails.*,Guests.Username from Pdetails,Guests where Guests.PdID = 4 && Pdetails.PdID = 4;
		String conditions = "Guests.PdID = " + pdID + " && Pdetails.PdID = " + pdID;
		ResultSet result = Database.allInfo("Pdetails.*,Guests.Username","Pdetails,Guests",conditions);
		return result;
	}
	
	public static void acceptBooking(int bookingID) {
		int propertyID =Database.getID("PropertyID","Bookings","BookingID",String.valueOf(bookingID));
		
		Database.setValue("Bookings", "IsAccepted",String.valueOf(1), "BookingID", String.valueOf(bookingID));
		
		removeBookings(propertyID);
	}
	
	//if booking accepted remove all other bookings for property
	public static void removeBookings(int propertyID) {
		String conditions = "isAccepted = 0 && PropertyID = " + propertyID;
		Database.removeValues("Bookings", conditions);
	}
	
	//checks if theres an accepted booking between guest and host
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