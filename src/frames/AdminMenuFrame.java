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
					AdminMenuFrame frame = new AdminMenuFrame(1);
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
	public AdminMenuFrame(int userID) {
		loadUIStyle();
		loadImageIcon();
		
		
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
				dispose();
				StagePerformanceFrame sframe = new StagePerformanceFrame();
				sframe.setVisible(true);	
			}
		});
		panel_2.add(stagePerformanceButton);
		
		JButton addShowButton = new JButton("Add Show");
		addShowButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				AddShowFrame aframe = new AddShowFrame();
				aframe.setVisible(true);
			}
		});
		panel_2.add(addShowButton);
		
		JButton removeShowButton = new JButton("Remove Show");
		removeShowButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				RemoveShowFrame rframe = new RemoveShowFrame();
				rframe.setVisible(true);
			}
		});
		panel_2.add(removeShowButton);
		
		JButton removePerformanceButton = new JButton("Remove Performance");
		removePerformanceButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				RemovePerformanceFrame rpframe = new RemovePerformanceFrame();
				rpframe.setVisible(true);
			}
		});
		panel_2.add(removePerformanceButton);
		
		JButton logoutButton = new JButton("Logout");
		logoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				WelcomeFrame wframe = new WelcomeFrame();
				wframe.setVisible(true);
			}
		});
		panel_2.add(logoutButton);
		
		JButton browseButton = new JButton("Browse shows");
		browseButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				BrowseFrame bframe = new BrowseFrame(1);
				bframe.setVisible(true);
			}
		});
		panel_2.add(browseButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 77, 784, 684);

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
