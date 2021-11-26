package database;

import classCode.*;
import java.sql.*;
import java.time.LocalDate;
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
	
	public static int getID(String target, String table, String column, String value) {
		int id = 0;
		try {
			ResultSet result = getValue(target,table,column,value);
			while (result.next()) {
				id = result.getInt(1);
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public static void insertValues(String table, String columnNames, String columnValues) {
		try {
			//INSERT INTO Guests(Username,Password,PdID) VALUES("firstUser","pass",1);
			Statement insert = con.createStatement();
			String query = "INSERT INTO " + table + "(" + columnNames + ") VALUES(" +
			columnValues + ");";
			int count = insert.executeUpdate(query);
			insert.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//gathers all information across multiple tables
	public static ResultSet allInfo(String targets, String tables, String conditions) {
		ResultSet result = null;
		try {
			Statement get = con.createStatement();
			String query = "SELECT " + targets + " FROM " + tables + " Where " + conditions + ";";
			result = get.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	//generate guest from email
	//select * from Pdetails,Guests,Addresses Where Pdetails.PdID=12 && Addresses.AdID = 28 && Guests.PdID=12;
	public static User getUser(String email, String userType) {
		User user = null;
		Address ad = null;
		
		int pdID = getID("PdID","Pdetails","Email",email);
		
		int adID = getID("AdID","Pdetails","PdID",String.valueOf(pdID));
		
		String table = "";
		
		if(userType.toLowerCase().equals("guests")) {
			table = "Guests";
		}
		else
			table = "Hosts";
		
		String tables = "Pdetails,Addresses," + table;
		String conditions = "Pdetails.PdID = " + pdID + "  && " + table + ".PdID = " + pdID
				+ " && Addresses.AdID = " + adID;
		ArrayList<Property> props = new ArrayList<>(); 
		
		try {
			ResultSet result = allInfo("*",tables,conditions);
			while(result.next()) {
				String title = result.getString(2);
				String name = result.getString(3);
				String sName = result.getString(4);
				String mobile = result.getString(6);
				String username = result.getString(11);
				String password = result.getString(12);
				String hNum = result.getString(15);
				String street = result.getString(16);
				String city = result.getString(17);
				String postCode = result.getString(18);
				ad = new Address(hNum,street,city,postCode);
				if(userType.toLowerCase().equals("guest")) {
					user = new Guest(title,name,sName,email,mobile,ad,username,password);
				}
				else
					user = new Host(title,name,sName,email,mobile,ad,username,password,props);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
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
	
	public static int toInt(boolean b) {
		if (b)
			return 1;
		else
			return 0;
	}
	
	//Function 2 add Property
	public static void addProperty(Host host, Property p) {
		
		// first add property to properties list for host
		host.addProperty(p);
		//get pdID
		int pdID = getID("PdID","Pdetails","Email",host.getEmail());
		
		//getHostID
		int hostID =getID("HostID","Hosts","PdID",String.valueOf(pdID));
		
		Address ad = p.getAddress();
		//add address to address table
		addAddress(ad);
		
		//gather the rest of property info
		String name = p.getName();
		String des = p.getDescription();
		String gen = p.getGenLoc();
		int breakfast = toInt(p.getBreakfast());
		int maxG = p.getMaxGuest();
		int nBeds = p.getNumBed();
		int nBedr = p.getNumBedroom();
		int nBath = p.getNumBathroom();
		String columns = "Name,Description,GenLocation,Breakfast,"
				+ "MaxGuest,NumBeds,NumBedrooms,NumBathrooms,HostID";
		String values = "'" + name + "','" + des + "','" + gen + "'," +
				breakfast + "," + maxG + "," + nBeds + "," + nBedr + "," +
				nBath + "," + hostID;
		
		//insert the property into table
		insertValues("Properties",columns,values);
		
		//get property id
		int propertyID = getID("PropertyID","Properties","HostID",String.valueOf(hostID));
			
		
		//add all facilities to respective tables
		addSleeping(p.getSleeping(),propertyID);
		addBathing(p.getBathing(),propertyID);
		addKitchen(p.getKitchen(),propertyID);
		addLiving(p.getLiving(),propertyID);
		addUtility(p.getUtility(),propertyID);
		addOutdoor(p.getOutdoor(),propertyID);
		
		//add all charge bands
		ArrayList<ChargeBand> bands = p.getBands();
		for(ChargeBand c : bands) {
			addChargeBand(c,propertyID);
		}
		
		//get sleeping id
		int sleepingID =  getID("SleepingID","Sleeping","PropertyID",String.valueOf(propertyID));
		//add all bedrooms to table
		ArrayList<Bedroom> bedrooms = p.getSleeping().getBedrooms();
		for(Bedroom b : bedrooms) {
			addBedroom(b,sleepingID);
		}
		
		//get bathing id
		int bathingID = getID("BathingID","Bathing","PropertyID",String.valueOf(propertyID));
		//add all bathrooms to table
		ArrayList<Bathroom> bathrooms = p.getBathing().getBathrooms();
		for(Bathroom b : bathrooms) {
			addBathroom(b,bathingID);
		}
	}
	
	// functions to add facilities to their tables in database
	//SLEEPING
	 public static void addSleeping(Sleeping s, int propertyID) {
		 String table = "Sleeping";
		 int hasBL = toInt(s.getHasBedLinen());
		 int hasT = toInt(s.getHasTowels());
		 int totalBr = s.getTotalBedrooms();
		 int totalB = s.getTotalBeds();
		 int totalS = s.getTotalSleepers();
		 insertValues(table,"hasBedLinen,HasTowels,TotalBedrooms,"
		 		+ "TotalBeds,TotalSleepers,PropertyID",hasBL +
		 		"," + hasT + "," + totalBr + "," + totalB + 
		 		"," + totalS + "," + propertyID);
	 }
	 
	 //BEDROOM
	 public static void addBedroom(Bedroom b,int sleepingID) {
		 String bed1 = b.getBed1().getType().toString();
		 String bed2 = "";
		 if (b.getBed2() != null)
			 bed2 = b.getBed2().getType().toString();
		 int numBeds = b.getNumBeds();
		 int numSleepers = b.getNumSleepers();
		 String table = "Bedrooms";
		 insertValues(table,"BedOne,BedTwo,NumBeds,NumSleepers,SleepingID","'" + bed1 + "','" + bed2 + "',"
		 + numBeds + "," + numSleepers + "," + sleepingID);
	 }
	 
	 //Bathroom
	 public static void addBathroom(Bathroom b, int bathingID) {
		 String table = "Bathrooms";
		 int toilet = toInt(b.getHasToilet());
		 int bath = toInt(b.getHasBath());
		 int shower = toInt(b.getHasShower());
		 int shared = toInt(b.getIsShared());
		 insertValues(table,"HasToilet,HasBath,HasShower,IsShared,BathingID",
				 toilet + "," + bath + "," + shower + "," +
		         shared + "," + bathingID);
	 }
	 
	 //BATHING
	 public static void addBathing(Bathing b, int propertyID) {
		 String table = "Bathing";
		 int hasHD = toInt(b.getHasHairDryer());
		 int hasS = toInt(b.getHasShampoo());
		 int hasTP = toInt(b.getHasToiletPaper());
		 int totalB = b.getTotalBathrooms();
		 insertValues(table,"HasHairDryer,HasShampoo,HasToiletPaper,"
		 		+ "TotalBathrooms,PropertyID",hasHD + "," + hasS + "," 
		 		+ hasTP + "," + totalB + "," + propertyID);
	 }
	 
	 //Kitchen
	 public static void addKitchen(Kitchen k, int propertyID) {
		 int hasF = toInt(k.getHasFridge());
		 int hasM = toInt(k.getHasMicrowave());
		 int hasO = toInt(k.getHasOven());
		 int hasS = toInt(k.getHasStove());
		 int hasD = toInt(k.getHasDishwasher());
		 int hasT = toInt(k.getHasTableware());
		 int hasC = toInt(k.getHasCookware());
		 int hasBP = toInt(k.getHasBasicProv());
		 String columns = "HasFridge,HasMicrowave,HasOven,HasStove,"
		 		+ "HasDishwasher,HasTableware,HasCookware,HasBasicProv,"
		 		+ "PropertyID";
		 String values = hasF + "," + hasM + "," + hasO + "," + hasS 
				 + "," + hasD + "," + hasT + "," + hasC + "," + hasBP 
				 + "," + propertyID;
		 insertValues("Kitchen",columns,values);
	 }
	 
	 //Living
	 public static void addLiving(Living l, int propertyID) {
		 int hasW = toInt(l.getHasWifi());
		 int hasT = toInt(l.getHasTv());
		 int hasSat = toInt(l.getHasSat());
		 int hasStr = toInt(l.getHasStreaming());
		 int hasD = toInt(l.getHasDvdPlayer());
		 int hasB = toInt(l.getHasBoardGames());
		 String columns = "HasWifi,HasTV,HasSat,HasStreaming,"
		 		+ "HasDVDPlayer,HasBoardGames,PropertyID";
		 String values = hasW + "," + hasT + "," + hasSat +
				 "," + hasStr + "," + hasD + "," + hasB + "," +
				 propertyID;
		 insertValues("Living",columns,values);
	 }
	 
	 //UTILITY
	 public static void addUtility(Utility u, int propertyID) {
		 int hasH = toInt(u.getHasHeating());
		 int hasW = toInt(u.getHasWashingMachine());
		 int hasD = toInt(u.getHasDryingMachine());
		 int hasF = toInt(u.getHasFireExtinguisher());
		 int hasS = toInt(u.getHasSmokeAlarm());
		 int hasFAK = toInt(u.getHasFirstAidKit());
		 String columns = "HasHeating,HasWashingMachine,"
		 		+ "HasDryingMachine,HasFireExtinguisher,"
		 		+ "HasSmokeAlarm,HasFirstAidKit,PropertyID";
		 String values = hasH + "," + hasW + "," + hasD + ","
				 + hasF + "," + hasS + "," + hasFAK + "," + propertyID;
		 insertValues("Utility",columns,values);
	 }
	 
	 //OUTDOOR
	 public static void addOutdoor(Outdoor o, int propertyID) {
		 int hasFP = toInt(o.getHasFreeParking());
		 int hasRP = toInt(o.getHasRoadParking());
		 int hasPCP = toInt(o.getHasPaidCarPark());
		 int hasP = toInt(o.getHasPatio());
		 int hasB = toInt(o.getHasBarbeque());
		 String columns = "HasFreeParking,HasRoadParking,"
		 		+ "HasPaidCarPark,HasPatio,HasBarbeque,PropertyID";
		 String values = hasFP + "," + hasRP + "," + hasPCP + 
				 "," + hasP + "," + hasB + "," + propertyID;
		 insertValues("Outdoor",columns,values);
	 }
	 
	 //ADD CHARGEBANDS
	 public static void addChargeBand(ChargeBand c, int propertyID) {
		 String start = c.getStart().toString();
		 String end = c.getEnd().toString();
		 int ppn = c.getPricePerNight();
		 int sc = c.getServiceCharge();
		 int cc = c.getCleaningCharge();
		 String columns = "StartDate,EndDate,PricePerNight,ServiceCharge,"
		 		+ "CleaningCharge,PropertyID";
		 String values = "'" + start + "','" + end + "'," + ppn +
				 "," + sc + "," + cc + "," + propertyID;
		 insertValues("ChargeBands",columns,values);
		 
	 }
	 
	
	public static void main (String [] args) {
		
		Address ad1 = new Address("salma","salma","salma","salma");
		ArrayList<Property> p = new ArrayList<>();
		Host salma = new Host("Miss","salma","salma","salma","salma",ad1,"salma","salma",p);
		
		// property 
		// make addresss
		Address ad = new Address("25","Green ln", "Sheffield", "s109ju");
		
		ArrayList<Review> reviews = new ArrayList<>();
		
		// make sleeping facility
		Bedroom room1 = new Bedroom(new Bed("Double"),new Bed("Single"));
		Bedroom room2 = new Bedroom(new Bed("Bunk"),new Bed("Kingsize"));
		Bedroom room3 = new Bedroom(new Bed("Bunk"),null);
		
		ArrayList<Bedroom> rooms = new ArrayList<>();
		rooms.add(room1);
		rooms.add(room2);
		rooms.add(room3);
		
		Sleeping sleeping = new Sleeping(true,false,rooms);
		
		//make bathing facility
		Bathroom broom1 = new Bathroom(true,false,false,true);
		Bathroom broom2 = new Bathroom(false,false,true,true);
		
		ArrayList<Bathroom> brooms = new ArrayList<>();
		brooms.add(broom1);
		brooms.add(broom2);
		
		Bathing bathing = new Bathing(true,true,false,brooms);
		
		//kitchen facility
		Kitchen kitchen = new Kitchen(true,false,true,true,false,true,true,false);
		
		//living facility
		Living living = new Living(true,false,false,false,true,true);
		
		//Utility facility
		Utility utility = new Utility(true,false,false,true,true,true);
		
		//outdoor facility
		Outdoor outdoor = new Outdoor(false,false,true,true,false);
		
		//chargebands
		ChargeBand band1 = new ChargeBand(LocalDate.of(2021, 11, 01), LocalDate.of(2021, 12, 01),10,25,5);
		ChargeBand band2 = new ChargeBand(LocalDate.of(2021, 02, 01), LocalDate.of(2021, 11, 01),7,5,5);
		ArrayList<ChargeBand> bands = new ArrayList<>();
		bands.add(band1);
		bands.add(band2);
		
		
		Property myProp = new Property(ad,"small house", "long description",
				"sheffield",false,sleeping,bathing,kitchen,living
				,utility,outdoor,bands,reviews);
		connectDB();
		System.out.println(getUser("salma","Host"));
		disconnectDB();
	}
}