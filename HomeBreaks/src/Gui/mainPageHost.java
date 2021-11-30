package Gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

import businessLogic.HostActions;
import classCode.*;
import database.Database;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.*;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

public class mainPageHost {

	private JFrame hostFrame;
	private JTable bookingsTable;
	private JTable guestTable;
	private DefaultTableModel guestsModel;
	DefaultTableModel bookingsModel;

	/**
	 * Launch the application.
	 */
	public void refreshGuests() {
		guestsModel.setRowCount(0);
	}
	
	public void refreshBookings() {
		bookingsModel.setRowCount(0);
	}
	
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
	
	public void showHide(TableColumn t, int min, int max, int pref) {
		t.setMinWidth(min);
		t.setMaxWidth(max);
		t.setPreferredWidth(pref);
	}
	
	public static void showBookings(User user, DefaultTableModel bookingsModel ) {
		Database.connectDB();
		try {
			ResultSet result = HostActions.showBookings(user);
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
		scrollPane.setBounds(20, 81, 767, 218);
		hostFrame.getContentPane().add(scrollPane);
		
		bookingsTable = new JTable();
		bookingsTable.setSurrendersFocusOnKeystroke(true);
		bookingsTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		bookingsTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Booking ID", "Start Date", "End Date", "No. Nights", "Cost per night", "Service Cost", "Cleaning Cost", "Total Charge", "GuestID", "PropertyID"
			}
		)
		{
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, Object.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			
			  @Override
			    public boolean isCellEditable(int row, int column) {
			       return false;
			    }
		});
		
		bookingsTable.getColumnModel().getColumn(2).setPreferredWidth(76);
		scrollPane.setViewportView(bookingsTable);
		bookingsModel = (DefaultTableModel) bookingsTable.getModel();
		
		
		
		showHide(bookingsTable.getColumn("Booking ID"),0,0,0);
		showHide(bookingsTable.getColumn("GuestID"),0,0,0);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 410, 610, 146);
		hostFrame.getContentPane().add(scrollPane_1);
		
		guestTable = new JTable();
		guestTable.setModel(new DefaultTableModel(
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
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		});
		scrollPane_1.setViewportView(guestTable);
		guestsModel = (DefaultTableModel) guestTable.getModel();
		 
		 //hide confidential details columns
		 
		 
		JButton accept = new JButton("Accept Selected Request");
		accept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowIndex = bookingsTable.getSelectedRow();
				if(rowIndex >= 0) {
					int bookingID = (int) Integer.parseInt(String.valueOf(bookingsTable.getValueAt(rowIndex, 0)));
					refreshBookings();
					Database.connectDB();
					HostActions.acceptBooking(bookingID);
					showBookings(user,bookingsModel);
					Database.disconnectDB();
				}
			}
		});
		accept.setBounds(574, 344, 213, 23);
		hostFrame.getContentPane().add(accept);
		
		JLabel acceptedBooking = new JLabel("Accepted Booking");
		acceptedBooking.setForeground(Color.GREEN);
		acceptedBooking.setFont(new Font("Arial", Font.BOLD, 15));
		acceptedBooking.setBounds(669, 411, 163, 18);
		hostFrame.getContentPane().add(acceptedBooking);
		acceptedBooking.setVisible(false);
		
		JLabel notAccepted = new JLabel("Not Accepted");
		notAccepted.setForeground(Color.RED);
		notAccepted.setFont(new Font("Arial", Font.BOLD, 15));
		notAccepted.setBounds(669, 458, 163, 23);
		hostFrame.getContentPane().add(notAccepted);
		notAccepted.setVisible(false);
		
		JButton showGuest = new JButton("show Guest Details");
		showGuest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowIndex = bookingsTable.getSelectedRow();
				if(rowIndex >= 0) {
					int guestID = (int) Integer.parseInt(String.valueOf(bookingsTable.getValueAt(rowIndex, 8)));
					int bookingID = (int) Integer.parseInt(String.valueOf(bookingsTable.getValueAt(rowIndex, 0)));
					//show confidential details if paired
					Database.connectDB();
					if(HostActions.isAccepted(bookingID)) {
						showHide(guestTable.getColumn("Title"),0,700,90);
						showHide(guestTable.getColumn("Forename"),0,700,90);
						showHide(guestTable.getColumn("Surname"),0,700,90);
						showHide(guestTable.getColumn("Email"),0,700,90);
						showHide(guestTable.getColumn("Mobile"),0,700,90);
						acceptedBooking.setVisible(true);
						notAccepted.setVisible(false);
					}
					else {
						showHide(guestTable.getColumn("Title"),0,0,0);
						showHide(guestTable.getColumn("Forename"),0,0,0);
						showHide(guestTable.getColumn("Surname"),0,0,0);
						showHide(guestTable.getColumn("Email"),0,0,0);
						showHide(guestTable.getColumn("Mobile"),0,0,0);
						notAccepted.setVisible(true);
						acceptedBooking.setVisible(false);
					}
					
					refreshGuests();
					try {
						ResultSet result = HostActions.showGuestDetails(guestID);
						while(result.next()) {
							String t = result.getString("Title");
							String f = result.getString("Firstname");
							String s = result.getString("Surname");
							String em = result.getString("Email");
							String m = result.getString("Mobile");
							String u = result.getString("Username");
							
							String [] guests = {t,f,s,em,m,u};
							guestsModel.addRow(guests);
						}
						result.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					Database.disconnectDB();
				}
			}
		});
		showGuest.setBounds(10, 376, 129, 23);
		hostFrame.getContentPane().add(showGuest);
		
		JButton showBooking = new JButton("Show All Bookings");
		showBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshBookings();
				Database.connectDB();
				showBookings(user,bookingsModel);
				Database.disconnectDB();
			}
		});
		showBooking.setBounds(574, 310, 213, 23);
		hostFrame.getContentPane().add(showBooking);
		
		
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
