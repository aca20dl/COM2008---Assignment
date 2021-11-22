package Gui;

import classCode.*;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Profile {

	private JFrame profileFrame;
	private Guest guest;
	private Host host;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Address ad = new Address("25","Green ln", "Sheffield", "s109ju");
					Guest salma = new Guest ("Miss","Salma","Hassan","s.h@email.com",
							"0114",ad,"Sal","password");
					Profile window = new Profile(salma);
					window.profileFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Profile(User user) {
		initialize(user);
	}
	
	public JFrame getFrame() {
		return profileFrame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(User user) {
		profileFrame = new JFrame();
		profileFrame.setBounds(100, 100, 882, 600);
		profileFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		profileFrame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 94, 850, 147);
		profileFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Title:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 0, 59, 20);
		panel.add(lblNewLabel);
		
		JLabel lblForename = new JLabel("Forename:");
		lblForename.setFont(new Font("Arial", Font.BOLD, 20));
		lblForename.setBounds(240, 0, 109, 20);
		panel.add(lblForename);
		
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setFont(new Font("Arial", Font.BOLD, 20));
		lblSurname.setBounds(485, 0, 92, 20);
		panel.add(lblSurname);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Arial", Font.BOLD, 20));
		lblEmail.setBounds(686, 0, 92, 20);
		panel.add(lblEmail);
		
		JLabel lblMobile = new JLabel("Mobile:");
		lblMobile.setFont(new Font("Arial", Font.BOLD, 20));
		lblMobile.setBounds(10, 68, 84, 20);
		panel.add(lblMobile);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Arial", Font.BOLD, 20));
		lblUsername.setBounds(240, 68, 109, 20);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Arial", Font.BOLD, 20));
		lblPassword.setBounds(485, 68, 109, 20);
		panel.add(lblPassword);
		
		JLabel title = new JLabel(user.getTitle());
		title.setForeground(Color.DARK_GRAY);
		title.setBackground(Color.BLACK);
		title.setFont(new Font("Arial", Font.BOLD, 15));
		title.setBounds(10, 31, 109, 20);
		panel.add(title);
		
		JLabel forename = new JLabel(user.getForename());
		forename.setFont(new Font("Arial", Font.BOLD, 15));
		forename.setBounds(240, 31, 109, 20);
		panel.add(forename);
		
		JLabel Surname = new JLabel(user.getSurname());
		Surname.setFont(new Font("Arial", Font.BOLD, 15));
		Surname.setBounds(485, 31, 109, 20);
		panel.add(Surname);
		
		JLabel email = new JLabel(user.getEmail());
		email.setFont(new Font("Arial", Font.BOLD, 15));
		email.setBounds(686, 31, 154, 20);
		panel.add(email);
		
		JLabel mobile = new JLabel(user.getMobile());
		mobile.setFont(new Font("Arial", Font.BOLD, 15));
		mobile.setBounds(10, 99, 109, 20);
		panel.add(mobile);
		
		//
		if(user.getClass().getName() == "classCode.Host")
			host = (Host) user;
		else
			guest = (Guest) user;
		
		
		JLabel username = new JLabel(guest.getGuestName());
		username.setFont(new Font("Arial", Font.BOLD, 15));
		username.setBounds(240, 99, 109, 20);
		panel.add(username);
		
		JLabel password = new JLabel(guest.getPassword());
		password.setFont(new Font("Arial", Font.BOLD, 15));
		password.setBounds(485, 99, 109, 20);
		panel.add(password);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 313, 850, 147);
		profileFrame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblHouseNumber = new JLabel("House Number:");
		lblHouseNumber.setFont(new Font("Arial", Font.BOLD, 20));
		lblHouseNumber.setBounds(32, 24, 154, 20);
		panel_1.add(lblHouseNumber);
		
		JLabel lblStreet = new JLabel("Street:");
		lblStreet.setFont(new Font("Arial", Font.BOLD, 20));
		lblStreet.setBounds(574, 24, 92, 20);
		panel_1.add(lblStreet);
		
		JLabel lblPostcode = new JLabel("PostCode:");
		lblPostcode.setFont(new Font("Arial", Font.BOLD, 20));
		lblPostcode.setBounds(574, 92, 109, 20);
		panel_1.add(lblPostcode);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setFont(new Font("Arial", Font.BOLD, 20));
		lblCity.setBounds(32, 92, 101, 20);
		panel_1.add(lblCity);
		
		JLabel number = new JLabel(user.getAddress().getHouse());
		number.setFont(new Font("Arial", Font.BOLD, 15));
		number.setBounds(32, 55, 352, 20);
		panel_1.add(number);
		
		JLabel street = new JLabel(user.getAddress().getStreet());
		street.setFont(new Font("Arial", Font.BOLD, 15));
		street.setBounds(574, 55, 266, 20);
		panel_1.add(street);
		
		JLabel city = new JLabel(user.getAddress().getPlace());
		city.setFont(new Font("Arial", Font.BOLD, 15));
		city.setBounds(32, 116, 352, 20);
		panel_1.add(city);
		
		JLabel postCode = new JLabel(user.getAddress().getPostCode());
		postCode.setFont(new Font("Arial", Font.BOLD, 15));
		postCode.setBounds(574, 116, 266, 20);
		panel_1.add(postCode);
		
		JLabel lblProfileDetails = new JLabel("Profile Details:");
		lblProfileDetails.setFont(new Font("Arial", Font.BOLD, 20));
		lblProfileDetails.setBounds(10, 28, 219, 20);
		profileFrame.getContentPane().add(lblProfileDetails);
		
		JLabel lblAddressDetails = new JLabel("Address Details:");
		lblAddressDetails.setFont(new Font("Arial", Font.BOLD, 20));
		lblAddressDetails.setBounds(10, 281, 219, 20);
		profileFrame.getContentPane().add(lblAddressDetails);
		
		JButton btnNewButton = new JButton("Back to Homepage");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if guest, go back to home page
				if(user.getClass().getName().equals("classCode.Host")) {
					// go to host page
				}
				else {
					mainPageGuest guestPage = new mainPageGuest(user);
					guestPage.getFrame().setVisible(true);
				}
				profileFrame.setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 13));
		btnNewButton.setBounds(688, 531, 156, 23);
		profileFrame.getContentPane().add(btnNewButton);
	}

}
