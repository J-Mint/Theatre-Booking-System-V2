package frames;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class AdminMenuFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMenuFrame frame = new AdminMenuFrame("1");
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
	public AdminMenuFrame(String userID) {
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
		
		JLabel headerLabel = new JLabel();
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerLabel.setBounds(0, 0, 777, 75);
		headerLabel.setBackground(SystemColor.menu);
		headerLabel.setFont(new Font("Arial", Font.BOLD, 46));
		headerLabel.setText("ADMIN MENU");
		panel.setLayout(null);
		panel.add(headerLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 73, 784, 688);
		contentPane.add(panel_2);
		
		JButton stagePerformanceButton = new JButton("Stage Performance");
		stagePerformanceButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		panel_2.add(stagePerformanceButton);
		
		JButton addShowButton = new JButton("Add Show");
		panel_2.add(addShowButton);
		
		JButton removeShowButton = new JButton("Remove Show");
		panel_2.add(removeShowButton);
		
		JButton removePerformanceButton = new JButton("Remove Performance");
		panel_2.add(removePerformanceButton);
		
		JButton logoutButton = new JButton("Logout");
		panel_2.add(logoutButton);
		
		JButton browseButton = new JButton("Browse shows");
		panel_2.add(browseButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 77, 784, 684);

	}
}
