package Gui;

import businessLogic.*;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.border.LineBorder;
import java.awt.Color;

import javax.swing.*;

import classCode.*;
import database.Database;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.event.ActionEvent;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class ListProp {

	private JFrame listPropFrm;
	private final JLayeredPane layeredPane = new JLayeredPane();
	private JTextField houseNum;
	private JTextField street;
	private JTextField city;
	private JTextField postCode;
	private JTextField shortName;
	private JTextField genLoc;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private ArrayList<Bedroom> bedrooms = new ArrayList<>();
	private DefaultListModel listBedroomsModel;
	
	private ArrayList<Bathroom> bathrooms = new ArrayList<>();
	private DefaultListModel listBathroomsModel;
	private JTextField pricePerNight;
	private JTextField serviceCharge;
	private JTextField cleaningCharge;
	
	private ArrayList<LocalDate> dates = new ArrayList<>();
	private ArrayList<ChargeBand> chargeBands = new ArrayList<>();
	private DefaultListModel listChargeBandsModel;
	
	//property values
	private Address ad;
	private String sName;
	private String des;
	private String genL;
	private boolean bf;
	private Sleeping sleeping;
	private Bathing bathing;
	private Kitchen kitchen;
	private Living living;
	private Utility utility;
	private Outdoor outdoor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListProp window = new ListProp(null);
					window.listPropFrm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the application.
	 */
	public ListProp(User user) {
		initialize(user);
	}
	
	// method to refresh bedroom list
	public void refreshBedrooms() {
		listBedroomsModel.removeAllElements();
		for(int i =0; i < bedrooms.size(); i++) {
			String bed2Output = "";
			if(bedrooms.get(i).getBed2() != null)
				bed2Output = ",  Bed2 = "+ bedrooms.get(i).getBed2().getType();
			listBedroomsModel.addElement("Bedroom" + (i+1) + ":  Bed1 =   " + 
		bedrooms.get(i).getBed1().getType() + bed2Output
		+ ",  num Beds = " + bedrooms.get(i).getNumBeds() +
		",   sleepers =  " + bedrooms.get(i).getNumSleepers());
		}
	}
	
	//method to refresh bathroom list
	public void refreshBathrooms() {
		listBathroomsModel.removeAllElements();
		for(int i = 0; i < bathrooms.size(); i++) {
			listBathroomsModel.addElement("Bathroom" + (i+1) + ":   Toilet = " + bathrooms.get(i).getHasToilet() 
					+ ",   Bath = " + bathrooms.get(i).getHasBath() + ",   Shower = " + 
					bathrooms.get(i).getHasShower() + ",   Shared = " + bathrooms.get(i).getIsShared());
		}
	}
	
	//method to refresh charge band list
		public void refreshChargeBands() {
			listChargeBandsModel.removeAllElements();
			for(int i = 0; i < chargeBands.size(); i++) {
				listChargeBandsModel.addElement("Band" + (i+1) + ":   From: " + chargeBands.get(i).getStart() 
						+ ",   To : " + chargeBands.get(i).getEnd() + ",   Price Per Night = " + 
						chargeBands.get(i).getPricePerNight() + ",   Service Charge = " + 
						chargeBands.get(i).getServiceCharge() + ",   Cleaning Charge = " + chargeBands.get(i).getCleaningCharge());
			}
		}
	
	public void goToPanel(JPanel p) {
		layeredPane.removeAll();
		layeredPane.add(p);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	public boolean noneEmpty(String[] s) {
		boolean noneEmpty = true;
		for(int i = 0; i < s.length; i++) {
			if(s[i].isEmpty()) {
				noneEmpty = false;
			}
		}
		return noneEmpty;
	}
	
	//functions for dates
	
	public static boolean fullYear(ArrayList<LocalDate> dates){
		ArrayList<Integer> daysOfYear = new ArrayList<>();
		for(LocalDate date : dates) {
			daysOfYear.add(date.getDayOfYear());
		}
		int sum = 0;
		for(int dayOfYear: daysOfYear) {
			sum = sum + dayOfYear;
		}
		if(sum != 66795)
			return false;
		else 
			return true;
	}
	
	public static boolean validDate(int year, int month, int day) {
		boolean valid = true;
		try {
			LocalDate date = LocalDate.of(year, month, day);
		}catch(DateTimeException e) {
			valid = false;
		}
		return valid;
	}
	
	public JFrame getFrame() {
		return listPropFrm;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(User user) {
		listPropFrm = new JFrame();
		listPropFrm.setTitle("List Property");
		listPropFrm.setBounds(100, 100, 897, 604);
		listPropFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		listPropFrm.getContentPane().setLayout(null);
		layeredPane.setBounds(10, 11, 878, 550);
		listPropFrm.getContentPane().add(layeredPane);
		
		
		JPanel addressP = new JPanel();
		layeredPane.setLayer(addressP, 9);
		addressP.setBounds(0, 0, 878, 550);
		layeredPane.add(addressP);
		addressP.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Confidential address details");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(260, 73, 279, 30);
		addressP.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("House number: ");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(260, 157, 154, 20);
		addressP.add(lblNewLabel_1);
		
		houseNum = new JTextField();
		houseNum.setBounds(408, 157, 106, 20);
		addressP.add(houseNum);
		houseNum.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Street name:");
		lblNewLabel_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(260, 217, 154, 20);
		addressP.add(lblNewLabel_1_1);
		
		street = new JTextField();
		street.setColumns(10);
		street.setBounds(408, 217, 106, 20);
		addressP.add(street);
		
		JLabel lblNewLabel_1_2 = new JLabel("City or Town:");
		lblNewLabel_1_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(260, 277, 154, 20);
		addressP.add(lblNewLabel_1_2);
		
		city = new JTextField();
		city.setColumns(10);
		city.setBounds(408, 277, 106, 20);
		addressP.add(city);
		
		JLabel lblNewLabel_1_3 = new JLabel("Post Code:");
		lblNewLabel_1_3.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_3.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1_3.setBounds(260, 337, 154, 20);
		addressP.add(lblNewLabel_1_3);
		
		postCode = new JTextField();
		postCode.setColumns(10);
		postCode.setBounds(408, 337, 106, 20);
		addressP.add(postCode);
		
		JButton p12 = new JButton("Next");
		p12.setFont(new Font("Arial", Font.BOLD, 15));
		p12.setBounds(408, 424, 106, 23);
		addressP.add(p12);
		
		JLabel emptyError1 = new JLabel("Fill out all Fields");
		emptyError1.setForeground(Color.GRAY);
		emptyError1.setVerticalAlignment(SwingConstants.TOP);
		emptyError1.setHorizontalAlignment(SwingConstants.LEFT);
		emptyError1.setFont(new Font("Arial", Font.BOLD, 18));
		emptyError1.setBounds(548, 157, 154, 20);
		emptyError1.setVisible(false);
		addressP.add(emptyError1);
		
		JPanel publicInfoP = new JPanel();
		layeredPane.setLayer(publicInfoP, 8);
		publicInfoP.setBounds(0, 0, 878, 550);
		layeredPane.add(publicInfoP);
		publicInfoP.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Public property Information");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_3.setBounds(10, 74, 268, 24);
		publicInfoP.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Short name: ");
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_4.setBounds(10, 136, 122, 24);
		publicInfoP.add(lblNewLabel_4);
		
		shortName = new JTextField();
		shortName.setBounds(142, 136, 171, 24);
		publicInfoP.add(shortName);
		shortName.setColumns(10);
		
		JLabel lblNewLabel_4_1 = new JLabel("General Location: ");
		lblNewLabel_4_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_4_1.setBounds(371, 136, 189, 24);
		publicInfoP.add(lblNewLabel_4_1);
		
		genLoc = new JTextField();
		genLoc.setColumns(10);
		genLoc.setBounds(549, 136, 171, 24);
		publicInfoP.add(genLoc);
		
		JLabel lblNewLabel_4_2 = new JLabel("Description:");
		lblNewLabel_4_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_4_2.setBounds(10, 234, 122, 24);
		publicInfoP.add(lblNewLabel_4_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(142, 238, 171, 98);
		publicInfoP.add(scrollPane);
		
		JTextArea description = new JTextArea();
		scrollPane.setViewportView(description);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Offers Breakfast ");
		lblNewLabel_4_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_4_1_1.setBounds(371, 234, 189, 24);
		publicInfoP.add(lblNewLabel_4_1_1);
		
		JButton p23 = new JButton("Next");
		p23.setFont(new Font("Arial", Font.BOLD, 15));
		p23.setBounds(549, 408, 93, 23);
		publicInfoP.add(p23);
		
		JCheckBox breakfast = new JCheckBox("Yes");
		breakfast.setFont(new Font("Arial", Font.BOLD, 15));
		breakfast.setBounds(560, 238, 101, 23);
		publicInfoP.add(breakfast);
		
		JLabel emptyError2 = new JLabel("Fill out all Fields");
		emptyError2.setVerticalAlignment(SwingConstants.TOP);
		emptyError2.setHorizontalAlignment(SwingConstants.LEFT);
		emptyError2.setForeground(Color.GRAY);
		emptyError2.setFont(new Font("Arial", Font.BOLD, 18));
		emptyError2.setBounds(549, 92, 154, 20);
		emptyError2.setVisible(false);
		publicInfoP.add(emptyError2);
		
		JPanel sleepingP = new JPanel();
		layeredPane.setLayer(sleepingP, 7);
		sleepingP.setBounds(0, 0, 878, 550);
		layeredPane.add(sleepingP);
		sleepingP.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Sleeping Facility");
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_5.setBounds(10, 63, 158, 24);
		sleepingP.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Has Bed linen:");
		lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_6.setBounds(10, 121, 145, 24);
		sleepingP.add(lblNewLabel_6);
		
		JCheckBox hasBedLinen = new JCheckBox("Yes");
		hasBedLinen.setFont(new Font("Arial", Font.BOLD, 15));
		hasBedLinen.setBounds(177, 121, 65, 23);
		sleepingP.add(hasBedLinen);
		
		JLabel label_towels = new JLabel("Has Towels:");
		label_towels.setFont(new Font("Arial", Font.BOLD, 20));
		label_towels.setBounds(515, 121, 145, 24);
		sleepingP.add(label_towels);
		
		JCheckBox hasTowels = new JCheckBox("Yes");
		hasTowels.setFont(new Font("Arial", Font.BOLD, 15));
		hasTowels.setBounds(682, 121, 65, 23);
		sleepingP.add(hasTowels);
		
		JPanel AddBedroom = new JPanel();
		AddBedroom.setBounds(10, 156, 858, 379);
		sleepingP.add(AddBedroom);
		AddBedroom.setLayout(null);
		
		JList bedroomsList = new JList();
		bedroomsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
			}
		});
		bedroomsList.setBounds(284, 53, 491, 294);
		AddBedroom.add(bedroomsList);
		
		// set defaultListModel here
		listBedroomsModel = new DefaultListModel();
		bedroomsList.setModel(listBedroomsModel);
		
		JLabel lblNewLabel_5_1 = new JLabel("Add Bedrooms");
		lblNewLabel_5_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_5_1.setBounds(10, 18, 208, 24);
		AddBedroom.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_7 = new JLabel("Rooms");
		lblNewLabel_7.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_7.setBounds(284, 11, 66, 31);
		AddBedroom.add(lblNewLabel_7);
		
		JLabel lblNewLabel_7_1 = new JLabel("Bed1:");
		lblNewLabel_7_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_7_1.setBounds(10, 100, 66, 31);
		AddBedroom.add(lblNewLabel_7_1);
		
		JComboBox bed1Type = new JComboBox();
		bed1Type.setModel(new DefaultComboBoxModel(new String[] {"SINGLE", "DOUBLE", "KINGSIZE", "BUNK"}));
		bed1Type.setBounds(86, 105, 107, 22);
		AddBedroom.add(bed1Type);
		
		JLabel lblNewLabel_7_1_1 = new JLabel("Bed2:");
		lblNewLabel_7_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_7_1_1.setBounds(10, 175, 66, 31);
		AddBedroom.add(lblNewLabel_7_1_1);
		
		JComboBox bed2Type = new JComboBox();
		bed2Type.setModel(new DefaultComboBoxModel(new String[] {"SINGLE", "DOUBLE", "KINGSIZE", "BUNK", "NONE"}));
		bed2Type.setBounds(86, 180, 107, 22);
		AddBedroom.add(bed2Type);
		
		JButton addRoom = new JButton("Add room");
		addRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Bed bed1 = new Bed(bed1Type.getSelectedItem().toString());
				Bed bed2 = null;
				if(!bed2Type.getSelectedItem().toString().equals("NONE"))
					bed2 = new Bed(bed2Type.getSelectedItem().toString());
				Bedroom room = new Bedroom(bed1,bed2);
				bedrooms.add(room);
				refreshBedrooms();
			}
		});
		addRoom.setFont(new Font("Arial", Font.BOLD, 15));
		addRoom.setBounds(10, 241, 107, 23);
		AddBedroom.add(addRoom);
		
		JButton removeRoom = new JButton("Remove room");
		removeRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bedroomsList.getSelectedIndex() >= 0)
					bedrooms.remove(bedroomsList.getSelectedIndex());
				refreshBedrooms();
			}
		});
		removeRoom.setFont(new Font("Arial", Font.BOLD, 15));
		removeRoom.setBounds(128, 242, 146, 23);
		AddBedroom.add(removeRoom);
		
		JButton p34 = new JButton("Next");
		p34.setBounds(765, 358, 93, 23);
		AddBedroom.add(p34);
		p34.setFont(new Font("Arial", Font.BOLD, 15));
		
		JLabel emptyBedrooms = new JLabel("Must have at least 1 bedroom");
		emptyBedrooms.setVerticalAlignment(SwingConstants.TOP);
		emptyBedrooms.setHorizontalAlignment(SwingConstants.LEFT);
		emptyBedrooms.setForeground(Color.GRAY);
		emptyBedrooms.setFont(new Font("Arial", Font.BOLD, 18));
		emptyBedrooms.setBounds(515, 63, 266, 20);
		emptyBedrooms.setVisible(false);
		sleepingP.add(emptyBedrooms);
		
		JPanel bathingP = new JPanel();
		layeredPane.setLayer(bathingP, 6);
		bathingP.setBounds(0, 0, 878, 550);
		layeredPane.add(bathingP);
		bathingP.setLayout(null);
		
		JLabel lblNewLabel_5_2 = new JLabel("Bathing Facility");
		lblNewLabel_5_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_5_2.setBounds(10, 51, 158, 24);
		bathingP.add(lblNewLabel_5_2);
		
		JLabel lblNewLabel_6_2 = new JLabel("Has hair dryer:");
		lblNewLabel_6_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_6_2.setBounds(10, 109, 145, 24);
		bathingP.add(lblNewLabel_6_2);
		
		JCheckBox hasHairDryer = new JCheckBox("Yes");
		hasHairDryer.setFont(new Font("Arial", Font.BOLD, 15));
		hasHairDryer.setBounds(177, 109, 65, 23);
		bathingP.add(hasHairDryer);
		
		JLabel lblNewLabel_6_1_1 = new JLabel("Has Shampoo:");
		lblNewLabel_6_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_6_1_1.setBounds(629, 109, 145, 24);
		bathingP.add(lblNewLabel_6_1_1);
		
		JCheckBox hasShampoo = new JCheckBox("Yes");
		hasShampoo.setFont(new Font("Arial", Font.BOLD, 15));
		hasShampoo.setBounds(796, 109, 65, 23);
		bathingP.add(hasShampoo);
		
		JLabel lblNewLabel_7_2 = new JLabel("Rooms");
		lblNewLabel_7_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_7_2.setBounds(294, 155, 66, 31);
		bathingP.add(lblNewLabel_7_2);
		
		JLabel lblNewLabel_7_1_2 = new JLabel("Bathroom");
		lblNewLabel_7_1_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_7_1_2.setBounds(20, 197, 83, 31);
		bathingP.add(lblNewLabel_7_1_2);
		
		
		JButton p45 = new JButton("Next");
		p45.setFont(new Font("Arial", Font.BOLD, 15));
		p45.setBounds(775, 513, 93, 23);
		bathingP.add(p45);
		
		JLabel lblNewLabel_7_1_2_1 = new JLabel("Has Toilet:");
		lblNewLabel_7_1_2_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_7_1_2_1.setBounds(20, 245, 83, 31);
		bathingP.add(lblNewLabel_7_1_2_1);
		
		JCheckBox hasToilet = new JCheckBox("Yes");
		hasToilet.setFont(new Font("Arial", Font.BOLD, 15));
		hasToilet.setBounds(129, 250, 65, 23);
		bathingP.add(hasToilet);
		
		JLabel lblNewLabel_7_1_2_1_1 = new JLabel("Has Bath:");
		lblNewLabel_7_1_2_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_7_1_2_1_1.setBounds(20, 300, 83, 31);
		bathingP.add(lblNewLabel_7_1_2_1_1);
		
		JCheckBox hasBath = new JCheckBox("Yes");
		hasBath.setFont(new Font("Arial", Font.BOLD, 15));
		hasBath.setBounds(129, 305, 65, 23);
		bathingP.add(hasBath);
		
		JLabel lblNewLabel_7_1_2_1_2 = new JLabel("Has Shower:");
		lblNewLabel_7_1_2_1_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_7_1_2_1_2.setBounds(20, 355, 93, 31);
		bathingP.add(lblNewLabel_7_1_2_1_2);
		
		JCheckBox hasShower = new JCheckBox("Yes");
		hasShower.setFont(new Font("Arial", Font.BOLD, 15));
		hasShower.setBounds(129, 360, 65, 23);
		bathingP.add(hasShower);
		
		JLabel lblNewLabel_7_1_2_1_2_1 = new JLabel("Is Shared:");
		lblNewLabel_7_1_2_1_2_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_7_1_2_1_2_1.setBounds(20, 410, 83, 31);
		bathingP.add(lblNewLabel_7_1_2_1_2_1);
		
		JCheckBox isShared = new JCheckBox("Yes");
		isShared.setFont(new Font("Arial", Font.BOLD, 15));
		isShared.setBounds(129, 415, 65, 23);
		bathingP.add(isShared);
		
		JList bathroomsList = new JList();
		bathroomsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
			}
		});
		bathroomsList.setBounds(294, 197, 491, 294);
		bathingP.add(bathroomsList);
		
		// set defaultListModel here
		listBathroomsModel = new DefaultListModel();
		bathroomsList.setModel(listBathroomsModel);
		
		JButton addBathroom = new JButton("Add room");
		addBathroom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Bathroom bathroom = new Bathroom(hasToilet.isSelected(),hasBath.isSelected(),
						hasShower.isSelected(),isShared.isSelected());
				bathrooms.add(bathroom);
				refreshBathrooms();
			}
		});
		addBathroom.setFont(new Font("Arial", Font.BOLD, 15));
		addBathroom.setBounds(20, 498, 107, 23);
		bathingP.add(addBathroom);
		
		JButton removeBathroom = new JButton("Remove room");
		removeBathroom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bathroomsList.getSelectedIndex() >= 0)
					bathrooms.remove(bathroomsList.getSelectedIndex());
				refreshBathrooms();
			}
		});
		removeBathroom.setFont(new Font("Arial", Font.BOLD, 15));
		removeBathroom.setBounds(138, 499, 146, 23);
		bathingP.add(removeBathroom);
		
		JLabel lblNewLabel_6_1_1_1 = new JLabel("Toilet Paper:");
		lblNewLabel_6_1_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_6_1_1_1.setBounds(319, 109, 145, 24);
		bathingP.add(lblNewLabel_6_1_1_1);
		
		JCheckBox hasToiletPaper = new JCheckBox("Yes");
		hasToiletPaper.setFont(new Font("Arial", Font.BOLD, 15));
		hasToiletPaper.setBounds(486, 109, 65, 23);
		bathingP.add(hasToiletPaper);
		
		JLabel emptyBathroom = new JLabel("Must have at least 1 bathroom");
		emptyBathroom.setVerticalAlignment(SwingConstants.TOP);
		emptyBathroom.setHorizontalAlignment(SwingConstants.LEFT);
		emptyBathroom.setForeground(Color.GRAY);
		emptyBathroom.setFont(new Font("Arial", Font.BOLD, 18));
		emptyBathroom.setBounds(486, 59, 274, 20);
		emptyBathroom.setVisible(false);
		bathingP.add(emptyBathroom);
		
		JPanel kitchenP = new JPanel();
		layeredPane.setLayer(kitchenP, 5);
		kitchenP.setBounds(0, 0, 878, 550);
		layeredPane.add(kitchenP);
		kitchenP.setLayout(null);
		
		JLabel lblNewLabel_5_2_1 = new JLabel("Kicthen Facility");
		lblNewLabel_5_2_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_5_2_1.setBounds(10, 51, 158, 24);
		kitchenP.add(lblNewLabel_5_2_1);
		
		JLabel lblNewLabel_8 = new JLabel("Has Fridge:");
		lblNewLabel_8.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_8.setBounds(62, 120, 158, 32);
		kitchenP.add(lblNewLabel_8);
		
		JCheckBox hasFridge = new JCheckBox("Yes");
		hasFridge.setFont(new Font("Arial", Font.BOLD, 15));
		hasFridge.setBounds(226, 127, 91, 23);
		kitchenP.add(hasFridge);
		
		JLabel lblNewLabel_8_2 = new JLabel("Has Microwave:");
		lblNewLabel_8_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_8_2.setBounds(62, 200, 158, 32);
		kitchenP.add(lblNewLabel_8_2);
		
		JCheckBox hasMicrowave = new JCheckBox("Yes");
		hasMicrowave.setFont(new Font("Arial", Font.BOLD, 15));
		hasMicrowave.setBounds(226, 207, 91, 23);
		kitchenP.add(hasMicrowave);
		
		JLabel lblNewLabel_8_3 = new JLabel("Has Oven:");
		lblNewLabel_8_3.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_8_3.setBounds(62, 280, 158, 32);
		kitchenP.add(lblNewLabel_8_3);
		
		JCheckBox hasOven = new JCheckBox("Yes");
		hasOven.setFont(new Font("Arial", Font.BOLD, 15));
		hasOven.setBounds(226, 287, 91, 23);
		kitchenP.add(hasOven);
		
		JLabel lblNewLabel_8_4 = new JLabel("Has Stove:");
		lblNewLabel_8_4.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_8_4.setBounds(62, 360, 158, 32);
		kitchenP.add(lblNewLabel_8_4);
		
		JCheckBox hasStove = new JCheckBox("Yes");
		hasStove.setFont(new Font("Arial", Font.BOLD, 15));
		hasStove.setBounds(226, 367, 91, 23);
		kitchenP.add(hasStove);
		
		JLabel lblNewLabel_8_4_1 = new JLabel("Basic Provisions:");
		lblNewLabel_8_4_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_8_4_1.setBounds(524, 360, 168, 32);
		kitchenP.add(lblNewLabel_8_4_1);
		
		JCheckBox hasBasicProv = new JCheckBox("Yes");
		hasBasicProv.setFont(new Font("Arial", Font.BOLD, 15));
		hasBasicProv.setBounds(688, 367, 91, 23);
		kitchenP.add(hasBasicProv);
		
		JCheckBox hasTableware = new JCheckBox("Yes");
		hasTableware.setFont(new Font("Arial", Font.BOLD, 15));
		hasTableware.setBounds(688, 287, 91, 23);
		kitchenP.add(hasTableware);
		
		JLabel lblNewLabel_8_3_1 = new JLabel("Has Tableware:");
		lblNewLabel_8_3_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_8_3_1.setBounds(524, 280, 158, 32);
		kitchenP.add(lblNewLabel_8_3_1);
		
		JLabel lblNewLabel_8_2_1 = new JLabel("Has Cookware:");
		lblNewLabel_8_2_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_8_2_1.setBounds(524, 200, 158, 32);
		kitchenP.add(lblNewLabel_8_2_1);
		
		JCheckBox hasCookware = new JCheckBox("Yes");
		hasCookware.setFont(new Font("Arial", Font.BOLD, 15));
		hasCookware.setBounds(688, 207, 91, 23);
		kitchenP.add(hasCookware);
		
		JCheckBox hasDishWasher = new JCheckBox("Yes");
		hasDishWasher.setFont(new Font("Arial", Font.BOLD, 15));
		hasDishWasher.setBounds(688, 127, 91, 23);
		kitchenP.add(hasDishWasher);
		
		JLabel lblNewLabel_8_1 = new JLabel("Has Dishwasher:");
		lblNewLabel_8_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_8_1.setBounds(524, 120, 168, 32);
		kitchenP.add(lblNewLabel_8_1);
		
		JButton p56 = new JButton("Next");
		p56.setFont(new Font("Arial", Font.BOLD, 15));
		p56.setBounds(686, 505, 93, 23);
		kitchenP.add(p56);
		
		JPanel livingP = new JPanel();
		layeredPane.setLayer(livingP, 4);
		livingP.setBounds(0, 0, 878, 550);
		layeredPane.add(livingP);
		livingP.setLayout(null);
		
		JLabel lblNewLabel_8_5 = new JLabel("Has Wifi:");
		lblNewLabel_8_5.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_8_5.setBounds(60, 155, 158, 32);
		livingP.add(lblNewLabel_8_5);
		
		JLabel lblNewLabel_8_2_2 = new JLabel("Has a TV:");
		lblNewLabel_8_2_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_8_2_2.setBounds(60, 235, 158, 32);
		livingP.add(lblNewLabel_8_2_2);
		
		JLabel lblNewLabel_8_3_2 = new JLabel("Has Sattelite:");
		lblNewLabel_8_3_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_8_3_2.setBounds(60, 315, 158, 32);
		livingP.add(lblNewLabel_8_3_2);
		
		JCheckBox hasSat = new JCheckBox("Yes");
		hasSat.setFont(new Font("Arial", Font.BOLD, 15));
		hasSat.setBounds(224, 322, 91, 23);
		livingP.add(hasSat);
		
		JCheckBox hasTv = new JCheckBox("Yes");
		hasTv.setFont(new Font("Arial", Font.BOLD, 15));
		hasTv.setBounds(224, 242, 91, 23);
		livingP.add(hasTv);
		
		JCheckBox hasWifi = new JCheckBox("Yes");
		hasWifi.setFont(new Font("Arial", Font.BOLD, 15));
		hasWifi.setBounds(224, 162, 91, 23);
		livingP.add(hasWifi);
		
		JLabel lblNewLabel_8_1_1 = new JLabel("Has Streaming:");
		lblNewLabel_8_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_8_1_1.setBounds(522, 155, 158, 32);
		livingP.add(lblNewLabel_8_1_1);
		
		JCheckBox hasStreaming = new JCheckBox("Yes");
		hasStreaming.setFont(new Font("Arial", Font.BOLD, 15));
		hasStreaming.setBounds(686, 162, 91, 23);
		livingP.add(hasStreaming);
		
		JCheckBox hasDvdPlayer = new JCheckBox("Yes");
		hasDvdPlayer.setFont(new Font("Arial", Font.BOLD, 15));
		hasDvdPlayer.setBounds(686, 242, 91, 23);
		livingP.add(hasDvdPlayer);
		
		JLabel lblNewLabel_8_2_1_1 = new JLabel("Has Dvd player:");
		lblNewLabel_8_2_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_8_2_1_1.setBounds(522, 235, 158, 32);
		livingP.add(lblNewLabel_8_2_1_1);
		
		JLabel lblNewLabel_8_3_1_1 = new JLabel("Board games:");
		lblNewLabel_8_3_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_8_3_1_1.setBounds(522, 315, 168, 32);
		livingP.add(lblNewLabel_8_3_1_1);
		
		JCheckBox hasBoardGames = new JCheckBox("Yes");
		hasBoardGames.setFont(new Font("Arial", Font.BOLD, 15));
		hasBoardGames.setBounds(686, 322, 91, 23);
		livingP.add(hasBoardGames);
		
		JLabel lblNewLabel_5_2_1_1 = new JLabel("Living Facility");
		lblNewLabel_5_2_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_5_2_1_1.setBounds(10, 53, 158, 24);
		livingP.add(lblNewLabel_5_2_1_1);
		
		JButton p67 = new JButton("Next");
		p67.setFont(new Font("Arial", Font.BOLD, 15));
		p67.setBounds(684, 504, 93, 23);
		livingP.add(p67);
		
		JPanel utilityP = new JPanel();
		layeredPane.setLayer(utilityP, 3);
		utilityP.setBounds(0, 0, 878, 550);
		layeredPane.add(utilityP);
		utilityP.setLayout(null);
		
		JLabel lblNewLabel_5_2_1_1_1 = new JLabel("Utility Facility");
		lblNewLabel_5_2_1_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_5_2_1_1_1.setBounds(10, 54, 158, 24);
		utilityP.add(lblNewLabel_5_2_1_1_1);
		
		JLabel lblNewLabel_8_5_1 = new JLabel("Has Heating:");
		lblNewLabel_8_5_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_8_5_1.setBounds(60, 156, 158, 32);
		utilityP.add(lblNewLabel_8_5_1);
		
		JCheckBox hasHeating = new JCheckBox("Yes");
		hasHeating.setFont(new Font("Arial", Font.BOLD, 15));
		hasHeating.setBounds(234, 163, 91, 23);
		utilityP.add(hasHeating);
		
		JCheckBox hasWashingMachine = new JCheckBox("Yes");
		hasWashingMachine.setFont(new Font("Arial", Font.BOLD, 15));
		hasWashingMachine.setBounds(234, 243, 91, 23);
		utilityP.add(hasWashingMachine);
		
		JLabel lblNewLabel_8_2_2_1 = new JLabel("Washing machine:");
		lblNewLabel_8_2_2_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_8_2_2_1.setBounds(60, 236, 176, 32);
		utilityP.add(lblNewLabel_8_2_2_1);
		
		JLabel lblNewLabel_8_3_2_1 = new JLabel("Drying Machine:");
		lblNewLabel_8_3_2_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_8_3_2_1.setBounds(60, 316, 158, 32);
		utilityP.add(lblNewLabel_8_3_2_1);
		
		JCheckBox hasDryingMachine = new JCheckBox("Yes");
		hasDryingMachine.setFont(new Font("Arial", Font.BOLD, 15));
		hasDryingMachine.setBounds(234, 323, 91, 23);
		utilityP.add(hasDryingMachine);
		
		JLabel lblNewLabel_8_3_1_1_1 = new JLabel("First Aid Kit:");
		lblNewLabel_8_3_1_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_8_3_1_1_1.setBounds(522, 316, 168, 32);
		utilityP.add(lblNewLabel_8_3_1_1_1);
		
		JLabel lblNewLabel_8_2_1_1_1 = new JLabel("Smoke Alarm:");
		lblNewLabel_8_2_1_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_8_2_1_1_1.setBounds(522, 236, 158, 32);
		utilityP.add(lblNewLabel_8_2_1_1_1);
		
		JCheckBox hasSmokeAlarm = new JCheckBox("Yes");
		hasSmokeAlarm.setFont(new Font("Arial", Font.BOLD, 15));
		hasSmokeAlarm.setBounds(699, 243, 91, 23);
		utilityP.add(hasSmokeAlarm);
		
		JCheckBox hasFireExtinguisher = new JCheckBox("Yes");
		hasFireExtinguisher.setFont(new Font("Arial", Font.BOLD, 15));
		hasFireExtinguisher.setBounds(699, 163, 91, 23);
		utilityP.add(hasFireExtinguisher);
		
		JLabel lblNewLabel_8_1_1_1 = new JLabel("Fire Extinguisher:");
		lblNewLabel_8_1_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_8_1_1_1.setBounds(522, 156, 176, 32);
		utilityP.add(lblNewLabel_8_1_1_1);
		
		JCheckBox hasFirstAidKit = new JCheckBox("Yes");
		hasFirstAidKit.setFont(new Font("Arial", Font.BOLD, 15));
		hasFirstAidKit.setBounds(699, 323, 91, 23);
		utilityP.add(hasFirstAidKit);
		
		JButton p78 = new JButton("Next");
		p78.setFont(new Font("Arial", Font.BOLD, 15));
		p78.setBounds(684, 505, 93, 23);
		utilityP.add(p78);
		
		JPanel outdoorP = new JPanel();
		layeredPane.setLayer(outdoorP, 2);
		outdoorP.setBounds(0, 0, 878, 550);
		layeredPane.add(outdoorP);
		outdoorP.setLayout(null);
		
		JLabel lblNewLabel_5_2_1_1_2 = new JLabel("Outdoor Facility");
		lblNewLabel_5_2_1_1_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_5_2_1_1_2.setBounds(10, 56, 158, 24);
		outdoorP.add(lblNewLabel_5_2_1_1_2);
		
		JLabel lblNewLabel_8_5_2 = new JLabel("Free parking:");
		lblNewLabel_8_5_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_8_5_2.setBounds(60, 158, 158, 32);
		outdoorP.add(lblNewLabel_8_5_2);
		
		JCheckBox hasFreeParking = new JCheckBox("Yes");
		hasFreeParking.setFont(new Font("Arial", Font.BOLD, 15));
		hasFreeParking.setBounds(224, 165, 91, 23);
		outdoorP.add(hasFreeParking);
		
		JCheckBox hasRoadParking = new JCheckBox("Yes");
		hasRoadParking.setFont(new Font("Arial", Font.BOLD, 15));
		hasRoadParking.setBounds(224, 245, 91, 23);
		outdoorP.add(hasRoadParking);
		
		JLabel lblNewLabel_8_2_2_2 = new JLabel("Road Parking:");
		lblNewLabel_8_2_2_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_8_2_2_2.setBounds(60, 238, 158, 32);
		outdoorP.add(lblNewLabel_8_2_2_2);
		
		JLabel lblNewLabel_8_3_2_2 = new JLabel("Paid Parking:");
		lblNewLabel_8_3_2_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_8_3_2_2.setBounds(60, 318, 158, 32);
		outdoorP.add(lblNewLabel_8_3_2_2);
		
		JCheckBox hasPaidParking = new JCheckBox("Yes");
		hasPaidParking.setFont(new Font("Arial", Font.BOLD, 15));
		hasPaidParking.setBounds(224, 325, 91, 23);
		outdoorP.add(hasPaidParking);
		
		JLabel lblNewLabel_8_2_1_1_2 = new JLabel("Has a Barbeque:");
		lblNewLabel_8_2_1_1_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_8_2_1_1_2.setBounds(522, 238, 158, 32);
		outdoorP.add(lblNewLabel_8_2_1_1_2);
		
		JCheckBox hasBarbeque = new JCheckBox("Yes");
		hasBarbeque.setFont(new Font("Arial", Font.BOLD, 15));
		hasBarbeque.setBounds(686, 245, 91, 23);
		outdoorP.add(hasBarbeque);
		
		JCheckBox hasPatio = new JCheckBox("Yes");
		hasPatio.setFont(new Font("Arial", Font.BOLD, 15));
		hasPatio.setBounds(686, 165, 91, 23);
		outdoorP.add(hasPatio);
		
		JLabel lblNewLabel_8_1_1_2 = new JLabel("Has a Patio:");
		lblNewLabel_8_1_1_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_8_1_1_2.setBounds(522, 158, 158, 32);
		outdoorP.add(lblNewLabel_8_1_1_2);
		
		JButton p89 = new JButton("Next");
		p89.setFont(new Font("Arial", Font.BOLD, 15));
		p89.setBounds(684, 507, 93, 23);
		outdoorP.add(p89);
		
		JLabel lblListAProperty = new JLabel("List A Property");
		lblListAProperty.setFont(new Font("Arial", Font.BOLD, 25));
		layeredPane.setLayer(lblListAProperty, 10);
		lblListAProperty.setHorizontalAlignment(SwingConstants.CENTER);
		lblListAProperty.setBounds(0, 0, 868, 37);
		layeredPane.add(lblListAProperty);
		
		JPanel chargeBandP = new JPanel();
		layeredPane.setLayer(chargeBandP, 1);
		chargeBandP.setBounds(0, 0, 878, 550);
		layeredPane.add(chargeBandP);
		chargeBandP.setLayout(null);
		
		JLabel lblNewLabel_5_2_1_1_2_1 = new JLabel("Charge Band");
		lblNewLabel_5_2_1_1_2_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_5_2_1_1_2_1.setBounds(10, 55, 158, 24);
		chargeBandP.add(lblNewLabel_5_2_1_1_2_1);
		
		JList chargeBandList = new JList();
		chargeBandList.setFont(new Font("Arial", Font.PLAIN, 10));
		chargeBandList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
			}
		});
		chargeBandList.setBounds(294, 200, 549, 294);
		chargeBandP.add(chargeBandList);
		// set defaultListModel here
		listChargeBandsModel = new DefaultListModel();
		chargeBandList.setModel(listChargeBandsModel);
		
		JLabel lblNewLabel_7_2_1 = new JLabel("Charge Bands for 2022");
		lblNewLabel_7_2_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_7_2_1.setBounds(294, 158, 174, 31);
		chargeBandP.add(lblNewLabel_7_2_1);
		
		JButton p910 = new JButton("Next");
		p910.setFont(new Font("Arial", Font.BOLD, 15));
		p910.setBounds(775, 516, 93, 23);
		chargeBandP.add(p910);
		
		JLabel lblNewLabel_5_2_1_1_2_1_1 = new JLabel("Start Date:");
		lblNewLabel_5_2_1_1_2_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_5_2_1_1_2_1_1.setBounds(10, 200, 99, 24);
		chargeBandP.add(lblNewLabel_5_2_1_1_2_1_1);
		
		JLabel lblNewLabel_5_2_1_1_2_1_1_1 = new JLabel("End Date");
		lblNewLabel_5_2_1_1_2_1_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_5_2_1_1_2_1_1_1.setBounds(10, 290, 99, 24);
		chargeBandP.add(lblNewLabel_5_2_1_1_2_1_1_1);
		
		JLabel lblNewLabel_5_2_1_1_2_1_1_1_1 = new JLabel("Price Per Night:");
		lblNewLabel_5_2_1_1_2_1_1_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_5_2_1_1_2_1_1_1_1.setBounds(10, 363, 114, 24);
		chargeBandP.add(lblNewLabel_5_2_1_1_2_1_1_1_1);
		
		JLabel lblNewLabel_5_2_1_1_2_1_1_1_2 = new JLabel("Service charge:");
		lblNewLabel_5_2_1_1_2_1_1_1_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_5_2_1_1_2_1_1_1_2.setBounds(10, 419, 114, 24);
		chargeBandP.add(lblNewLabel_5_2_1_1_2_1_1_1_2);
		
		
		JComboBox startDay = new JComboBox();
		startDay.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		startDay.setBounds(119, 202, 62, 22);
		chargeBandP.add(startDay);
		
		JComboBox startMonth = new JComboBox();
		startMonth.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		startMonth.setBounds(205, 202, 65, 22);
		chargeBandP.add(startMonth);
		
		JLabel lblNewLabel_5_2_1_1_2_1_1_2 = new JLabel("Day");
		lblNewLabel_5_2_1_1_2_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_2_1_1_2_1_1_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_5_2_1_1_2_1_1_2.setBounds(116, 167, 65, 24);
		chargeBandP.add(lblNewLabel_5_2_1_1_2_1_1_2);
		
		JLabel lblNewLabel_5_2_1_1_2_1_1_2_1 = new JLabel("Month");
		lblNewLabel_5_2_1_1_2_1_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_2_1_1_2_1_1_2_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_5_2_1_1_2_1_1_2_1.setBounds(205, 167, 65, 24);
		chargeBandP.add(lblNewLabel_5_2_1_1_2_1_1_2_1);
		
		JComboBox endDay = new JComboBox();
		endDay.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		endDay.setBounds(119, 292, 62, 22);
		chargeBandP.add(endDay);
		
		JLabel lblNewLabel_5_2_1_1_2_1_1_2_2 = new JLabel("Day");
		lblNewLabel_5_2_1_1_2_1_1_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_2_1_1_2_1_1_2_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_5_2_1_1_2_1_1_2_2.setBounds(116, 257, 65, 24);
		chargeBandP.add(lblNewLabel_5_2_1_1_2_1_1_2_2);
		
		JLabel lblNewLabel_5_2_1_1_2_1_1_2_1_1 = new JLabel("Month");
		lblNewLabel_5_2_1_1_2_1_1_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_2_1_1_2_1_1_2_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_5_2_1_1_2_1_1_2_1_1.setBounds(205, 257, 65, 24);
		chargeBandP.add(lblNewLabel_5_2_1_1_2_1_1_2_1_1);
		
		JComboBox endMonth = new JComboBox();
		endMonth.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		endMonth.setBounds(205, 292, 65, 22);
		chargeBandP.add(endMonth);
		
		JLabel lblNewLabel_5_2_1_1_2_1_1_1_2_1 = new JLabel("Cleaning charge:");
		lblNewLabel_5_2_1_1_2_1_1_1_2_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_5_2_1_1_2_1_1_1_2_1.setBounds(10, 476, 123, 24);
		chargeBandP.add(lblNewLabel_5_2_1_1_2_1_1_1_2_1);
		
		pricePerNight = new JTextField();
		pricePerNight.setText("\u00A3");
		pricePerNight.setBounds(134, 363, 106, 20);
		chargeBandP.add(pricePerNight);
		pricePerNight.setColumns(10);
		
		serviceCharge = new JTextField();
		serviceCharge.setText("\u00A3");
		serviceCharge.setColumns(10);
		serviceCharge.setBounds(134, 419, 106, 20);
		chargeBandP.add(serviceCharge);
		
		cleaningCharge = new JTextField();
		cleaningCharge.setText("\u00A3");
		cleaningCharge.setColumns(10);
		cleaningCharge.setBounds(136, 476, 106, 20);
		chargeBandP.add(cleaningCharge);
		
		JLabel startError = new JLabel("Start date should be before end date");
		startError.setForeground(Color.GRAY);
		startError.setFont(new Font("Arial", Font.BOLD, 15));
		startError.setBounds(205, 46, 289, 24);
		startError.setVisible(false);
		chargeBandP.add(startError);
		
		
		JLabel overlappError = new JLabel("Charge band dates overlapp");
		overlappError.setForeground(Color.GRAY);
		overlappError.setFont(new Font("Arial", Font.BOLD, 15));
		overlappError.setBounds(205, 81, 289, 24);
		overlappError.setVisible(false);
		chargeBandP.add(overlappError);
		
		JLabel fullYearError = new JLabel("charge bands must cover all dates in  2022");
		fullYearError.setForeground(Color.GRAY);
		fullYearError.setFont(new Font("Arial", Font.BOLD, 15));
		fullYearError.setBounds(205, 116, 332, 31);
		fullYearError.setVisible(false);
		chargeBandP.add(fullYearError);
		
		JLabel invalidError = new JLabel("Invalid Start/End date");
		invalidError.setForeground(Color.GRAY);
		invalidError.setFont(new Font("Arial", Font.BOLD, 15));
		invalidError.setBounds(10, 128, 158, 24);
		invalidError.setVisible(false);
		chargeBandP.add(invalidError);
		
		JLabel emptyCError = new JLabel("Fill out all fields");
		emptyCError.setForeground(Color.GRAY);
		emptyCError.setFont(new Font("Arial", Font.BOLD, 15));
		emptyCError.setBounds(10, 328, 148, 24);
		emptyCError.setVisible(false);
		chargeBandP.add(emptyCError);
		
		
		JButton addChargeBand = new JButton("Add charge band");
		addChargeBand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//get start date and month value
				int startDate = Integer.parseInt(startDay.getSelectedItem().toString());
				int sMonth = Integer.parseInt(startMonth.getSelectedItem().toString());
				// get end date and month value
				int endDate = Integer.parseInt(endDay.getSelectedItem().toString());
				int eMonth = Integer.parseInt(endMonth.getSelectedItem().toString());
				
				String ppnS = User.removeSemiColon(pricePerNight.getText().replace("£", ""));
				String scS = User.removeSemiColon(serviceCharge.getText().replace("£", ""));
				String ccS = User.removeSemiColon(cleaningCharge.getText().replace("£", ""));
				
				ChargeBand chargeb = null;
				if(!ppnS.isBlank() && !scS.isBlank() && !ccS.isBlank()) {
					double ppn = Double.parseDouble(pricePerNight.getText().replace("£", ""));
					double sc = Double.parseDouble(serviceCharge.getText().replace("£", ""));
					double cc = Double.parseDouble(cleaningCharge.getText().replace("£", ""));
					
					if(validDate(2022,sMonth,startDate) && validDate(2022,eMonth,endDate)) {
						LocalDate start = LocalDate.of(2022, sMonth, startDate);
						LocalDate end = LocalDate.of(2022, eMonth, endDate);
						if(end.isAfter(start)) {
							if(!dates.isEmpty()) {//if dates not empty
								if(dates.get(dates.size()-1).isBefore(start)){//check if last date is before current start date
									chargeb = new ChargeBand(start,end,ppn,sc,cc);
									dates.addAll(ChargeBand.getDatesBetween(start,end));
									overlappError.setVisible(false);
									startError.setVisible(false);
									fullYearError.setVisible(false);
									invalidError.setVisible(false);
									emptyCError.setVisible(false);
								}
								else {
									overlappError.setVisible(true);
									startError.setVisible(false);
									fullYearError.setVisible(false);
									invalidError.setVisible(false);
									emptyCError.setVisible(false);
								}
							}
							else {// this means its the first charge band added
								chargeb = new ChargeBand(start,end,ppn,sc,cc);
								dates.addAll(ChargeBand.getDatesBetween(start,end));
								overlappError.setVisible(false);
								startError.setVisible(false);
								fullYearError.setVisible(false);
								invalidError.setVisible(false);
								emptyCError.setVisible(false);
								
							}
						}
						else {
							startError.setVisible(true);
							overlappError.setVisible(false);
							fullYearError.setVisible(false);
							invalidError.setVisible(false);
							emptyCError.setVisible(false);
						}
					}
					else {
						invalidError.setVisible(true);
						startError.setVisible(false);
						overlappError.setVisible(false);
						fullYearError.setVisible(false);
						emptyCError.setVisible(false);
					}
				}
				else {
					emptyCError.setVisible(true);
					invalidError.setVisible(false);
					startError.setVisible(false);
					overlappError.setVisible(false);
					fullYearError.setVisible(false);
				}
				
				if(chargeb != null)
					chargeBands.add(chargeb);
				refreshChargeBands();
				
				
			}
		});
		addChargeBand.setFont(new Font("Arial", Font.BOLD, 15));
		addChargeBand.setBounds(0, 527, 158, 23);
		chargeBandP.add(addChargeBand);
		
		JButton removeChargeBands = new JButton("Remove All ");
		removeChargeBands.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dates.clear();
				chargeBands.clear();
				refreshChargeBands();
			}
		});
		removeChargeBands.setFont(new Font("Arial", Font.BOLD, 15));
		removeChargeBands.setBounds(168, 528, 158, 23);
		chargeBandP.add(removeChargeBands);
		
		JPanel addPropertyP = new JPanel();
		layeredPane.setLayer(addPropertyP, 0);
		addPropertyP.setBounds(0, 0, 878, 550);
		layeredPane.add(addPropertyP);
		addPropertyP.setLayout(null);
		
		JLabel lblNewLabel_5_2_1_1_2_1_2 = new JLabel("List Your Property");
		lblNewLabel_5_2_1_1_2_1_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_5_2_1_1_2_1_2.setBounds(10, 56, 194, 24);
		addPropertyP.add(lblNewLabel_5_2_1_1_2_1_2);
		
		JLabel lblNewLabel_5_2_1_1_2_1_2_1 = new JLabel("Check all details are correct");
		lblNewLabel_5_2_1_1_2_1_2_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_5_2_1_1_2_1_2_1.setBounds(10, 88, 279, 24);
		addPropertyP.add(lblNewLabel_5_2_1_1_2_1_2_1);
		
		JLabel lblNewLabel_5_2_1_1_2_1_2_2 = new JLabel("Address details:     go to page 1");
		lblNewLabel_5_2_1_1_2_1_2_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_5_2_1_1_2_1_2_2.setBounds(10, 169, 253, 24);
		addPropertyP.add(lblNewLabel_5_2_1_1_2_1_2_2);
		
		JLabel lblNewLabel_5_2_1_1_2_1_2_2_1 = new JLabel("Public information: go to page 2");
		lblNewLabel_5_2_1_1_2_1_2_2_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_5_2_1_1_2_1_2_2_1.setBounds(10, 209, 253, 24);
		addPropertyP.add(lblNewLabel_5_2_1_1_2_1_2_2_1);
		
		JLabel lblNewLabel_5_2_1_1_2_1_2_2_2 = new JLabel("Sleeping Facility:    go to page 3");
		lblNewLabel_5_2_1_1_2_1_2_2_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_5_2_1_1_2_1_2_2_2.setBounds(10, 249, 253, 24);
		addPropertyP.add(lblNewLabel_5_2_1_1_2_1_2_2_2);
		
		JLabel lblNewLabel_5_2_1_1_2_1_2_2_3 = new JLabel("Bathing Facility:      go to page 4");
		lblNewLabel_5_2_1_1_2_1_2_2_3.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_5_2_1_1_2_1_2_2_3.setBounds(10, 289, 253, 24);
		addPropertyP.add(lblNewLabel_5_2_1_1_2_1_2_2_3);
		
		JLabel lblNewLabel_5_2_1_1_2_1_2_2_4 = new JLabel("Kicthen  Facility:     go to page 5");
		lblNewLabel_5_2_1_1_2_1_2_2_4.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_5_2_1_1_2_1_2_2_4.setBounds(10, 329, 253, 24);
		addPropertyP.add(lblNewLabel_5_2_1_1_2_1_2_2_4);
		
		JLabel lblNewLabel_5_2_1_1_2_1_2_2_5 = new JLabel("Living Facility:        go to page 6");
		lblNewLabel_5_2_1_1_2_1_2_2_5.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_5_2_1_1_2_1_2_2_5.setBounds(10, 369, 253, 24);
		addPropertyP.add(lblNewLabel_5_2_1_1_2_1_2_2_5);
		
		JLabel lblNewLabel_5_2_1_1_2_1_2_2_6 = new JLabel("Utility Facility:         go to page 7");
		lblNewLabel_5_2_1_1_2_1_2_2_6.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_5_2_1_1_2_1_2_2_6.setBounds(10, 409, 253, 24);
		addPropertyP.add(lblNewLabel_5_2_1_1_2_1_2_2_6);
		
		JLabel lblNewLabel_5_2_1_1_2_1_2_2_6_1 = new JLabel("Outdoor Facility:    go to page 8");
		lblNewLabel_5_2_1_1_2_1_2_2_6_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_5_2_1_1_2_1_2_2_6_1.setBounds(10, 449, 253, 24);
		addPropertyP.add(lblNewLabel_5_2_1_1_2_1_2_2_6_1);
		
		JLabel lblNewLabel_5_2_1_1_2_1_2_2_6_1_1 = new JLabel("Charge Bands:       go to page 9");
		lblNewLabel_5_2_1_1_2_1_2_2_6_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_5_2_1_1_2_1_2_2_6_1_1.setBounds(10, 489, 253, 24);
		addPropertyP.add(lblNewLabel_5_2_1_1_2_1_2_2_6_1_1);
		
		JButton listProperty = new JButton("List Property");
		listProperty.setFont(new Font("Arial", Font.BOLD, 30));
		listProperty.setBounds(381, 169, 219, 45);
		addPropertyP.add(listProperty);
		
		JButton btnGoToPage = new JButton("Go To Page:");
		btnGoToPage.setFont(new Font("Arial", Font.BOLD, 15));
		btnGoToPage.setBounds(678, 503, 125, 23);
		addPropertyP.add(btnGoToPage);
		
		JComboBox pageNum = new JComboBox();
		pageNum.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"}));
		pageNum.setBounds(817, 504, 51, 22);
		addPropertyP.add(pageNum);
		
		//next button action listeners
		p12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String houseN = User.removeSemiColon(houseNum.getText().trim().toLowerCase());
				String streetN = User.removeSemiColon(street.getText().trim().toLowerCase());
				String cityN = User.removeSemiColon(city.getText().trim().toLowerCase());
				String pc = User.removeSemiColon(postCode.getText().trim().toLowerCase());
				String [] inputs = {houseN,streetN,cityN,pc};
				if(noneEmpty(inputs)) {
					emptyError1.setVisible(false);
					ad = new Address(houseN,streetN,cityN,pc);
					goToPanel(publicInfoP);
				}
				else {
					emptyError1.setVisible(true);
				}
					
			}
		});
		
		p23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sName = User.removeSemiColon(shortName.getText().trim().toLowerCase());
				des = User.removeSemiColon(description.getText().trim().toLowerCase());
				genL = User.removeSemiColon(genLoc.getText().trim().toLowerCase());
				bf = breakfast.isSelected();
				String [] inputs = {sName,des,genL}; 
				if(noneEmpty(inputs)) {
					emptyError2.setVisible(false);
					goToPanel(sleepingP);
				}
				else {
					emptyError2.setVisible(true);
				}
			}
		});
		
		p34.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bedrooms.isEmpty()) {
					emptyBedrooms.setVisible(true);
				}
				else {
					emptyBedrooms.setVisible(false);
					boolean hasBL = hasBedLinen.isSelected();
					boolean hasT = hasTowels.isSelected();
					sleeping = new Sleeping(hasBL,hasT,bedrooms);
					goToPanel(bathingP);
				}
			}
		});
		
		p45.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bathrooms.isEmpty()) {
					emptyBathroom.setVisible(true);
				}
				else {
					emptyBathroom.setVisible(false);
					boolean hhd = hasHairDryer.isSelected();
					boolean tp = hasToiletPaper.isSelected();
					boolean hs = hasShampoo.isSelected();
					bathing = new Bathing(hhd,tp,hs,bathrooms);
					goToPanel(kitchenP);
				}
			}
		});
		
		p56.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean f = hasFridge.isSelected();
				boolean m = hasMicrowave.isSelected();
				boolean o = hasOven.isSelected();
				boolean s = hasStove.isSelected();
				boolean d = hasDishWasher.isSelected();
				boolean t = hasTableware.isSelected();
				boolean c = hasCookware.isSelected();
				boolean bp = hasBasicProv.isSelected();
				kitchen = new Kitchen(f,m,o,s,d,t,c,bp);
				goToPanel(livingP);
			}
		});
		
		p67.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean w = hasWifi.isSelected();
				boolean t = hasTv.isSelected();
				boolean sat = hasSat.isSelected();
				boolean str = hasStreaming.isSelected();
				boolean d = hasDvdPlayer.isSelected();
				boolean b = hasBoardGames.isSelected();
				living = new Living(w,t,sat,str,d,b);
				goToPanel(utilityP);
			}
		});
		
		p78.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean h = hasHeating.isSelected();
				boolean w = hasWashingMachine.isSelected();
				boolean d = hasDryingMachine.isSelected();
				boolean fe = hasFireExtinguisher.isSelected();
				boolean s = hasSmokeAlarm.isSelected();
				boolean fak = hasFirstAidKit.isSelected();
				utility = new Utility(h,w,d,fe,s,fak);
				goToPanel(outdoorP);
			}
		});
		
		p89.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean fp = hasFreeParking.isSelected();
				boolean rp = hasRoadParking.isSelected();
				boolean pcp = hasPaidParking.isSelected();
				boolean p = hasPatio.isSelected();
				boolean b = hasBarbeque.isSelected();
				outdoor = new Outdoor(fp,rp,pcp,p,b);
				goToPanel(chargeBandP);
			}
		});
		
		p910.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fullYear(dates)){// made charge bands for full year
					fullYearError.setVisible(false);
					goToPanel(addPropertyP); 
				}
				else
					fullYearError.setVisible(true);
			}
		});
		
		listProperty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// make property 
				
				ArrayList<Review> reviews = new ArrayList<>();
				Property property = new Property(ad,sName,des,genL,
						bf,sleeping.getTotalSleepers(),sleeping.getTotalBeds()
						,sleeping.getTotalBedrooms(),bathing.getTotalBathrooms(),0); // initially average rating is 0
				//add add property function here
				Database.connectDB();
				Houses.addProperty((Host)user,property, sleeping, bathing, kitchen, living, utility, outdoor, chargeBands);
				Database.disconnectDB();
				
				//back to home page
				mainPageHost hostPage =  new mainPageHost(user);
				hostPage.getFrame().setVisible(true);
				listPropFrm.setVisible(false);
			}
		});
		
		btnGoToPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String p = pageNum.getSelectedItem().toString();
				int page = Integer.parseInt(p);
				switch(page) {
				case 1: goToPanel(addressP);
				break;
				case 2: goToPanel(publicInfoP);
				break;
				case 3: goToPanel(sleepingP);
				break;
				case 4: goToPanel(bathingP);
				break;
				case 5: goToPanel(kitchenP);
				break;
				case 6: goToPanel(livingP);
				break;
				case 7: goToPanel(utilityP);
				break;
				case 8: goToPanel(outdoorP);
				break;
				case 9: goToPanel(chargeBandP); 
				break;
				case 10: goToPanel(addPropertyP); ;
				}
			}
		});
	}
	
	
}
