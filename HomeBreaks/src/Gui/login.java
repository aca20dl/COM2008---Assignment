package Gui;
import businessLogic.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import classCode.*;
import database.Database;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.Button;
import javax.swing.SwingConstants;

public class login  {
	private JFrame frmLoginPage;
	private JPasswordField password;
	private JTextField Email;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frmLoginPage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
		
	}
	
	public JFrame getFrame() {
		return frmLoginPage;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLoginPage = new JFrame();
		frmLoginPage.setIconImage(Toolkit.getDefaultToolkit().getImage(login.class.getResource("/Images/chalet.jpg")));
		frmLoginPage.setTitle("Login Page");
		frmLoginPage.setBounds(100, 100, 990, 687);
		frmLoginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginPage.getContentPane().setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(270, 51, 532, 522);
		frmLoginPage.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton guestLoginButton = new JButton("Log In as Guest");
		guestLoginButton.setBounds(122, 389, 137, 37);
		panel.add(guestLoginButton);
		guestLoginButton.setBackground(Color.BLACK);
		guestLoginButton.setFont(new Font("Dialog", Font.BOLD, 14));
		guestLoginButton.setForeground(Color.WHITE);
		
		JLabel lblEmailAdress = new JLabel("Login with email \n");
		lblEmailAdress.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmailAdress.setBounds(122, 171, 305, 31);
		panel.add(lblEmailAdress);
		lblEmailAdress.setFont(new Font("Arial", Font.BOLD, 25));
		
		password = new JPasswordField();
		password.setFont(new Font("Dialog", Font.PLAIN, 20));
		password.setBounds(122, 307, 305, 37);
		panel.add(password);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(122, 273, 292, 22);
		panel.add(lblPassword);
		lblPassword.setFont(new Font("Arial", Font.BOLD, 25));
		
		Email = new JTextField();
		Email.setFont(new Font("Dialog", Font.PLAIN, 20));
		Email.setBounds(122, 213, 305, 37);
		panel.add(Email);
		Email.setColumns(10);
		
		JLabel lblLogIn = new JLabel("Log In");
		lblLogIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogIn.setFont(new Font("Arial", Font.BOLD, 50));
		lblLogIn.setBounds(169, 12, 189, 81);
		panel.add(lblLogIn);
		
		JButton homeButton = new JButton("Home");
		homeButton.setBackground(Color.WHITE);
		homeButton.setBounds(310, 451, 117, 25);
		panel.add(homeButton);
		
		JLabel notMatch = new JLabel("Username and Password don't match");
		notMatch.setHorizontalAlignment(SwingConstants.CENTER);
		notMatch.setForeground(Color.GRAY);
		notMatch.setFont(new Font("Arial", Font.BOLD, 15));
		notMatch.setBounds(119, 80, 308, 37);
		panel.add(notMatch);
		notMatch.setVisible(false);
		
		JLabel notExist = new JLabel("User with that email doesn't exist");
		notExist.setHorizontalAlignment(SwingConstants.CENTER);
		notExist.setForeground(Color.GRAY);
		notExist.setFont(new Font("Arial", Font.BOLD, 15));
		notExist.setBounds(119, 110, 308, 37);
		panel.add(notExist);
		notExist.setVisible(false);
		
		JLabel emptyFields = new JLabel("Fill in all fields");
		emptyFields.setForeground(Color.GRAY);
		emptyFields.setHorizontalAlignment(SwingConstants.CENTER);
		emptyFields.setFont(new Font("Arial", Font.BOLD, 15));
		emptyFields.setBounds(115, 146, 312, 14);
		panel.add(emptyFields);
		emptyFields.setVisible(false);
		
		JButton hostLoginButton = new JButton("Log In as Host");
		hostLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = User.removeSemiColon(Email.getText().toLowerCase());
				char [] charPass = password.getPassword();
				String pass = "";
				for(int i = 0; i < charPass.length; i++) {
					pass = pass + charPass[i];
				}
				pass = User.removeSemiColon(pass);
				if(email.isBlank() || pass.isBlank()) {
					emptyFields.setVisible(true);
					notExist.setVisible(false);
					notMatch.setVisible(false);
				}
				else {
					Database.connectDB();
					//hash the password
					pass = guestActions.hash(pass);
					if(Accounts.exists(email)) {
						// checks if given email and password match
						if(Accounts.loginUser(email, pass, "Hosts")) {
							//take host to host page
							User user = Accounts.getUser(email, "Host");
							mainPageHost hostPage = new mainPageHost(user);
							emptyFields.setVisible(false);
							notExist.setVisible(false);
							notMatch.setVisible(false);
							hostPage.getFrame().setVisible(true);
							frmLoginPage.setVisible(false);
						}
						else {
							emptyFields.setVisible(false);
							notExist.setVisible(false);
							notMatch.setVisible(true);
						}
					}
					else {
						emptyFields.setVisible(false);
						notExist.setVisible(true);
						notMatch.setVisible(false);
					}
					Database.disconnectDB();
				}
			}
		});
		hostLoginButton.setForeground(Color.WHITE);
		hostLoginButton.setFont(new Font("Dialog", Font.BOLD, 14));
		hostLoginButton.setBackground(Color.BLACK);
		hostLoginButton.setBounds(290, 389, 137, 37);
		panel.add(hostLoginButton);
		
		JLabel label = new JLabel("");
		label.setBackground(new Color(0, 0, 0));
		label.setBounds(0, 0, 984, 659);
		frmLoginPage.getContentPane().add(label);
		label.setIcon(new ImageIcon(login.class.getResource("/Images/costa.jpg")));
		
		guestLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = User.removeSemiColon(Email.getText().toLowerCase());
				char [] charPass = password.getPassword();
				String pass = "";
				for(int i = 0; i < charPass.length; i++) {
					pass = pass + charPass[i];
				}
				if(email.isBlank() || pass.isBlank()) {
					emptyFields.setVisible(true);
					notExist.setVisible(false);
					notMatch.setVisible(false);
				}
				else {
					//hash the password
					pass = guestActions.hash(pass);
					
					Database.connectDB();
					if(Accounts.exists(email)) {
						// checks if given email and password match
						if(Accounts.loginUser(email, pass, "Guests")) {
							//take guest to guest page
							User user = Accounts.getUser(email, "Guest");
							mainPageGuest guestPage = new mainPageGuest(user);
							emptyFields.setVisible(false);
							notExist.setVisible(false);
							notMatch.setVisible(false);
							guestPage.getFrame().setVisible(true);
							frmLoginPage.setVisible(false);
						}
						else {
							emptyFields.setVisible(false);
							notExist.setVisible(false);
							notMatch.setVisible(true);
						}
					}
					else {
						emptyFields.setVisible(false);
						notExist.setVisible(true);
						notMatch.setVisible(false);
					}
					Database.disconnectDB();
				}
			}
		});
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lobbyPage lobby = new lobbyPage();
				lobby.getFrame().setVisible(true);
				frmLoginPage.setVisible(false);	
			}
		});
		
	}
}
