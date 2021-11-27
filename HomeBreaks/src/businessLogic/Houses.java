package businessLogic;

import database.*;

import java.util.ArrayList;

import classCode.*;

public class Houses{
	
	public static int toInt(boolean b) {
		if (b)
			return 1;
		else
			return 0;
	}
	
	public static void addProperty(Host host, Property p,Sleeping s
			,Bathing b,Kitchen k,Living l,Utility u,Outdoor o,
			ArrayList<ChargeBand> bands) {
		
		//get pdID
		int pdID = Database.getID("PdID","Pdetails","Email",host.getEmail());
		
		//getHostID
		int hostID = Database.getID("HostID","Hosts","PdID",String.valueOf(pdID));
		
		Address ad = p.getAddress();
		//add address to address table
		Database.addAddress(ad);
		
		//get address id
		int adID = Database.getID("AdID","Addresses","PostCode",p.getAddress().getPostCode());
		//gather the rest of property info
		String name = p.getName();
		String des = p.getDescription();
		String gen = p.getGenLoc();
		int breakfast = toInt(p.getBreakfast());
		int maxG = p.getMaxGuest();
		int nBeds = p.getNumBed();
		int nBedr = p.getNumBedroom();
		int nBath = p.getNumBathroom();
		String columns = "Name,Description,GenLocation,Breakfast,"
				+ "MaxGuest,NumBeds,NumBedrooms,NumBathrooms,HostID,AdID";
		String values = "'" + name + "','" + des + "','" + gen + "'," +
				breakfast + "," + maxG + "," + nBeds + "," + nBedr + "," +
				nBath + "," + hostID + "," + adID;
		
		//insert the property into table
		Database.insertValues("Properties",columns,values);
		
		//get property id
		int propertyID = Database.getID("PropertyID","Properties","HostID",String.valueOf(hostID));
			
		
		//add all facilities to respective tables
		addSleeping(s,propertyID);
		addBathing(b,propertyID);
		addKitchen(k,propertyID);
		addLiving(l,propertyID);
		addUtility(u,propertyID);
		addOutdoor(o,propertyID);
		
		//add all charge bands
		for(ChargeBand c : bands) {
			addChargeBand(c,propertyID);
		}
		
		//get sleeping id
		int sleepingID =  Database.getID("SleepingID","Sleeping","PropertyID",String.valueOf(propertyID));
		//add all bedrooms to table
		ArrayList<Bedroom> bedrooms = s.getBedrooms();
		
		for(Bedroom room : bedrooms) {
			addBedroom(room,sleepingID);
		}
		
		//get bathing id
		int bathingID = Database.getID("BathingID","Bathing","PropertyID",String.valueOf(propertyID));
		//add all bathrooms to table
		ArrayList<Bathroom> bathrooms = b.getBathrooms();
		for(Bathroom bRoom : bathrooms) {
			addBathroom(bRoom,bathingID);
		}
	}
	
	// functions to add facilities to their tables in database
	//SLEEPING
	 public static void addSleeping(Sleeping s, int propertyID) {
		 String table = "Sleeping";
		 int hasBL = toInt(s.getHasBedLinen());
		 int hasT = toInt(s.getHasTowels());
		 int totalBr = s.getTotalBedrooms();
		 int totalB = s.getTotalBeds();
		 int totalS = s.getTotalSleepers();
		 Database.insertValues(table,"hasBedLinen,HasTowels,TotalBedrooms,"
		 		+ "TotalBeds,TotalSleepers,PropertyID",hasBL +
		 		"," + hasT + "," + totalBr + "," + totalB + 
		 		"," + totalS + "," + propertyID);
	 }
	 
	 //BEDROOM
	 public static void addBedroom(Bedroom b,int sleepingID) {
		 String bed1 = b.getBed1().getType().toString();
		 String bed2 = "";
		 if (b.getBed2() != null)
			 bed2 = b.getBed2().getType().toString();
		 int numBeds = b.getNumBeds();
		 int numSleepers = b.getNumSleepers();
		 String table = "Bedrooms";
		 Database.insertValues(table,"BedOne,BedTwo,NumBeds,NumSleepers,SleepingID","'" + bed1 + "','" + bed2 + "',"
		 + numBeds + "," + numSleepers + "," + sleepingID);
	 }
	 
	 //Bathroom
	 public static void addBathroom(Bathroom b, int bathingID) {
		 String table = "Bathrooms";
		 int toilet = toInt(b.getHasToilet());
		 int bath = toInt(b.getHasBath());
		 int shower = toInt(b.getHasShower());
		 int shared = toInt(b.getIsShared());
		 Database.insertValues(table,"HasToilet,HasBath,HasShower,IsShared,BathingID",
				 toilet + "," + bath + "," + shower + "," +
		         shared + "," + bathingID);
	 }
	 
