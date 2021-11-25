package Gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import database.Database;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.Button;

public class login  {
	JFrame frmLoginPage;
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
		frmLoginPage.setResizable(false);
		frmLoginPage.setIconImage(Toolkit.getDefaultToolkit().getImage(login.class.getResource("/Images/chalet.jpg")));
		frmLoginPage.setTitle("Login Page");
		frmLoginPage.setBounds(100, 100, 1200, 800);
		frmLoginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginPage.getContentPane().setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(337, 118, 532, 522);
		frmLoginPage.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton guestLoginButton = new JButton("Log In as Guest");
		guestLoginButton.setBounds(122, 389, 137, 37);
		panel.add(guestLoginButton);
		guestLoginButton.setBackground(Color.BLACK);
		guestLoginButton.setFont(new Font("Dialog", Font.BOLD, 14));
		guestLoginButton.setForeground(Color.WHITE);
		
		JLabel lblEmailAdress = new JLabel("Login with email \n");
		lblEmailAdress.setBounds(143, 171, 273, 31);
		panel.add(lblEmailAdress);
		lblEmailAdress.setFont(new Font("DialogInput", Font.BOLD, 25));
		
		password = new JPasswordField();
		password.setFont(new Font("Dialog", Font.PLAIN, 20));
		password.setBounds(122, 307, 305, 37);
		panel.add(password);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(214, 273, 200, 22);
		panel.add(lblPassword);
		lblPassword.setFont(new Font("DialogInput", Font.BOLD, 25));
		
		Email = new JTextField();
		Email.setFont(new Font("Dialog", Font.PLAIN, 20));
		Email.setBounds(122, 213, 305, 37);
		panel.add(Email);
		Email.setColumns(10);
		
		JLabel lblLogIn = new JLabel("Log In");
		lblLogIn.setFont(new Font("Dialog", Font.BOLD, 50));
		lblLogIn.setBounds(169, 12, 189, 100);
		panel.add(lblLogIn);
		
		JButton signUpButton = new JButton("SIgn Up");
		signUpButton.setBackground(Color.WHITE);
		signUpButton.setBounds(310, 352, 117, 25);
		panel.add(signUpButton);
		
		JButton hostLoginButton = new JButton("Log In as Host");
		hostLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = Email.getText().toLowerCase();
				char [] charPass = password.getPassword();
				String pass = "";
				for(int i = 0; i < charPass.length; i++) {
					pass = pass + charPass[i];
				}
				Database.connectDB();
				if(Database.exists(email)) {
					// checks if given email and password match
					if(Database.loginUser(email, pass, "Hosts")) {
						System.out.println("email and password matched!");
						//take host to host page
					}
					else {
						System.out.println("username and password does not match");
					}
				}
				else
					System.out.println("User with that email doesn't exist!");
				Database.disconnectDB();
			}
		});
		hostLoginButton.setForeground(Color.WHITE);
		hostLoginButton.setFont(new Font("Dialog", Font.BOLD, 14));
		hostLoginButton.setBackground(Color.BLACK);
		hostLoginButton.setBounds(290, 389, 137, 37);
		panel.add(hostLoginButton);
		
		JLabel label = new JLabel("");
		label.setBackground(new Color(0, 0, 0));
		label.setBounds(0, -15, 1200, 800);
		frmLoginPage.getContentPane().add(label);
		label.setIcon(new ImageIcon(login.class.getResource("/Images/costa.jpg")));
		
		guestLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = Email.getText();
				char [] charPass = password.getPassword();
				String pass = "";
				for(int i = 0; i < charPass.length; i++) {
					pass = pass + charPass[i];
				}
				Database.connectDB();
				if(Database.exists(email)) {
					// checks if given email and password match
					if(Database.loginUser(email, pass, "Guests")) {
						System.out.println("email and password matched!");
						//take guest to guest page
					}
					else {
						System.out.println("username and password does not match");
					}
				}
				else
					System.out.println("User with that email doesn't exist!");
				Database.disconnectDB();
			}
		});
		signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registration regi = new registration();
				JFrame register = regi.getFrame();
				register.setVisible(true);
				frmLoginPage.setVisible(false);
				
				
				
			}
		});
		
	}
}
