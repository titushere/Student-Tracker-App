# StudentTrackerApp
All java files used to create this project

package rst_forum;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.io.IOException;
import javax.swing.ImageIcon;

public class EmployeeTracker {
	
	public JFrame frmEmployeeTracker;
	private JTextField tf1;
	private JTextField tf2;
	private JTextField tf3;
	private JTextField tf4;
	private JTextField tf5;
	private JTextField tf;
	private JTable table;
	private JTable table_1;
	Connection conn;
	JRadioButton r1, r2, r3;
	JComboBox comboBox;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeTracker window = new EmployeeTracker();
					window.frmEmployeeTracker.setVisible(true);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	String getGender() {
		if(r1.isSelected()) {
			return "Female";
		}
		else if(r2.isSelected()) {
			return "Male";
		}
		else {
			return "Other";
		}
	}
	
	private void showData() {
		String q = "Select * from employee";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(q);
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	void fillComboBox() throws SQLException{
		String q = "select name from employee";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(q);
		while(rs.next()) {
			comboBox.addItem(rs.getString("name"));
		}
	}
	
	public EmployeeTracker() {
		conn = DbConn.dbConnection();
		initialize();
	}
	
	private void initialize() {
		frmEmployeeTracker = new JFrame();
		frmEmployeeTracker.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmEmployeeTracker.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\titus\\OneDrive\\Desktop\\Titus\\Pictures\\thumb-350-994023.png"));
		frmEmployeeTracker.setTitle("Employee Tracker");
		frmEmployeeTracker.setBounds(0, 0, 1540, 820);
		frmEmployeeTracker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEmployeeTracker.getContentPane().setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String q = "select * from employee where name=?";
				String vada = comboBox.getSelectedItem().toString();
				try {
					PreparedStatement ps = conn.prepareStatement(q);
					ps.setString(1, vada);
					ResultSet rs = ps.executeQuery();
					if(rs.next()) {
						tf1.setText(String.valueOf(rs.getInt("eid")));
						tf2.setText(rs.getString("name"));
						tf3.setText(rs.getString("dept"));
						tf4.setText(rs.getString("cname"));
						tf5.setText(String.valueOf(rs.getInt("salary")));
						
						String gender = rs.getString("gender");
						
						switch(gender) {
						case "Male":
							r1.setSelected(true);
							break;
						case "Female":
							r2.setSelected(true);
							break;
						default:
								r3.setSelected(true);
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		comboBox.setBounds(70, 48, 285, 35);
		frmEmployeeTracker.getContentPane().add(comboBox);
		
		tf1 = new JTextField();
		tf1.setBounds(123, 162, 232, 26);
		frmEmployeeTracker.getContentPane().add(tf1);
		tf1.setColumns(10);
		
		tf2 = new JTextField();
		tf2.setColumns(10);
		tf2.setBounds(123, 231, 232, 26);
		frmEmployeeTracker.getContentPane().add(tf2);
		
		tf3 = new JTextField();
		tf3.setColumns(10);
		tf3.setBounds(123, 298, 232, 26);
		frmEmployeeTracker.getContentPane().add(tf3);
		
		tf4 = new JTextField();
		tf4.setColumns(10);
		tf4.setBounds(123, 357, 232, 26);
		frmEmployeeTracker.getContentPane().add(tf4);
		
		tf5 = new JTextField();
		tf5.setColumns(10);
		tf5.setBounds(123, 415, 232, 26);
		frmEmployeeTracker.getContentPane().add(tf5);
		
		tf = new JTextField();
		tf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String q = "select * from employee where name=?";
				try {
					PreparedStatement ps = conn.prepareStatement(q);
					ps.setString(1, tf.getText());
					ResultSet rs = ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		tf.setBounds(856, 68, 477, 35);
		frmEmployeeTracker.getContentPane().add(tf);
		tf.setColumns(10);
		
		r1 = new JRadioButton("Female");
		r1.setForeground(new Color(255, 0, 255));
		r1.setBackground(Color.WHITE);
		r1.setBounds(208, 483, 66, 21);
		frmEmployeeTracker.getContentPane().add(r1);
		
		r2 = new JRadioButton("Male");
		r2.setForeground(Color.BLUE);
		r2.setBackground(Color.WHITE);
		r2.setBounds(123, 483, 66, 21);
		frmEmployeeTracker.getContentPane().add(r2);
		
		r3 = new JRadioButton("Others");
		r3.setForeground(Color.GRAY);
		r3.setBackground(Color.WHITE);
		r3.setBounds(289, 483, 66, 21);
		frmEmployeeTracker.getContentPane().add(r3);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(r1);bg.add(r2);bg.add(r3);
		
		JLabel lbl = new JLabel("Search Here");
		lbl.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lbl.setBounds(763, 75, 101, 35);
		frmEmployeeTracker.getContentPane().add(lbl);
		
		JLabel lbl1 = new JLabel("Emp ID");
		lbl1.setBounds(56, 175, 57, 13);
		frmEmployeeTracker.getContentPane().add(lbl1);
		
		JLabel lbl2 = new JLabel("Name");
		lbl2.setBounds(56, 244, 57, 13);
		frmEmployeeTracker.getContentPane().add(lbl2);
		
		JLabel lbl3 = new JLabel("Company");
		lbl3.setBounds(56, 311, 57, 13);
		frmEmployeeTracker.getContentPane().add(lbl3);
		
		JLabel lbl4 = new JLabel("Dept");
		lbl4.setBounds(56, 375, 57, 13);
		frmEmployeeTracker.getContentPane().add(lbl4);
		
		JLabel lbl5 = new JLabel("Salary");
		lbl5.setBounds(56, 428, 57, 13);
		frmEmployeeTracker.getContentPane().add(lbl5);
		
		JLabel lbl6 = new JLabel("Gender");
		lbl6.setBounds(56, 491, 61, 13);
		frmEmployeeTracker.getContentPane().add(lbl6);
		
		table = new JTable();
		table.setBounds(745, 174, 590, 476);
		frmEmployeeTracker.getContentPane().add(table);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String q = "Insert into employee values(?,?,?,?,?,?)";
				try {
					PreparedStatement ps = conn.prepareStatement(q);
					ps.setInt(1, Integer.parseInt(tf1.getText()));
					ps.setString(2, tf2.getText());
					ps.setString(3, tf3.getText());
					ps.setString(4, tf4.getText());
					ps.setInt(5, Integer.parseInt(tf5.getText()));
					ps.setString(6, getGender());
					
					ps.execute();
					
					JOptionPane.showMessageDialog(btnInsert, "Data entered successfully!");
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnInsert.setBackground(Color.LIGHT_GRAY);
		btnInsert.setForeground(Color.WHITE);
		btnInsert.setBounds(56, 556, 85, 21);
		frmEmployeeTracker.getContentPane().add(btnInsert);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String q = "delete from employee where eid=?";
				try {
					PreparedStatement ps = conn.prepareStatement(q);
					ps.setInt(1, Integer.parseInt(tf1.getText()));
					int opt = JOptionPane.showConfirmDialog(btnDelete, "Are you sure?");
					if(opt == 0) {
						ps.execute();
						showData();
					}
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBackground(Color.LIGHT_GRAY);
		btnDelete.setBounds(99, 594, 85, 21);
		frmEmployeeTracker.getContentPane().add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String q = "update employee set name=?,cname=?,dept=?,salary=?,gender=? where eid=?";
				try {
					PreparedStatement ps = conn.prepareStatement(q);
					ps.setInt(1, Integer.parseInt(tf1.getText()));
					ps.setString(2, tf2.getText());
					ps.setString(3, tf3.getText());
					ps.setString(4, tf4.getText());
					ps.setInt(5, Integer.parseInt(tf5.getText()));
					ps.setString(6, getGender());
					
					ps.execute();
					JOptionPane.showMessageDialog(btnUpdate, e);
					showData();
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setBackground(Color.LIGHT_GRAY);
		btnUpdate.setBounds(142, 629, 85, 21);
		frmEmployeeTracker.getContentPane().add(btnUpdate);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(table.print()) {
						JOptionPane.showMessageDialog(btnPrint, "Printing in progress!", "Success", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(btnPrint, "Printing abort!", "Failed", JOptionPane.ERROR_MESSAGE);
					}
				}catch(PrinterException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnPrint.setForeground(Color.WHITE);
		btnPrint.setBackground(Color.LIGHT_GRAY);
		btnPrint.setBounds(189, 670, 85, 21);
		frmEmployeeTracker.getContentPane().add(btnPrint);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showData();
			}
		});
		btnLoad.setBackground(Color.LIGHT_GRAY);
		btnLoad.setForeground(Color.WHITE);
		btnLoad.setBounds(232, 701, 85, 21);
		frmEmployeeTracker.getContentPane().add(btnLoad);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{"Emp ID", "Name", "Company", "Dept", "Salary", "Gender"},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		table_1.setBounds(745, 147, 590, 26);
		frmEmployeeTracker.getContentPane().add(table_1);
		
		JButton btnShutdown = new JButton("Shutdown");
		btnShutdown.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnShutdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String q = JOptionPane.showInputDialog(btnShutdown, "Enter time in seconds:");
				int t = Integer.parseInt(q);
				try {
					Runtime.getRuntime().exec("shutdown -s -t"+t);
				}catch(IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnShutdown.setBackground(Color.GRAY);
		btnShutdown.setForeground(Color.WHITE);
		btnShutdown.setBounds(1208, 670, 125, 35);
		frmEmployeeTracker.getContentPane().add(btnShutdown);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon("C:\\Users\\titus\\Dropbox\\My PC (LAPTOP-OVCE9221)\\Downloads\\Tracker-Header-Angle-01-1.png"));
		lblBackground.setBounds(0, 0, 1526, 783);
		frmEmployeeTracker.getContentPane().add(lblBackground);
		
		
		try {
			fillComboBox();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
}
