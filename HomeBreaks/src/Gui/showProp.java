package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.*;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

import classCode.*;
import businessLogic.*;
import database.*;

import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.List;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Label;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;

public class showProp {

	private JFrame frame;
	private JTextField name;
	private JTextField genLoc;
	private JTextField breakfast;
	private JTextField hasBedLinen;
	private JTextField hasTowel;
	private JTextField totalBedrooms;
	private JTextField totalBeds;
	private JTextField totalSleepers;
	private JTextField totalAverage;
	private JTextField HasHairDryer;
	private JTextField hasToiletPaper;
	private JTextField totalBathrooms;
	private JTextField hasShampoo;
	private JTextField hasFridge;
	private JTextField hasOven;
	private JTextField hasStove;
	private JTextField hasMicrowave;
	private JTextField hasBasicProv;
	private JTextField hasCookware;
	private JTextField hasTableware;
	private JTextField hasDishwasher;
	private JTextField hasWifi;
	private JTextField hasSat;
	private JTextField hasTv;
	private JTextField hasBoardGames;
	private JTextField hasDvdPlayer;
	private JTextField hasStreaming;
	private JTextField hasHeating;
	private JTextField hasDryingMachine;
	private JTextField hasWashingMachine;
	private JTextField hasFirstAidKit;
	private JTextField hasSmokeAlarm;
	private JTextField hasFireExtinguisher;
	private JTextField hasFreeParking;
	private JTextField hasPaidCarPark;
	private JTextField hasRoadParking;
	private JTextField hasBarbeque;
	private JTextField hasPatio;
	private JTable tableBedrooms;
	private JTable tableBathrooms;
	private JTable tableChargeBands;
	private JTextField cleanliness;
	private JTextField communication;
	private JTextField checkIn;
	private JTextField Accuracy;
	private JTextField location;
	private JTextField value;
	//all the facilities and lists for property
	private String [] pInfo;
	private Sleeping sleeping;
	private ArrayList<Bedroom> bedrooms;
	private Bathing bathing;
	private ArrayList<Bathroom> bathrooms;
	private Kitchen kitchen;
	private Living living;
	private Utility utility;
	private Outdoor outdoor;
	private ArrayList<ChargeBand> bands;
	private String [] pubInfo;
	private String [] hostInfo;
	private ArrayList<String> reviews;
	private JTextField username;
	private JTextField superHost;
	DefaultListModel reviewsModel;
	
