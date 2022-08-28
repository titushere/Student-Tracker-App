package rst_forum;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class SignUp {
	
	JFrame frmSignup;
	Connection conn;
	private JTextField tf;
	private JTextField pf;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp window = new SignUp();
					window.frmSignup.setVisible(true);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SignUp() {
		conn = DbConn.dbConnection();
		initialize();
	}
	
	private void initialize() {
		frmSignup = new JFrame();
		frmSignup.setTitle("SignUp");
		frmSignup.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmSignup.getContentPane().setForeground(Color.WHITE);
		frmSignup.setBounds(500, 150, 450, 500);
		frmSignup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSignup.getContentPane().setLayout(null);
		
		JLabel lbl1 = new JLabel("Username:");
		lbl1.setForeground(Color.WHITE);
		lbl1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl1.setBounds(91, 115, 88, 34);
		frmSignup.getContentPane().add(lbl1);
		
		tf = new JTextField();
		tf.setBounds(223, 124, 129, 19);
		frmSignup.getContentPane().add(tf);
		tf.setColumns(10);
		
		JLabel lbl2 = new JLabel("Password:");
		lbl2.setForeground(Color.WHITE);
		lbl2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl2.setBounds(91, 198, 88, 34);
		frmSignup.getContentPane().add(lbl2);
		
		pf = new JTextField();
		pf.setColumns(10);
		pf.setBounds(223, 207, 129, 19);
		frmSignup.getContentPane().add(pf);
		
		JLabel lbl3 = new JLabel("Note: Create a strong password");
		lbl3.setForeground(new Color(30, 144, 255));
		lbl3.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lbl3.setBounds(146, 393, 163, 19);
		frmSignup.getContentPane().add(lbl3);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String q = "insert into login values(?,?)";
				try {
					PreparedStatement ps = conn.prepareStatement(q);
					ps.setString(1, tf.getText());
					ps.setString(2, String.valueOf(pf.getText()));
					ps.execute();
					JOptionPane.showMessageDialog(btnRegister, "Registered successfully!");
					Login window = new Login();
					window.frmEmployeetracker.setVisible(true);
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnRegister.setBackground(Color.BLUE);
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRegister.setBounds(163, 312, 95, 23);
		frmSignup.getContentPane().add(btnRegister);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon("C:\\Users\\titus\\Dropbox\\My PC (LAPTOP-OVCE9221)\\Downloads\\page-background-default.jpg"));
		lblBackground.setBounds(0, 0, 436, 463);
		frmSignup.getContentPane().add(lblBackground);
		
	}
}
