package classCode;
public class Review{
	//variables for each review
	//description of satisfaction
	public String satisfaction;
	//rating 1-5 for each catergory
	public int cleanliness;
	public int communication;
	public int checkIn;
	public int accuracy;
	public int location;
	public int value;
	
	//get methods
	
	public String getSatisfaction() {
		return satisfaction;
	}
	
	public int getCleanliness() {
		return cleanliness;
	}
	
	public int getCommunication() {
		return communication;
	}
	
	public int getCheckIn() {
		return checkIn;
	}
	
	public int getAccuracy() {
		return accuracy;
	}
	
	public int getLocation() {
		return location;
	}
	
	public int getValue() {
		return value;
	}
	
	//constructor for a review
	public Review(String s, int cl,int com,int chin,int acc,int loc,int v) {
		satisfaction = s;
		cleanliness = cl;
		communication = com;
		checkIn = chin;
		accuracy = acc;
		location = loc;
		value =v;
	}
	
	public String toString() {
		return "Satisfaction: " + satisfaction + "\nCleanliness: " + cleanliness +
				"\nCommunication: " + communication + "\nCheck-in: " + checkIn +
				"\nAccuracy: "+ accuracy + "\nLocation: " + location + 
				"\nValue: " + value;
	}
	public static void main( String [] args) {
		Review myRev = new Review("Very Happy",1,3,5,2,5,6);
		System.out.println(myRev);
	}
}