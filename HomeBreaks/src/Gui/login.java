package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class login  extends JFrame implements ActionListener{
	
	
	private static JLabel passwordl, label;
	private static JTextField username;
	private static JButton button;
	private static JTextField password;
	
	
	public login() {
		//JPanel class
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		//JFrame class
		JFrame frame = new JFrame();
		frame.setTitle("Login");
		frame.setLocation(new Point(500, 300));
		frame.add(panel);
		frame.setSize(new Dimension(400, 200));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Username label
		label = new JLabel("Username");
		label.setBounds(100, 8, 70, 20);
		panel.add(label);
		
		//Username text field
		username = new JTextField();
		username.setBounds(100, 27, 193, 28);
		panel.add(username);
		
		//Password label
		passwordl = new JLabel();
		passwordl.setBounds(100, 55, 70, 20);
		panel.add(passwordl);
		
		//Password text field
		password = new JPasswordField();
		password.setBounds(100, 75, 193, 28);
		panel.add(password);
		
		//Button
		button = new JButton("Login");
		button.setBounds(100, 110, 90, 25);
		button.setForeground(Color.WHITE);
		button.setBackground(Color.BLACK);
		button.addActionListener((ActionListener) new login());
		panel.add(button);
		
		
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		String Username = username.getText();
		String Password = password.getText();
		
		if (Username.contentEquals("Systems") && Password.contentEquals("123"))
			JOptionPane.showMessageDialog(null, "Login Successful");
		else
			JOptionPane.showMessageDialog(null, "Username or Password incorrect  ");
		
		
	}
	public static void main(String [] args) {
		new login();
	}

}
