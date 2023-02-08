
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DBConnector {
	private Connection conn;

	public DBConnector() {
		conn = null;
	}

	public void connect() {
		try {
			//Scanner s = new Scanner(new File("Credentials.txt"));
			String uname = "root";
			String pwd = "joee";
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/theatre", uname, pwd);
			} catch (SQLException e) {
			System.out.println("Connection failed.");
			e.printStackTrace();
			return;
		}
	}

	public ResultSet runQuery(String sql) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, // allows us to move forward and back
																				// in the ResultSet
					ResultSet.CONCUR_UPDATABLE);
			pst.execute();
			ResultSet results = pst.getResultSet();
			if (results != null) {
				if (results.last()) {
					results.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first
											// element
				}
			}
			return results;
		} catch (SQLException e) {
			System.out.println(sql + "\n failed to run.");
			e.printStackTrace();
			return null;
		}

	}

	public void close() {
		try {
			conn.close();
			System.out.println("Connection closed.");
		} catch (SQLException e) {
			System.out.println("Connection not closed.");
			e.printStackTrace();
		}
	}
}