	public void goToPanel(JPanel p,JLayeredPane layeredPane) {
		layeredPane.removeAll();
		layeredPane.add(p);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					showProp window = new showProp(null,4);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public showProp(User user, int propertyID) {
		initialize(user, propertyID);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(User user, int propertyID) {
		
		Database.connectDB();
		// get all the values for facilities and information
		sleeping = Houses.getSleeping(propertyID);
		bedrooms = Houses.getBedrooms(propertyID);
		bathing = Houses.getBathing(propertyID);
		bathrooms = Houses.getBathrooms(propertyID);
		kitchen = Houses.getKitchen(propertyID);
		living = Houses.getLiving(propertyID);
		utility = Houses.getUtility(propertyID);
		outdoor = Houses.getoutdoor(propertyID);
		pubInfo = Houses.getProperty(propertyID);
		bands = Houses.getBands(propertyID);
		hostInfo = guestActions.hostPubInfo(propertyID);
		reviews = Houses.getReviews(propertyID);
		Database.disconnectDB();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 801, 601);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Property Information");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 229, 24);
		frame.getContentPane().add(lblNewLabel);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(376, 102, 392, 427);
		frame.getContentPane().add(layeredPane);
		
		// function to switch panels
		
		JPanel publicInfo = new JPanel();
		layeredPane.setLayer(publicInfo, 9);
		publicInfo.setBounds(0, 0, 392, 427);
		layeredPane.add(publicInfo);
		publicInfo.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Public Information");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 11, 190, 34);
		publicInfo.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Name");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(10, 56, 190, 34);
		publicInfo.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Description");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_1_1.setBounds(10, 137, 104, 34);
		publicInfo.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("General Location");
		lblNewLabel_1_1_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_1_2.setBounds(10, 237, 190, 34);
		publicInfo.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("Offers Breakfast:");
		lblNewLabel_1_1_3.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_1_3.setBounds(10, 329, 190, 34);
		publicInfo.add(lblNewLabel_1_1_3);
		
		name = new JTextField(pubInfo[0]);
		name.setBackground(Color.WHITE);
		name.setEditable(false);
		name.setBounds(10, 91, 170, 20);
		publicInfo.add(name);
		name.setColumns(10);
		
		genLoc = new JTextField(pubInfo[2]);
		genLoc.setBackground(Color.WHITE);
		genLoc.setEditable(false);
		genLoc.setColumns(10);
		genLoc.setBounds(10, 282, 170, 20);
		publicInfo.add(genLoc);
		
		breakfast = new JTextField(String.valueOf(Houses.toBool(Integer.valueOf(pubInfo[3]))));
		breakfast.setBackground(Color.WHITE);
		breakfast.setEditable(false);
		breakfast.setColumns(10);
		breakfast.setBounds(10, 368, 170, 20);
		publicInfo.add(breakfast);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 165, 170, 61);
		publicInfo.add(scrollPane);
		
		JTextArea description = new JTextArea(pubInfo[1]);
		description.setEditable(false);
		scrollPane.setViewportView(description);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Average Rating:");
		lblNewLabel_1_2_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_2_1_1.setBounds(211, 11, 120, 34);
		publicInfo.add(lblNewLabel_1_2_1_1);
		
		totalAverage = new JTextField(pubInfo[4]);
		totalAverage.setEditable(false);
		totalAverage.setColumns(10);
		totalAverage.setBackground(Color.WHITE);
		totalAverage.setBounds(341, 19, 41, 20);
		publicInfo.add(totalAverage);
		
		JLabel lblNewLabel_1_2_1_1_1 = new JLabel("Cleanliness:");
		lblNewLabel_1_2_1_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_2_1_1_1.setBounds(210, 77, 120, 34);
		publicInfo.add(lblNewLabel_1_2_1_1_1);
		
		Database.connectDB();
		cleanliness = new JTextField(String.valueOf(Houses.getAverageReview(propertyID, "Cleanliness")));
		cleanliness.setEditable(false);
		cleanliness.setColumns(10);
		cleanliness.setBackground(Color.WHITE);
		cleanliness.setBounds(340, 85, 41, 20);
		publicInfo.add(cleanliness);
		
		JLabel lblNewLabel_1_2_1_1_2 = new JLabel("Communication:");
		lblNewLabel_1_2_1_1_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_2_1_1_2.setBounds(211, 137, 131, 34);
		publicInfo.add(lblNewLabel_1_2_1_1_2);
		
		communication = new JTextField(String.valueOf(Houses.getAverageReview(propertyID, "Communication")));
		communication.setEditable(false);
		communication.setColumns(10);
		communication.setBackground(Color.WHITE);
		communication.setBounds(341, 145, 41, 20);
		publicInfo.add(communication);
		
		JLabel lblNewLabel_1_2_1_1_3 = new JLabel("Check-In:");
		lblNewLabel_1_2_1_1_3.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_2_1_1_3.setBounds(211, 192, 120, 34);
		publicInfo.add(lblNewLabel_1_2_1_1_3);
		
		checkIn = new JTextField(String.valueOf(Houses.getAverageReview(propertyID, "CheckIn")));
		checkIn.setEditable(false);
		checkIn.setColumns(10);
		checkIn.setBackground(Color.WHITE);
		checkIn.setBounds(341, 200, 41, 20);
		publicInfo.add(checkIn);
		
		JLabel lblNewLabel_1_2_1_1_4 = new JLabel("Accuracy:");
		lblNewLabel_1_2_1_1_4.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_2_1_1_4.setBounds(210, 256, 120, 34);
		publicInfo.add(lblNewLabel_1_2_1_1_4);
		
		Accuracy = new JTextField(String.valueOf(Houses.getAverageReview(propertyID, "Accuracy")));
		Accuracy.setEditable(false);
		Accuracy.setColumns(10);
		Accuracy.setBackground(Color.WHITE);
		Accuracy.setBounds(340, 264, 41, 20);
		publicInfo.add(Accuracy);
		
		JLabel lblNewLabel_1_2_1_1_5 = new JLabel("Location:");
		lblNewLabel_1_2_1_1_5.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_2_1_1_5.setBounds(211, 317, 120, 34);
		publicInfo.add(lblNewLabel_1_2_1_1_5);
		
		location = new JTextField(String.valueOf(Houses.getAverageReview(propertyID, "Location")));
		location.setEditable(false);
		location.setColumns(10);
		location.setBackground(Color.WHITE);
		location.setBounds(341, 325, 41, 20);
		publicInfo.add(location);
		
		JLabel lblNewLabel_1_2_1_1_6 = new JLabel("Value for Money:");
		lblNewLabel_1_2_1_1_6.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_2_1_1_6.setBounds(211, 382, 131, 34);
		publicInfo.add(lblNewLabel_1_2_1_1_6);
		
		value = new JTextField(String.valueOf(Houses.getAverageReview(propertyID, "Value")));
		value.setEditable(false);
		value.setColumns(10);
		value.setBackground(Color.WHITE);
		value.setBounds(341, 390, 41, 20);
		publicInfo.add(value);
		Database.disconnectDB();
		
		JPanel sleepingP = new JPanel();
		layeredPane.setLayer(sleepingP, 8);
		sleepingP.setLayout(null);
		sleepingP.setBounds(0, 0, 392, 427);
		layeredPane.add(sleepingP);
		
		JLabel lblNewLabel_1_2 = new JLabel("Sleeping Facility");
		lblNewLabel_1_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(10, 11, 120, 34);
		sleepingP.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_1_4 = new JLabel("Has Bedlinen:");
		lblNewLabel_1_1_4.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_1_4.setBounds(10, 56, 190, 34);
		sleepingP.add(lblNewLabel_1_1_4);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Has Towels;");
		lblNewLabel_1_1_2_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_1_2_1.setBounds(10, 122, 190, 34);
		sleepingP.add(lblNewLabel_1_1_2_1);
		
		JLabel lblNewLabel_1_1_3_2 = new JLabel("Total Bedrooms");
		lblNewLabel_1_1_3_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_1_3_2.setBounds(10, 198, 190, 34);
		sleepingP.add(lblNewLabel_1_1_3_2);
		
		hasBedLinen = new JTextField(String.valueOf(sleeping.getHasBedLinen()));
		hasBedLinen.setEditable(false);
		hasBedLinen.setColumns(10);
		hasBedLinen.setBackground(Color.WHITE);
		hasBedLinen.setBounds(10, 91, 170, 20);
		sleepingP.add(hasBedLinen);
		
		hasTowel = new JTextField(String.valueOf(sleeping.getHasTowels()));
		hasTowel.setEditable(false);
		hasTowel.setColumns(10);
		hasTowel.setBackground(Color.WHITE);
		hasTowel.setBounds(10, 167, 170, 20);
		sleepingP.add(hasTowel);
		
		totalBedrooms = new JTextField(String.valueOf(sleeping.getTotalBedrooms()));
		totalBedrooms.setEditable(false);
		totalBedrooms.setColumns(10);
		totalBedrooms.setBackground(Color.WHITE);
		totalBedrooms.setBounds(10, 237, 170, 20);
		sleepingP.add(totalBedrooms);
		
		JLabel lblNewLabel_1_1_3_1_1 = new JLabel("Total Beds");
		lblNewLabel_1_1_3_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_1_3_1_1.setBounds(10, 268, 134, 34);
		sleepingP.add(lblNewLabel_1_1_3_1_1);
		
		totalBeds = new JTextField(String.valueOf(sleeping.getTotalBeds()));
		totalBeds.setEditable(false);
		totalBeds.setColumns(10);
		totalBeds.setBackground(Color.WHITE);
		totalBeds.setBounds(10, 307, 170, 20);
		sleepingP.add(totalBeds);
		
		JLabel lblNewLabel_1_1_3_1_1_1 = new JLabel("Total Sleepers");
		lblNewLabel_1_1_3_1_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_1_3_1_1_1.setBounds(10, 338, 134, 34);
		sleepingP.add(lblNewLabel_1_1_3_1_1_1);
		
		totalSleepers = new JTextField(String.valueOf(sleeping.getTotalSleepers()));
		totalSleepers.setEditable(false);
		totalSleepers.setColumns(10);
		totalSleepers.setBackground(Color.WHITE);
		totalSleepers.setBounds(10, 377, 170, 20);
		sleepingP.add(totalSleepers);
		
		JPanel bathingP = new JPanel();
		layeredPane.setLayer(bathingP, 7);
		bathingP.setLayout(null);
		bathingP.setBounds(0, 0, 392, 427);
		layeredPane.add(bathingP);
		
		JLabel lblNewLabel_1_3 = new JLabel("Bathing Facility");
		lblNewLabel_1_3.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_3.setBounds(10, 11, 190, 34);
		bathingP.add(lblNewLabel_1_3);
		
		JLabel label = new JLabel("Has Hair dryer:");
		label.setFont(new Font("Arial", Font.BOLD, 15));
		label.setBounds(10, 56, 190, 34);
		bathingP.add(label);
		
		JLabel lblNewLabel_1_1_2_2 = new JLabel("Has Toilet paper:");
		lblNewLabel_1_1_2_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_1_2_2.setBounds(10, 231, 190, 34);
		bathingP.add(lblNewLabel_1_1_2_2);
		
		JLabel lblNewLabel_1_1_3_1 = new JLabel("Total Bathrooms:");
		lblNewLabel_1_1_3_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_1_3_1.setBounds(10, 315, 190, 34);
		bathingP.add(lblNewLabel_1_1_3_1);
		
		HasHairDryer = new JTextField(String.valueOf(bathing.getHasHairDryer()));
		HasHairDryer.setEditable(false);
		HasHairDryer.setColumns(10);
		HasHairDryer.setBackground(Color.WHITE);
		HasHairDryer.setBounds(10, 91, 170, 20);
		bathingP.add(HasHairDryer);
		
		hasToiletPaper = new JTextField(String.valueOf(bathing.getHasToiletPaper()));
		hasToiletPaper.setEditable(false);
		hasToiletPaper.setColumns(10);
		hasToiletPaper.setBackground(Color.WHITE);
		hasToiletPaper.setBounds(10, 269, 170, 20);
		bathingP.add(hasToiletPaper);
		
		totalBathrooms = new JTextField(String.valueOf(bathing.getTotalBathrooms()));
		totalBathrooms.setEditable(false);
		totalBathrooms.setColumns(10);
		totalBathrooms.setBackground(Color.WHITE);
		totalBathrooms.setBounds(10, 354, 170, 20);
		bathingP.add(totalBathrooms);
		
		hasShampoo = new JTextField(String.valueOf(bathing.getHasShampoo()));
		hasShampoo.setEditable(false);
		hasShampoo.setColumns(10);
		hasShampoo.setBackground(Color.WHITE);
		hasShampoo.setBounds(10, 180, 170, 20);
		bathingP.add(hasShampoo);
		
		JLabel lblNewLabel_1_1_2_2_1 = new JLabel("Has Shampoo");
		lblNewLabel_1_1_2_2_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_1_2_2_1.setBounds(10, 140, 190, 34);
		bathingP.add(lblNewLabel_1_1_2_2_1);
		
		JPanel kitchenP = new JPanel();
		layeredPane.setLayer(kitchenP, 6);
		kitchenP.setLayout(null);
		kitchenP.setBounds(0, 0, 392, 427);
		layeredPane.add(kitchenP);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Kitchen Facility");
		lblNewLabel_1_3_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_3_1.setBounds(10, 11, 190, 34);
		kitchenP.add(lblNewLabel_1_3_1);
		
		JLabel hasHairDryer_1 = new JLabel("Has Fridge:");
		hasHairDryer_1.setFont(new Font("Arial", Font.BOLD, 15));
		hasHairDryer_1.setBounds(10, 56, 139, 34);
		kitchenP.add(hasHairDryer_1);
		
		JLabel lblNewLabel_1_1_2_2_2 = new JLabel("Has Oven:");
		lblNewLabel_1_1_2_2_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_1_2_2_2.setBounds(10, 231, 139, 34);
		kitchenP.add(lblNewLabel_1_1_2_2_2);
		
		JLabel lblNewLabel_1_1_3_1_2 = new JLabel("Has Stove:");
		lblNewLabel_1_1_3_1_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_1_3_1_2.setBounds(10, 315, 139, 34);
		kitchenP.add(lblNewLabel_1_1_3_1_2);
		
		hasFridge = new JTextField(String.valueOf(kitchen.getHasFridge()));
		hasFridge.setEditable(false);
		hasFridge.setColumns(10);
		hasFridge.setBackground(Color.WHITE);
		hasFridge.setBounds(10, 91, 139, 20);
		kitchenP.add(hasFridge);
		
		hasOven = new JTextField(String.valueOf(kitchen.getHasOven()));
		hasOven.setEditable(false);
		hasOven.setColumns(10);
		hasOven.setBackground(Color.WHITE);
		hasOven.setBounds(10, 269, 139, 20);
		kitchenP.add(hasOven);
		
		hasStove = new JTextField(String.valueOf(kitchen.getHasStove()));
		hasStove.setEditable(false);
		hasStove.setColumns(10);
		hasStove.setBackground(Color.WHITE);
		hasStove.setBounds(10, 354, 139, 20);
		kitchenP.add(hasStove);
		
		hasMicrowave = new JTextField(String.valueOf(kitchen.getHasMicrowave()));
		hasMicrowave.setEditable(false);
		hasMicrowave.setColumns(10);
		hasMicrowave.setBackground(Color.WHITE);
		hasMicrowave.setBounds(10, 180, 139, 20);
		kitchenP.add(hasMicrowave);
		
		JLabel lblNewLabel_1_1_2_2_1_1 = new JLabel("Has Microwave:");
		lblNewLabel_1_1_2_2_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_1_2_2_1_1.setBounds(10, 140, 139, 34);
		kitchenP.add(lblNewLabel_1_1_2_2_1_1);
		
		hasBasicProv = new JTextField(String.valueOf(kitchen.getHasBasicProv()));
		hasBasicProv.setEditable(false);
		hasBasicProv.setColumns(10);
		hasBasicProv.setBackground(Color.WHITE);
		hasBasicProv.setBounds(221, 354, 139, 20);
		kitchenP.add(hasBasicProv);
		
		JLabel lblNewLabel_1_1_3_1_2_1 = new JLabel("Has Basic Provisions:");
		lblNewLabel_1_1_3_1_2_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_1_3_1_2_1.setBounds(221, 315, 161, 34);
		kitchenP.add(lblNewLabel_1_1_3_1_2_1);
		
		hasCookware = new JTextField(String.valueOf(kitchen.getHasCookware()));
		hasCookware.setEditable(false);
		hasCookware.setColumns(10);
		hasCookware.setBackground(Color.WHITE);
		hasCookware.setBounds(221, 269, 139, 20);
		kitchenP.add(hasCookware);
		
		JLabel lblNewLabel_1_1_2_2_2_1 = new JLabel("Has Cookware:");
		lblNewLabel_1_1_2_2_2_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_1_2_2_2_1.setBounds(221, 231, 139, 34);
		kitchenP.add(lblNewLabel_1_1_2_2_2_1);
		
		hasTableware = new JTextField(String.valueOf(kitchen.getHasTableware()));
		hasTableware.setEditable(false);
		hasTableware.setColumns(10);
		hasTableware.setBackground(Color.WHITE);
		hasTableware.setBounds(221, 180, 139, 20);
		kitchenP.add(hasTableware);
		
		JLabel lblNewLabel_1_1_2_2_1_1_1 = new JLabel("Has Tableware:");
		lblNewLabel_1_1_2_2_1_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_1_2_2_1_1_1.setBounds(221, 140, 139, 34);
		kitchenP.add(lblNewLabel_1_1_2_2_1_1_1);
		
		hasDishwasher = new JTextField(String.valueOf(kitchen.getHasDishwasher()));
		hasDishwasher.setEditable(false);
		hasDishwasher.setColumns(10);
		hasDishwasher.setBackground(Color.WHITE);
		hasDishwasher.setBounds(221, 91, 139, 20);
		kitchenP.add(hasDishwasher);
		
		JLabel hasHairDryer_1_1 = new JLabel("Has Dishwasher:");
		hasHairDryer_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		hasHairDryer_1_1.setBounds(221, 56, 139, 34);
		kitchenP.add(hasHairDryer_1_1);
		
		JPanel livingP = new JPanel();
		layeredPane.setLayer(livingP, 5);
		livingP.setLayout(null);
		livingP.setBounds(0, 0, 392, 427);
		layeredPane.add(livingP);
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel("Living Facility");
		lblNewLabel_1_3_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_3_1_1.setBounds(10, 11, 190, 34);
		livingP.add(lblNewLabel_1_3_1_1);
		
		JLabel hasHairDryer_1_2 = new JLabel("Has Wifi:");
		hasHairDryer_1_2.setFont(new Font("Arial", Font.BOLD, 15));
		hasHairDryer_1_2.setBounds(10, 90, 139, 34);
		livingP.add(hasHairDryer_1_2);
		
		JLabel lblNewLabel_1_1_2_2_2_2 = new JLabel("Has Satellite:");
		lblNewLabel_1_1_2_2_2_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_1_2_2_2_2.setBounds(10, 300, 139, 34);
		livingP.add(lblNewLabel_1_1_2_2_2_2);
		
		hasWifi = new JTextField(String.valueOf(living.getHasWifi()));
		hasWifi.setEditable(false);
		hasWifi.setColumns(10);
		hasWifi.setBackground(Color.WHITE);
		hasWifi.setBounds(10, 125, 139, 20);
		livingP.add(hasWifi);
		
		hasSat = new JTextField(String.valueOf(living.getHasSat()));
		hasSat.setEditable(false);
		hasSat.setColumns(10);
		hasSat.setBackground(Color.WHITE);
		hasSat.setBounds(10, 338, 139, 20);
		livingP.add(hasSat);
		
		hasTv = new JTextField(String.valueOf(living.getHasTv()));
		hasTv.setEditable(false);
		hasTv.setColumns(10);
		hasTv.setBackground(Color.WHITE);
		hasTv.setBounds(10, 238, 139, 20);
		livingP.add(hasTv);
		
		JLabel lblNewLabel_1_1_2_2_1_1_2 = new JLabel("Has TV:");
		lblNewLabel_1_1_2_2_1_1_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_1_2_2_1_1_2.setBounds(10, 198, 139, 34);
		livingP.add(lblNewLabel_1_1_2_2_1_1_2);
		
		hasBoardGames = new JTextField(String.valueOf(living.getHasBoardGames()));
		hasBoardGames.setEditable(false);
		hasBoardGames.setColumns(10);
		hasBoardGames.setBackground(Color.WHITE);
		hasBoardGames.setBounds(221, 338, 139, 20);
		livingP.add(hasBoardGames);
		
		JLabel lblNewLabel_1_1_2_2_2_1_1 = new JLabel("Has Board Games:");
		lblNewLabel_1_1_2_2_2_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_1_2_2_2_1_1.setBounds(221, 300, 139, 34);
		livingP.add(lblNewLabel_1_1_2_2_2_1_1);
		
		hasDvdPlayer = new JTextField(String.valueOf(living.getHasDvdPlayer()));
		hasDvdPlayer.setEditable(false);
		hasDvdPlayer.setColumns(10);
		hasDvdPlayer.setBackground(Color.WHITE);
		hasDvdPlayer.setBounds(221, 238, 139, 20);
		livingP.add(hasDvdPlayer);
		
		JLabel lblNewLabel_1_1_2_2_1_1_1_1 = new JLabel("Has DVD Player:");
		lblNewLabel_1_1_2_2_1_1_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_1_2_2_1_1_1_1.setBounds(221, 198, 139, 34);
		livingP.add(lblNewLabel_1_1_2_2_1_1_1_1);
		
		hasStreaming = new JTextField(String.valueOf(living.getHasStreaming()));
		hasStreaming.setEditable(false);
		hasStreaming.setColumns(10);
		hasStreaming.setBackground(Color.WHITE);
		hasStreaming.setBounds(221, 125, 139, 20);
		livingP.add(hasStreaming);
		
		JLabel hasHairDryer_1_1_1 = new JLabel("Has Streaming:");
		hasHairDryer_1_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		hasHairDryer_1_1_1.setBounds(221, 90, 139, 34);
		livingP.add(hasHairDryer_1_1_1);
		
		JPanel utilityP = new JPanel();
		layeredPane.setLayer(utilityP, 4);
		utilityP.setLayout(null);
		utilityP.setBounds(0, 0, 392, 427);
		layeredPane.add(utilityP);
		
		JLabel lblNewLabel_1_3_1_1_1 = new JLabel("Utility Facility");
		lblNewLabel_1_3_1_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_3_1_1_1.setBounds(10, 11, 190, 34);
		utilityP.add(lblNewLabel_1_3_1_1_1);
		
		JLabel hasHairDryer_1_2_1 = new JLabel("Has Heating:");
		hasHairDryer_1_2_1.setFont(new Font("Arial", Font.BOLD, 15));
		hasHairDryer_1_2_1.setBounds(10, 90, 139, 34);
		utilityP.add(hasHairDryer_1_2_1);
		
		JLabel lblNewLabel_1_1_2_2_2_2_1 = new JLabel("Drying Machine:");
		lblNewLabel_1_1_2_2_2_2_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_1_2_2_2_2_1.setBounds(10, 300, 139, 34);
		utilityP.add(lblNewLabel_1_1_2_2_2_2_1);
		
		hasHeating = new JTextField(String.valueOf(utility.getHasHeating()));
		hasHeating.setEditable(false);
		hasHeating.setColumns(10);
		hasHeating.setBackground(Color.WHITE);
		hasHeating.setBounds(10, 125, 139, 20);
		utilityP.add(hasHeating);
		
		hasDryingMachine = new JTextField(String.valueOf(utility.getHasDryingMachine()));
		hasDryingMachine.setEditable(false);
		hasDryingMachine.setColumns(10);
		hasDryingMachine.setBackground(Color.WHITE);
		hasDryingMachine.setBounds(10, 338, 139, 20);
		utilityP.add(hasDryingMachine);
		
		hasWashingMachine = new JTextField(String.valueOf(utility.getHasWashingMachine()));
		hasWashingMachine.setEditable(false);
		hasWashingMachine.setColumns(10);
		hasWashingMachine.setBackground(Color.WHITE);
		hasWashingMachine.setBounds(10, 238, 139, 20);
		utilityP.add(hasWashingMachine);
		
		JLabel lblNewLabel_1_1_2_2_1_1_2_1 = new JLabel("Washing Machine:");
		lblNewLabel_1_1_2_2_1_1_2_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_1_2_2_1_1_2_1.setBounds(10, 198, 139, 34);
		utilityP.add(lblNewLabel_1_1_2_2_1_1_2_1);
		
		hasFirstAidKit = new JTextField(String.valueOf(utility.getHasFirstAidKit()));
		hasFirstAidKit.setEditable(false);
		hasFirstAidKit.setColumns(10);
		hasFirstAidKit.setBackground(Color.WHITE);
		hasFirstAidKit.setBounds(221, 338, 139, 20);
		utilityP.add(hasFirstAidKit);
		
		JLabel lblNewLabel_1_1_2_2_2_1_1_1 = new JLabel("First Aid Kit:");
		lblNewLabel_1_1_2_2_2_1_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_1_2_2_2_1_1_1.setBounds(221, 300, 139, 34);
		utilityP.add(lblNewLabel_1_1_2_2_2_1_1_1);
		
		hasSmokeAlarm = new JTextField(String.valueOf(utility.getHasSmokeAlarm()));
		hasSmokeAlarm.setEditable(false);
		hasSmokeAlarm.setColumns(10);
		hasSmokeAlarm.setBackground(Color.WHITE);
		hasSmokeAlarm.setBounds(221, 238, 139, 20);
		utilityP.add(hasSmokeAlarm);
		
		JLabel lblNewLabel_1_1_2_2_1_1_1_1_1 = new JLabel("Smoke Alarm:");
		lblNewLabel_1_1_2_2_1_1_1_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_1_2_2_1_1_1_1_1.setBounds(221, 198, 139, 34);
		utilityP.add(lblNewLabel_1_1_2_2_1_1_1_1_1);
		
		hasFireExtinguisher = new JTextField(String.valueOf(utility.getHasFireExtinguisher()));
		hasFireExtinguisher.setEditable(false);
		hasFireExtinguisher.setColumns(10);
		hasFireExtinguisher.setBackground(Color.WHITE);
		hasFireExtinguisher.setBounds(221, 125, 139, 20);
		utilityP.add(hasFireExtinguisher);
		
		JLabel hasHairDryer_1_1_1_1 = new JLabel("Fire Exinguisher:");
		hasHairDryer_1_1_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		hasHairDryer_1_1_1_1.setBounds(221, 90, 139, 34);
		utilityP.add(hasHairDryer_1_1_1_1);
		
		JPanel outdoorP = new JPanel();
		layeredPane.setLayer(outdoorP, 3);
		outdoorP.setLayout(null);
		outdoorP.setBounds(0, 0, 392, 427);
		layeredPane.add(outdoorP);
		
		JLabel lblNewLabel_1_3_1_1_2 = new JLabel("Outdoor Facility");
		lblNewLabel_1_3_1_1_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_3_1_1_2.setBounds(10, 11, 190, 34);
		outdoorP.add(lblNewLabel_1_3_1_1_2);
		
		JLabel hasHairDryer_1_2_2 = new JLabel("Free Parking:");
		hasHairDryer_1_2_2.setFont(new Font("Arial", Font.BOLD, 15));
		hasHairDryer_1_2_2.setBounds(10, 90, 139, 34);
		outdoorP.add(hasHairDryer_1_2_2);
		
		JLabel lblNewLabel_1_1_2_2_2_2_2 = new JLabel("Paid Car Park");
		lblNewLabel_1_1_2_2_2_2_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_1_2_2_2_2_2.setBounds(10, 300, 139, 34);
		outdoorP.add(lblNewLabel_1_1_2_2_2_2_2);
		
		hasFreeParking = new JTextField(String.valueOf(outdoor.getHasFreeParking()));
		hasFreeParking.setEditable(false);
		hasFreeParking.setColumns(10);
		hasFreeParking.setBackground(Color.WHITE);
		hasFreeParking.setBounds(10, 125, 139, 20);
		outdoorP.add(hasFreeParking);
		
		hasPaidCarPark = new JTextField(String.valueOf(outdoor.getHasPaidCarPark()));
		hasPaidCarPark.setEditable(false);
		hasPaidCarPark.setColumns(10);
		hasPaidCarPark.setBackground(Color.WHITE);
		hasPaidCarPark.setBounds(10, 338, 139, 20);
		outdoorP.add(hasPaidCarPark);
		
		hasRoadParking = new JTextField(String.valueOf(outdoor.getHasRoadParking()));
		hasRoadParking.setEditable(false);
		hasRoadParking.setColumns(10);
		hasRoadParking.setBackground(Color.WHITE);
		hasRoadParking.setBounds(10, 238, 139, 20);
		outdoorP.add(hasRoadParking);
		
		JLabel lblNewLabel_1_1_2_2_1_1_2_2 = new JLabel("Road Parking:");
		lblNewLabel_1_1_2_2_1_1_2_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_1_2_2_1_1_2_2.setBounds(10, 198, 139, 34);
		outdoorP.add(lblNewLabel_1_1_2_2_1_1_2_2);
		
		hasBarbeque = new JTextField(String.valueOf(outdoor.getHasBarbeque()));
		hasBarbeque.setEditable(false);
		hasBarbeque.setColumns(10);
		hasBarbeque.setBackground(Color.WHITE);
		hasBarbeque.setBounds(221, 238, 139, 20);
		outdoorP.add(hasBarbeque);
		
		JLabel lblNewLabel_1_1_2_2_1_1_1_1_2 = new JLabel("Has Barbeque:");
		lblNewLabel_1_1_2_2_1_1_1_1_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_1_2_2_1_1_1_1_2.setBounds(221, 198, 139, 34);
		outdoorP.add(lblNewLabel_1_1_2_2_1_1_1_1_2);
		
		hasPatio = new JTextField(String.valueOf(outdoor.getHasPatio()));
		hasPatio.setEditable(false);
		hasPatio.setColumns(10);
		hasPatio.setBackground(Color.WHITE);
		hasPatio.setBounds(221, 125, 139, 20);
		outdoorP.add(hasPatio);
		
		JLabel hasHairDryer_1_1_1_2 = new JLabel("Has Patio");
		hasHairDryer_1_1_1_2.setFont(new Font("Arial", Font.BOLD, 15));
		hasHairDryer_1_1_1_2.setBounds(221, 90, 139, 34);
		outdoorP.add(hasHairDryer_1_1_1_2);
		
		JPanel bedroomsP = new JPanel();
		layeredPane.setLayer(bedroomsP, 2);
		bedroomsP.setLayout(null);
		bedroomsP.setBounds(0, 0, 392, 427);
		layeredPane.add(bedroomsP);
		
		JLabel lblNewLabel_1_3_1_1_2_1 = new JLabel("Bedrooms");
		lblNewLabel_1_3_1_1_2_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_3_1_1_2_1.setBounds(10, 11, 190, 34);
		bedroomsP.add(lblNewLabel_1_3_1_1_2_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 81, 372, 146);
		bedroomsP.add(scrollPane_1);
		
		tableBedrooms = new JTable();
		tableBedrooms.setEnabled(false);
		tableBedrooms.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Bed1", "Bed2", "No.Beds", "No. Sleepers"
			}
		));
		scrollPane_1.setViewportView(tableBedrooms);
		
		// set bedrooms model and fill table
		DefaultTableModel bedroomsModel = (DefaultTableModel) tableBedrooms.getModel();
		Object bedroomsRow [] = new Object[4];
		for(int i = 0; i < bedrooms.size(); i++) {
			bedroomsRow[0] = bedrooms.get(i).getBed1().getType().toString();
			Bed bed2 = bedrooms.get(i).getBed2();
			if(!(bed2 == null)) {
				bedroomsRow[1] = bed2.getType().toString();
			}
			else
				bedroomsRow[1] = "NONE";
			bedroomsRow[2] = bedrooms.get(i).getNumBeds();
			bedroomsRow[3] = bedrooms.get(i).getNumSleepers();
			bedroomsModel.addRow(bedroomsRow);
		}
		
		JPanel bathroomsP = new JPanel();
		layeredPane.setLayer(bathroomsP, 1);
		bathroomsP.setLayout(null);
		bathroomsP.setBounds(0, 0, 392, 427);
		layeredPane.add(bathroomsP);
		
		JLabel lblNewLabel_1_3_1_1_2_1_1 = new JLabel("Bathrooms:");
		lblNewLabel_1_3_1_1_2_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_3_1_1_2_1_1.setBounds(10, 11, 190, 34);
		bathroomsP.add(lblNewLabel_1_3_1_1_2_1_1);
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(10, 81, 372, 146);
		bathroomsP.add(scrollPane_1_1);
		
		tableBathrooms = new JTable();
		tableBathrooms.setEnabled(false);
		tableBathrooms.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Toilet", "Bath", "Shower", "Is Shared"
			}
		));
		scrollPane_1_1.setViewportView(tableBathrooms);
		
		// set bathrooms model and fill table
		DefaultTableModel bathroomsModel = (DefaultTableModel) tableBathrooms.getModel();
		Object bathroomsRow [] = new Object[4];
		for(int i = 0; i < bathrooms.size(); i++) {
			bathroomsRow[0] = bathrooms.get(i).getHasToilet();
			bathroomsRow[1] = bathrooms.get(i).getHasBath();
			bathroomsRow[2] = bathrooms.get(i).getHasShower();
			bathroomsRow[3] = bathrooms.get(i).getIsShared();
			bathroomsModel.addRow(bathroomsRow);
				}
		
		JPanel chargeBandsP = new JPanel();
		layeredPane.setLayer(chargeBandsP, 0);
		chargeBandsP.setLayout(null);
		chargeBandsP.setBounds(0, 0, 392, 427);
		layeredPane.add(chargeBandsP);
		
		JLabel lblNewLabel_1_3_1_1_2_1_2 = new JLabel("Charge Bands");
		lblNewLabel_1_3_1_1_2_1_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1_3_1_1_2_1_2.setBounds(10, 11, 190, 34);
		chargeBandsP.add(lblNewLabel_1_3_1_1_2_1_2);
		
		JScrollPane scrollPane_1_2 = new JScrollPane();
		scrollPane_1_2.setBounds(0, 81, 392, 146);
		chargeBandsP.add(scrollPane_1_2);
		
		tableChargeBands = new JTable();
		tableChargeBands.setEnabled(false);
		tableChargeBands.setModel(new DefaultTableModel(
			new Object[][] {
				
			},
			
			new String[] {
				"Start", "End", "Per Night", "Service Cost", "Cleaning Cost"
				
			} 
			
			
		));
		tableChargeBands.getColumnModel().getColumn(0).setPreferredWidth(70);
		tableChargeBands.getColumnModel().getColumn(1).setPreferredWidth(70);
		tableChargeBands.getColumnModel().getColumn(4).setPreferredWidth(83);
		scrollPane_1_2.setViewportView(tableChargeBands);
		// set chargeband model and fill table
		DefaultTableModel chargeBandsModel = (DefaultTableModel) tableChargeBands.getModel();
		Object chargeBandsRow [] = new Object[5];
				for(int i = 0; i < bands.size(); i++) {
					chargeBandsRow[0] = bands.get(i).getStart().toString();
					chargeBandsRow[1] = bands.get(i).getEnd().toString();
					chargeBandsRow[2] = bands.get(i).getPricePerNight();
					chargeBandsRow[3] = bands.get(i).getServiceCharge();
					chargeBandsRow[4] = bands.get(i).getCleaningCharge();
					chargeBandsModel.addRow(chargeBandsRow);
				}
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 102, 161, 317);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 151, 23);
		panel.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("View Property Info");
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.add(mnNewMenu);
		
		JMenuItem pub = new JMenuItem("Public Info");
		pub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToPanel(publicInfo,layeredPane);
			}
		});
		mnNewMenu.add(pub);
		
		JMenuItem sleepM = new JMenuItem("Sleeping");
		sleepM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToPanel(sleepingP,layeredPane);
			}
		});
		mnNewMenu.add(sleepM);
		
		JMenuItem bedroomM = new JMenuItem("Bedrooms");
		bedroomM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToPanel(bedroomsP,layeredPane);
			}
		});
		mnNewMenu.add(bedroomM);
		
		JMenuItem bathingM = new JMenuItem("Bathing");
		bathingM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToPanel(bathingP,layeredPane);
			}
		});
		mnNewMenu.add(bathingM);
		
		JMenuItem bathroomM = new JMenuItem("Bathrooms");
		bathroomM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToPanel(bathroomsP,layeredPane);
			}
		});
		mnNewMenu.add(bathroomM);
		
		JMenuItem kitchenM = new JMenuItem("Kitchen");
		kitchenM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToPanel(kitchenP,layeredPane);
			}
		});
		mnNewMenu.add(kitchenM);
		
		JMenuItem livingM = new JMenuItem("Living");
		livingM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToPanel(livingP,layeredPane);
			}
		});
		mnNewMenu.add(livingM);
		
		JMenuItem utilityM = new JMenuItem("Utility");
		utilityM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToPanel(utilityP,layeredPane);
			}
		});
		mnNewMenu.add(utilityM);
		
		JMenuItem outdoorM = new JMenuItem("Outdoor");
		outdoorM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToPanel(outdoorP,layeredPane);
			}
		});
		mnNewMenu.add(outdoorM);
		
		JMenuItem chargeBandM = new JMenuItem("ChargeBands ");
		chargeBandM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToPanel(chargeBandsP,layeredPane);
			}
		});
		mnNewMenu.add(chargeBandM);
		
		JLabel lblNewLabel_2 = new JLabel("Host Username:");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_2.setBounds(181, 102, 131, 24);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Super Host:");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_2_1.setBounds(181, 163, 131, 24);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		username = new JTextField(hostInfo[0]);
		username.setEditable(false);
		username.setColumns(10);
		username.setBackground(Color.WHITE);
		username.setBounds(181, 132, 170, 20);
		frame.getContentPane().add(username);
		
		boolean isSuperHost = Houses.toBool(Integer.parseInt(hostInfo[1]));
		superHost = new JTextField(String.valueOf(isSuperHost));
		superHost.setEditable(false);
		superHost.setColumns(10);
		superHost.setBackground(Color.WHITE);
		superHost.setBounds(181, 198, 170, 20);
		frame.getContentPane().add(superHost);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 11));
		btnNewButton.setBounds(10, 532, 93, 23);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(181, 260, 170, 145);
		frame.getContentPane().add(scrollPane_2);
		
		JList reviewList = new JList();
		scrollPane_2.setViewportView(reviewList);
		
		reviewsModel = new DefaultListModel();
		if(!reviews.isEmpty()) {
			for(String review: reviews) {
				reviewsModel.addElement(review);
			}
		}
		else {
			reviewsModel.addElement("No Reviews yet");
		}
		
		reviewList.setModel(reviewsModel);
		
		JLabel lblNewLabel_3 = new JLabel("Reviews:");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_3.setBounds(181, 229, 73, 20);
		frame.getContentPane().add(lblNewLabel_3);
	}
			
	private static void addPopup(Component component, final JPopupMenu popup) {
	}
}
