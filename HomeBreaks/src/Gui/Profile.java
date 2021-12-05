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
import javax.swing.JTextField;
import javax.swing.UIManager;

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
					Profile window = new Profile(null);
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
		profileFrame.setBounds(100, 100, 898, 612);
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
		lblEmail.setBounds(485, 68, 92, 20);
		panel.add(lblEmail);
		
		JLabel lblMobile = new JLabel("Mobile:");
		lblMobile.setFont(new Font("Arial", Font.BOLD, 20));
		lblMobile.setBounds(10, 68, 84, 20);
		panel.add(lblMobile);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Arial", Font.BOLD, 20));
		lblUsername.setBounds(240, 68, 109, 20);
		panel.add(lblUsername);
		
		JTextField title = new JTextField(user.getTitle());
		title.setEditable(false);
		title.setForeground(Color.DARK_GRAY);
		title.setBackground(Color.WHITE);
		title.setFont(new Font("Arial", Font.BOLD, 11));
		title.setBounds(10, 31, 149, 20);
		panel.add(title);
		
		JTextField forename = new JTextField(user.getForename());
		forename.setBackground(Color.WHITE);
		forename.setEditable(false);
		forename.setFont(new Font("Arial", Font.BOLD, 11));
		forename.setBounds(240, 31, 143, 20);
		panel.add(forename);
		
		JTextField Surname = new JTextField(user.getSurname());
		Surname.setBackground(Color.WHITE);
		Surname.setEditable(false);
		Surname.setFont(new Font("Arial", Font.BOLD, 11));
		Surname.setBounds(485, 31, 109, 20);
		panel.add(Surname);
		
		JTextField email = new JTextField(user.getEmail());
		email.setBackground(Color.WHITE);
		email.setEditable(false);
		email.setFont(new Font("Arial", Font.BOLD, 11));
		email.setBounds(485, 99, 291, 20);
		panel.add(email);
		
		JTextField mobile = new JTextField(user.getMobile());
		mobile.setBackground(Color.WHITE);
		mobile.setEditable(false);
		mobile.setFont(new Font("Arial", Font.BOLD, 11));
		mobile.setBounds(10, 99, 149, 20);
		panel.add(mobile);
		
		// differenced between host and guest
		String username1 = "";
		String password1 = "";
		
		if(user.getClass().getName() == "classCode.Host") {
			host = (Host) user;
			username1 = host.getHostName();
			password1 = host.getPassword();
		}
		else {
			guest = (Guest) user;
			username1 = guest.getGuestName();
			password1 = guest.getPassword();
		}
		
		
		JTextField username = new JTextField(username1);
		username.setBackground(Color.WHITE);
		username.setEditable(false);
		username.setFont(new Font("Arial", Font.BOLD, 11));
		username.setBounds(240, 99, 143, 20);
		panel.add(username);
		
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
		
		JTextField number = new JTextField(user.getAddress().getHouse());
		number.setBackground(Color.WHITE);
		number.setEditable(false);
		number.setFont(new Font("Arial", Font.BOLD, 11));
		number.setBounds(32, 55, 176, 20);
		panel_1.add(number);
		
		JTextField street = new JTextField(user.getAddress().getStreet());
		street.setBackground(Color.WHITE);
		street.setEditable(false);
		street.setFont(new Font("Arial", Font.BOLD, 11));
		street.setBounds(574, 55, 162, 20);
		panel_1.add(street);
		
		JTextField city = new JTextField(user.getAddress().getPlace());
		city.setBackground(Color.WHITE);
		city.setEditable(false);
		city.setFont(new Font("Arial", Font.BOLD, 11));
		city.setBounds(32, 116, 177, 20);
		panel_1.add(city);
		
		JTextField postCode = new JTextField(user.getAddress().getPostCode());
		postCode.setBackground(Color.WHITE);
		postCode.setEditable(false);
		postCode.setFont(new Font("Arial", Font.BOLD, 11));
		postCode.setBounds(574, 116, 162, 20);
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
					mainPageHost hostPage = new mainPageHost(user);
					hostPage.getFrame().setVisible(true);
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
