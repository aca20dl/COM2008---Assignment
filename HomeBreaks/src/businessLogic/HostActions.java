package businessLogic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;

import classCode.*;
import database.*;

public class HostActions{
	public static ResultSet showBookings(User user) {
		//select Bookings.* from Bookings,Hosts,Properties where Hosts.HostID = 3 && Properties.HostID = 3;
		user = (Host) user;
		int pdID = Database.getID("PdID","Pdetails","Email",user.getEmail());
		int hostID = Database.getID("HostID","Hosts","PdID",String.valueOf(pdID));
		//get all the property IDs of a host
		ArrayList<Integer> propertyIDs = new ArrayList<>();
		try {
			ResultSet  result = Database.getValue("PropertyID", "Properties", "HostID", String.valueOf(hostID));
			while(result.next()) {
				int id = result.getInt("PropertyID");
				propertyIDs.add(id);
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// create query
		String ids = "";
		for(int i = 0; i < propertyIDs.size(); i++) {
			if(i == propertyIDs.size() -1) {
				ids = ids + propertyIDs.get(i);
			}
			else
				ids = ids + propertyIDs.get(i) + ",";
		}
		ResultSet result2 = null;
		if(!ids.isBlank()) {
			String conditions = "PropertyID in (" + ids + ")";
			result2 = Database.allInfo("*", "Bookings",conditions);
		}
		return result2;
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
	
	//if booking accepted remove all other overlapping bookings for property
	public static void removeBookings(int propertyID) {
		//
		LocalDate start = null;
		LocalDate end = null;
		//get value of start and end date that is accepted
		try {
			ResultSet result = Database.allInfo("StartDate,EndDate", "Bookings", "IsAccepted = 1 && PropertyID = " + String.valueOf(propertyID));
			while(result.next()) {
				start = result.getDate("StartDate").toLocalDate();
				end = result.getDate("EndDate").toLocalDate();
			}
			result.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// get list of bookings for property that are not accepted + remove if dates overlapp
		try {
			ResultSet result = Database.allInfo("BookingID,StartDate,EndDate", "Bookings","IsAccepted = 0 && PropertyID = " + String.valueOf(propertyID));
			while (result.next()) {
				int bookingID = result.getInt("BookingID");
				LocalDate start1 = result.getDate("StartDate").toLocalDate();
				LocalDate end1 = result.getDate("EndDate").toLocalDate();
				if(ChargeBand.overlaps(start, end, start1, end1)) {
					// remove the bookings
					String conditions = "isAccepted = 0 && BookingID = " + bookingID;
					Database.removeValues("Bookings", conditions);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//checks if theres an accepted booking between guest and host
	public static boolean isAccepted(int bookingID) {
		int isAccepted = 0;
		try {
			ResultSet result = Database.getValue("IsAccepted", "Bookings", "BookingID", String.valueOf(bookingID));
			while(result.next()) {
				 isAccepted = result.getInt("IsAccepted");
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return isAccepted == 1;
	}
	
	public static void main (String [] args) {
		Database.connectDB();
		removeBookings(2);
		Database.disconnectDB();
	}
}