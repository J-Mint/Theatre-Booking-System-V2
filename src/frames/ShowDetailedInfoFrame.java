package frames;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import DBConnector.*;

public class ShowDetailedInfoFrame extends JFrame {

	private JPanel contentPane;
	private int performanceID, userID;

	/**
	 * Create the frame.
	 */
	public ShowDetailedInfoFrame(int performanceID, int userID) {
		this.performanceID = performanceID;
		this.userID = userID;

		loadUIStyle();
		loadImageIcon();
		configureFrame();
		configureContentPane();
		configureHeaderPanel();
		configureBodyPanel();

		// centre application on screen
		setLocationRelativeTo(null);

	}

	private void configureBodyPanel() {
		JPanel bodyPanel = new JPanel();
		bodyPanel.setBounds(0, 77, 784, 684);
		contentPane.add(bodyPanel);
		bodyPanel.setLayout(null);

		DBConnector DBC = new DBConnector();
		DBC.connect();
		String query = "SELECT performance_ID, show_name, type, description, date, stage_time, duration, circle_price, stall_price, language, has_live_music, performers FROM performances JOIN shows on performances.show_id = shows.show_id WHERE performance_id ="
				+ performanceID;
		ResultSet rs = DBC.runQuery(query);
		String row[] = new String[12];
		try {
			while (rs.next()) {
				for (int i = 0; i < 12; i++) {
					row[i] = rs.getString(i + 1);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String query1 = "SELECT COUNT(*) FROM seats WHERE performance_id=" + performanceID + " AND seat_booked=false";
		ResultSet rs1 = DBC.runQuery(query1);
		String seatsAvailable = null;
		try {
			while (rs1.next()) {
				seatsAvailable = rs1.getString(1);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		DBC.close();

		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setFont(new Font("Arial", Font.PLAIN, 24));
		lblTitle.setBounds(42, 58, 123, 28);
		bodyPanel.add(lblTitle);

		JLabel lblTitle1 = new JLabel(row[1]);
		lblTitle1.setFont(new Font("Arial", Font.PLAIN, 24));
		lblTitle1.setBounds(240, 58, 123, 28);
		bodyPanel.add(lblTitle1);

		JLabel lblType = new JLabel("Type:");
		lblType.setFont(new Font("Arial", Font.PLAIN, 24));
		lblType.setBounds(42, 97, 123, 28);
		bodyPanel.add(lblType);

		JLabel lblType1 = new JLabel(row[2]);
		lblType1.setFont(new Font("Arial", Font.PLAIN, 24));
		lblType1.setBounds(240, 97, 123, 28);
		bodyPanel.add(lblType1);

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Arial", Font.PLAIN, 24));
		lblDescription.setBounds(42, 136, 136, 28);
		bodyPanel.add(lblDescription);

		JLabel lblDescription1 = new JLabel(row[3]);
		lblDescription1.setFont(new Font("Arial", Font.PLAIN, 24));
		lblDescription1.setBounds(240, 136, 123, 28);
		bodyPanel.add(lblDescription1);

		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Arial", Font.PLAIN, 24));
		lblDate.setBounds(42, 175, 136, 28);
		bodyPanel.add(lblDate);

		JLabel lblDate1 = new JLabel(row[4]);
		lblDate1.setFont(new Font("Arial", Font.PLAIN, 24));
		lblDate1.setBounds(240, 175, 123, 28);
		bodyPanel.add(lblDate1);

		JLabel lblStageTime = new JLabel("Stage Time:");
		lblStageTime.setFont(new Font("Arial", Font.PLAIN, 24));
		lblStageTime.setBounds(42, 214, 136, 28);
		bodyPanel.add(lblStageTime);

		JLabel lblStageTime1 = new JLabel(row[5]);
		lblStageTime1.setFont(new Font("Arial", Font.PLAIN, 24));
		lblStageTime1.setBounds(240, 214, 123, 28);
		bodyPanel.add(lblStageTime1);

		JLabel lblDuration = new JLabel("Duration:");
		lblDuration.setFont(new Font("Arial", Font.PLAIN, 24));
		lblDuration.setBounds(42, 253, 136, 28);
		bodyPanel.add(lblDuration);

		JLabel lblDuration1 = new JLabel(row[6]);
		lblDuration1.setFont(new Font("Arial", Font.PLAIN, 24));
		lblDuration1.setBounds(240, 253, 123, 28);
		bodyPanel.add(lblDuration1);

		JLabel lblSeatAvailability = new JLabel("Seats Available:");
		lblSeatAvailability.setFont(new Font("Arial", Font.PLAIN, 24));
		lblSeatAvailability.setBounds(42, 292, 184, 28);
		bodyPanel.add(lblSeatAvailability);

		JLabel lblSeatAvailability1 = new JLabel(seatsAvailable);
		lblSeatAvailability1.setFont(new Font("Arial", Font.PLAIN, 24));
		lblSeatAvailability1.setBounds(240, 292, 184, 28);
		bodyPanel.add(lblSeatAvailability1);

		JLabel lblStallPrice = new JLabel("Stall Price :£");
		lblStallPrice.setFont(new Font("Arial", Font.PLAIN, 24));
		lblStallPrice.setBounds(42, 331, 159, 28);
		bodyPanel.add(lblStallPrice);

		JLabel lblStallPrice1 = new JLabel(row[8]);
		lblStallPrice1.setFont(new Font("Arial", Font.PLAIN, 24));
		lblStallPrice1.setBounds(240, 331, 123, 28);
		bodyPanel.add(lblStallPrice1);

		JLabel lblCirclePrice = new JLabel("Circle Price :£");
		lblCirclePrice.setFont(new Font("Arial", Font.PLAIN, 24));
		lblCirclePrice.setBounds(42, 370, 159, 28);
		bodyPanel.add(lblCirclePrice);

		JLabel lblCirclePrice1 = new JLabel(row[7]);
		lblCirclePrice1.setFont(new Font("Arial", Font.PLAIN, 24));
		lblCirclePrice1.setBounds(240, 370, 123, 28);
		bodyPanel.add(lblCirclePrice1);

		JButton btnNewButton = new JButton("Go back");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				BrowseFrame bframe = new BrowseFrame(userID);
				bframe.setVisible(true);
			}
		});
		btnNewButton.setBounds(42, 577, 114, 80);
		bodyPanel.add(btnNewButton);

		JButton btnContinue = new JButton("Continue");
		if (Integer.parseInt(seatsAvailable) < 1) {
			btnContinue.setEnabled(false);
			btnContinue.setText("No seats available");
		}
		btnContinue.setFont(new Font("Arial", Font.PLAIN, 16));
		btnContinue.setBounds(166, 577, 571, 80);
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				if (userID == 0) {
					LoginFrame lframe = new LoginFrame(userID);
					lframe.setVisible(true);
				} else {
					BookingFrame boframe = new BookingFrame(lblCirclePrice1.getText(), lblStallPrice1.getText(), userID,
							performanceID);
					boframe.setVisible(true);
				}
			}
		});
		bodyPanel.add(btnContinue);

