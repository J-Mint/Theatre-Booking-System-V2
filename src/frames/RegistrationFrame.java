package frames;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistrationFrame extends JFrame {

	private JPanel contentPane;
	private JLabel userLabel;
	private JLabel passwordLabel;
	private JTextField userTextField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JCheckBox showPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationFrame frame = new RegistrationFrame(0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegistrationFrame(int userID) {
		try { 
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		    e.printStackTrace();
		}
		userLabel = new JLabel("SET USERNAME");
		passwordLabel = new JLabel("SET PASSWORD");
		userTextField = new JTextField();
		passwordField = new JPasswordField();
		loginButton = new JButton("CREATE USER\r\n");
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
		welcomeLabel.setText("Register");
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
					// check the database for the username and password
					DBConnector DBC = new DBConnector();
					DBC.connect();
					// decrypt password
					// pwdText =
					String query = "INSERT INTO users (username, password) VALUES ('"+userText+"', '"+pwdText+"')";
					DBC.runQuery(query);
					DBC.close();
					JOptionPane.showMessageDialog(contentPane, "Registration Successful");
					dispose();
					LoginFrame lframe = new LoginFrame(userID);
					lframe.setVisible(true);


				}

	});}

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
				LoginFrame lframe = new LoginFrame(userID);
				lframe.setVisible(true);
			}
		});

		backButton.setBounds(10, 710, 226, 40);
		contentPane.add(backButton);
	}
}