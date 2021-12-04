package businessLogic;

import java.nio.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.sql.*;
import java.time.LocalDate;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

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
		double nn = b.getNumNights();
		double ppn = b.getPricePerNight();
		double sc = b.getServiceCharge();
		double cc = b.getCleaningCharge();
		double total = b.getTotalCharge();
		
		//insert into bookings table
		String columns = "StartDate,EndDate,NumNights,PricePerNight,ServiceCharge,"
				+ "CleaningCharge,TotalCharge,GuestID,PropertyID,IsAccepted";
		String values = "'" + start + "','" + end + "'," + nn + "," + ppn + "," + sc + ","+ cc + "," +
				total + "," + guestID + "," + propertyID + "," + 0;
		Database.insertValues("Bookings", columns, values);
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
	
	// checks if property is available to book
	public static boolean available(int propertyID, LocalDate start, LocalDate end) {
		boolean isAvailable = true;
		try {
			ResultSet result = Database.allInfo("*", "Bookings", "PropertyID = " + String.valueOf(propertyID) + " && isAccepted = 1");
			while(result.next()) {
				LocalDate start1 = result.getDate("StartDate").toLocalDate();
				LocalDate end1 = result.getDate("EndDate").toLocalDate();
				if(ChargeBand.overlaps(start, end, start1, end1)) {
					isAvailable = false;
				}
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isAvailable;
	}
	
	//Generates a list of all properties with a location
	public static ResultSet getAllProps(String location) {
		String conditions = "GenLocation LIKE '%" + location + "%'";
		ResultSet result = Database.allInfo("*", "Properties", conditions);
		return result;
	}
	
	//delete bookings if date now is 5 days after their end date
	public static void cleanBookings() {
		LocalDate now = LocalDate.now().minusDays(5);
		Database.removeValues("Bookings", "EndDate < '" + now + "'");
	}
	
	//checks if guest has already submitted booking for a property
	public static boolean bookingExists(User user, int propertyID) {
		boolean exists = false;
		// get guest ID
		int pdID = Database.getID("PdID","Pdetails","Email",user.getEmail());
		int guestID = Database.getID("GuestID", "Guests", "PdID", String.valueOf(pdID));
		String conditions = "PropertyID = " + propertyID + " && GuestID = " + guestID;
		try {
			ResultSet result = Database.allInfo("*", "Bookings", conditions);
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

	//hash a password
	public static String hash(String password) {
		char [] cPass = password.toCharArray();
		String newPass = "";
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-512");
			md.update(charsAsBB(cPass));
			byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
			for(int i = 0; i < 16; i++) {//get first 16
				newPass = newPass + hashedPassword[i];
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return newPass;
	}
	
	private static ByteBuffer charsAsBB(char [] chars) {
		CharBuffer cb = CharBuffer.wrap(chars);
		Charset utfCharset = Charset.forName("UTF-8");
		ByteBuffer bb = utfCharset.encode(cb);
		return bb;
	}
	
	public static void main (String [] args) {
	}
}
