package classCode;
import java.util.*;

public class Host extends User{
	//variables specific for host here, host name is public
	private String HostName;
	private Boolean isSuperHost;
	private double avrgRating;
	
	// get methods for variables
	public String getHostName(){
		return HostName;
	}
	
	public boolean getIsSuperHost() {
		return isSuperHost;
	}
	
	public double getAvrgRating() {
		return avrgRating;
	}
	
	
	
	// set methods
	public void setHostName(String hn) {
		HostName = hn;
	}
	
	
	// constructor for host
	public Host(String t, String f, String s, String e, String m,
			String p, Address ad, String hn,int ar) {
		super(t,f,s,e,m,p,ad);
		HostName = hn;
		avrgRating = ar;
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
	}
	
	//create methods for listing property, accept req
	
	public String toString() {
		String output = super.toString() + "\nHost Name: " + HostName + 
				"\nSuper Host: " + isSuperHost;
		return output;
	}
}