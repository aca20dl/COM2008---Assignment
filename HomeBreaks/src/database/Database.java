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
			String query = "Select DISTINCT " + target + " FROM " + table + " WHERE " + column + " = \"" + value + "\";"; 
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
	
	//remove items
	public static void removeValues(String table,String conditions) {
		try {
			Statement remove = con.createStatement();
			String query = "DELETE FROM " + table + " WHERE " + conditions + " ;";
			int count = remove.executeUpdate(query);
			remove.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void setValue(String table, String targetColumn, String newValue, String column,String value) {
		try {
			Statement setType =  con.createStatement();
			String query = "UPDATE " + table + " SET " + targetColumn + " =" + newValue + " WHERE "
					+ column + " = " + value + ";";
			int count = setType.executeUpdate(query);
			setType.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//gathers all information across multiple tables
	public static ResultSet allInfo(String targets, String tables, String conditions) {
		ResultSet result = null;
		try {
			Statement get = con.createStatement();
			String query = "SELECT Distinct " + targets + " FROM " + tables + " Where " + conditions + ";";
			result = get.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	//add address
	public static void addAddress(Address ad) {
		String house = ad.getHouse();
		String street = ad.getStreet();
		String place = ad.getPlace();
		String postCode = ad.getPostCode();
		
		// execute addition to address table query
		insertValues("Addresses","House,Street,place,PostCode","'" + house +
				"','" + street + "','" + place + "','" + postCode + "'");
	}

	 
	
	public static void main (String [] args) {
		
		Address ad1 = new Address("salma","salma","salma","salma");
		Host salma = new Host("Miss","salma","salma","salma","salma",ad1,"salma","salma",0);
		
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
		
		
	}
}