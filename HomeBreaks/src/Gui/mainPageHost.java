package Gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

import classCode.*;

public class mainPageHost {

	private JFrame hostFrame;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Address ad1 = new Address("salma","salma","salma","salma");
					User salma =new Host("Miss","salma","salma","salma","salma",ad1,"salma","salma",0);
					mainPageHost window = new mainPageHost(salma);
					window.hostFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public JFrame getFrame() {
		return hostFrame;
	}

	/**
	 * Create the application.
	 */
	public mainPageHost(User user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(User user) {
		hostFrame = new JFrame();
		hostFrame.setBounds(100, 100, 1200, 800);
		hostFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		hostFrame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Your Bookings:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 23, 163, 47);
		hostFrame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 83, 719, 230);
		hostFrame.getContentPane().add(scrollPane);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "1", "2", "3", "4", "5"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(list);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.GRAY);
		hostFrame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmHome = new JMenuItem("Home");
		mnNewMenu.add(mntmHome);
		
		JMenuItem mntmProfile = new JMenuItem("Profile");
		mntmProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Profile profile = new Profile(user);
				profile.getFrame().setVisible(true);
				hostFrame.setVisible(false);
			}
		});
		mnNewMenu.add(mntmProfile);
		
		JMenuItem addProp = new JMenuItem("List Property");
		addProp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListProp list = new ListProp(user);
				list.getFrame().setVisible(true);
				hostFrame.setVisible(false);
			}
		});
		mnNewMenu.add(addProp);
		
		JMenuItem logout = new JMenuItem("LogOut");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lobbyPage lob = new lobbyPage();
				lob.getFrame().setVisible(true);
				hostFrame.setVisible(false);
			}
		});
		mnNewMenu.add(logout);
	}
}
