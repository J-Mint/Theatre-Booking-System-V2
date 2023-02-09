import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ShowDetailedFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ShowDetailedFrame frame = new ShowDetailedFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public ShowDetailedFrame(int performanceID) {
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

		JLabel headerLabel = new JLabel();
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerLabel.setBounds(0, 0, 777, 75);
		headerLabel.setBackground(SystemColor.menu);
		headerLabel.setFont(new Font("Arial", Font.BOLD, 46));
		headerLabel.setText("You are booking tickets to see:");

		panel.setLayout(null);
		panel.add(headerLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 77, 784, 684);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		DBConnector DBC = new DBConnector();
		DBC.connect();
		String query = "SELECT performance_ID, show_name, type, description, date, stage_time, duration, circle_price, stall_price FROM performances JOIN shows on performances.show_id = shows.show_id WHERE performance_id ="
				+ performanceID;
		ResultSet rs = DBC.runQuery(query);
		String row[] = new String[9];
		try {
			while (rs.next()) {
				for (int i = 0; i < 9; i++) {
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
		panel_1.add(lblTitle);

		JLabel lblTitle1 = new JLabel(row[1]);
		lblTitle1.setFont(new Font("Arial", Font.PLAIN, 24));
		lblTitle1.setBounds(240, 58, 123, 28);
		panel_1.add(lblTitle1);

		JLabel lblType = new JLabel("Type:");
		lblType.setFont(new Font("Arial", Font.PLAIN, 24));
		lblType.setBounds(42, 97, 123, 28);
		panel_1.add(lblType);

		JLabel lblType1 = new JLabel(row[2]);
		lblType1.setFont(new Font("Arial", Font.PLAIN, 24));
		lblType1.setBounds(240, 97, 123, 28);
		panel_1.add(lblType1);

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Arial", Font.PLAIN, 24));
		lblDescription.setBounds(42, 136, 136, 28);
		panel_1.add(lblDescription);

		JLabel lblDescription1 = new JLabel(row[3]);
		lblDescription1.setFont(new Font("Arial", Font.PLAIN, 24));
		lblDescription1.setBounds(240, 136, 123, 28);
		panel_1.add(lblDescription1);

		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Arial", Font.PLAIN, 24));
		lblDate.setBounds(42, 175, 136, 28);
		panel_1.add(lblDate);

		JLabel lblDate1 = new JLabel(row[4]);
		lblDate1.setFont(new Font("Arial", Font.PLAIN, 24));
		lblDate1.setBounds(240, 175, 123, 28);
		panel_1.add(lblDate1);

		JLabel lblStageTime = new JLabel("Stage Time:");
		lblStageTime.setFont(new Font("Arial", Font.PLAIN, 24));
		lblStageTime.setBounds(42, 214, 136, 28);
		panel_1.add(lblStageTime);

		JLabel lblStageTime1 = new JLabel(row[5]);
		lblStageTime1.setFont(new Font("Arial", Font.PLAIN, 24));
		lblStageTime1.setBounds(240, 214, 123, 28);
		panel_1.add(lblStageTime1);

		JLabel lblDuration = new JLabel("Duration:");
		lblDuration.setFont(new Font("Arial", Font.PLAIN, 24));
		lblDuration.setBounds(42, 253, 136, 28);
		panel_1.add(lblDuration);

		JLabel lblDuration1 = new JLabel(row[6]);
		lblDuration1.setFont(new Font("Arial", Font.PLAIN, 24));
		lblDuration1.setBounds(240, 253, 123, 28);
		panel_1.add(lblDuration1);

		JLabel lblSeatAvailability = new JLabel("Seats Available:");
		lblSeatAvailability.setFont(new Font("Arial", Font.PLAIN, 24));
		lblSeatAvailability.setBounds(42, 292, 184, 28);
		panel_1.add(lblSeatAvailability);

		JLabel lblSeatAvailability1 = new JLabel(seatsAvailable);
		lblSeatAvailability1.setFont(new Font("Arial", Font.PLAIN, 24));
		lblSeatAvailability1.setBounds(240, 292, 184, 28);
		panel_1.add(lblSeatAvailability1);

		JLabel lblStallPrice = new JLabel("Stall Price :£");
		lblStallPrice.setFont(new Font("Arial", Font.PLAIN, 24));
		lblStallPrice.setBounds(42, 331, 159, 28);
		panel_1.add(lblStallPrice);

		JLabel lblStallPrice1 = new JLabel(row[8]);
		lblStallPrice1.setFont(new Font("Arial", Font.PLAIN, 24));
		lblStallPrice1.setBounds(240, 331, 123, 28);
		panel_1.add(lblStallPrice1);

		JLabel lblCirclePrice = new JLabel("Circle Price :£");
		lblCirclePrice.setFont(new Font("Arial", Font.PLAIN, 24));
		lblCirclePrice.setBounds(42, 370, 159, 28);
		panel_1.add(lblCirclePrice);

		JLabel lblCirclePrice1 = new JLabel(row[7]);
		lblCirclePrice1.setFont(new Font("Arial", Font.PLAIN, 24));
		lblCirclePrice1.setBounds(240, 370, 123, 28);
		panel_1.add(lblCirclePrice1);

		JButton btnNewButton = new JButton("Go back");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				BrowseFrame bframe = new BrowseFrame();
				bframe.setVisible(true);
			}
		});
		btnNewButton.setBounds(42, 446, 114, 80);
		panel_1.add(btnNewButton);

		JButton btnContinue = new JButton("Continue");
		if (Integer.parseInt(seatsAvailable) < 1) {
			btnContinue.setEnabled(false);
			btnContinue.setText("No seats available");
		}
		btnContinue.setFont(new Font("Arial", Font.PLAIN, 16));
		btnContinue.setBounds(166, 446, 571, 80);
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				BookingFrame boframe = new BookingFrame(lblCirclePrice1.getText(), lblStallPrice1.getText());
				boframe.setVisible(true);
			}
		});
		panel_1.add(btnContinue);
	}

}
