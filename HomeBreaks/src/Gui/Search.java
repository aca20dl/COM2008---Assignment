package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import classCode.*;
import businessLogic.*;
import database.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Search {

	private JFrame frame;
	private JTextField location;
	private JTable propertiesTable;
	private DefaultTableModel propertiesModel;
	private ArrayList<ChargeBand> bands;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Search window = new Search(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Create the application.
	 */
	public Search(User user) {
		initialize(user);
	}
	
	public void refreshProperties() {
		propertiesModel.setRowCount(0);
	}
	
	//generates properties table 
		public static void showProperties(String location, LocalDate start, LocalDate end, DefaultTableModel propertiesModel ) {
			Database.connectDB();
			try {
				ResultSet result = guestActions.getAllProps(location);
				while(result.next()) {
				int id = result.getInt("PropertyID");
				String name = result.getString("Name");
				String genLoc = result.getString("GenLocation");
				boolean available = guestActions.available(id, start, end);
				Object booking [] = {id,name,genLoc,available};
				
				propertiesModel.addRow(booking);
				
				}
				result.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			Database.disconnectDB();
		}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(User user) {
		frame = new JFrame();
		frame.setBounds(100, 100, 897, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Search for a property");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(222, 11, 418, 39);
		frame.getContentPane().add(lblNewLabel);
		
		location = new JTextField();
		location.setBounds(176, 97, 165, 20);
		frame.getContentPane().add(location);
		location.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Enter a location:");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBounds(10, 89, 178, 31);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Start Date:");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(10, 184, 109, 31);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JComboBox startDay = new JComboBox();
		startDay.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		startDay.setBounds(176, 184, 55, 22);
		frame.getContentPane().add(startDay);
		
		JComboBox startMonth = new JComboBox();
		startMonth.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		startMonth.setBounds(245, 184, 55, 22);
		frame.getContentPane().add(startMonth);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("End Date:");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_1_1.setBounds(10, 226, 109, 31);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		JComboBox endDay = new JComboBox();
		endDay.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		endDay.setBounds(176, 226, 55, 22);
		frame.getContentPane().add(endDay);
		
		JComboBox endMonth = new JComboBox();
		endMonth.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		endMonth.setBounds(245, 226, 55, 22);
		frame.getContentPane().add(endMonth);
		
		JLabel lblNewLabel_2 = new JLabel("Day");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(176, 147, 48, 26);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Month");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_1.setBounds(245, 147, 55, 26);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(397, 97, 478, 394);
		frame.getContentPane().add(scrollPane);
		
		propertiesTable = new JTable();
		propertiesTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"PropertyID", "Name", "General Location", "Available"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		propertiesTable.getColumnModel().getColumn(0).setResizable(false);
		propertiesTable.getColumnModel().getColumn(1).setResizable(false);
		propertiesTable.getColumnModel().getColumn(2).setResizable(false);
		propertiesTable.getColumnModel().getColumn(2).setPreferredWidth(95);
		propertiesTable.getColumnModel().getColumn(3).setResizable(false);
		scrollPane.setViewportView(propertiesTable);
		propertiesModel = (DefaultTableModel) propertiesTable.getModel(); 
		
		JButton showProperty = new JButton("Show Property Details");
		showProperty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowIndex = propertiesTable.getSelectedRow();
				if(rowIndex >= 0) {
					int propertyID = (int) Integer.parseInt(String.valueOf(propertiesTable.getValueAt(rowIndex, 0)));
					showProp show = new showProp(user, propertyID);
					show.getFrame().setVisible(true);
				}
			}
		});
		showProperty.setBounds(22, 413, 165, 23);
		frame.getContentPane().add(showProperty);
		
		JLabel exists = new JLabel("You have already made a booking for this property");
		exists.setForeground(Color.GRAY);
		exists.setFont(new Font("Arial", Font.BOLD, 15));
		exists.setBounds(438, 55, 418, 31);
		frame.getContentPane().add(exists);
		exists.setVisible(false);
		
		JLabel unavailable = new JLabel("property Unavailable");
		unavailable.setHorizontalAlignment(SwingConstants.CENTER);
		unavailable.setForeground(Color.GRAY);
		unavailable.setFont(new Font("Arial", Font.BOLD, 15));
		unavailable.setBounds(136, 365, 251, 31);
		frame.getContentPane().add(unavailable);
		unavailable.setVisible(false);
		
		JButton bookProperty = new JButton("Book Property");
		bookProperty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				unavailable.setVisible(false);
				exists.setVisible(false);
				int rowIndex = propertiesTable.getSelectedRow();
				if(rowIndex >= 0) {
					int propertyID = (int) Integer.parseInt(String.valueOf(propertiesTable.getValueAt(rowIndex, 0)));
					boolean available = Boolean.parseBoolean(String.valueOf(propertiesTable.getValueAt(rowIndex, 3)));
					//bands = Houses.getBands(propertyID);
					int startd = Integer.parseInt(startDay.getSelectedItem().toString());
					int startm = Integer.parseInt(startMonth.getSelectedItem().toString());
					
					int endd = Integer.parseInt(endDay.getSelectedItem().toString());
					int endm = Integer.parseInt(endMonth.getSelectedItem().toString());
					
					String loc = location.getText().trim();
					
					mainPageGuest.showHide(propertiesTable.getColumn("PropertyID"), 0, 0, 0);
					if(ListProp.validDate(2022,startm,startd) && 
							ListProp.validDate(2022,endm,endd)) {
						// if date is valid make a booking
						// insert the booking if property is available
						if(available) {
							Database.connectDB();
							if(guestActions.bookingExists(user, propertyID)) {
								bands = Houses.getBands(propertyID);
								Booking booking = Booking.makeBooking(LocalDate.of(2022, startm, startd), LocalDate.of(2022, endm, endd), bands);
								guestActions.addBooking(user, propertyID, booking);
							}
							else {
								exists.setVisible(true);
								unavailable.setVisible(false);
							}
							Database.disconnectDB();
						}
						else {
							unavailable.setVisible(true);
							exists.setVisible(false);
						}
					}
					
				}
			}
		});
		bookProperty.setBounds(210, 413, 131, 23);
		frame.getContentPane().add(bookProperty);
		
		JButton searchB = new JButton("Search");
		searchB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int startd = Integer.parseInt(startDay.getSelectedItem().toString());
				int startm = Integer.parseInt(startMonth.getSelectedItem().toString());
				
				int endd = Integer.parseInt(endDay.getSelectedItem().toString());
				int endm = Integer.parseInt(endMonth.getSelectedItem().toString());
				
				String loc = location.getText().trim();
				
				mainPageGuest.showHide(propertiesTable.getColumn("PropertyID"), 0, 0, 0);
				if(ListProp.validDate(2022,startm,startd) && 
						ListProp.validDate(2022,endm,endd)) {
					refreshProperties();
					showProperties(location.getText(),LocalDate.of(2022, startm, startd),LocalDate.of(2022, endm, endd),propertiesModel);
				}
				else {
					//show invalid date error
				}
			}
		});
		searchB.setFont(new Font("Arial", Font.BOLD, 15));
		searchB.setBounds(10, 287, 93, 23);
		frame.getContentPane().add(searchB);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user == null) {
					lobbyPage lobby = new lobbyPage();
					lobby.getFrame().setVisible(true);
				}
				else {
					mainPageGuest guestPage = new mainPageGuest(user);
					guestPage.getFrame().setVisible(true);
				}
				frame.setVisible(false);
			}
		});
		btnGoBack.setBounds(22, 468, 165, 23);
		frame.getContentPane().add(btnGoBack);
		
		if(user == null) {
			bookProperty.setVisible(false);
		}
	}
}
