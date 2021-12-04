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
}