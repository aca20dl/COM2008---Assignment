package classCode;
public class User{
	// variables for confidential details here
	private String title;
	private String forename;
	private String surname;
	private String email;
	private String mobile;
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
	public User(String t, String f, String s, String e, String m, Address a) {
		title = t;
		forename = f;
		surname = s;
		email = e;
		mobile = m;
		address = a;
	}
	
	public String toString() {
		return "Title: " + title + "\nForename: " + forename + 
				"\nSurname: " + surname + "\nEmail: " + email
				+ "\nMobile Number: " + mobile + "\n" + address;
	}
	public static void main (String [] args) {
		Address ad = new Address("25","Green ln", "Sheffield", "s109ju");
		User salma = new User("Miss","Salma","Hassan","s.h@email.com",
				"07946658987",ad);
		System.out.println(salma);
	}
}