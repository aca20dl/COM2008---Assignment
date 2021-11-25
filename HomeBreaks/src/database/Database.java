package database;

import classCode.*;
import java.sql.*;
import java.util.*;

public class Database{
	private static Connection con = null; // connection object
	private static String username = "team035";
	private static String password = "a0d11805" ;
			
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
	
	public static void insertValues(String table, String columnNames, String columnValues) {
		try {
			//INSERT INTO Guests(Username,Password,PdID) VALUES("firstUser","pass",1);
			Statement insert = con.createStatement();
			String query = "INSERT INTO " + table + "(" + columnNames + ") VALUES(" +
			columnValues + ");";
			int count = insert.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
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
	
	// add address
	
	public static void addAddress(Address ad) {
		String house = ad.getHouse();
		String street = ad.getStreet();
		String place = ad.getPlace();
		String postCode = ad.getPostCode();
		
		// execute addition to address table query
					insertValues("Addresses","House,Street,place,PostCode","'" + house +
							"','" + street + "','" + place + "','" + postCode + "'");
	}
	
	// adds user to the Pdetails table
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
		
		//make 2 instances of prepared statement: address, pdetails
		
		try {
			// execute addition to address table query
			addAddress(ad);
			
			//execute addition to personal details table
			ResultSet result = getValue("AdID", "Addresses","PostCode",ad.getPostCode());
			int adID = 0;
			while(result.next())
				adID = result.getInt(1);
			result.close();
			
			insertValues("Pdetails","Title,Firstname,Surname,Email,Mobile,IsHost,IsGuest,AdID",
					"'" + title + "','" + name + "','" + surname + "','" + email + "','" + mobile +
					"'," + isHost + "," + isGuest + "," + adID);

			
		}catch (SQLException exx) {
			exx.printStackTrace();
		}
		
	}
	
	//adds the user to either the Guest/host table
	public static void addUserType(User user) {
		String username = "";
		String password = "";
		String table = "";
		String email = user.getEmail();
		
		if(user.getClass().getName().equals("classCode.Host")) {
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
			insertValues(table,"Username,Password,pdID","'" + username + "','" + password + "','" + pdID + "'");
			
		} catch (SQLException e) {
			e.printStackTrace();
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
	
	
	//given a user it checks if the entered personal details match
	//the personal details in the database
	//this will be used if a registered user is trying to register again as either guest/host
	public static boolean matchPdetails(User user) {
		String title = "";
		String firstname = "";
		String surname = "";
		String email = "";
		String mobile = "";
		// address details
		String home = "";
		String street = "";
		String city = "";
		String postCode = "";
		boolean matches = false;
		
		// get personal info from database
		ResultSet result = getValue("*","Pdetails","Email",user.getEmail());
		try {
			while(result.next()) {
				title = result.getString(2);
				firstname = result.getString(3);
				surname = result.getString(4);
				email = result.getString(5);
				mobile = result.getString(6);
			}
			result = getValue("*","Addresses", "PostCode",user.getAddress().getPostCode());
			while(result.next()) {
				home = result.getString(2);
				street = result.getString(3);
				city = result.getString(4);
				postCode = result.getString(5);
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Address ad = new Address(home,street,city,postCode);
		if(title.equals(user.getTitle()) && firstname.equals(user.getForename()) 
				&& surname.equals(user.getSurname()) && email.equals(user.getEmail())
				&& mobile.equals(user.getMobile()) && ad.equals(user.getAddress()))
			matches = true;
		return matches;
	}
	//login with email and password, must have login as host/ login as register button
	public static boolean loginUser(String email, String password, String table) {
		String actualPass = "";
		try {
			Statement getUser = con.createStatement();
			//gets the password from Guest/host table where the users email is the email given
			String query = "SELECT " + table + ".* FROM " + table + " INNER JOIN Pdetails ON " + table + ".PdID=Pdetails.PdID WHERE Pdetails.Email=\"" + email +  "\";"; 
			ResultSet result = getUser.executeQuery(query);
			while(result.next()) {
				actualPass = result.getString(3);
			}
			getUser.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actualPass.equals(password);
	}
	
	// functions to add facilities to their tables in database
	 public static void addSleeping(Sleeping s) {
		 
	 }
	
	public static void main (String [] args) {
		//INSERT INTO Guests(Username,Password,PdID) VALUES("firstUser","pass",1);
		connectDB();
		String table = "Guests";
		String columnNames = "Username,Password,PdID";
		String columnValues = "'method','method',1";
		insertValues(table,columnNames,columnValues);
		disconnectDB();
	}
}