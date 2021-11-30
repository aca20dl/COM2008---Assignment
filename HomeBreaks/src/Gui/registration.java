package Gui;

import businessLogic.*;

import java.util.regex.*;

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
	
	public void goToPanel(JPanel p) {
		layeredPane.removeAll();
		layeredPane.add(p);
		layeredPane.repaint();
		layeredPane.revalidate();
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
	
	/**
	 * Checks if the password and email are valid
	 */
	static boolean isValidEmail(String email) {
	    String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	    return email.matches(regex);
	}
	
	static boolean isValidPassword(String password) {
		if (password == null) {
            return false;
        }
	    String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=._])" + "(?=\\S+$).{8,20}$";
	    return password.matches(regex);
	}
	
	static boolean isValidPhone(String mobile) {
	    String regex = "(?=.*[+])" + "^(?=.*[0-9])" + "(?=\\S+$).{10,13}$";
	    return mobile.matches(regex);
	}
	
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
		
		JLabel errorMsg = new JLabel("Fill out all fields to register");
		errorMsg.setBounds(17, 83, 331, 47);
		panel_1.add(errorMsg);
		errorMsg.setEnabled(false);
		errorMsg.setForeground(Color.RED);
		errorMsg.setFont(new Font("Arial", Font.BOLD, 20));
		errorMsg.setVisible(false);
		
		JLabel formTitle = new JLabel("Register");
		formTitle.setBounds(140, 133, 310, 47);
		panel_1.add(formTitle);
		formTitle.setFont(new Font("Arial", Font.BOLD, 40));
		formTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		JButton next1Button = new JButton("Next");
		
		next1Button.setForeground(Color.WHITE);
		next1Button.setBackground(Color.BLACK);
		next1Button.setFont(new Font("Dialog", Font.BOLD, 25));
		next1Button.setBounds(141, 490, 305, 37);
		panel_1.add(next1Button);
		
		JLabel errorMsgPass1 = new JLabel("The password must contain at least one upper case character, a lower case ");
		errorMsgPass1.setEnabled(false);
		errorMsgPass1.setVisible(false);
		errorMsgPass1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		errorMsgPass1.setBounds(10, 567, 634, 59);
		panel_1.add(errorMsgPass1);
		
		JLabel errorMsgPass2 = new JLabel("character, a number [0-9] and a special character [@#$%^&-+=()]");
		errorMsgPass2.setEnabled(false);
		errorMsgPass2.setVisible(false);
		errorMsgPass2.setHorizontalAlignment(SwingConstants.CENTER);
		errorMsgPass2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		errorMsgPass2.setBounds(0, 601, 644, 59);
		panel_1.add(errorMsgPass2);
		
		JLabel errorMsgEmail = new JLabel("Please enter a valid email");
		errorMsgEmail.setHorizontalAlignment(SwingConstants.CENTER);
		errorMsgEmail.setEnabled(false);
		errorMsgEmail.setVisible(false);
		errorMsgEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		errorMsgEmail.setBounds(47, 538, 484, 37);
		panel_1.add(errorMsgEmail);
		
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
		tFirstname.setHorizontalAlignment(SwingConstants.LEFT);
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
		
		JLabel emptyFields1 = new JLabel("Fill out all fields to register");
		emptyFields1.setFont(new Font("Arial", Font.BOLD, 20));
		emptyFields1.setForeground(Color.GRAY);
		emptyFields1.setBounds(56, 50, 280, 39);
		panel2.add(emptyFields1);
		
		JButton back1 = new JButton("Back");
		back1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToPanel(panel_1);
			}
		});
		back1.setFont(new Font("Arial", Font.BOLD, 15));
		back1.setBounds(521, 483, 93, 23);
		panel2.add(back1);
		
		JLabel errorMsgPhone = new JLabel("Please enter a valid phone number starting with \"+\" and your country code");
		errorMsgPhone.setEnabled(false);
		errorMsgPhone.setVisible(false);
		errorMsgPhone.setFont(new Font("Tahoma", Font.PLAIN, 17));
		errorMsgPhone.setBounds(43, 568, 598, 55);
		panel2.add(errorMsgPhone);
		emptyFields1.setVisible(false);
		
		
		
		
		
		
		JPanel panel3 = new JPanel();
		layeredPane.setLayer(panel3, 0);
		panel3.setBounds(0, 0, 855, 765);
		layeredPane.add(panel3);
		panel3.setLayout(null);
		
		JLabel emptyFields2 = new JLabel("Fill out all fields to register");
		emptyFields2.setForeground(Color.GRAY);
		emptyFields2.setFont(new Font("Arial", Font.BOLD, 20));
		emptyFields2.setBounds(180, 139, 280, 37);
		panel3.add(emptyFields2);
		emptyFields2.setVisible(false);
		
		JLabel usedEmail = new JLabel("That email has already been registered as Host/Guest");
		usedEmail.setBounds(10, 42, 526, 29);
		panel3.add(usedEmail);
		usedEmail.setEnabled(false);
		usedEmail.setFont(new Font("Arial", Font.BOLD, 20));
		usedEmail.setForeground(Color.RED);
		usedEmail.setVisible(false);
		
		JLabel notMatch = new JLabel("Details do not match this existing email");
		notMatch.setForeground(Color.GRAY);
		notMatch.setFont(new Font("Arial", Font.BOLD, 20));
		notMatch.setBounds(10, 82, 526, 29);
		panel3.add(notMatch);
		notMatch.setVisible(false);
		
		JButton hostReg = new JButton("HOST");
		hostReg.setForeground(Color.WHITE);
		hostReg.setBackground(Color.BLACK);
		hostReg.setBounds(180, 545, 130, 25);
		panel3.add(hostReg);
		hostReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//collects address info
				String house = tHouse.getText().toLowerCase();
				String street = tStreet.getText().toLowerCase();
				String city = tCity.getText().toLowerCase();
				String postCode = tPostCode.getText().toLowerCase();
				//collects user info
				String name = tFirstname.getText().toLowerCase();
				String surname = tSurname.getText().toLowerCase();
				String email = tEmail.getText().toLowerCase();
				String mobile = tMobile.getText().toLowerCase();
				String username = tUsername.getText().toLowerCase();
				char[] password = tPassword.getPassword();
				String pass = "";
				for(int i = 0; i < password.length; i++) {
					pass = pass + password[i];
				}
				
				//checks if any section is empty
				if( house.isBlank() || street.isBlank() || city.isBlank() || postCode.isBlank() || title == null) {
					emptyFields2.setVisible(true);
					usedEmail.setVisible(false);
					notMatch.setVisible(false);
				}
				else {
					//hash the password
					pass = guestActions.hash(pass);
					
					emptyFields2.setVisible(false);
					usedEmail.setVisible(false);
					notMatch.setVisible(false);
					// creates the Address and Host objects if all fields not empty
					Address ad = new Address(house,street,city,postCode); 
					//initially average rating for host is 0
					User host = new Host(title,name,surname,email,mobile,ad,username,pass,0);
					//removes any possible SQL injection
					host.cleanInputs();
					
					Database.connectDB();
					//if user doesn't exist, add the user to all the tables
					 if(!Accounts.exists(host.getEmail())) {
						 Accounts.addUser(host);
						 Accounts.addUserType(host);
						 
						 //takes user to login page
						 login log = new login();
						 JFrame login = log.getFrame();
						 login.setVisible(true);
						 frmRegistration.setVisible(false);
					 }
					 else {
						//check if given pdetails match the pdetails existing for that email
						 if(Accounts.matchPdetails(host)) {
							//if user exists and not already host add them to host table
							 if(!Accounts.isHost(host.getEmail())) {
								 Accounts.addUserType(host);
								 Accounts.setUserType(host);
								 
								 //take user to login page
								 login log = new login();
								 JFrame login = log.getFrame();
								 login.setVisible(true);
								 frmRegistration.setVisible(false);
							 }
							 else { // this means isHost == 1 so already registered as host
								 usedEmail.setVisible(true);
							 }
						 }
						 else
							 notMatch.setVisible(true);
					 }
					Database.disconnectDB();
						
					
					// if email is used do usedEmail.setVisible(true)
					
					//remove error messages
					//usedEmail.setVisible(false);

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
				String house = tHouse.getText().toLowerCase();
				String street = tStreet.getText().toLowerCase();
				String city = tCity.getText().toLowerCase();
				String postCode = tPostCode.getText().toLowerCase();
				//collects user info
				String name = tFirstname.getText().toLowerCase();
				String surname = tSurname.getText().toLowerCase();
				String email = tEmail.getText().toLowerCase();
				char [] password = tPassword.getPassword();
				String username = tUsername.getText().toLowerCase();
				String pass = "";
				for(int i = 0; i < password.length; i++) {
					pass = pass + password[i];
				}
				String mobile = tMobile.getText().toLowerCase();
				
				//checks if any section is empty
				if(house.isBlank() || street.isBlank() || city.isBlank() || postCode.isBlank() || title == null)  {
					emptyFields2.setVisible(true);
					usedEmail.setVisible(false);
					notMatch.setVisible(false);
				}
				else {
					emptyFields2.setVisible(false);
					usedEmail.setVisible(false);
					notMatch.setVisible(false);
					//hash the password
					pass = guestActions.hash(pass);
					
					// creates the Address and Host objects if all fields not empty
					Address ad = new Address(house,street,city,postCode);
					//initially guest has zero bookings
					User guest = new Guest(title,name,surname,email,mobile,ad,username,pass);
					
					//removes any possible SQL injection
					guest.cleanInputs();
					
					//if user doesn't exist, add them to all the tables
					Database.connectDB();
					 if(!Accounts.exists(guest.getEmail())) {
						 Accounts.addUser(guest);
						 Accounts.addUserType(guest);
						 
						//take user to login page
						 login log = new login();
						 JFrame login = log.getFrame();
						 login.setVisible(true);
						 frmRegistration.setVisible(false);
					 }
					 else {
						 //check if given pdetails match the pdetails existing for that email
						 if(Accounts.matchPdetails(guest)) {
							//if user exists and not already guest add them to host table
							 if(!Accounts.isGuest(guest.getEmail())) {
								 Accounts.addUserType(guest);
								 Accounts.setUserType(guest);
								 
								//take user to login page
								 login log = new login();
								 JFrame login = log.getFrame();
								 login.setVisible(true);
								 frmRegistration.setVisible(false);
							 }
							 else { // this means isGuest == 1 so already registered as host
								 usedEmail.setVisible(true);
							 }
						 }
						 else
							 notMatch.setVisible(true);
					 }
					 Database.disconnectDB();
					
					// if email is used do usedEmail.setVisible(true)

					// add method to insert this guest into guest table
					
					
					//remove error messages
					 emptyFields2.setVisible(false);
				}
			}
		});
		
		
		next1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char [] password = tPassword.getPassword();
				String pass = "";
				for(int i = 0; i < password.length; i++) {
					pass = pass + password[i];
				}
				
				
				
				if(tEmail.getText().isBlank()  || tUsername.getText().isBlank() || pass.isBlank()) {
					errorMsg.setVisible(true);
					errorMsgPass1.setVisible(false);
					errorMsgPass2.setVisible(false);
					errorMsgEmail.setVisible(false);
				}
				
				else {
					if( !isValidEmail(tEmail.getText()) && !isValidPassword(pass)) {
						errorMsgEmail.setVisible(true);
						errorMsgPass1.setVisible(true);
						errorMsgPass2.setVisible(true);
						errorMsg.setVisible(false);
					}
					else if(!(isValidPassword(pass))&& isValidEmail(tEmail.getText())) {
						
						errorMsgPass1.setVisible(true);
						errorMsgPass2.setVisible(true);
						errorMsgEmail.setVisible(false);
						errorMsg.setVisible(false);
					}
					else if((isValidPassword(pass)) && !isValidEmail(tEmail.getText()) ) {
						errorMsgEmail.setVisible(true);
						errorMsgPass1.setVisible(false);
						errorMsgPass2.setVisible(false);
						errorMsg.setVisible(false);
					}
					else{
						errorMsg.setVisible(false);
						errorMsgPass1.setVisible(false);
						errorMsgPass2.setVisible(false);
						errorMsgEmail.setVisible(false);
						errorMsg.setVisible(false);
						goToPanel(panel2);
					}
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
				if (title == null || name.isBlank() || surname.isBlank() || mobile.isBlank()) {
					emptyFields1.setVisible(true);
				}
				else {
					if( !isValidPhone(tMobile.getText()) ) {
						errorMsgPhone.setVisible(true);
					}
					else {
						emptyFields1.setVisible(false);
						errorMsgPhone.setVisible(true);
						goToPanel(panel3);
					}
				}
				
			}
		});
		
		guestReg.setFont(new Font("Dialog", Font.BOLD, 20));
		
		tCity = new JTextField();
		tCity.setBounds(180, 450, 280, 33);
		panel3.add(tCity);
		tCity.setColumns(10);
		
		JButton back = new JButton("Go back");
		back.setBounds(500, 683, 135, 25);
		panel3.add(back);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToPanel(panel2);
			}
		});
		back.setFont(new Font("Arial", Font.BOLD, 12));
		
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
