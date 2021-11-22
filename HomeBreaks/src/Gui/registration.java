package Gui;

import java.awt.EventQueue;
import classCode.*;
import database.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;
import java.awt.Color;
import java.sql.*;
import java.util.*;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import java.awt.SystemColor;

public class registration {

	private JFrame frmRegistration;
	private JTextField tFirstname;
	private JTextField tSurname;
	private JTextField tEmail;
	private JTextField tMobile;
	private JTextField tUsername;
	private JPasswordField tPassword;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private String title;
	private JTextField tHouse;
	private JTextField tStreet;
	private JTextField tCity;
	private JTextField tPostCode;
	private final JPanel panel = new JPanel();
	private final JLayeredPane layeredPane = new JLayeredPane();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registration window = new registration();
					window.frmRegistration.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public registration() {
		initialize();
	}
	
	public JFrame getFrame() {
		return frmRegistration;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegistration = new JFrame();
		frmRegistration.getContentPane().setEnabled(false);
		frmRegistration.setResizable(false);
		frmRegistration.setTitle("Registration");
		frmRegistration.setBounds(100, 100, 1171, 793);
		frmRegistration.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegistration.getContentPane().setLayout(null);
		layeredPane.setBounds(510, 2, 869, 765);
		frmRegistration.getContentPane().add(layeredPane);
		
		JPanel panel_1 = new JPanel();
		layeredPane.setLayer(panel_1, 2);
		panel_1.setBounds(0, 0, 865, 765);
		layeredPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lEmail = new JLabel("Email");
		lEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lEmail.setBounds(140, 230, 305, 37);
		panel_1.add(lEmail);
		lEmail.setFont(new Font("Dialog", Font.BOLD, 25));
		
		tEmail = new JTextField();
		tEmail.setBounds(141, 270, 305, 37);
		panel_1.add(tEmail);
		tEmail.setColumns(10);
		
		JLabel lUsername = new JLabel("Username");
		lUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lUsername.setBounds(144, 310, 305, 37);
		panel_1.add(lUsername);
		lUsername.setForeground(Color.BLACK);
		lUsername.setFont(new Font("Dialog", Font.BOLD, 25));
		
		tUsername = new JTextField();
		tUsername.setBounds(141, 350, 305, 37);
		panel_1.add(tUsername);
		tUsername.setColumns(10);
		
		JLabel lPassword = new JLabel("Password:  ");
		lPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lPassword.setBounds(145, 390, 305, 37);
		panel_1.add(lPassword);
		lPassword.setFont(new Font("Dialog", Font.BOLD, 25));
		
		tPassword = new JPasswordField();
		tPassword.setBounds(141, 430, 305, 37);
		panel_1.add(tPassword);
		
		JLabel usedEmail = new JLabel("That email has already been used");
		usedEmail.setBounds(17, 7, 331, 29);
		panel_1.add(usedEmail);
		usedEmail.setEnabled(false);
		usedEmail.setFont(new Font("Arial", Font.BOLD, 20));
		usedEmail.setForeground(Color.RED);
		
		JLabel errorMsg = new JLabel("Fill out all fields to register");
		errorMsg.setBounds(18, 2, 331, 47);
		panel_1.add(errorMsg);
		errorMsg.setEnabled(false);
		errorMsg.setForeground(Color.RED);
		errorMsg.setFont(new Font("Arial", Font.BOLD, 20));
		
		JLabel formTitle = new JLabel("Register");
		formTitle.setBounds(140, 133, 310, 47);
		panel_1.add(formTitle);
		formTitle.setFont(new Font("Arial", Font.BOLD, 40));
		formTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		//initially hidden
		errorMsg.setVisible(false);
		usedEmail.setVisible(false);
		
		JButton next1Button = new JButton("Next");
		
		next1Button.setForeground(Color.WHITE);
		next1Button.setBackground(Color.BLACK);
		next1Button.setFont(new Font("Dialog", Font.BOLD, 25));
		next1Button.setBounds(141, 490, 305, 37);
		panel_1.add(next1Button);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.WHITE);
		layeredPane.setLayer(panel2, 1);
		panel2.setBounds(0, 0, 865, 765);
		layeredPane.add(panel2);
		panel2.setLayout(null);
		
