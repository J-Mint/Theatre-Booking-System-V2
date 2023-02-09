import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JInternalFrame;
import java.awt.Panel;
import java.awt.Label;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class BookingFrame extends JFrame{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookingFrame frame = new BookingFrame();
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
	public BookingFrame() {
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
		
		//stall seats qty 120
		Panel panel_2 = new Panel();
		panel_2.setBounds(10, 36, 764, 285);
		panel_1.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		for (int i = 1; i <= 120; i++) {
		JButton btnNewButton = new JButton("" + i);
		panel_2.add(btnNewButton);
		}
		
		
		// circle seats qty 80
		Panel panel_2_1 = new Panel();
		panel_2_1.setBounds(10, 350, 764, 235);
		panel_1.add(panel_2_1);
		panel_2_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		for (int i = 121; i <= 200; i++) {
			JButton btnNewButton = new JButton("" + i);
			panel_2_1.add(btnNewButton);
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
		panel_1.add(btnNewButton_1);
		
	}
}
