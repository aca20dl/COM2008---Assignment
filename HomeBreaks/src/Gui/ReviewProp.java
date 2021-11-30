package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;

import businessLogic.guestActions;
import classCode.*;
import database.*;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReviewProp {

	private JFrame reviewPropFrame;

	public JFrame getFrame() {
		return reviewPropFrame;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				//	ReviewProp window = new ReviewProp();
					//window.reviewPropFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ReviewProp(User user, int propertyID) {
		initialize(user,propertyID);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(User user, int propertyID) {
		reviewPropFrame = new JFrame();
		reviewPropFrame.setBounds(100, 100, 764, 600);
		reviewPropFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		reviewPropFrame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Review A Property");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setBounds(10, 11, 270, 57);
		reviewPropFrame.getContentPane().add(lblNewLabel);
		
		JLabel lblCommentYourStaisfaction = new JLabel("comment your satisfaction");
		lblCommentYourStaisfaction.setFont(new Font("Arial", Font.BOLD, 20));
		lblCommentYourStaisfaction.setBounds(227, 58, 255, 43);
		reviewPropFrame.getContentPane().add(lblCommentYourStaisfaction);
		
		JTextArea satisfaction = new JTextArea();
		satisfaction.setBounds(227, 112, 255, 57);
		reviewPropFrame.getContentPane().add(satisfaction);
		
		JLabel lblRateClean = new JLabel("Cleanliness:");
		lblRateClean.setFont(new Font("Arial", Font.BOLD, 20));
		lblRateClean.setBounds(227, 180, 167, 58);
		reviewPropFrame.getContentPane().add(lblRateClean);
		
		JComboBox cleanliness = new JComboBox();
		cleanliness.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		cleanliness.setBounds(438, 201, 44, 22);
		reviewPropFrame.getContentPane().add(cleanliness);
		
		JLabel lblRateCommunication = new JLabel("Communication:");
		lblRateCommunication.setFont(new Font("Arial", Font.BOLD, 20));
		lblRateCommunication.setBounds(227, 235, 214, 58);
		reviewPropFrame.getContentPane().add(lblRateCommunication);
		
		JComboBox communication = new JComboBox();
		communication.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		communication.setBounds(438, 256, 44, 22);
		reviewPropFrame.getContentPane().add(communication);
		
		JLabel lblRateClean_1_1 = new JLabel("Ease of check-in:");
		lblRateClean_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblRateClean_1_1.setBounds(227, 290, 167, 58);
		reviewPropFrame.getContentPane().add(lblRateClean_1_1);
		
		JComboBox checkIn = new JComboBox();
		checkIn.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		checkIn.setBounds(438, 311, 44, 22);
		reviewPropFrame.getContentPane().add(checkIn);
		
		JComboBox value = new JComboBox();
		value.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		value.setBounds(438, 475, 44, 22);
		reviewPropFrame.getContentPane().add(value);
		
		JLabel lblAccuracyOfDescription = new JLabel("Accuracy of info:");
		lblAccuracyOfDescription.setFont(new Font("Arial", Font.BOLD, 20));
		lblAccuracyOfDescription.setBounds(227, 344, 214, 58);
		reviewPropFrame.getContentPane().add(lblAccuracyOfDescription);
		
		JLabel lblRateClean_1_2 = new JLabel("Quality of location:");
		lblRateClean_1_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblRateClean_1_2.setBounds(227, 399, 214, 58);
		reviewPropFrame.getContentPane().add(lblRateClean_1_2);
		
		JComboBox location = new JComboBox();
		location.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		location.setBounds(438, 420, 44, 22);
		reviewPropFrame.getContentPane().add(location);
		
		JComboBox accuracy = new JComboBox();
		accuracy.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		accuracy.setBounds(438, 365, 44, 22);
		reviewPropFrame.getContentPane().add(accuracy);
		
		JLabel lblRateClean_1_1_1 = new JLabel("Value for Money:");
		lblRateClean_1_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblRateClean_1_1_1.setBounds(227, 454, 167, 58);
		reviewPropFrame.getContentPane().add(lblRateClean_1_1_1);
		
		JButton submit = new JButton("Submit Review");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String satisf = User.removeSemiColon(satisfaction.getText().trim());
				int clean = Integer.parseInt(cleanliness.getSelectedItem().toString());
				int comm = Integer.parseInt(communication.getSelectedItem().toString());
				int ch = Integer.parseInt(checkIn.getSelectedItem().toString());
				int acc = Integer.parseInt(accuracy.getSelectedItem().toString());
				int loc = Integer.parseInt(location.getSelectedItem().toString());
				int val = Integer.parseInt(value.getSelectedItem().toString());
				Review review = new Review (satisf,clean,comm,ch,acc,loc,val);
				// need guestID and property ID to review
				Database.connectDB();
				guestActions.addReview(user, propertyID, review);
				Database.disconnectDB();
				mainPageGuest guestPage = new mainPageGuest(user);
				guestPage.getFrame().setVisible(true);
				reviewPropFrame.setVisible(false);
			}
		});
		submit.setFont(new Font("Arial", Font.BOLD, 15));
		submit.setBounds(599, 520, 143, 23);
		reviewPropFrame.getContentPane().add(submit);
	}
}
