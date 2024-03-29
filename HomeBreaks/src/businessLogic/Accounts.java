package businessLogic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classCode.*;
import database.*;

public class Accounts{
	// adds user to the Pdetails + Addresses table
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
		String password = user.getPassword();
		//getting address details
		Address ad = user.getAddress();
		
		//make 2 instances of prepared statement: address, pdetails
		
		try {
			// execute addition to address table query
			Database.addAddress(ad);
			
			//execute addition to personal details table
			ResultSet result = Database.getValue("AdID", "Addresses","PostCode",ad.getPostCode());
			int adID = 0;
			while(result.next())
				adID = result.getInt(1);
			result.close();
			
			Database.insertValues("Pdetails","Title,Firstname,Surname,Email,Mobile,Password,IsHost,IsGuest,AdID",
					"'" + title + "','" + name + "','" + surname + "','" + email + "','" + mobile +
					"','" + password + "'," + isHost + "," + isGuest + "," + adID);

			
		}catch (SQLException exx) {
			exx.printStackTrace();
		}
		
	}
	
	//adds the user to either the Guest/host table
		public static void addUserType(User user) {
			String username = "";
			String table = "";
			String email = user.getEmail();
			double averageRating = 0;
			int isSuperHost = Houses.toInt(false);
			
			if(user.getClass().getName().equals("classCode.Host")) {
				username = ((Host) user).getHostName();
				averageRating = ((Host) user).getAvrgRating();
				isSuperHost =Houses.toInt(((Host)user).getIsSuperHost());
				table = "Hosts";
			}
			else {
				username = ((Guest) user).getGuestName();
				table = "Guests";
			}
			
			try {
				ResultSet result = Database.getValue("PdID","Pdetails","Email",email);
				int pdID = 0;
				while(result.next()) {
					pdID = result.getInt(1);
				}
				result.close();
				String columnValues = "'" + username + "','"  + pdID + "'";
				String columns = "Username,PdID";
				// if host add change query to match host table
				if(user.getClass().getName().equals("classCode.Host")) {
					columnValues = "'" + username + "','" + averageRating + "','" +isSuperHost + "','" + pdID + "'";
					columns = "Username,AverageRating,IsSuperHost,PdID";
				}
				Database.insertValues(table,columns,columnValues);
				
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
			Database.setValue("Pdetails", type, String.valueOf(1), "Email","'" +  user.getEmail() + "'" );
		}
		
		// returns true if email already exists in Pdetails table
		public static boolean exists(String email) {
			int count = 0;
			//if result set not empty when searching for email then user exists
			try {
				ResultSet result = Database.getValue("*","Pdetails","Email",email);
				if(result.next()) {
					count = 1;
				}
				result.close();
			}catch(SQLException e2) {
				e2.printStackTrace();
			}
			return count > 0;
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
			String password = "";
			boolean matches = false;
			
			// get personal info from database
			ResultSet result = Database.getValue("*","Pdetails","Email",user.getEmail());
			try {
				while(result.next()) {
					title = result.getString("Title");
					firstname = result.getString("Firstname");
					surname = result.getString("Surname");
					email = result.getString("Email");
					mobile = result.getString("Mobile");
					password = result.getString("Password");
				}
				result = Database.getValue("*","Addresses", "PostCode",user.getAddress().getPostCode());
				while(result.next()) {
					home = result.getString("House");
					street = result.getString("Street");
					city = result.getString("Place");
					postCode = result.getString("PostCode");
				}
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Address ad = new Address(home,street,city,postCode);
			if(title.equals(user.getTitle()) && firstname.equals(user.getForename()) 
					&& surname.equals(user.getSurname()) && email.equals(user.getEmail())
					&& mobile.equals(user.getMobile()) && password.equals(user.getPassword()) 
					&& ad.equals(user.getAddress()))
				matches = true;
			return matches;
		}
		
		//login with email and password, must have login as host/ login as register button
		public static boolean loginUser(String email, String password, String table) {
			String actualPass = "";
			try {
				String conditions = "Pdetails.Email = '" + email + "' && Pdetails.Password = '" + password + "'";
				ResultSet result = Database.allInfo("*",table + ",Pdetails",conditions);
				while(result.next()) {
					actualPass = result.getString("Password");
				}
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return actualPass.equals(password);
		}
		
		//generate user from email
		public static User getUser(String email, String userType) {
			User user = null;
			Address ad = null;
			
			int pdID = Database.getID("PdID","Pdetails","Email",email);
			
			int adID = Database.getID("AdID","Pdetails","PdID",String.valueOf(pdID));
			
			String table = "";
			
			if(userType.toLowerCase().equals("guest")) {
				table = "Guests";
			}
			else
				table = "Hosts";
			
			String tables = "Pdetails,Addresses," + table;
			String conditions = "Pdetails.PdID = " + pdID + "  && " + table + ".PdID = " + pdID
					+ " && Addresses.AdID = " + adID;
			ArrayList<Property> props = new ArrayList<>(); 
			
			try {
				ResultSet result = Database.allInfo("*",tables,conditions);
				while(result.next()) {
					String title = result.getString("Title");
					String name = result.getString("FirstName");
					String sName = result.getString("Surname");
					String mobile = result.getString("Mobile");
					String username = result.getString("Username");
					String password = result.getString("Password");
					String hNum = result.getString("House");
					String street = result.getString("Street");
					String city = result.getString("place");
					String postCode = result.getString("PostCode");
					ad = new Address(hNum,street,city,postCode);
					if(userType.toLowerCase().equals("guest")) {
						user = new Guest(title,name,sName,email,mobile,password,ad,username);
					}
					else
						user = new Host(title,name,sName,email,mobile,password,ad,username,0); 
				}
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return user;
		}
		
		//returns true if given email is guest - checks IsGuest value in table
		public static boolean isGuest(String email) {
			int isGuest = 0;
			ResultSet result = Database.getValue("IsGuest","Pdetails","Email",email);
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
			ResultSet result = Database.getValue("IsHost","Pdetails","Email",email);
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
				
}