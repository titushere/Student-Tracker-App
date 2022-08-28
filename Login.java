package rst_forum;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;

public class Login {

	JFrame frmEmployeetracker;
	Connection conn;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmEmployeetracker.setVisible(true);
				}catch(Exception e) {
					e.printStackTrace();
				}	
			}
		});
	}

	public Login() {
		conn = DbConn.dbConnection();
		initialize();
	}

	private void initialize() {
		frmEmployeetracker = new JFrame();
		frmEmployeetracker.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmEmployeetracker.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\titus\\OneDrive\\Desktop\\Titus\\Pictures\\medium-athah-anime-naruto-madara-uchiha-13-19-inches-wall-poster-original-imaf9h6wzf2zu6y6.jpeg"));
		frmEmployeetracker.setTitle("Employee Tracker Login");
		frmEmployeetracker.setBounds(500, 150, 862, 500);
		frmEmployeetracker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEmployeetracker.getContentPane().setLayout(null);
		
		JTextField tf = new JTextField();
		tf.setBounds(536, 125, 265, 30);
		tf.setColumns(10);
		frmEmployeetracker.getContentPane().add(tf);
		
		JTextField pf = new JTextField();
		pf.setBounds(536, 205, 265, 30);
		pf.setColumns(10);
		frmEmployeetracker.getContentPane().add(pf);
		
		JLabel lbl = new JLabel("Username");
		lbl.setForeground(Color.WHITE);
		lbl.setBounds(536, 105, 265, 16);
		lbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		frmEmployeetracker.getContentPane().add(lbl);
		
		JLabel lbl2 = new JLabel("Password");
		lbl2.setForeground(Color.WHITE);
		lbl2.setBounds(536, 187, 265, 16);
		lbl2.setFont(new Font("Tahoma", Font.BOLD, 13));
		frmEmployeetracker.getContentPane().add(lbl2);
		
		JButton button = new JButton("Login");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String q = "Select * from login where username=? and password=?";
				try {
					PreparedStatement ps = conn.prepareStatement(q);
					ps.setString(1, tf.getText());
					ps.setString(2, String.valueOf(pf.getText()));
					ResultSet rs = ps.executeQuery();
					if(rs.next()) {
						JOptionPane.showMessageDialog(button, "Welcome to Employee Tracker!");
						EmployeeTracker window = new EmployeeTracker();
						window.frmEmployeeTracker.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(button, "Invalid username or password!");
					}
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		button.setForeground(Color.WHITE);
		button.setBackground(Color.BLUE);
		button.setBounds(536, 277, 265, 30);
		frmEmployeetracker.getContentPane().add(button);
		
		JLabel lbl3 = new JLabel("SignUp if not a registered user!");
		lbl3.setBounds(568, 325, 233, 16);
		lbl3.setForeground(Color.WHITE);
		lbl3.setFont(new Font("Tahoma", Font.BOLD, 13));
		frmEmployeetracker.getContentPane().add(lbl3);
		
		JButton btnSignUp = new JButton("Register here!");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUp window = new SignUp();
				window.frmSignup.setVisible(true);
			}
		});
		btnSignUp.setBackground(new Color(0, 0, 255));
		btnSignUp.setForeground(Color.WHITE);
		btnSignUp.setBounds(580, 351, 164, 21);
		frmEmployeetracker.getContentPane().add(btnSignUp);
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setIcon(new ImageIcon("C:\\Users\\titus\\Dropbox\\My PC (LAPTOP-OVCE9221)\\Downloads\\desktop-pc-hacker-security.jpg"));
		lblBackground.setBounds(0, 0, 848, 463);
		frmEmployeetracker.getContentPane().add(lblBackground);
		
	}
}
