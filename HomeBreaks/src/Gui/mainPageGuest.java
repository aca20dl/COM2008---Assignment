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
import java.time.LocalDate;
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
	private DefaultTableModel addressModel;
	private int propertyID;
	private JTable addressTable;
	private String [] address;

	/**
	 * Launch the application.
	 */
	
	public void refreshHosts() {
		hostsModel.setRowCount(0);
	}
	
	public void refreshBookings() {
		bookingsModel.setRowCount(0);
	}
	
	public void refreshAddress() {
		addressModel.setRowCount(0);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainPageGuest window = new mainPageGuest(null);
					window.guestFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//shows and hides columns
	public static void showHide(TableColumn t, int min, int max, int pref) {
		t.setMinWidth(min);
		t.setMaxWidth(max);
		t.setPreferredWidth(pref);
	}
	
	//generates booking table 
	public static void showBookings(User user, DefaultTableModel bookingsModel ) {
		Database.connectDB();
		//clean finished bookings
		guestActions.cleanBookings();
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
		guestFrame.setBounds(100, 100, 894, 680);
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
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		});
		bookingsTable.getColumnModel().getColumn(4).setPreferredWidth(76);
		scrollPane.setViewportView(bookingsTable);
		bookingsModel = (DefaultTableModel) bookingsTable.getModel(); 
		showHide(bookingsTable.getColumn("Booking ID"),0,0,0);
		showHide(bookingsTable.getColumn("GuestID"),0,0,0);
		showHide(bookingsTable.getColumn("PropertyID"),0,0,0);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(46, 412, 481, 198);
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
				@Override
			    public boolean isCellEditable(int row, int column) {
			       return false;
			    }
			});
		scrollPane_1.setViewportView(hostTable);
		hostsModel = (DefaultTableModel) hostTable.getModel();
		
		JLabel notAccepted = new JLabel("Booking not yet Accepted");
		notAccepted.setForeground(Color.RED);
		notAccepted.setFont(new Font("Arial", Font.BOLD, 15));
		notAccepted.setBounds(541, 373, 189, 23);
		guestFrame.getContentPane().add(notAccepted);
		notAccepted.setVisible(false);
		
		JLabel acceptedBooking = new JLabel("Booking is accepted");
		acceptedBooking.setForeground(Color.GREEN);
		acceptedBooking.setFont(new Font("Arial", Font.BOLD, 15));
		acceptedBooking.setBounds(538, 344, 163, 18);
		guestFrame.getContentPane().add(acceptedBooking);
		acceptedBooking.setVisible(false);
		
		JButton subReview = new JButton("Submit Review");
		subReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReviewProp rev = new ReviewProp(user ,propertyID);
				rev.getFrame().setVisible(true);
				guestFrame.setVisible(false);
			}
		});
		subReview.setBounds(298, 378, 123, 23);
		guestFrame.getContentPane().add(subReview);
		subReview.setVisible(false);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(548, 412, 300, 198);
		guestFrame.getContentPane().add(scrollPane_2);
		scrollPane_2.setVisible(false);
		
		addressTable = new JTable();
		addressTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"House No.", "Steet", "City", "Post Code"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_2.setViewportView(addressTable);
		addressTable.setVisible(false);
		
		JButton showHost = new JButton("Show Details");
		showHost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowIndex = bookingsTable.getSelectedRow();
				if(rowIndex >= 0) {
					propertyID = (int) Integer.parseInt(String.valueOf(bookingsTable.getValueAt(rowIndex, 9)));
					int guestID = (int) Integer.parseInt(String.valueOf(bookingsTable.getValueAt(rowIndex, 8)));
					int bookingID = (int) Integer.parseInt(String.valueOf(bookingsTable.getValueAt(rowIndex, 0)));
					LocalDate end = LocalDate.parse( String.valueOf(bookingsTable.getValueAt(rowIndex, 2)));
					
					Database.connectDB();
					// hide private details column if not accepted
					if(HostActions.isAccepted(bookingID)) {
						showHide(hostTable.getColumn("Title"),0,700,90);
						showHide(hostTable.getColumn("Forename"),0,700,90);
						showHide(hostTable.getColumn("Surname"),0,700,90);
						showHide(hostTable.getColumn("Email"),0,700,90);
						showHide(hostTable.getColumn("Mobile"),0,700,90);
						acceptedBooking.setVisible(true);
						notAccepted.setVisible(false);
						
						//if accepted show property address
						// fill address table
						addressModel = (DefaultTableModel) addressTable.getModel();
						address = Houses.getAddress(propertyID);
						addressModel.addRow(address);
						addressTable.setVisible(true);
						scrollPane_2.setVisible(true);
						
						
						// if accepted and current date is after end date, user can submit review
						if(LocalDate.now().isAfter(end)) {
							// and guest hasnt already made review
							if(!guestActions.reviewExists(guestID, propertyID))
								subReview.setVisible(true);
						}
					}
					else {
						showHide(hostTable.getColumn("Title"),0,0,0);
						showHide(hostTable.getColumn("Forename"),0,0,0);
						showHide(hostTable.getColumn("Surname"),0,0,0);
						showHide(hostTable.getColumn("Email"),0,0,0);
						showHide(hostTable.getColumn("Mobile"),0,0,0);
						acceptedBooking.setVisible(false);
						notAccepted.setVisible(true);
						subReview.setVisible(false);
						addressTable.setVisible(false);
						scrollPane_2.setVisible(false);
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
		
		JButton showBooking = new JButton("Show Bookings");
		showBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshBookings();
				showBookings(user,bookingsModel);
			}
		});
		showBooking.setBounds(642, 310, 206, 23);
		guestFrame.getContentPane().add(showBooking);
		
		JButton showProp = new JButton("View Property");
		showProp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowIndex = bookingsTable.getSelectedRow();
				if(rowIndex >= 0) {
					int propertyID = (int) Integer.parseInt(String.valueOf(bookingsTable.getValueAt(rowIndex, 9)));
					showProp show = new showProp(user, propertyID);
					show.getFrame().setVisible(true);
				}
				
			}
		});
		showProp.setBounds(46, 328, 114, 23);
		guestFrame.getContentPane().add(showProp);
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.GRAY);
		guestFrame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem search = new JMenuItem("Search");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Search searchPage = new Search(user);
				guestFrame.setVisible(false);
				searchPage.getFrame().setVisible(true);
			}
		});
		mnNewMenu.add(search);
		
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
