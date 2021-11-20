package classCode;

public class Living{
	private boolean hasWifi;
	private boolean hasTv;
	private boolean hasSat;
	private boolean hasStreaming;
	private boolean hasDvdPlayer;
	private boolean hasBoardGames;
	
	public boolean getHasWifi() {
		return hasWifi;
	}
	
	public boolean getHasTv() {
		return hasTv;
	}
	
	public boolean getHasSat() {
		return hasSat;
	}
	
	public boolean getHasStreaming() {
		return hasStreaming;
	}
	
	public boolean getHasDvdPlayer() {
		return hasDvdPlayer;
	}
	
	public boolean getHasBoardGames() {
		return hasBoardGames;
	}
	
	public Living(boolean w, boolean t, boolean sat, boolean str,
			boolean d, boolean b) {
		hasWifi = w;
		hasTv= t;
		hasSat = sat;
		hasStreaming = str;
		hasDvdPlayer = d;
		hasBoardGames = b;
	}
	
	public String toString() {
		return "Has Wifi: " + hasWifi + "\nHas Tv: " + hasTv + 
				"\nHas Sattelite: " + hasSat + "\nHas Streaming: " + 
				hasStreaming + "\nHas Dvd Player: " + hasDvdPlayer
				+ "\nHas Board Games: " + hasBoardGames;
	}
	
	public static void main (String [] args) {
		Living living = new Living(true,false,false,false,true,true);
		System.out.println(living);
	}
}