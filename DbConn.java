package rst_forum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DbConn {
	
	static Connection dbConnection() {
		
		String url = "jdbc:mysql://localhost:3306/abc";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, "root", "root");
			JOptionPane.showMessageDialog(null, "Connection Successful!");
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Connection Unsuccessful!");
			e.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] args) {
		dbConnection();

	}
}
