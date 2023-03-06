package frames;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import DBConnector.DBConnector;

import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.MouseAdapter;

public class LoginFrame extends JFrame {

	private JPanel contentPane;

	JLabel userLabel;
	JLabel passwordLabel;
	JTextField userTextField;
	JPasswordField passwordField;
	JButton loginButton;
	JCheckBox showPassword;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame(0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param userID 
	 */
	public LoginFrame(int userID) {
		try { 
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		    e.printStackTrace();
		}
		userLabel = new JLabel("USERNAME");
		passwordLabel = new JLabel("PASSWORD");
		userTextField = new JTextField();
		passwordField = new JPasswordField();
		loginButton = new JButton("LOGIN");
		showPassword = new JCheckBox("Show Password");
		showPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (showPassword.isSelected()) {
					passwordField.setEchoChar((char) 0);
				} else {
					passwordField.setEchoChar('*');
				}
			}
		});

		ImageIcon icon = new ImageIcon("src/icon.png");

		setIconImage(icon.getImage());
		setTitle("Theatre Booking System V3");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(000, 000, 800, 800);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 800, 75);
		contentPane.add(panel);

		JLabel welcomeLabel = new JLabel();
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setBounds(0, 0, 777, 75);
		welcomeLabel.setBackground(SystemColor.menu);
		welcomeLabel.setFont(new Font("Arial", Font.BOLD, 46));
		welcomeLabel.setText("Login");
		panel.setLayout(null);
		panel.add(welcomeLabel);

		setLocationAndSize();
		addComponentsToPane(userID);

		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String userText;
				String pwdText;
				userText = userTextField.getText();
				pwdText = passwordField.getText();
				int count = -9999;
				int userID = 0;
					// check the database for the username and password
					DBConnector DBC = new DBConnector();
					DBC.connect();
					// decrypt password
					// pwdText =
					String query = "SELECT COUNT(*), user_id FROM users WHERE username='" + userText + "'"
							+ " AND password='" + pwdText + "'";
					ResultSet rs = DBC.runQuery(query);
					try {
						while (rs.next()) {
							count = Integer.parseInt(rs.getString(1));
							userID = Integer.parseInt(rs.getString(2));
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					DBC.close();

					JFrame frame = new JFrame("");
					// if matches details in the database
					if (count == 1) {
						if (userID == 1) {
							JOptionPane.showMessageDialog(frame, "Login Successful");
							dispose();
							AdminMenuFrame adframe = new AdminMenuFrame(1);
							adframe.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(frame, "Login Successful");
							dispose();
							BrowseFrame bframe = new BrowseFrame(userID);
							bframe.setVisible(true);
						}
					} else {
						JOptionPane.showMessageDialog(frame, "Invalid Username or Password");
					}

				}
				

		});
	}

	public void setLocationAndSize() {
		userLabel.setBounds(10, 86, 764, 30);
		passwordLabel.setBounds(10, 156, 100, 30);
		userTextField.setBounds(110, 86, 664, 30);
		passwordField.setBounds(110, 156, 664, 30);
		showPassword.setBounds(110, 186, 664, 30);
		loginButton.setBounds(246, 710, 528, 40);

	}

	public void addComponentsToPane(int userID) {
		contentPane.add(userLabel);
		contentPane.add(passwordLabel);
		contentPane.add(userTextField);
		contentPane.add(passwordField);
		contentPane.add(showPassword);
		contentPane.add(loginButton);

		JButton backButton = new JButton("GO BACK");
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				BrowseFrame bframe = new BrowseFrame(0);
				bframe.setVisible(true);
			}
		});
		backButton.setBounds(10, 710, 226, 40);
		contentPane.add(backButton);
		
		btnNewButton = new JButton("CREATE NEW USER");
		if (userID == 0) {
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				RegistrationFrame rframe = new RegistrationFrame(userID);
				rframe.setVisible(true);
				
				
			}
		});
		}
		btnNewButton.setBounds(10, 659, 226, 40);
		contentPane.add(btnNewButton);
	}
}
