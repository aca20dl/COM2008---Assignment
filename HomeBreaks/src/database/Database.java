package database;

import classCode.*;
import java.sql.*;
import java.util.*;

public class Database{
	private static Connection con = null; // connection object
	private static String username = "team035";
	private static String password = "a0d11805" ;
	private static PreparedStatement adStat;
	private static PreparedStatement pdStat;
	private static PreparedStatement userStat;
			
	//gets connection to database
	public static Connection connectDB(){
		try {
			con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team035", username, password);
		}catch (SQLException e) {
	        throw new Error("Problem", e);
		}
		return con;
	}
	
	//closes connection to database
	public static void disconnectDB() {
		try {
	        if (con != null) {
	            con.close();
	        }
	      } catch (SQLException ex) {
	          System.out.println(ex.getMessage());
	      }
	}
	//gets id from email in Pdetails table
	public static int getID(String column, String value, String table) {
		Statement stmt = null;
		String query = "select * from " + table + " where " + column + " = \"" + value + "\";"; 
		int id = 0;
		try {
			stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(query);
			while(result.next()) {
				id = result.getInt(1);
			}
			result.close();
		}catch (SQLException exx) {
			exx.printStackTrace();
		}finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return id;
	}
	public static void addUser(User user) {
		String username = "";
		String password = "";
		String table = "";
		
		if(user.getClass().getName() == "Host") {
			username = ((Host) user).getHostName();
			password = ((Host) user).getPassword();
			table = "Hosts";
		}
		else {
			username = ((Guest) user).getGuestName();
			password = ((Guest) user).getPassword();
			table = "Guests";
		}
		
		
		//get all info from user
		String title = user.getTitle();
		String name = user.getForename();
		String surname = user.getSurname();
		String email = user.getEmail();
		String mobile = user.getMobile();
		//getting address details
		Address ad = user.getAddress();
		String house = ad.getHouse();
		String street = ad.getStreet();
		String place = ad.getPlace();
		String postCode = ad.getPostCode();
		
		//make 3 instances of prepared statement: address, pdetails and user
		try {
			adStat = con.prepareStatement("INSERT INTO Addresses (House,Street,place,PostCode) VALUES (?,?,?,?)");
			pdStat = con.prepareStatement("INSERT INTO Pdetails (Title,Firstname,Surname,Email,Mobile,AdID) VALUES(?,?,?,?,?,?)");
			userStat = con.prepareStatement("INSERT INTO " + table + " (Username,Password,PdID) VALUES (?,?,?)");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			// execute addition to address table query
			adStat.setString(1, house);
			adStat.setString(2, street);
			adStat.setString(3, place);
			adStat.setString(4, postCode);
			int count = adStat.executeUpdate();
			
			//execute addition to personal details table
			int AdID = getID("PostCode",postCode,"Addresses");
			pdStat.setString(1, title);
			pdStat.setString(2, name);
			pdStat.setString(3, surname);
			pdStat.setString(4, email);
			pdStat.setString(5, mobile);
			pdStat.setString(6, String.valueOf(AdID));
			int count1 = pdStat.executeUpdate();
			
			// execute addition to either guest/host table
			int pdID = getID("Email",email,"Pdetails");
			userStat.setString(1, username);
			userStat.setString(2, password);
			userStat.setString(3, String.valueOf(pdID));
			
			int count2 = userStat.executeUpdate();
		}catch (SQLException exx) {
			exx.printStackTrace();
		}finally {
				try {
					if(adStat != null)
						adStat.close();
					if(pdStat != null)
						pdStat.close();
					if(userStat != null)
						userStat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	}
}