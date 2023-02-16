package frames;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.event.MouseAdapter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class WelcomeFrame extends JFrame {

	private JPanel mainPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeFrame frame = new WelcomeFrame();
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
	public WelcomeFrame() {
		loadUIStyle();
		loadImageIcon();
		loadFrameAndMainPanel();
		loadWelcomePanel();
		loadBodyPanel();		
	}

	private void loadBodyPanel() {
		JPanel bodyPanel = new JPanel();
		bodyPanel.setBounds(0, 77, 784, 684);
		JLabel clickToBeginLabel = new JLabel();
		clickToBeginLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				BrowseFrame bframe = new BrowseFrame(0);
				bframe.setVisible(true);
			}
		});
		clickToBeginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		clickToBeginLabel.setBackground(SystemColor.menu);
		clickToBeginLabel.setFont(new Font("Arial", Font.BOLD, 35));
		clickToBeginLabel.setText("Click to begin");
		clickToBeginLabel.setBounds(0, 0, 784, 684);
		bodyPanel.add(clickToBeginLabel);
		mainPanel.add(bodyPanel);
		bodyPanel.setLayout(null);
	}

	private void loadWelcomePanel() {
		JPanel headerPanel = new JPanel();
		headerPanel.setBounds(0, 0, 800, 75);
		mainPanel.add(headerPanel);
		
		JLabel welcomeLabel = new JLabel();
		welcomeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				BrowseFrame bframe = new BrowseFrame(0);
				bframe.setVisible(true);
			}
		});
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setBounds(0, 0, 777, 75);
		welcomeLabel.setBackground(SystemColor.menu);
		welcomeLabel.setFont(new Font("Arial", Font.BOLD, 46));
		welcomeLabel.setText("Welcome to the Theatre Royal");
		headerPanel.setLayout(null);
		headerPanel.add(welcomeLabel);
	}

	public void loadUIStyle() {
		try { 
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	public void loadImageIcon() {
		ImageIcon icon = new ImageIcon("src/icon.png");
		setIconImage(icon.getImage());
		setTitle("Theatre Booking System V3");
	}
	
	public void loadFrameAndMainPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(000, 000, 800, 800);
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
	}

	
	

}
