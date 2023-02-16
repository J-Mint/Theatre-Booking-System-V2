package frames;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import DBConnector.DBConnector;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddShowFrame extends JFrame {

	private JPanel contentPane;
	private JTextField titleTextfield;
	private JTextField descriptionTextfield;
	private JTextField durationTextfield;
	private JTextField performersTextfield;
	private JTextField languageTextfield;
	private String showType;
	private boolean liveMusic;
	private JRadioButton yesLiveMusicRadioButton, noLiveMusicRadioButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddShowFrame frame = new AddShowFrame();
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
	public AddShowFrame() {
		showType = "Theatre";
		
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
		checkoutLabel.setText("Add a Show");
		panel.setLayout(null);
		panel.add(checkoutLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 77, 784, 684);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel titleLabel = new JLabel("Title");
		titleLabel.setBounds(43, 28, 89, 35);
		panel_1.add(titleLabel);

		JLabel descriptionLabel = new JLabel("Description");
		descriptionLabel.setBounds(43, 74, 89, 34);
		panel_1.add(descriptionLabel);

		JLabel durationLabel = new JLabel("Duration");
		durationLabel.setBounds(43, 119, 89, 35);
		panel_1.add(durationLabel);

		JLabel typeLabel = new JLabel("Type");
		typeLabel.setBounds(43, 165, 89, 35);
		panel_1.add(typeLabel);

		JLabel languageLabel = new JLabel("Language");
		languageLabel.setBounds(43, 211, 89, 35);
		panel_1.add(languageLabel);

		JLabel liveMusicLabel = new JLabel("Live music");
		liveMusicLabel.setBounds(43, 257, 89, 35);
		panel_1.add(liveMusicLabel);

		JLabel performersLabel = new JLabel("Performers");
		performersLabel.setBounds(43, 303, 89, 35);
		panel_1.add(performersLabel);

		JButton addShowButton = new JButton("Add show");
		addShowButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DBConnector DBC = new DBConnector();
				String query = "CALL insertShow('" + titleTextfield.getText() + "', '" + showType + "', '" + descriptionTextfield.getText() + "', " + durationTextfield.getText() + ", '" + languageTextfield.getText() + "' , " + liveMusic + ", '"+performersTextfield.getText()+"')";
				try {
					DBC.runQuery(query);
				} catch (Exception e1) {}
				
				// get confirmation that it worked???
				dispose();
				AdminMenuFrame aframe = new AdminMenuFrame("1");
				aframe.setVisible(true);
			}
			
		});
		addShowButton.setBounds(642, 617, 89, 23);
		panel_1.add(addShowButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				AdminMenuFrame aframe = new AdminMenuFrame("1");
				aframe.setVisible(true);
			}
		});
		cancelButton.setBounds(43, 617, 89, 23);
		panel_1.add(cancelButton);

		titleTextfield = new JTextField();
		titleTextfield.setBounds(167, 27, 519, 35);
		panel_1.add(titleTextfield);
		titleTextfield.setColumns(10);

		descriptionTextfield = new JTextField();
		descriptionTextfield.setBounds(167, 74, 519, 35);
		panel_1.add(descriptionTextfield);
		descriptionTextfield.setColumns(10);

		durationTextfield = new JTextField();
		durationTextfield.setBounds(167, 119, 519, 35);
		panel_1.add(durationTextfield);
		durationTextfield.setColumns(10);

		performersTextfield = new JTextField();
		performersTextfield.setBounds(167, 303, 519, 35);
		panel_1.add(performersTextfield);
		performersTextfield.setColumns(10);
		enablePerformers(false);

		ButtonGroup typeGroup = new ButtonGroup();
		ButtonGroup liveMusicGroup = new ButtonGroup();
		
		yesLiveMusicRadioButton = new JRadioButton("Yes", false);
		yesLiveMusicRadioButton.setEnabled(false);
		yesLiveMusicRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (showType != "Theatre") {
				enablePerformers(true);
				liveMusic = true;
				}
				
			}
		});
		yesLiveMusicRadioButton.setBounds(167, 257, 109, 35);
		panel_1.add(yesLiveMusicRadioButton);
		liveMusicGroup.add(yesLiveMusicRadioButton);
		
		noLiveMusicRadioButton = new JRadioButton("No", true);
		noLiveMusicRadioButton.setEnabled(false);
		noLiveMusicRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				enablePerformers(false);
				liveMusic = false;
			}
		});
		noLiveMusicRadioButton.setBounds(278, 257, 109, 35);
		panel_1.add(noLiveMusicRadioButton);
		liveMusicGroup.add(noLiveMusicRadioButton);

		
		
		JRadioButton theatreRadioButton = new JRadioButton("Theatre");
		theatreRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showType = "Theatre";
				enableLanguage(true);
				enableLiveMusic(false);
				enablePerformers(false);
				
			}
		});
		theatreRadioButton.setBounds(500, 165, 109, 35);
		panel_1.add(theatreRadioButton);
		typeGroup.add(theatreRadioButton);

		JRadioButton concertRadioButton = new JRadioButton("Concert");
		concertRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showType = "Concert";
				enableLanguage(false);
				enableLiveMusic(true);
			}
		});
		concertRadioButton.setBounds(167, 165, 109, 35);
		panel_1.add(concertRadioButton);
		typeGroup.add(concertRadioButton);

		JRadioButton musicalRadioButton = new JRadioButton("Musical");
		musicalRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showType = "Musical";
				enableLanguage(true);
				enableLiveMusic(true);
			}
		});
		musicalRadioButton.setBounds(278, 165, 109, 35);
		panel_1.add(musicalRadioButton);
		typeGroup.add(musicalRadioButton);

		JRadioButton operaRadioButton = new JRadioButton("Opera");
		operaRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showType = "Opera";
				enableLanguage(true);
				enableLiveMusic(true);
			}
		});
		operaRadioButton.setBounds(389, 165, 109, 35);
		panel_1.add(operaRadioButton);
		typeGroup.add(operaRadioButton);

		
		languageTextfield = new JTextField();
		languageTextfield.setBounds(167, 211, 519, 35);
		panel_1.add(languageTextfield);
		languageTextfield.setColumns(10);

	}

	public void enableLanguage(boolean value) {
		languageTextfield.setEnabled(value);
	}

	public void enableLiveMusic(boolean value) {
		yesLiveMusicRadioButton.setEnabled(value);
		noLiveMusicRadioButton.setEnabled(value);
		if (liveMusic) {
			enablePerformers(true);
		}
	}

	public void enablePerformers(boolean value) {
		performersTextfield.setEnabled(value);
	}

}
