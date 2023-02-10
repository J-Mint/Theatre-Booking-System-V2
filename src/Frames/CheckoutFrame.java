package Frames;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import DBConnector.*;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
public class CheckoutFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckoutFrame frame = new CheckoutFrame("100", "10", "10", "2023-06-01");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param price 
	 * @param soonestDate 
	 */
	public CheckoutFrame(String price, String concessionCount, String ticketCount, String soonestDate) {
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
		
		textField = new JTextField();
		textField.setBounds(205, 116, 529, 28);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Forename");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setBounds(50, 115, 145, 26);
		panel_1.add(lblNewLabel);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSurname.setFont(new Font("Arial", Font.PLAIN, 16));
		lblSurname.setBounds(50, 152, 145, 26);
		panel_1.add(lblSurname);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(205, 152, 529, 28);
		panel_1.add(textField_1);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAddress.setFont(new Font("Arial", Font.PLAIN, 16));
		lblAddress.setBounds(50, 189, 145, 26);
		panel_1.add(lblAddress);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(205, 191, 529, 28);
		panel_1.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(205, 230, 529, 28);
		panel_1.add(textField_3);
		
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
		checkoutLabel_1_1.setText("" +Double.parseDouble(price));
		checkoutLabel_1_1.setFont(new Font("Arial", Font.BOLD, 46));
		checkoutLabel_1_1.setBackground(SystemColor.menu);
		checkoutLabel_1_1.setBounds(292, 29, 272, 75);
		panel_1.add(checkoutLabel_1_1);
		
		
		
		JButton btnNewButton = new JButton("Back to basket");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				BasketFrame bframe = new BasketFrame();
				bframe.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 512, 185, 91);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Complete purchase");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// add order to order to order table
				// clear the basket
				// back to welcome screen
				// generate PO file
			}
		});
		btnNewButton_1.setBounds(208, 512, 566, 91);
		panel_1.add(btnNewButton_1);
		
		ButtonGroup radioButtonGroup = new ButtonGroup();
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Collect from Box Office");
		rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checkoutLabel_1_1.setText("" + Double.parseDouble(price));
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
				//update the price
				int concessionCount1 = Integer.parseInt(concessionCount);
				if (concessionCount1 == 0) {
					//price +=ticketCount;
					Double price1 = Double.parseDouble(price) + Double.parseDouble(ticketCount);
					checkoutLabel_1_1.setText("" + price1);
				} else if (concessionCount1 == 1){
					//price += 1;
					Double price1 = Double.parseDouble(price) + 1;
					checkoutLabel_1_1.setText("" + price1);
				} 
				// else free postage.
			}
		});
		LocalDate minShowDate = LocalDate.parse(soonestDate);
		if (minShowDate.compareTo(LocalDate.now().plusDays(7)) <= 0 ) {
			rdbtnNewRadioButton_1.setEnabled(false);
		}
		rdbtnNewRadioButton_1.setFont(new Font("Arial", Font.PLAIN, 16));
		rdbtnNewRadioButton_1.setBounds(419, 265, 315, 23);
		radioButtonGroup.add(rdbtnNewRadioButton_1);
		panel_1.add(rdbtnNewRadioButton_1);
	}
}
