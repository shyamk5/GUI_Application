package com.nit.guibuilder;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUIBuilder_ScrollFrame {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	private static final String SELECT_STUDENT_QUERY = "SELECT STID,SNAME,SADD,SAVG FROM STUDENT ORDER BY STID";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIBuilder_ScrollFrame window = new GUIBuilder_ScrollFrame();
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
	public GUIBuilder_ScrollFrame() {
		initialize();
		initializeJDBC();
	}
	
	private void initializeJDBC() {
		try {
			//Establish the Connection object
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","IMSHYAM","URVIL");
			//Create PreparedSTatement object
			ps = con.prepareStatement(SELECT_STUDENT_QUERY,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			//execute the query
			rs = ps.executeQuery();
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//initializeJDBC
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					if(rs != null) {
						rs.close();
					}
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				try {
					if(ps != null) {
						ps.close();
					}
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				try {
					if(con != null) {
						con.close();
					}
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
			}//windowClosing
		});
		frame.setBounds(100, 100, 700, 547);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student ID");
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		lblNewLabel.setBounds(163, 33, 104, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblStudentName = new JLabel("Student Name");
		lblStudentName.setFont(new Font("Serif", Font.PLAIN, 18));
		lblStudentName.setBounds(163, 84, 136, 25);
		frame.getContentPane().add(lblStudentName);
		
		JLabel lblStudentAddress = new JLabel("Student Address");
		lblStudentAddress.setFont(new Font("Serif", Font.PLAIN, 18));
		lblStudentAddress.setBounds(163, 134, 136, 25);
		frame.getContentPane().add(lblStudentAddress);
		
		JLabel lblStudentMarks = new JLabel("Student Marks");
		lblStudentMarks.setFont(new Font("Serif", Font.PLAIN, 18));
		lblStudentMarks.setBounds(163, 188, 136, 25);
		frame.getContentPane().add(lblStudentMarks);
		
		textField = new JTextField();
		textField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		textField.setBounds(352, 33, 200, 25);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(352, 84, 200, 25);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		textField_2.setColumns(10);
		textField_2.setBounds(352, 134, 200, 25);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("SansSerif", Font.PLAIN, 16));
		textField_3.setColumns(10);
		textField_3.setBounds(352, 188, 200, 25);
		frame.getContentPane().add(textField_3);
		
		JButton btnNewButton = new JButton("First");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					rs.first();
					textField.setText(rs.getString(1));
					textField_1.setText(rs.getString(2));
					textField_2.setText(rs.getString(3));
					textField_3.setText(rs.getString(4));
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
			}//actionPerformed
		});
		btnNewButton.setBackground(new Color(128, 255, 128));
		btnNewButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnNewButton.setBounds(53, 300, 104, 33);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnLast = new JButton("Last");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					rs.last();
					textField.setText(rs.getString(1));
					textField_1.setText(rs.getString(2));
					textField_2.setText(rs.getString(3));
					textField_3.setText(rs.getString(4));
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
			}//actionPerformed
		});
		btnLast.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnLast.setBackground(new Color(128, 255, 128));
		btnLast.setBounds(210, 300, 104, 33);
		frame.getContentPane().add(btnLast);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!rs.isLast()) {
						rs.next();
						textField.setText(rs.getString(1));
						textField_1.setText(rs.getString(2));
						textField_2.setText(rs.getString(3));
						textField_3.setText(rs.getString(4));
					}//if
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
			}//actionPerformed
		});
		btnNext.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnNext.setBackground(new Color(128, 255, 128));
		btnNext.setBounds(368, 300, 104, 33);
		frame.getContentPane().add(btnNext);
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!rs.isFirst()) {
						rs.previous();
						textField.setText(rs.getString(1));
						textField_1.setText(rs.getString(2));
						textField_2.setText(rs.getString(3));
						textField_3.setText(rs.getString(4));
					}//if
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
			}
		});
		btnPrevious.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnPrevious.setBackground(new Color(128, 255, 128));
		btnPrevious.setBounds(514, 300, 104, 33);
		frame.getContentPane().add(btnPrevious);
	}
}
