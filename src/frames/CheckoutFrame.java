package frames;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckoutFrame frame = new CheckoutFrame("100", "2", "2", "2023-10-10", 1);
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
	public CheckoutFrame(String price, String concessionCount, String ticketCount, String soonestDate, int userID) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
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

		JLabel checkoutLabel = new JLabel();
		checkoutLabel.setHorizontalAlignment(SwingConstants.CENTER);
		checkoutLabel.setBounds(0, 0, 777, 75);
		checkoutLabel.setBackground(SystemColor.menu);
		checkoutLabel.setFont(new Font("Arial", Font.BOLD, 46));
		checkoutLabel.setText("Checkout");
		panel.setLayout(null);
		panel.add(checkoutLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 77, 784, 684);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		forenameTextfield = new JTextField();
		forenameTextfield.setBounds(205, 116, 529, 28);
		panel_1.add(forenameTextfield);
		forenameTextfield.setColumns(10);

		JLabel forenameLabel = new JLabel("Forename");
		forenameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		forenameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		forenameLabel.setBounds(50, 115, 145, 26);
		panel_1.add(forenameLabel);

		JLabel surnameLabel = new JLabel("Surname");
		surnameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		surnameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		surnameLabel.setBounds(50, 152, 145, 26);
		panel_1.add(surnameLabel);

		surnameTextfield = new JTextField();
		surnameTextfield.setColumns(10);
		surnameTextfield.setBounds(205, 152, 529, 28);
		panel_1.add(surnameTextfield);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAddress.setFont(new Font("Arial", Font.PLAIN, 16));
		lblAddress.setBounds(50, 189, 145, 26);
		panel_1.add(lblAddress);

		addressTextfield = new JTextField();
		addressTextfield.setColumns(10);
		addressTextfield.setBounds(205, 191, 529, 28);
		panel_1.add(addressTextfield);

		cardNoTextfield = new JTextField();
		cardNoTextfield.setColumns(10);
		cardNoTextfield.setBounds(205, 230, 529, 28);
		panel_1.add(cardNoTextfield);

		JLabel lblCardNumber = new JLabel("Card Number");
		lblCardNumber.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCardNumber.setFont(new Font("Arial", Font.PLAIN, 16));
		lblCardNumber.setBounds(50, 229, 145, 26);
		panel_1.add(lblCardNumber);

		JLabel checkoutLabel_1 = new JLabel();
		checkoutLabel_1.setText("Your Total: £");
		checkoutLabel_1.setFont(new Font("Arial", Font.BOLD, 46));
		checkoutLabel_1.setBackground(SystemColor.menu);
		checkoutLabel_1.setBounds(10, 29, 287, 75);
		panel_1.add(checkoutLabel_1);

		JLabel checkoutLabel_1_1 = new JLabel();
		checkoutLabel_1_1.setText("" + Double.parseDouble(price));
		checkoutLabel_1_1.setFont(new Font("Arial", Font.BOLD, 46));
		checkoutLabel_1_1.setBackground(SystemColor.menu);
		checkoutLabel_1_1.setBounds(292, 29, 272, 75);
		panel_1.add(checkoutLabel_1_1);

		JButton btnNewButton = new JButton("Back to basket");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				BasketFrame bframe = new BasketFrame(userID);
				bframe.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 512, 185, 91);
		panel_1.add(btnNewButton);

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
				query = "SELECT order_id FROM orders WHERE customer_id="+userID+" AND order_date='"+date+"'";
				ResultSet rs = DBC.runQuery(query);
				while (rs.next()) {
					orderID = rs.getString(1);
				}
				} catch (Exception e1) {
					
				}
				WelcomeFrame frame = new WelcomeFrame();
				frame.setVisible(true);
				// generte po file
				
				while (orderID.length() < 7) {
					orderID=String.format("%7s", orderID).replace(" " , "0");
				}
				
				try {
					FileWriter myWriter = new FileWriter("C:\\Users\\Joe\\Downloads\\PO" + orderID + ".txt");
					myWriter.write("---------------------------------------------------\n");
					myWriter.write("THANK YOU FOR YOUR PURCHASE.\n");
					myWriter.write("---------------------------------------------------\n");
					myWriter.write("NAME: " + forenameTextfield.getText().toUpperCase() + " "
							+ surnameTextfield.getText().toUpperCase() + "\n");
					myWriter.write("---------------------------------------------------\n");
					myWriter.write("ADDRESS: " + addressTextfield.getText().toUpperCase() + "\n");
					myWriter.write("CARD NO: **** **** **** "+(cardNoTextfield.getText().substring(cardNoTextfield.getText().length() - 4))+"\n");
					myWriter.write("ORDER TOTAL: £"+price+"\n");
					myWriter.write("---------------------------------------------------\n");
					myWriter.write("ORDER_ID: "+orderID +"\n");
					myWriter.write("CUSTOMER_ID: "+userID+"\n");
					myWriter.write("---------------------------------------------------\n");
					
					
					
					// for loop number of tickets in basket
					for (int i = 1; i <= Integer.parseInt(ticketCount); i++) {
						myWriter.write("TICKET " + i + "\n");
						myWriter.write("PERFORMANCE_ID: \n");
						myWriter.write("SEAT_ID: \n");
						myWriter.write("SEAT_TYPE: \n");
						myWriter.write("STANDARD_TICKET: \n");
						myWriter.write("PRICE: \n");
						myWriter.write("SHOW_TITLE: \n");
						myWriter.write("DATE: \n");
						myWriter.write("TIME: \n");
						myWriter.write("---------------------------------------------------\n");
					}

					myWriter.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		completePurchaseButton.setBounds(208, 512, 566, 91);
		panel_1.add(completePurchaseButton);

		ButtonGroup radioButtonGroup = new ButtonGroup();
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Collect from Box Office");
		rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checkoutLabel_1_1.setText("" + Double.parseDouble(price));
				collectionRequired = true;

			}
		});
		rdbtnNewRadioButton.setFont(new Font("Arial", Font.PLAIN, 16));
		rdbtnNewRadioButton.setBounds(205, 265, 216, 23);
		radioButtonGroup.add(rdbtnNewRadioButton);
		panel_1.add(rdbtnNewRadioButton);

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
					checkoutLabel_1_1.setText("" + price1);
				} else if (concessionCount1 == 1) {
					// price += 1;
					Double price1 = Double.parseDouble(price) + 1;
					checkoutLabel_1_1.setText("" + price1);
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
		panel_1.add(rdbtnNewRadioButton_1);
	}

}