		JButton next2Button = new JButton("Next");
		next2Button.setForeground(Color.WHITE);
		next2Button.setFont(new Font("Dialog", Font.BOLD, 25));
		next2Button.setBackground(Color.BLACK);
		next2Button.setBounds(142, 364, 305, 37);
		panel2.add(next2Button);
		
		JLabel lFirstname = new JLabel("First Name");
		lFirstname.setHorizontalAlignment(SwingConstants.CENTER);
		lFirstname.setBounds(56, 170, 200, 33);
		panel2.add(lFirstname);
		lFirstname.setFont(new Font("Arial", Font.BOLD, 20));
		
		JLabel lSurname = new JLabel("Surname");
		lSurname.setHorizontalAlignment(SwingConstants.CENTER);
		lSurname.setBounds(284, 170, 280, 33);
		panel2.add(lSurname);
		lSurname.setFont(new Font("Arial", Font.BOLD, 20));
		
		tSurname = new JTextField();
		tSurname.setBounds(284, 210, 280, 33);
		panel2.add(tSurname);
		tSurname.setColumns(10);
		
		tFirstname = new JTextField();
		tFirstname.setHorizontalAlignment(SwingConstants.CENTER);
		tFirstname.setBounds(56, 210, 200, 33);
		panel2.add(tFirstname);
		tFirstname.setColumns(10);
		
		tMobile = new JTextField();
		tMobile.setBounds(56, 289, 200, 33);
		panel2.add(tMobile);
		tMobile.setColumns(10);
		
		JLabel lMobile = new JLabel("Mobile");
		lMobile.setHorizontalAlignment(SwingConstants.CENTER);
		lMobile.setBounds(56, 255, 200, 33);
		panel2.add(lMobile);
		lMobile.setFont(new Font("Arial", Font.BOLD, 20));
		