	 //BATHING
	 public static void addBathing(Bathing b, int propertyID) {
		 String table = "Bathing";
		 int hasHD = toInt(b.getHasHairDryer());
		 int hasS = toInt(b.getHasShampoo());
		 int hasTP = toInt(b.getHasToiletPaper());
		 int totalB = b.getTotalBathrooms();
		 Database.insertValues(table,"HasHairDryer,HasShampoo,HasToiletPaper,"
		 		+ "TotalBathrooms,PropertyID",hasHD + "," + hasS + "," 
		 		+ hasTP + "," + totalB + "," + propertyID);
	 }
	 
	 //Kitchen
	 public static void addKitchen(Kitchen k, int propertyID) {
		 int hasF = toInt(k.getHasFridge());
		 int hasM = toInt(k.getHasMicrowave());
		 int hasO = toInt(k.getHasOven());
		 int hasS = toInt(k.getHasStove());
		 int hasD = toInt(k.getHasDishwasher());
		 int hasT = toInt(k.getHasTableware());
		 int hasC = toInt(k.getHasCookware());
		 int hasBP = toInt(k.getHasBasicProv());
		 String columns = "HasFridge,HasMicrowave,HasOven,HasStove,"
		 		+ "HasDishwasher,HasTableware,HasCookware,HasBasicProv,"
		 		+ "PropertyID";
		 String values = hasF + "," + hasM + "," + hasO + "," + hasS 
				 + "," + hasD + "," + hasT + "," + hasC + "," + hasBP 
				 + "," + propertyID;
		 Database.insertValues("Kitchen",columns,values);
	 }
	 
	 //Living
	 public static void addLiving(Living l, int propertyID) {
		 int hasW = toInt(l.getHasWifi());
		 int hasT = toInt(l.getHasTv());
		 int hasSat = toInt(l.getHasSat());
		 int hasStr = toInt(l.getHasStreaming());
		 int hasD = toInt(l.getHasDvdPlayer());
		 int hasB = toInt(l.getHasBoardGames());
		 String columns = "HasWifi,HasTV,HasSat,HasStreaming,"
		 		+ "HasDVDPlayer,HasBoardGames,PropertyID";
		 String values = hasW + "," + hasT + "," + hasSat +
				 "," + hasStr + "," + hasD + "," + hasB + "," +
				 propertyID;
		 Database.insertValues("Living",columns,values);
	 }
	 
	 //UTILITY
	 public static void addUtility(Utility u, int propertyID) {
		 int hasH = toInt(u.getHasHeating());
		 int hasW = toInt(u.getHasWashingMachine());
		 int hasD = toInt(u.getHasDryingMachine());
		 int hasF = toInt(u.getHasFireExtinguisher());
		 int hasS = toInt(u.getHasSmokeAlarm());
		 int hasFAK = toInt(u.getHasFirstAidKit());
		 String columns = "HasHeating,HasWashingMachine,"
		 		+ "HasDryingMachine,HasFireExtinguisher,"
		 		+ "HasSmokeAlarm,HasFirstAidKit,PropertyID";
		 String values = hasH + "," + hasW + "," + hasD + ","
				 + hasF + "," + hasS + "," + hasFAK + "," + propertyID;
		 Database.insertValues("Utility",columns,values);
	 }
	 
	 //OUTDOOR
	 public static void addOutdoor(Outdoor o, int propertyID) {
		 int hasFP = toInt(o.getHasFreeParking());
		 int hasRP = toInt(o.getHasRoadParking());
		 int hasPCP = toInt(o.getHasPaidCarPark());
		 int hasP = toInt(o.getHasPatio());
		 int hasB = toInt(o.getHasBarbeque());
		 String columns = "HasFreeParking,HasRoadParking,"
		 		+ "HasPaidCarPark,HasPatio,HasBarbeque,PropertyID";
		 String values = hasFP + "," + hasRP + "," + hasPCP + 
				 "," + hasP + "," + hasB + "," + propertyID;
		 Database.insertValues("Outdoor",columns,values);
	 }
	 
	 //ADD CHARGEBANDS
	 public static void addChargeBand(ChargeBand c, int propertyID) {
		 String start = c.getStart().toString();
		 String end = c.getEnd().toString();
		 int ppn = c.getPricePerNight();
		 int sc = c.getServiceCharge();
		 int cc = c.getCleaningCharge();
		 String columns = "StartDate,EndDate,PricePerNight,ServiceCharge,"
		 		+ "CleaningCharge,PropertyID";
		 String values = "'" + start + "','" + end + "'," + ppn +
				 "," + sc + "," + cc + "," + propertyID;
		 Database.insertValues("ChargeBands",columns,values);
		 
	 }
}