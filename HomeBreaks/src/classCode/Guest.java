package classCode;

import java.util.*;

public class Guest extends User{
	//variables specific for guest here, guest name is public
	private String guestName;
	private String password;
	
	// get methods for variables
	public String getGuestName(){
		return guestName;
	}
	
	public String getPassword() {
		return password;
	}
	
	
	// set methods
	public void setGuestName(String gn) {
		guestName = gn;
	}
	
	public void setPassword(String p) {
		password = p;
	}
	
	// constructor for guest
	public Guest(String t, String f, String s, String e, String m,
			Address ad, String gn, String p) {
		super(t,f,s,e,m,ad);
		guestName = gn;
		password = p;
	}
	
	public void cleanInputs() {
		super.cleanInputs();
		guestName = removeSemiColon(guestName.strip());
		password = removeSemiColon(password.strip());		
	}
	
	//create methods for req2book, browse + review
	
	public String toString() {
		return super.toString() + "\nGuest Name: " + guestName + 
				"\nPassword: " + password;
	}
	
	public static void main(String [] args) {
		Address ad = new Address("25","Green ln", "Sheffield", "s109ju");
		Guest salma = new Guest ("Miss","Salma;Drop tables Guest","Hassan","s.h@email.com",
				"0114",ad,"Sal","password");
		salma.cleanInputs();
		System.out.println(salma);
		
	}
}