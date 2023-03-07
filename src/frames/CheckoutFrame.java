package frames;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import DBConnector.DBConnector;

public class CheckoutFrame extends JFrame {

	private JPanel contentPane;
	private JTextField forenameTextfield;
	private JTextField surnameTextfield;
	private JTextField addressTextfield;
	private JTextField cardNoTextfield;
	private Boolean collectionRequired;
	private String price, concessionCount, ticketCount, soonestDate;
	private int userID;

	public CheckoutFrame(String price, String concessionCount, String ticketCount, String soonestDate, int userID) {
		this.price = price;
		this.concessionCount = concessionCount;
		this.ticketCount = ticketCount;
		this.soonestDate = soonestDate;
		this.userID = userID;
		loadUIStyle();
		loadImageIcon();
		configureFrame();
		configureContentPane();
		configureHeaderPanel();
		configureBodyPanel();
		
		//centre application on screen
		setLocationRelativeTo(null);
	}

	private void configureBodyPanel() {
		JPanel bodyPanel = new JPanel();
		bodyPanel.setBounds(0, 77, 784, 684);
		contentPane.add(bodyPanel);
		bodyPanel.setLayout(null);

		forenameTextfield = new JTextField();
		forenameTextfield.setBounds(205, 116, 529, 28);
		bodyPanel.add(forenameTextfield);
		forenameTextfield.setColumns(10);

		JLabel forenameLabel = new JLabel("Forename");
		forenameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		forenameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		forenameLabel.setBounds(50, 115, 145, 26);
		bodyPanel.add(forenameLabel);

		JLabel surnameLabel = new JLabel("Surname");
		surnameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		surnameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		surnameLabel.setBounds(50, 152, 145, 26);
		bodyPanel.add(surnameLabel);

		surnameTextfield = new JTextField();
		surnameTextfield.setColumns(10);
		surnameTextfield.setBounds(205, 152, 529, 28);
		bodyPanel.add(surnameTextfield);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAddress.setFont(new Font("Arial", Font.PLAIN, 16));
		lblAddress.setBounds(50, 189, 145, 26);
		bodyPanel.add(lblAddress);

		addressTextfield = new JTextField();
		addressTextfield.setColumns(10);
		addressTextfield.setBounds(205, 191, 529, 28);
		bodyPanel.add(addressTextfield);

		cardNoTextfield = new JTextField();
		cardNoTextfield.setColumns(10);
		cardNoTextfield.setBounds(205, 230, 529, 28);
		bodyPanel.add(cardNoTextfield);

		JLabel lblCardNumber = new JLabel("Card Number");
		lblCardNumber.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCardNumber.setFont(new Font("Arial", Font.PLAIN, 16));
		lblCardNumber.setBounds(50, 229, 145, 26);
		bodyPanel.add(lblCardNumber);

		JLabel yourTotalLabel = new JLabel();
		yourTotalLabel.setText("Your Total: £");
		yourTotalLabel.setFont(new Font("Arial", Font.BOLD, 46));
		yourTotalLabel.setBackground(SystemColor.menu);
		yourTotalLabel.setBounds(10, 29, 287, 75);
		bodyPanel.add(yourTotalLabel);

		JLabel dynamicPriceLabel = new JLabel();
		dynamicPriceLabel.setText("" + Double.parseDouble(price));
		dynamicPriceLabel.setFont(new Font("Arial", Font.BOLD, 46));
		dynamicPriceLabel.setBackground(SystemColor.menu);
		dynamicPriceLabel.setBounds(292, 29, 272, 75);
		bodyPanel.add(dynamicPriceLabel);

		JButton cancelButton = new JButton("Back to basket");
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				BasketFrame bframe = new BasketFrame(userID);
				bframe.setVisible(true);
			}
		});
		cancelButton.setBounds(10, 512, 185, 91);
		bodyPanel.add(cancelButton);

		JButton completePurchaseButton = new JButton("Complete purchase");
		completePurchaseButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// display a joptionpane message dialog Successful. Thank you for your order.
				JFrame dialogFrame = new JFrame("JOptionPane showMessageDialog example");
				JOptionPane.showMessageDialog(dialogFrame, "Purchase successful. Purchase Order File Downloaded.");
				dispose();
				DBConnector DBC = new DBConnector();
				DBC.connect();
				LocalDate date = LocalDate.now();
				String query = "CALL orderCompleteInsertion(" + userID + ", '" + forenameTextfield.getText() + "', '"
						+ surnameTextfield.getText() + "', '" + addressTextfield.getText() + "', '" + date + "' , "
						+ collectionRequired + ")";
				String orderID = "";
				try {
					DBC.runQuery(query);
					query = "SELECT order_id FROM orders WHERE customer_id=" + userID + " AND order_date='" + date
							+ "'";
					ResultSet rs = DBC.runQuery(query);
					while (rs.next()) {
						orderID = rs.getString(1);
					}
				} catch (Exception e1) {

				}
				// view basket where userID matches
				query = "SELECT performance_id, seat_id, standard_or_concession, price FROM basket WHERE user_id ="
						+ userID;
				// save as result set
				ResultSet rs = DBC.runQuery(query);
				try {
					while (rs.next()) {
						String query1 = "CALL orderLineInsertion(" + orderID + ", " + rs.getString(1) + ","
								+ rs.getString(2) + ",'" + rs.getString(3) + "'," + rs.getString(4) + ")";
						DBC.runQuery(query1);
					}
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				// generate po file

				String formatOrderID = String.format("%7s", orderID).replace(" ", "0");

				try {
					FileWriter myWriter = new FileWriter("C:\\Users\\Joe\\Downloads\\PO" + formatOrderID + ".txt");
					myWriter.write("---------------------------------------------------\n");
					myWriter.write("THANK YOU FOR YOUR PURCHASE.\n");
					myWriter.write("---------------------------------------------------\n");
					myWriter.write("NAME: " + forenameTextfield.getText().toUpperCase() + " "
							+ surnameTextfield.getText().toUpperCase() + "\n");
					myWriter.write("---------------------------------------------------\n");
					myWriter.write("ADDRESS: " + addressTextfield.getText().toUpperCase() + "\n");
					myWriter.write("CARD NO: **** **** **** "
							+ (cardNoTextfield.getText().substring(cardNoTextfield.getText().length() - 4)) + "\n");
					myWriter.write("ORDER TOTAL: £" + price + "\n");
					myWriter.write("---------------------------------------------------\n");
					myWriter.write("ORDER_ID: " + orderID + "\n");
					myWriter.write("CUSTOMER_ID: " + userID + "\n");
					myWriter.write("---------------------------------------------------\n");

					query = "CALL getOrderLines(" + orderID + ")";
					ResultSet rs2 = DBC.runQuery(query);
					try {
						while (rs2.next()) {
							Double price = Double.parseDouble(rs2.getString(5));
							price = Math.round(price * 100) / 100.00;
							myWriter.write("PERFORMANCE_ID: " + rs2.getString(1) + "\n");
							myWriter.write("SEAT_ID: " + rs2.getString(2) + "\n");
							myWriter.write("SEAT_TYPE: " + rs2.getString(3).toUpperCase() + "\n");
							myWriter.write("TICKET_TYPE: " + rs2.getString(4).toUpperCase() + "\n");
							myWriter.write("PRICE: " + price + "\n");
							myWriter.write("SHOW_TITLE: " + rs2.getString(6).toUpperCase() + "\n");
							myWriter.write("DATE: " + rs2.getString(7) + "\n");
							myWriter.write("STAGE_TIME: " + rs2.getString(8).toUpperCase() + "\n");
							myWriter.write("---------------------------------------------------\n");

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					myWriter.close();

				} catch (IOException e1) {
					e1.printStackTrace();
				}
				// clear the basket where user id = match;
				String query2 = "SET FOREIGN_KEY_CHECKS=0";
				DBC.runQuery(query2);
				query = "DELETE FROM basket WHERE user_id ="+userID;
				DBC.runQuery(query);
				query2 = "SET FOREIGN_KEY_CHECKS=1";
				DBC.runQuery(query2);
				
				// log out the user
				WelcomeFrame frame = new WelcomeFrame();
				frame.setVisible(true);
				DBC.close();
			}

		});
		completePurchaseButton.setBounds(208, 512, 566, 91);
		bodyPanel.add(completePurchaseButton);

		ButtonGroup radioButtonGroup = new ButtonGroup();
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Collect from Box Office");
		rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dynamicPriceLabel.setText("" + Double.parseDouble(price));
				collectionRequired = true;

			}
		});
		rdbtnNewRadioButton.setFont(new Font("Arial", Font.PLAIN, 16));
		rdbtnNewRadioButton.setBounds(205, 265, 216, 23);
		radioButtonGroup.add(rdbtnNewRadioButton);
		bodyPanel.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Post to My Address");
		rdbtnNewRadioButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				collectionRequired = false;
				// update the price
				int concessionCount1 = Integer.parseInt(concessionCount);
				if (concessionCount1 == 0) {
					// price +=ticketCount;
					Double price1 = Double.parseDouble(price) + Double.parseDouble(ticketCount);
					price1 = Math.round(price1 * 100) / 100.00;
					dynamicPriceLabel.setText("" + price1);
					
				} else if (concessionCount1 == 1) {
					// price += 1;
					Double price1 = Double.parseDouble(price) + 1;
					price1 = Math.round(price1 * 100) / 100.00;
					dynamicPriceLabel.setText("" + price1);
				}
				// else free postage.
			}
		});
		LocalDate minShowDate = LocalDate.parse(soonestDate);
		if (minShowDate.compareTo(LocalDate.now().plusDays(7)) <= 0) {
			rdbtnNewRadioButton_1.setEnabled(false);
		}
		rdbtnNewRadioButton_1.setFont(new Font("Arial", Font.PLAIN, 16));
		rdbtnNewRadioButton_1.setBounds(419, 265, 315, 23);
		radioButtonGroup.add(rdbtnNewRadioButton_1);
		bodyPanel.add(rdbtnNewRadioButton_1);
	}

	private void configureHeaderPanel() {
		JPanel headerPanel = new JPanel();
		headerPanel.setBounds(0, 0, 800, 75);
		contentPane.add(headerPanel);

		JLabel checkoutLabel = new JLabel();
		checkoutLabel.setHorizontalAlignment(SwingConstants.CENTER);
		checkoutLabel.setBounds(0, 0, 777, 75);
		checkoutLabel.setBackground(SystemColor.menu);
		checkoutLabel.setFont(new Font("Arial", Font.BOLD, 46));
		checkoutLabel.setText("Checkout");
		headerPanel.setLayout(null);
		headerPanel.add(checkoutLabel);
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
