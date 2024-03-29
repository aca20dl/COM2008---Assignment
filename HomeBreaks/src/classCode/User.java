package classCode;
public class User{
	// variables for confidential details here
	private String title;
	private String forename;
	private String surname;
	private String email;
	private String mobile;
	private String password;
	private Address address;
	
	// get methods 
	
	public String getTitle() {
		return title;
	}
	
	public String getForename() {
		return forename;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Address getAddress() {
		return address;
	}
	
	// set methods for variables - idk if needed
	
	public void setTitle(String t) {
		title = t;
	}
	
	public void setForename(String f) {
		forename = f;
	}
	
	public void setSurname(String s) {
		surname = s;
	}
	
	public void setEmail(String e) {
		email = e;
	}
	
	public void setMobile(String m) {
		mobile = m;
	}
	
	// constructor for person
	public User(String t, String f, String s, String e, String m, String p, Address a) {
		title = t;
		forename = f;
		surname = s;
		email = e;
		mobile = m;
		password = p;
		address = a;
	}
	
	//removes section after semi colon in a given inpu
	public static String removeSemiColon(String input) {
		String [] out = input.split(";");
		String output = out[0];
		return output;
	}
	public void cleanInputs() {
		String house = removeSemiColon(address.getHouse());
		String place = removeSemiColon(address.getPlace());
		String street = removeSemiColon(address.getStreet());
		String postCode = removeSemiColon(address.getPostCode());
		
		address = new Address(house,place,street,postCode);
		
		title = removeSemiColon(title.strip());
		forename = removeSemiColon(forename.strip());
		surname = removeSemiColon(surname.strip());
		email = removeSemiColon(email);
		mobile = removeSemiColon(mobile.strip());	
		password = removeSemiColon(password.strip());	
	}
	
	
	public String toString() {
		return "Title: " + title + "\nForename: " + forename + 
				"\nSurname: " + surname + "\nEmail: " + email
				+ "\nMobile Number: " + mobile + "\nPassword: " + password
				+ "\n" + address;
	}
}