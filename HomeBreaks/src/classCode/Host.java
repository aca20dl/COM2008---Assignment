package classCode;
import java.util.*;

public class Host extends User{
	//variables specific for host here, host name is public
	private String HostName;
	private String password;
	private Boolean isSuperHost;
	private ArrayList<Property> properties; //<- list of properties
	private double avrgRating;
	
	// get methods for variables
	public String getHostName(){
		return HostName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public boolean getIsSuperHost() {
		return isSuperHost;
	}
	
	public ArrayList<Property> getProperties() {
		return properties;
	}
	
	public double getAvrgRating() {
		return avrgRating;
	}
	
	//computes the average rating of host 
	//gets sum of average review from each property
	// and then takes average of that
	public double average(ArrayList<Property> p) {
		double sum = 0;
		if(!p.isEmpty()) {
			for(int i = 0; i < p.size();i++) {
				sum = sum + p.get(i).avrgReview();
			}
		}
		double average = sum/properties.size();
		return average;
	}
	
	// set methods
	public void setHostName(String hn) {
		HostName = hn;
	}
	
	public void setPassword(String p) {
		password = p;
	}
	
	// constructor for host
	public Host(String t, String f, String s, String e, String m,
			Address ad, String hn, String p,ArrayList<Property> pr) {
		super(t,f,s,e,m,ad);
		HostName = hn;
		password = p;
		properties = pr;
		avrgRating = average(properties);
		isSuperHost = avrgRating >= 4.7;
	}
	
	
	//shows only the names of each property
	public String showProperties(ArrayList<Property> a){
		String names = "[";
		for(int i = 0; i < a.size(); i++) {
			if(i == a.size()-1)
				names = names + a.get(i).getName();
			else
				names = names + a.get(i).getName() + ",";
		}
		return names +"]";
	}
	
	public void cleanInputs() {
		super.cleanInputs();
		HostName = removeSemiColon(HostName.strip());
		password = removeSemiColon(password.strip());		
	}
	
	//create methods for listing property, accept req
	
	public String toString() {
		String output = super.toString() + "\nHost Name: " + HostName + 
				"\nPassword: " + password + "\nSuper Host: " + isSuperHost;
		if(!properties.isEmpty()) {
		output = output + "\nProperties: " + showProperties(properties) +
				"\nAverage property rating: " + avrgRating;
		}
		return output;
	}
}