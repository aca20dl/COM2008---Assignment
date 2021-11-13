package Gui;

import java.awt.EventQueue;
import classCode.*;

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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegistration = new JFrame();
		frmRegistration.setResizable(false);
		frmRegistration.setTitle("Registration");
		frmRegistration.setBounds(100, 100, 900, 650);
		frmRegistration.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegistration.getContentPane().setLayout(null);
		
		JLabel formTitle = new JLabel("Registration");
		formTitle.setBounds(0, 0, 888, 47);
		formTitle.setFont(new Font("Arial", Font.BOLD, 40));
		formTitle.setHorizontalAlignment(SwingConstants.CENTER);
		frmRegistration.getContentPane().add(formTitle);
		
		JLabel lTitle = new JLabel("Title");
		lTitle.setFont(new Font("Arial", Font.BOLD, 20));
		lTitle.setBounds(32, 80, 53, 29);
		frmRegistration.getContentPane().add(lTitle);
		
		JLabel lFirstname = new JLabel("First Name: ");
		lFirstname.setFont(new Font("Arial", Font.BOLD, 20));
		lFirstname.setBounds(32, 143, 165, 22);
		frmRegistration.getContentPane().add(lFirstname);
		
		JRadioButton rbMrs = new JRadioButton("Mrs");
		rbMrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbMrs.isSelected())
					title = rbMrs.getText();
			}
		});
		buttonGroup.add(rbMrs);
		rbMrs.setFont(new Font("Arial", Font.PLAIN, 15));
		rbMrs.setBounds(97, 86, 58, 23);
		frmRegistration.getContentPane().add(rbMrs);
		
		JRadioButton rbMr = new JRadioButton("Mr");
		rbMr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbMr.isSelected())
					title = rbMr.getText();
			}
		});
		buttonGroup.add(rbMr);
		rbMr.setFont(new Font("Arial", Font.PLAIN, 15));
		rbMr.setBounds(157, 86, 58, 23);
		frmRegistration.getContentPane().add(rbMr);
		
		JRadioButton rbDr = new JRadioButton("Dr");
		rbDr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbDr.isSelected())
					title = rbDr.getText();
			}
		});
		buttonGroup.add(rbDr);
		rbDr.setFont(new Font("Arial", Font.PLAIN, 15));
		rbDr.setBounds(217, 86, 58, 23);
		frmRegistration.getContentPane().add(rbDr);
		
		JRadioButton rbMiss = new JRadioButton("Miss");
		rbMiss.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbMiss.isSelected())
					title = rbMiss.getText();
			}
		});
		buttonGroup.add(rbMiss);
		rbMiss.setFont(new Font("Arial", Font.PLAIN, 15));
		rbMiss.setBounds(277, 86, 58, 23);
		frmRegistration.getContentPane().add(rbMiss);
		
		JRadioButton rbOther = new JRadioButton("Other");
		rbOther.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbOther.isSelected())
					title = rbOther.getText();
			}
		});
		buttonGroup.add(rbOther);
		rbOther.setFont(new Font("Arial", Font.PLAIN, 15));
		rbOther.setBounds(337, 86, 79, 23);
		frmRegistration.getContentPane().add(rbOther);
		
		tFirstname = new JTextField();
		tFirstname.setBounds(157, 143, 191, 20);
		frmRegistration.getContentPane().add(tFirstname);
		tFirstname.setColumns(10);
		
		JLabel lSurname = new JLabel("Surname: ");
		lSurname.setFont(new Font("Arial", Font.BOLD, 20));
		lSurname.setBounds(32, 203, 165, 22);
		frmRegistration.getContentPane().add(lSurname);
		
		tSurname = new JTextField();
		tSurname.setColumns(10);
		tSurname.setBounds(157, 203, 191, 20);
		frmRegistration.getContentPane().add(tSurname);
		
		JLabel lEmail = new JLabel("Email:  ");
		lEmail.setFont(new Font("Arial", Font.BOLD, 20));
		lEmail.setBounds(32, 263, 165, 22);
		frmRegistration.getContentPane().add(lEmail);
		
		tEmail = new JTextField();
		tEmail.setColumns(10);
		tEmail.setBounds(157, 263, 191, 20);
		frmRegistration.getContentPane().add(tEmail);
		
		JLabel lMobile = new JLabel("Mobile:  ");
		lMobile.setFont(new Font("Arial", Font.BOLD, 20));
		lMobile.setBounds(32, 323, 165, 22);
		frmRegistration.getContentPane().add(lMobile);
		
		tMobile = new JTextField();
		tMobile.setColumns(10);
		tMobile.setBounds(157, 323, 191, 20);
		frmRegistration.getContentPane().add(tMobile);
		
		JLabel lUsername = new JLabel("Username:  ");
		lUsername.setFont(new Font("Arial", Font.BOLD, 20));
		lUsername.setBounds(32, 383, 165, 22);
		frmRegistration.getContentPane().add(lUsername);
		
		tUsername = new JTextField();
		tUsername.setColumns(10);
		tUsername.setBounds(157, 383, 191, 20);
		frmRegistration.getContentPane().add(tUsername);
		
		JLabel lPassword = new JLabel("Password:  ");
		lPassword.setFont(new Font("Arial", Font.BOLD, 20));
		lPassword.setBounds(32, 443, 165, 22);
		frmRegistration.getContentPane().add(lPassword);
		
		tPassword = new JPasswordField();
		tPassword.setBounds(157, 443, 191, 20);
		frmRegistration.getContentPane().add(tPassword);
		
		JLabel errorMsg = new JLabel("Fill out all fields to register");
		errorMsg.setForeground(Color.RED);
		errorMsg.setFont(new Font("Arial", Font.BOLD, 20));
		errorMsg.setBounds(510, 98, 331, 47);
		//initially hidden
		errorMsg.setVisible(false);
		frmRegistration.getContentPane().add(errorMsg);
		
		JButton guestReg = new JButton("Register as a Guest");
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
				String mobile = tMobile.getText();
				String username = tUsername.getText();
				char[] password = tPassword.getPassword();
				String pass = "";
				for(int i = 0; i < password.length; i++) {
					pass = pass + password[i];
					}
				//checks if any section is empty
				if(name.isBlank() || surname.isBlank() || email.isBlank()
						|| mobile.isBlank() || house.isBlank() || street.isBlank() ||
						city.isBlank() || postCode.isBlank() || username.isBlank() ||
						pass.isBlank() || title == null)  {
					errorMsg.setVisible(true);
				}
				else {
					//method to check this guest doesn't already exist in db, so email not used b4
					
					// if email is used do usedEmail.setVisible(true)
					//creates address
					Address ad = new Address(house,street,city,postCode);
					//creates new guest if no fields are empty and email is not used
					Guest guest = new Guest(title,name,surname,email,mobile,ad,username,pass);
					// add method to insert this guest into guest table
					
					
					//remove error messages
					errorMsg.setVisible(false);
					System.out.println(guest);
				}
			}
		});
		guestReg.setFont(new Font("Arial", Font.BOLD, 12));
		guestReg.setBounds(200, 551, 165, 29);
		frmRegistration.getContentPane().add(guestReg);
		
		JButton hostReg = new JButton("Register as a Host");
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
				if(name.isBlank() || surname.isBlank() || email.isBlank()
						|| mobile.isBlank() || house.isBlank() || street.isBlank() ||
						city.isBlank() || postCode.isBlank() || username.isBlank() ||
						pass.isBlank() || title == null) {
					errorMsg.setVisible(true);
				}
				else {
					//method to check this host doesn't already exist in db, so email not used b4
					
					// if email is used do usedEmail.setVisible(true)
					// creates new address
					Address ad = new Address(house,street,city,postCode); 
					//creates new host if no fields are empty and email is unused
					Host host = new Host(title,name,surname,email,mobile,ad,username,pass,properties);
					
					//remove error messages
					//usedEmail.setVisible(false);
					errorMsg.setVisible(false);
					// add method to insert this host into host table
				
					System.out.println(host);
				}
			}
		});
		hostReg.setFont(new Font("Arial", Font.BOLD, 12));
		hostReg.setBounds(380, 551, 165, 29);
		frmRegistration.getContentPane().add(hostReg);
		
		JLabel usedEmail = new JLabel("That email has already been used");
		usedEmail.setFont(new Font("Arial", Font.BOLD, 20));
		usedEmail.setForeground(Color.RED);
		usedEmail.setBounds(510, 167, 331, 29);
		usedEmail.setVisible(false);
		frmRegistration.getContentPane().add(usedEmail);
		
		JLabel lAddress = new JLabel("Address details");
		lAddress.setFont(new Font("Arial", Font.BOLD, 20));
		lAddress.setBounds(510, 203, 230, 21);
		frmRegistration.getContentPane().add(lAddress);
		
		JLabel lHouse = new JLabel("House No. ");
		lHouse.setFont(new Font("Arial", Font.BOLD, 20));
		lHouse.setBounds(510, 263, 165, 22);
		frmRegistration.getContentPane().add(lHouse);
		
		tHouse = new JTextField();
		tHouse.setColumns(10);
		tHouse.setBounds(635, 263, 191, 20);
		frmRegistration.getContentPane().add(tHouse);
		
		JLabel lStreet = new JLabel("Street:  ");
		lStreet.setFont(new Font("Arial", Font.BOLD, 20));
		lStreet.setBounds(510, 323, 165, 22);
		frmRegistration.getContentPane().add(lStreet);
		
		tStreet = new JTextField();
		tStreet.setColumns(10);
		tStreet.setBounds(635, 323, 191, 20);
		frmRegistration.getContentPane().add(tStreet);
		
		JLabel lCity = new JLabel("City: ");
		lCity.setFont(new Font("Arial", Font.BOLD, 20));
		lCity.setBounds(510, 383, 165, 22);
		frmRegistration.getContentPane().add(lCity);
		
		tCity = new JTextField();
		tCity.setColumns(10);
		tCity.setBounds(635, 383, 191, 20);
		frmRegistration.getContentPane().add(tCity);
		
		JLabel lPostCode = new JLabel("Post Code:  ");
		lPostCode.setFont(new Font("Arial", Font.BOLD, 20));
		lPostCode.setBounds(510, 443, 165, 22);
		frmRegistration.getContentPane().add(lPostCode);
		
		tPostCode = new JTextField();
		tPostCode.setColumns(10);
		tPostCode.setBounds(635, 443, 191, 20);
		frmRegistration.getContentPane().add(tPostCode);
		
		JButton clear = new JButton("Clear all fields");
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
		clear.setBounds(560, 551, 165, 29);
		frmRegistration.getContentPane().add(clear);
	}
}
