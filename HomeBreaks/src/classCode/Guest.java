package classCode;

import java.util.*;

public class Guest extends User{
	//variables specific for guest here, guest name is public
	private String guestName;
	
	// get methods for variables
	public String getGuestName(){
		return guestName;
	}
	
	
	// set methods
	public void setGuestName(String gn) {
		guestName = gn;
	}
	
	
	// constructor for guest
	public Guest(String t, String f, String s, String e, String m,
			String p , Address ad, String gn) {
		super(t,f,s,e,m,p,ad);
		guestName = gn;
	}
	
	public void cleanInputs() {
		super.cleanInputs();
		guestName = removeSemiColon(guestName.strip());		
	}
	
	public String toString() {
		return super.toString() + "\nGuest Name: " + guestName ;
	}
}