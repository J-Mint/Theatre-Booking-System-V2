package Frames;
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
import javax.swing.JInternalFrame;
import java.awt.Panel;
import java.awt.Label;
import java.awt.FlowLayout;
import javax.swing.JButton;
import DBConnector.*;
public class BookingFrame extends JFrame {

	private JPanel contentPane;
	private int ticketCount;
	private Double total;
	private ArrayList<String[]> tempBasket;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookingFrame frame = new BookingFrame("5", "10", null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BookingFrame(String circlePrice, String stallPrice, int userID) {
		tempBasket = new ArrayList<>();
		ticketCount = 0;
		total = 0.0;
		String performanceID = "1";
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
		headerLabel.setText("Select your seats");
		panel.setLayout(null);
		panel.add(headerLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 77, 784, 684);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		// stall seats qty 120
		Panel panel_2 = new Panel();
		panel_2.setBounds(10, 36, 764, 285);
		panel_1.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblCount = new JLabel("" + ticketCount);
		lblCount.setFont(new Font("Arial", Font.PLAIN, 16));
		lblCount.setBounds(126, 625, 72, 14);
		panel_1.add(lblCount);

		JLabel lblTotal1 = new JLabel("");
		lblTotal1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblTotal1.setBounds(126, 648, 72, 14);
		panel_1.add(lblTotal1);

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
						panel_2.add(btnNewButton);
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
										String[] order = { performanceID, "Standard", seatID, ""+price};
										tempBasket.add(order);
										total += price;
										lblTotal1.setText("" + total);
									} else {
										double price = ((Double.parseDouble(stallPrice)) * 3.0 / 4.0);
										String[] order = { performanceID, "Concession", seatID, ""+price };
										tempBasket.add(order);
										total += price;
										lblTotal1.setText("" + total);
									}
								} else {
									// chosen to cancel
								}
							}
						});
						panel_2.add(btnNewButton);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DBC.close();
		}

		// circle seats qty 80
		Panel panel_2_1 = new Panel();
		panel_2_1.setBounds(10, 350, 764, 235);
		panel_1.add(panel_2_1);
		panel_2_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

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
						panel_2_1.add(btnNewButton);

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
										Double price = Double.parseDouble(circlePrice);;
										String[] order = { performanceID, "Standard", seatID, ""+price  };
										tempBasket.add(order);
										lblTotal1.setText("" + price);
									} else {
										Double price = ((Double.parseDouble(circlePrice)) * 3.0 / 4.0);
										String[] order = { performanceID, "Concession", seatID, ""+price};
										tempBasket.add(order);
										lblTotal1.setText("" + price);

									}
								}
							}
						});
						panel_2_1.add(btnNewButton);
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
		panel_1.add(label_1);

		Label label = new Label("Stall seats:");
		label.setBounds(10, 3, 764, 36);
		panel_1.add(label);
		label.setAlignment(Label.CENTER);
		label.setFont(new Font("Arial", Font.PLAIN, 30));

		JLabel lblNewLabel = new JLabel("Ticket count:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 623, 113, 14);
		panel_1.add(lblNewLabel);

		JLabel lblTotal = new JLabel("Total:\r\n");
		lblTotal.setFont(new Font("Arial", Font.PLAIN, 16));
		lblTotal.setBounds(10, 648, 113, 14);
		panel_1.add(lblTotal);

		JButton btnNewButton_1 = new JButton("Add to basket");
		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 16));
		btnNewButton_1.setBounds(204, 614, 570, 59);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// add array objects to basket
				DBConnector DBC = new DBConnector();
				DBC.connect();
				for (String[] i : tempBasket) {
					String query = "INSERT INTO basket (performance_id, standard_or_concession, seat_id, price) VALUES ("+i[0]+", '"+i[1]+"', "+i[2]+","+i[3]+")";
					DBC.runQuery(query);
					query = "UPDATE seats SET seat_booked=true WHERE performance_id="+i[0]+" AND seat_id="+i[2];
					DBC.runQuery(query);
				}
				DBC.close();
				btnNewButton_1.setEnabled(false);
				dispose();
				//go to basket page
				BasketFrame dframe = new BasketFrame(userID);
				dframe.setVisible(true);
				//add basket page to browse frame
			}
		});
		panel_1.add(btnNewButton_1);

	}
}
