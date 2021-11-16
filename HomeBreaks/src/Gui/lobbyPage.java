package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class lobbyPage {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					lobbyPage window = new lobbyPage();
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
	public lobbyPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 400, 800);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, -12, 400, 800);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(lobbyPage.class.getResource("/Images/autumn.jpg")));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(400, 0, 400, 800);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblSignUp = new JLabel("Home Breaks");
		lblSignUp.setForeground(new Color(0, 0, 0));
		lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignUp.setFont(new Font("Dialog", Font.BOLD, 50));
		lblSignUp.setBounds(0, 0, 400, 83);
		panel_1.add(lblSignUp);
		
		JButton signupButton = new JButton("Sign Up");
		signupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registration regi = new registration();
				regi.frmRegistration.setVisible(true);
				frame.setVisible(false);
			}
		});
		signupButton.setBackground(Color.BLACK);
		signupButton.setForeground(Color.WHITE);
		signupButton.setFont(new Font("Dialog", Font.BOLD, 25));
		signupButton.setBounds(47, 555, 300, 35);
		panel_1.add(signupButton);
		
		JButton loginButton = new JButton("Log In");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login log1 = new login();
				log1.frmLoginPage.setVisible(true);
				frame.setVisible(false);
			}
		});
		loginButton.setForeground(Color.WHITE);
		loginButton.setBackground(Color.BLACK);
		loginButton.setFont(new Font("Dialog", Font.BOLD, 25));
		loginButton.setBounds(47, 505, 300, 35);
		panel_1.add(loginButton);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(0, -12, 400, 800);
		panel_1.add(lblNewLabel_2);
		lblNewLabel_2.setIcon(new ImageIcon(lobbyPage.class.getResource("/Images/mountains.jpg")));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(800, 0, 400, 800);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(0, 0, 400, 800);
		panel_2.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(lobbyPage.class.getResource("/Images/countryside.jpg")));
	}
}
