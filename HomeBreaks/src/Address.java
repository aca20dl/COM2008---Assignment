//can be used for confidential address of user or property
public class Address{
	//this is either number or name of house
	private String house;
	private String street;
	//city village town...
	private String place;
	private String postCode;
	
	//get Methods for variables
	public String getHouse() {
		return house;
	}
	public String getStreet() {
		return street;
	}
	public String getPlace() {
		return place;
	}
	public String getPostCode() {
		return postCode;
	}
	
	//set methods
	public void setHouse(String h) {
		house = h;
	}
	
	public void setStreet(String s) {
		street = s;
	}
	
	public void setPlace(String p) {
		place = p;
	}
	
	public void setPostCode(String pc) {
		postCode = pc;
	}
	
	//constructor for address
	public Address(String h, String s, String p, String pc) {
		house = h;
		street = s;
		place = p;
		postCode = pc;
	}
	public String toString() {
		return "House Name/number: " + house + "\nStreet: " + street +
				"\nTown/City/Village: " + place + "\nPost Code: " + postCode;
	}
	
	public static void main (String [] args) {
		Address ad = new Address("25","Green ln", "Sheffield", "s109ju");
		System.out.println(ad);
	}
}