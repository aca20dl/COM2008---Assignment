package Gui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.border.LineBorder;
import java.awt.Color;

import javax.swing.*;

import classCode.*;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.event.ActionEvent;

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListProp window = new ListProp();
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
	public ListProp() {
		initialize();
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
	
	public void goToPanel(JPanel p) {
		layeredPane.removeAll();
		layeredPane.add(p);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		listPropFrm = new JFrame();
		listPropFrm.setTitle("List Property");
		listPropFrm.setBounds(100, 100, 1200, 800);
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
		
		JTextArea description = new JTextArea();
		description.setBounds(142, 238, 171, 98);
		publicInfoP.add(description);
		
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
		
		JLabel hasTowels = new JLabel("Has Towels:");
		hasTowels.setFont(new Font("Arial", Font.BOLD, 20));
		hasTowels.setBounds(515, 121, 145, 24);
		sleepingP.add(hasTowels);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Yes");
		chckbxNewCheckBox_1.setFont(new Font("Arial", Font.BOLD, 15));
		chckbxNewCheckBox_1.setBounds(682, 121, 65, 23);
		sleepingP.add(chckbxNewCheckBox_1);
		
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
		
		JCheckBox hasDishware = new JCheckBox("Yes");
		hasDishware.setFont(new Font("Arial", Font.BOLD, 15));
		hasDishware.setBounds(688, 127, 91, 23);
		kitchenP.add(hasDishware);
		
		JLabel lblNewLabel_8_1 = new JLabel("Has Dishware:");
		lblNewLabel_8_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_8_1.setBounds(524, 120, 158, 32);
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
		
		JButton btnGoToPage = new JButton("Go To Page:");
		btnGoToPage.setFont(new Font("Arial", Font.BOLD, 15));
		btnGoToPage.setBounds(668, 581, 125, 23);
		listPropFrm.getContentPane().add(btnGoToPage);
		
		JComboBox pageNum = new JComboBox();
		pageNum.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"}));
		pageNum.setBounds(807, 582, 51, 22);
		listPropFrm.getContentPane().add(pageNum);
		
		//next button action listeners
		p12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToPanel(publicInfoP);
			}
		});
		
		p23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToPanel(sleepingP);
			}
		});
		
		p34.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToPanel(bathingP);
			}
		});
		
		p45.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToPanel(kitchenP);
			}
		});
		
		p56.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToPanel(livingP);
			}
		});
		
		p67.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToPanel(utilityP);
			}
		});
		
		p78.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToPanel(outdoorP);
			}
		});
		
		// choose what page to go to
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
				case 9: goToPanel(addressP); // change when u make chargeband page
				break;
				}
			}
		});
	}
}
