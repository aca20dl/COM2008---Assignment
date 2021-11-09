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
	
	public static void main(String [] args) {
		Address ad = new Address("25","Green ln", "Sheffield", "s109ju");
		
		Review myRev = new Review("Happy",4,4,5,2,5,4);
		Review myRev2 = new Review("Upset",1,2,1,1,1,2);
		
		Review myRev3 = new Review("Happy",3,3,5,2,5,7);
		Review myRev4 = new Review("Upset",1,4,1,1,3,2);
		
		ArrayList<Review> reviews = new ArrayList<>();
		reviews.add(myRev);
		reviews.add(myRev2);
		
		ArrayList<Review> reviews2 = new ArrayList<>();
		reviews2.add(myRev3);
		reviews2.add(myRev4);
		
		Property myProp = new Property(ad,"small house", "long description",
				"sheffield",false,6,2,3,2,reviews);
		Property myProp2 = new Property(ad,"big house", "long description",
				"sheffield",true,4,2,3,2,reviews2);
		
		ArrayList<Property> props = new ArrayList<>();
		props.add(myProp);
		props.add(myProp2);
		
		Host salma = new Host ("Miss","Salma","Hassan","s.h@email.com",
				"0114",ad,"Sal","password",props);
		System.out.println(salma);
		System.out.println();
	}
}