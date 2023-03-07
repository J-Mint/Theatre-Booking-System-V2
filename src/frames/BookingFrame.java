package frames;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JInternalFrame;
import java.awt.Panel;
import java.awt.Label;
import java.awt.FlowLayout;
import javax.swing.JButton;
import DBConnector.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookingFrame extends JFrame {

	private JPanel contentPane;
	private int ticketCount, userID, performanceID;
	private Double total;
	private ArrayList<String[]> tempBasket;
	private String stallPrice, circlePrice;


	public BookingFrame(String circlePrice, String stallPrice, int userID, int performanceID) {
		this.userID = userID;
		this.performanceID = performanceID;
		this.circlePrice = circlePrice;
		this.stallPrice = stallPrice;
		tempBasket = new ArrayList<>();
		ticketCount = 0;
		total = 0.0;
		
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

		// get stall seat availability
		Panel stallSeatsButtonPanel = new Panel();
		stallSeatsButtonPanel.setBounds(10, 36, 764, 285);
		bodyPanel.add(stallSeatsButtonPanel);
		stallSeatsButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblCount = new JLabel("" + ticketCount);
		lblCount.setFont(new Font("Arial", Font.PLAIN, 16));
		lblCount.setBounds(364, 625, 72, 14);
		bodyPanel.add(lblCount);

		JLabel lblTotal1 = new JLabel("");
		lblTotal1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblTotal1.setBounds(364, 650, 72, 14);
		bodyPanel.add(lblTotal1);

		for (int i = 1; i <= 120; i++) {
			DBConnector DBC = new DBConnector();
			DBC.connect();
			String query = "SELECT seat_booked FROM seats WHERE seat_id=" + i + " AND performance_id =" + performanceID;
			ResultSet rs = DBC.runQuery(query);
			String seatID = "" + i;
			try {
				while (rs.next()) {
					// if seat booked create button and set disabled
					int booked = Integer.parseInt(rs.getString(1));
					if (booked == 1) {
						JButton btnNewButton = new JButton("" + i);
						btnNewButton.setEnabled(false);
						stallSeatsButtonPanel.add(btnNewButton);
					} else {
						JButton btnNewButton = new JButton("" + i);
						btnNewButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								// prompt is seat + seat_id a standard ticket or conecssion?
								String[] options = { "Standard", "Concession", "Cancel" };
								int choice = JOptionPane.showOptionDialog(null,
										"Is this for a Standard ticket or Concession?",
										"Standard or Concession ticket?", 0, 3, null, options, options[0]);
								if (choice == 0 || choice == 1) {
									// chosen Standard if 0, concession if 1
									btnNewButton.setEnabled(false);
									ticketCount++;
									lblCount.setText("" + ticketCount);
									if (choice == 0) {
										double price = Double.parseDouble(stallPrice);
										String[] order = { ("" + performanceID), "Standard", seatID, "" + price };
										tempBasket.add(order);
										total += price;
										total = Math.round(total * 100) / 100.00;
										lblTotal1.setText("" + total);
									} else {
										double price = ((Double.parseDouble(stallPrice)) * 3.0 / 4.0);
										String[] order = { ("" + performanceID), "Concession", seatID, "" + price };
										tempBasket.add(order);
										total += price;
										total = Math.round(total * 100) / 100.00;
										lblTotal1.setText("" + total);
									}
								} else {
									// chosen to cancel
								}
							}
						});
						stallSeatsButtonPanel.add(btnNewButton);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DBC.close();
		}

		// get circle seat availability
		Panel circleSeatsButtonPanel = new Panel();
		circleSeatsButtonPanel.setBounds(10, 350, 764, 235);
		bodyPanel.add(circleSeatsButtonPanel);
		circleSeatsButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		for (int i = 121; i <= 200; i++) {
			DBConnector DBC = new DBConnector();
			DBC.connect();
			String query = "SELECT seat_booked FROM seats WHERE seat_id=" + i + " AND performance_id =" + performanceID;
			ResultSet rs = DBC.runQuery(query);
			String seatID = "" + i;
			try {
				while (rs.next()) {
					// if seat booked create button and set disabled
					int booked = Integer.parseInt(rs.getString(1));
					if (booked == 1) {
						JButton btnNewButton = new JButton("" + i);
						btnNewButton.setEnabled(false);
						circleSeatsButtonPanel.add(btnNewButton);

					} else {
						JButton btnNewButton = new JButton("" + i);
						btnNewButton.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {
								// prompt is seat + seat_id a standard ticket or conecssion?
								String[] options = { "Standard", "Concession", "Cancel" };
								int choice = JOptionPane.showOptionDialog(null,
										"Is this for a Standard ticket or Concession?",
										"Standard or Concession ticket?", 0, 3, null, options, options[0]);
								if (choice == 0 || choice == 1) {
									// chosen Standard if 0, concession if 1
									btnNewButton.setEnabled(false);
									ticketCount++;
									lblCount.setText("" + ticketCount);
									if (choice == 0) {
										Double price = Double.parseDouble(circlePrice);
										;
										String[] order = { ("" + performanceID), "Standard", seatID, "" + price };
										tempBasket.add(order);
										total += price;
										total = Math.round(total * 100) / 100.00;
										lblTotal1.setText("" + total);
									} else {
										Double price = ((Double.parseDouble(circlePrice)) * 3.0 / 4.0);
										String[] order = { ("" + performanceID), "Concession", seatID, "" + price };
										tempBasket.add(order);
										total += price;
										total = Math.round(total * 100) / 100.00;
										lblTotal1.setText("" + total);

									}
								}
							}
						});
						circleSeatsButtonPanel.add(btnNewButton);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DBC.close();
		}

		Label label_1 = new Label("Circle seats:");
		label_1.setFont(new Font("Arial", Font.PLAIN, 30));
		label_1.setAlignment(Label.CENTER);
		label_1.setBounds(10, 316, 764, 36);
		bodyPanel.add(label_1);

		Label label = new Label("Stall seats:");
		label.setBounds(10, 3, 764, 36);
		bodyPanel.add(label);
		label.setAlignment(Label.CENTER);
		label.setFont(new Font("Arial", Font.PLAIN, 30));

		JLabel lblNewLabel = new JLabel("Ticket count:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setBounds(270, 625, 113, 14);
		bodyPanel.add(lblNewLabel);

		JLabel lblTotal = new JLabel("Total:\r\n");
		lblTotal.setFont(new Font("Arial", Font.PLAIN, 16));
		lblTotal.setBounds(270, 650, 113, 14);
		bodyPanel.add(lblTotal);

		JButton addButton = new JButton("Add to basket");
		addButton.setFont(new Font("Arial", Font.PLAIN, 16));
		addButton.setBounds(437, 614, 337, 59);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// add array objects to basket
				DBConnector DBC = new DBConnector();
				DBC.connect();
				for (String[] i : tempBasket) {
					String query = "INSERT INTO basket (user_id, performance_id, standard_or_concession, seat_id, price) VALUES ('"
							+ userID + "', " + i[0] + ", '" + i[1] + "', " + i[2] + "," + i[3] + ")";
					DBC.runQuery(query);
					query = "UPDATE seats SET seat_booked=true WHERE performance_id=" + i[0] + " AND seat_id=" + i[2];
					DBC.runQuery(query);
				}
				DBC.close();
				addButton.setEnabled(false);
				dispose();
				// go to basket page
				BasketFrame dframe = new BasketFrame(userID);
				dframe.setVisible(true);
				// add basket page to browse frame
			}
		});
		bodyPanel.add(addButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				// go to basket page
				BrowseFrame dframe = new BrowseFrame(userID);
				dframe.setVisible(true);
				// add basket page to browse frame
			}
		});
		cancelButton.setFont(new Font("Arial", Font.PLAIN, 16));
		cancelButton.setBounds(10, 614, 248, 59);
		bodyPanel.add(cancelButton);

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
		headerLabel.setText("Select your seats");
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
