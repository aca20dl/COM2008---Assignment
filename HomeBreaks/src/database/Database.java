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
	//gets results from a query 
	//Constructs the query SELECT target FROM table WHERE column = value;
	public static ResultSet getValue(String target, String table, String column, String value) {
		ResultSet result = null;
		try {
			Statement getValue = con.createStatement();
			String query = "Select " + target + " FROM " + table + " WHERE " + column + " = \"" + value + "\";"; 
			result = getValue.executeQuery(query);
		}catch (SQLException exx) {
			exx.printStackTrace();
		}
		return result;
	}
	
	//returns true if given email is guest - checks IsGuest value in table
	public static boolean isGuest(String email) {
		int isGuest = 0;
		ResultSet result = getValue("IsGuest","Pdetails","Email",email);
		try {
			while(result.next()) {
				isGuest=result.getInt(1);
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isGuest == 1;
	}
	
	//returns true if given email is host - checks IsHost value in table
	public static boolean isHost(String email) {
		int isHost = 0;
		ResultSet result = getValue("IsHost","Pdetails","Email",email);
		try {
			while(result.next()) {
				isHost=result.getInt(1);
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isHost == 1;
	}
	
	// returns true if email already exists in Pdetails table
	public static boolean exists(String email) {
		int count = 0;
		//if result set not empty when searching for email then user exists
		try {
			ResultSet result = getValue("*","Pdetails","Email",email);
			if(result.next()) {
				count = 1;
			}
			result.close();
		}catch(SQLException e2) {
			e2.printStackTrace();
		}
		return count > 0;
	}
	
	// adds user to the Address and Pdetails table
	public static void addUser(User user) {
		
		int isHost=0;
		int isGuest=0;
		
		if(user.getClass().getName() == "classCode.Host") 
			isHost = 1;
		else
			isGuest = 1;
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
		
		//make 2 instances of prepared statement: address, pdetails
		try {
			adStat = con.prepareStatement("INSERT INTO Addresses "
					+ "(House,Street,place,PostCode) VALUES "
					+ "(?,?,?,?)");
			pdStat = con.prepareStatement("INSERT INTO Pdetails "
					+ "(Title,Firstname,Surname,Email,Mobile,"
					+ "IsHost,IsGuest,AdID) VALUES(?,?,?,?,?,?,?,?)");
			
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
			ResultSet result = getValue("AdID", "Addresses","PostCode",postCode);
			int adID = 0;
			while(result.next())
				adID = result.getInt(1);
			result.close();
			
			pdStat.setString(1, title);
			pdStat.setString(2, name);
			pdStat.setString(3, surname);
			pdStat.setString(4, email);
			pdStat.setString(5, mobile);
			pdStat.setString(6, String.valueOf(isHost));
			pdStat.setString(7, String.valueOf(isGuest));
			pdStat.setString(8, String.valueOf(adID));
			int count1 = pdStat.executeUpdate();
			
		}catch (SQLException exx) {
			exx.printStackTrace();
		}finally {
				try {
					if(adStat != null)
						adStat.close();
					if(pdStat != null)
						pdStat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	}
	
	//adds the user to either the Guest/host table
	public static void addUserType(User user) {
		String username = "";
		String password = "";
		String table = "";
		String email = user.getEmail();
		
		if(user.getClass().getName() == "classCode.Host") {
			username = ((Host) user).getHostName();
			password = ((Host) user).getPassword();
			table = "Hosts";
		}
		else {
			username = ((Guest) user).getGuestName();
			password = ((Guest) user).getPassword();
			table = "Guests";
		}
		
		try {
			
			ResultSet result = getValue("PdID","Pdetails","Email",email);
			int pdID = 0;
			while(result.next()) {
				pdID = result.getInt(1);
			}
			result.close();
			userStat = con.prepareStatement("INSERT INTO " + table + " (Username,Password,PdID) VALUES (?,?,?)");
			userStat.setString(1, username);
			userStat.setString(2, password);
			userStat.setString(3, String.valueOf(pdID));
			int count = userStat.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				if(userStat != null)
					userStat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//when registering again sets either isHost/isGuest to 1
	public static void setUserType(User user) {
		String type = "";
		if(user.getClass().getName() == "classCode.Host") {
			type = "isHost";
		}
		else {
			type = "IsGuest";
		}
		
		try {
			Statement setType = con.createStatement();
			String query = "UPDATE Pdetails SET " + type + "= 1 WHERE Email = \"" + user.getEmail() + "\";"; 
			int count = setType.executeUpdate(query);
			setType.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}