		JRadioButton rbOther = new JRadioButton("Other");
		rbOther.setBounds(389, 100, 103, 26);
		panel2.add(rbOther);
		rbOther.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbOther.isSelected())
					title = rbOther.getText();
			}
		});
		buttonGroup.add(rbOther);
		rbOther.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		JRadioButton rbMrs = new JRadioButton("Mrs");
		rbMrs.setBounds(182, 100, 67, 26);
		panel2.add(rbMrs);
		rbMrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbMrs.isSelected())
					title = rbMrs.getText();
			}
		});
		buttonGroup.add(rbMrs);
		rbMrs.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		JRadioButton rbMr = new JRadioButton("Mr");
		rbMr.setBounds(122, 100, 85, 26);
		panel2.add(rbMr);
		rbMr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbMr.isSelected())
					title = rbMr.getText();
			}
		});
		buttonGroup.add(rbMr);
		rbMr.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		JRadioButton rbMiss = new JRadioButton("Miss");
		rbMiss.setBounds(253, 100, 76, 26);
		panel2.add(rbMiss);
		rbMiss.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbMiss.isSelected())
					title = rbMiss.getText();
			}
		});
		buttonGroup.add(rbMiss);
		rbMiss.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		JRadioButton rbDr = new JRadioButton("Dr");
		rbDr.setBounds(333, 100, 67, 26);
		panel2.add(rbDr);
		rbDr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbDr.isSelected())
					title = rbDr.getText();
			}
		});
		buttonGroup.add(rbDr);
		rbDr.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		JLabel lTitle = new JLabel("Title");
		lTitle.setBounds(56, 100, 52, 24);
		panel2.add(lTitle);
		lTitle.setFont(new Font("Arial", Font.BOLD, 20));
		
		
		
		
		
		
		JPanel panel3 = new JPanel();
		layeredPane.setLayer(panel3, 0);
		panel3.setBounds(0, 0, 855, 765);
		layeredPane.add(panel3);
		panel3.setLayout(null);
		
		JButton hostReg = new JButton("HOST");
		hostReg.setForeground(Color.WHITE);
		hostReg.setBackground(Color.BLACK);
		hostReg.setBounds(180, 545, 130, 25);
		panel3.add(hostReg);
		hostReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//collects address info
				String house = tHouse.getText();
				String street = tStreet.getText();
				String city = tCity.getText();
				String postCode = tPostCode.getText();
				//collects user info
				String name = tFirstname.getText();
				String surname = tSurname.getText();
				String email = tEmail.getText();
				String mobile = tMobile.getText();
				String username = tUsername.getText();
				char[] password = tPassword.getPassword();
				String pass = "";
				for(int i = 0; i < password.length; i++) {
					pass = pass + password[i];
				}
				//initially host will have no properties so empty property array
				ArrayList<Property> properties = new ArrayList<>();
				
				//checks if any section is empty
				if( house.isBlank() || street.isBlank() || city.isBlank() || postCode.isBlank() || title == null) {
					errorMsg.setVisible(true);
				}
				else {
					// creates the Address and Host objects if all fields not empty
					Address ad = new Address(house,street,city,postCode); 
					User host = new Host(title,name,surname,email,mobile,ad,username,pass,properties);
					//removes any possible SQL injection
					host.cleanInputs();
					
					Database.connectDB();
					//if user doesn't exist, add the user to all the tables
					 if(!Database.exists(host.getEmail())) {
						 Database.addUser(host);
						 Database.addUserType(host);
						 
						 //takes user to login page
						 login log = new login();
						 JFrame login = log.getFrame();
						 login.setVisible(true);
						 frmRegistration.setVisible(false);
					 }
					 else {
						//check if given pdetails match the pdetails existing for that email
						 if(Database.matchPdetails(host)) {
							//if user exists and not already host add them to host table
							 if(!Database.isHost(host.getEmail())) {
								 Database.addUserType(host);
								 Database.setUserType(host);
								 
								 //take user to login page
								 login log = new login();
								 JFrame login = log.getFrame();
								 login.setVisible(true);
								 frmRegistration.setVisible(false);
							 }
							 else { // this means isHost == 1 so already registered as host
								 System.out.println("User already registered as host!");
							 }
						 }
						 else
							 System.out.println("Details do not match given email");
					 }
					Database.disconnectDB();
						
					
					// if email is used do usedEmail.setVisible(true)
					
					//remove error messages
					//usedEmail.setVisible(false);
					errorMsg.setVisible(false);

				}
			}
		});
		hostReg.setFont(new Font("Dialog", Font.BOLD, 20));
		
		JLabel lAddress = new JLabel("Address details");
		lAddress.setBounds(180, 187, 301, 59);
		panel3.add(lAddress);
		lAddress.setFont(new Font("Dialog", Font.BOLD, 30));
		
		JLabel lHouse = new JLabel("House No. ");
		lHouse.setBounds(180, 285, 124, 24);
		panel3.add(lHouse);
		lHouse.setFont(new Font("Arial", Font.BOLD, 20));
		
		tHouse = new JTextField();
		tHouse.setBounds(180, 310, 124, 33);
		panel3.add(tHouse);
		tHouse.setColumns(10);
		
		tStreet = new JTextField();
		tStreet.setBounds(180, 380, 280, 33);
		panel3.add(tStreet);
		tStreet.setColumns(10);
		
		JLabel lStreet = new JLabel("Street");
		lStreet.setBounds(180, 355, 94, 24);
		panel3.add(lStreet);
		lStreet.setFont(new Font("Arial", Font.BOLD, 20));
		
		JLabel lCity = new JLabel("City");
		lCity.setBounds(180, 425, 60, 24);
		panel3.add(lCity);
		lCity.setFont(new Font("Arial", Font.BOLD, 20));
		
		JButton guestReg = new JButton("GUEST");
		guestReg.setBackground(Color.BLACK);
		guestReg.setForeground(Color.WHITE);
		guestReg.setBounds(330, 545, 130, 25);
		panel3.add(guestReg);
		guestReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//collects address info
				String house = tHouse.getText();
				String street = tStreet.getText();
				String city = tCity.getText();
				String postCode = tPostCode.getText();
				//collects user info
				String name = tFirstname.getText();
				String surname = tSurname.getText();
				String email = tEmail.getText();
				char [] password = tPassword.getPassword();
				String username = tUsername.getText();
				String pass = "";
				for(int i = 0; i < password.length; i++) {
					pass = pass + password[i];
				}
				String mobile = tMobile.getText();
				
				//checks if any section is empty
				if(house.isBlank() || street.isBlank() || city.isBlank() || postCode.isBlank() || title == null)  {
					errorMsg.setVisible(true);
				}
				else {
					
					// creates the Address and Host objects if all fields not empty
					Address ad = new Address(house,street,city,postCode);
					//initially guest has zero bookings
					User guest = new Guest(title,name,surname,email,mobile,ad,username,pass);
					
					//removes any possible SQL injection
					guest.cleanInputs();
					
					//if user doesn't exist, add them to all the tables
					Database.connectDB();
					 if(!Database.exists(guest.getEmail())) {
						 Database.addUser(guest);
						 Database.addUserType(guest);
						 
						//take user to login page
						 login log = new login();
						 JFrame login = log.getFrame();
						 login.setVisible(true);
						 frmRegistration.setVisible(false);
					 }
					 else {
						 //check if given pdetails match the pdetails existing for that email
						 if(Database.matchPdetails(guest)) {
							//if user exists and not already guest add them to host table
							 if(!Database.isGuest(guest.getEmail())) {
								 Database.addUserType(guest);
								 Database.setUserType(guest);
								 
								//take user to login page
								 login log = new login();
								 JFrame login = log.getFrame();
								 login.setVisible(true);
								 frmRegistration.setVisible(false);
							 }
							 else { // this means isGuest == 1 so already registered as host
								 System.out.println("User already registered as guest");
							 }
						 }
						 else
							 System.out.println("Details do not match given email");
					 }
					 Database.disconnectDB();
					
					// if email is used do usedEmail.setVisible(true)

					// add method to insert this guest into guest table
					
					
					//remove error messages
					errorMsg.setVisible(false);
				}
			}
		});
		
		
		next1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = tEmail.getText();
				char [] password = tPassword.getPassword();
				String pass = "";
				for(int i = 0; i < password.length; i++) {
					pass = pass + password[i];
					}
				String username = tUsername.getText();
				if(email.isBlank() || username.isBlank() || pass.isBlank()) {
					errorMsg.setVisible(true);
				}
				else {
					layeredPane.removeAll();
					layeredPane.add(panel2);
					layeredPane.repaint();
					layeredPane.revalidate();
					errorMsg.setVisible(false);
				}
			}
		});
		next2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = tFirstname.getText();
				String surname = tSurname.getText();
				String email = tEmail.getText();
				char [] password = tPassword.getPassword();
				String username = tUsername.getText();
				String pass = "";
				String mobile = tMobile.getText();
				if (name.isBlank() || surname.isBlank() || mobile.isBlank()) {
					errorMsg.setVisible(true);
				}
				else {
					layeredPane.removeAll();
					layeredPane.add(panel3);
					layeredPane.repaint();
					layeredPane.revalidate();
					errorMsg.setVisible(false);
				}
			}
		});
		
		guestReg.setFont(new Font("Dialog", Font.BOLD, 20));
		
		tCity = new JTextField();
		tCity.setBounds(180, 450, 280, 33);
		panel3.add(tCity);
		tCity.setColumns(10);
		
		JButton clear = new JButton("Clear all fields");
		clear.setEnabled(false);
		clear.setBounds(500, 683, 135, 25);
		panel3.add(clear);
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// clears all text from text fields
				buttonGroup.clearSelection();
				tHouse.setText("");
				tStreet.setText("");
				tCity.setText("");
				tPostCode.setText("");
				tFirstname.setText("");
				tSurname.setText("");
				tEmail.setText("");
				tMobile.setText("");
				tUsername.setText("");
				tPassword.setText("");
			}
		});
		clear.setFont(new Font("Arial", Font.BOLD, 12));
		
		tPostCode = new JTextField();
		tPostCode.setBounds(320, 310, 140, 33);
		panel3.add(tPostCode);
		tPostCode.setColumns(10);
		
		JLabel lPostCode = new JLabel("Post Code");
		lPostCode.setBounds(320, 285, 137, 24);
		panel3.add(lPostCode);
		lPostCode.setFont(new Font("Arial", Font.BOLD, 20));
		
		JLabel lblRegisterAs = new JLabel("Register as:");
		lblRegisterAs.setFont(new Font("Dialog", Font.BOLD, 25));
		lblRegisterAs.setBounds(232, 500, 204, 33);
		panel3.add(lblRegisterAs);
		panel.setBackground(UIManager.getColor("OptionPane.questionDialog.border.background"));
		panel.setBounds(0, -15, 509, 911);
		frmRegistration.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(registration.class.getResource("/Images/chalet.jpg")));
		lblNewLabel.setBounds(-242, -11, 751, 858);
		panel.add(lblNewLabel);
	}
}