		JLabel lblLanguage = new JLabel("Language:");
		lblLanguage.setFont(new Font("Arial", Font.PLAIN, 24));
		lblLanguage.setBounds(42, 409, 159, 28);
		bodyPanel.add(lblLanguage);

		JLabel lblLiveMusic = new JLabel("Live Music:");
		lblLiveMusic.setFont(new Font("Arial", Font.PLAIN, 24));
		lblLiveMusic.setBounds(42, 448, 159, 28);
		bodyPanel.add(lblLiveMusic);

		JLabel lblPerformers = new JLabel("Performers: ");
		lblPerformers.setFont(new Font("Arial", Font.PLAIN, 24));
		lblPerformers.setBounds(42, 487, 159, 28);
		bodyPanel.add(lblPerformers);

		JLabel lblLanguage_1 = new JLabel(row[9]);
		lblLanguage_1.setFont(new Font("Arial", Font.PLAIN, 24));
		lblLanguage_1.setBounds(240, 409, 123, 28);
		bodyPanel.add(lblLanguage_1);

		JLabel lblLiveMusic_1 = new JLabel(row[10]);
		lblLiveMusic_1.setFont(new Font("Arial", Font.PLAIN, 24));
		lblLiveMusic_1.setBounds(240, 448, 123, 28);
		bodyPanel.add(lblLiveMusic_1);

		JLabel performers = new JLabel(row[11]);
		performers.setFont(new Font("Arial", Font.PLAIN, 24));
		performers.setBounds(240, 487, 123, 28);
		bodyPanel.add(performers);
	}

	private void configureHeaderPanel() {
		JPanel headerPanel = new JPanel();
		headerPanel.setBounds(0, 0, 800, 75);
		contentPane.add(headerPanel);

		JLabel headerLabel = new JLabel();
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerLabel.setBounds(0, 0, 777, 75);
		headerLabel.setBackground(SystemColor.menu);
		headerLabel.setFont(new Font("Arial", Font.BOLD, 46));
		headerLabel.setText("You are booking tickets to see:");

		headerPanel.setLayout(null);
		headerPanel.add(headerLabel);
	}

	private void configureContentPane() {
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	private void configureFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(000, 000, 800, 800);
	}

	private void loadImageIcon() {
		ImageIcon icon = new ImageIcon("src/icon.png");
		setIconImage(icon.getImage());
		setTitle("Theatre Booking System V3");
	}

	private void loadUIStyle() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
