package Gui;
import classCode.*;
import database.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import businessLogic.*;

public class mainPageGuest {

	private JFrame guestFrame;
	private JTable bookingsTable;
	private JTable hostTable;
	private DefaultTableModel hostsModel;
	private DefaultTableModel bookingsModel;

	/**
	 * Launch the application.
	 */
	
	public void refreshHosts() {
		hostsModel.setRowCount(0);
	}
	
	public void refreshBookings() {
		bookingsModel.setRowCount(0);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Address ad = new Address("25","Green ln", "Sheffield", "s109ju");
					Guest salma = new Guest ("Miss","Salma","Hassan","s.h@email.com",
							"0114",ad,"Sal","password");
					mainPageGuest window = new mainPageGuest(salma);
					window.guestFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//shows and hides columns
	public void showHide(TableColumn t, int min, int max, int pref) {
		t.setMinWidth(min);
		t.setMaxWidth(max);
		t.setPreferredWidth(pref);
	}
	
	//generates booking table 
	public static void showBookings(User user, DefaultTableModel bookingsModel ) {
		Database.connectDB();
		try {
			ResultSet result = guestActions.showBookings(user);
			while(result.next()) {
			String id = String.valueOf(result.getInt("BookingID"));
			String sDate = result.getDate("StartDate").toString();
			String eDate = result.getDate("EndDate").toString();
			String numNights =String.valueOf(result.getInt("NumNights"));
			String ppn = String.valueOf(result.getInt("PricePerNight"));
			String sc =String.valueOf(result.getInt("ServiceCharge"));
			String cc = String.valueOf(result.getInt("CleaningCharge"));
			String tc = String.valueOf(result.getInt("TotalCharge"));
			String guestID = String.valueOf(result.getInt("GuestID"));
			String propertyID = String.valueOf(result.getInt("PropertyID"));
			
			String booking [] = {id,sDate,eDate,numNights,ppn,sc,cc,tc,guestID,propertyID};
			
			bookingsModel.addRow(booking);
			
			}
			result.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Database.disconnectDB();
	}
	/**
	 * Create the application.
	 */
	public mainPageGuest(User user) {
		initialize(user);
	}
	
	public JFrame getFrame() {
		return guestFrame;
	}

	/**
	 * Initialize the contents of the guestFrame.
	 */
	private void initialize(User user) {
		guestFrame = new JFrame();
		guestFrame.setBounds(100, 100, 1200, 800);
		guestFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guestFrame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Your Bookings:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 23, 163, 47);
		guestFrame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 113, 802, 198);
		guestFrame.getContentPane().add(scrollPane);
		
		bookingsTable = new JTable();
		bookingsTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Booking ID", "Start Date", "End Date", "No. Nights", "Cost per night", "Service Cost", "Cleaning Cost", "Total Charge", "GuestID", "PropertyID"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, Object.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		bookingsTable.getColumnModel().getColumn(4).setPreferredWidth(76);
		scrollPane.setViewportView(bookingsTable);
		bookingsModel = (DefaultTableModel) bookingsTable.getModel(); 
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(46, 412, 642, 198);
		guestFrame.getContentPane().add(scrollPane_1);
		
		hostTable = new JTable();
		hostTable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Title", "Forename", "Surname", "Email", "Mobile", "Username"
				}
			) {
				Class[] columnTypes = new Class[] {
					Object.class, Object.class, Object.class, Object.class, Object.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
		scrollPane_1.setViewportView(hostTable);
		hostsModel = (DefaultTableModel) hostTable.getModel(); 
		//hide confidential details columns
		 //hide confidential details columns
		showHide(hostTable.getColumn("Title"),0,0,0);
		showHide(hostTable.getColumn("Forename"),0,0,0);
		showHide(hostTable.getColumn("Surname"),0,0,0);
		showHide(hostTable.getColumn("Email"),0,0,0);
		showHide(hostTable.getColumn("Mobile"),0,0,0);
		
		JLabel lblHostInformation = new JLabel("Host information:");
		lblHostInformation.setFont(new Font("Arial", Font.BOLD, 20));
		lblHostInformation.setBounds(10, 321, 189, 47);
		guestFrame.getContentPane().add(lblHostInformation);
		
		JButton showHost = new JButton("show Host for selected booking");
		showHost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowIndex = bookingsTable.getSelectedRow();
				if(rowIndex >= 0) {
					int propertyID = (int) Integer.parseInt(String.valueOf(bookingsTable.getValueAt(rowIndex, 9)));	
					int guestID = (int) Integer.parseInt(String.valueOf(bookingsTable.getValueAt(rowIndex, 8)));
					
					Database.connectDB();
					if(HostActions.isAccepted(guestID)) {
						showHide(hostTable.getColumn("Title"),0,700,90);
						showHide(hostTable.getColumn("Forename"),0,700,90);
						showHide(hostTable.getColumn("Surname"),0,700,90);
						showHide(hostTable.getColumn("Email"),0,700,90);
						showHide(hostTable.getColumn("Mobile"),0,700,90);
					}
					refreshHosts();
					try {
						ResultSet result = guestActions.showHostDetails(propertyID);
						while(result.next()) {
							String t = result.getString("Title");
							String f = result.getString("Firstname");
							String s = result.getString("Surname");
							String em = result.getString("Email");
							String m = result.getString("Mobile");
							String u = result.getString("Username");
							
							String [] guests = {t,f,s,em,m,u};
							hostsModel.addRow(guests);
						}
						result.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					Database.disconnectDB();
				}
			}
		});
		showHost.setBounds(44, 379, 197, 23);
		guestFrame.getContentPane().add(showHost);
		
		JButton showBooking = new JButton("Show all bookings");
		showBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshBookings();
				Database.connectDB();
				showBookings(user,bookingsModel);
				Database.disconnectDB();
			}
		});
		showBooking.setBounds(716, 310, 132, 23);
		guestFrame.getContentPane().add(showBooking);
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.GRAY);
		guestFrame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmHome = new JMenuItem("Home");
		mnNewMenu.add(mntmHome);
		
		JMenuItem mntmProfile = new JMenuItem("Profile");
		mntmProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Profile profile = new Profile(user);
				profile.getFrame().setVisible(true);
				guestFrame.setVisible(false);
			}
		});
		mnNewMenu.add(mntmProfile);
		
		JMenuItem logout = new JMenuItem("LogOut");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lobbyPage lob = new lobbyPage();
				lob.getFrame().setVisible(true);
				guestFrame.setVisible(false);
			}
		});
		mnNewMenu.add(logout);
	}
}
