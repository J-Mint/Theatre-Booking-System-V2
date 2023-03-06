package frames;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import javax.swing.DefaultComboBoxModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class AddShowFrame extends JFrame {

	private JPanel contentPane;
	private JTextField titleTextfield;
	private JTextField descriptionTextfield;
	private JTextField durationTextfield;
	private JTextField performersTextfield;
	private JTextField languageTextfield;
	private String showType;
	private boolean liveMusic;
	private JComboBox liveComboBox;

	public AddShowFrame() {
		showType = "Musical";
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
		// create a new panel for body content
		JPanel bodyPanel = new JPanel();
		bodyPanel.setBounds(0, 77, 784, 684);
		contentPane.add(bodyPanel);
		bodyPanel.setLayout(null);

		// create the labels to define what should go in each text field.
		JLabel titleLabel = new JLabel("Title");
		titleLabel.setBounds(43, 28, 89, 35);
		bodyPanel.add(titleLabel);

		JLabel descriptionLabel = new JLabel("Description");
		descriptionLabel.setBounds(43, 74, 89, 34);
		bodyPanel.add(descriptionLabel);

		JLabel durationLabel = new JLabel("Duration");
		durationLabel.setBounds(43, 119, 89, 35);
		bodyPanel.add(durationLabel);

		JLabel typeLabel = new JLabel("Type");
		typeLabel.setBounds(43, 165, 89, 35);
		bodyPanel.add(typeLabel);

		JLabel languageLabel = new JLabel("Language");
		languageLabel.setBounds(43, 211, 89, 35);
		bodyPanel.add(languageLabel);

		JLabel liveMusicLabel = new JLabel("Live music");
		liveMusicLabel.setBounds(43, 257, 89, 35);
		bodyPanel.add(liveMusicLabel);

		JLabel performersLabel = new JLabel("Performers");
		performersLabel.setBounds(43, 303, 89, 35);
		bodyPanel.add(performersLabel);

		// create the text fields to get user input.
		titleTextfield = new JTextField();
		titleTextfield.setBounds(167, 27, 519, 35);
		bodyPanel.add(titleTextfield);
		titleTextfield.setColumns(10);

		descriptionTextfield = new JTextField();
		descriptionTextfield.setBounds(167, 74, 519, 35);
		bodyPanel.add(descriptionTextfield);
		descriptionTextfield.setColumns(10);

		durationTextfield = new JTextField();
		durationTextfield.setBounds(167, 119, 519, 35);
		bodyPanel.add(durationTextfield);
		durationTextfield.setColumns(10);

		performersTextfield = new JTextField();
		performersTextfield.setBounds(167, 303, 519, 35);
		bodyPanel.add(performersTextfield);
		performersTextfield.setColumns(10);

		languageTextfield = new JTextField();
		languageTextfield.setBounds(167, 211, 519, 35);
		bodyPanel.add(languageTextfield);
		languageTextfield.setColumns(10);

		// create a drop down menu to limit user input
		JComboBox typeComboBox = new JComboBox();
		typeComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int typeIndex = typeComboBox.getSelectedIndex();
				if (typeIndex == 0) {
					showType = "Concert";
					enableLanguage(false);
					enableLiveMusicBox(true);
				} else if (typeIndex == 1 || typeIndex == 2) {
					enableLanguage(true);
					enableLiveMusicBox(true);
					if (typeIndex == 1) {
						showType = "Musical";
					} else {
						showType = "Opera";
					}
				} else {
					enableLanguage(true);
					enableLiveMusicBox(false);
					showType = "Theatre";
					enablePerformers(false);
				}
			}
		});
		typeComboBox.setModel(new DefaultComboBoxModel(new String[] { "Concert", "Musical", "Opera", "Theatre" }));
		typeComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
		typeComboBox.setBounds(167, 163, 519, 35);
		bodyPanel.add(typeComboBox);
		liveComboBox = new JComboBox();
		liveComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int liveIndex = liveComboBox.getSelectedIndex();
				if (liveIndex == 0) {
					liveMusic = true;
					enablePerformers(true);

				} else {
					liveMusic = false;
					enablePerformers(false);
				}
			}
		});
		liveComboBox.setModel(new DefaultComboBoxModel(new String[] { "Yes", "No" }));
		liveComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
		liveComboBox.setBounds(167, 257, 519, 35);
		bodyPanel.add(liveComboBox);
		typeComboBox.setSelectedIndex(1);

		// create the buttons to confirm and cancel
		JButton addShowButton = new JButton("Add show");
		addShowButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				DBConnector DBC = new DBConnector();
				DBC.connect();
				String query = "CALL insertShow('" + titleTextfield.getText() + "', '" + showType + "', '"
						+ descriptionTextfield.getText() + "', " + durationTextfield.getText() + ", '"
						+ languageTextfield.getText() + "' , " + liveMusic + ", '" + performersTextfield.getText()
						+ "')";
				try {
					DBC.runQuery(query);

				} catch (Exception e1) {
					JFrame frame = new JFrame("");
					JOptionPane.showMessageDialog(frame,
							"There was an error adding the show. Try adjusting the input formats.");
				}
				DBC.close();
				JFrame frame = new JFrame("");
				JOptionPane.showMessageDialog(frame, "Show Added Successfully.");
				// go back to the admin menu frame
				dispose();
				AdminMenuFrame aframe = new AdminMenuFrame(1);
				aframe.setVisible(true);
			}

		});
		addShowButton.setBounds(255, 638, 519, 35);
		bodyPanel.add(addShowButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				AdminMenuFrame aframe = new AdminMenuFrame(1);
				aframe.setVisible(true);
			}
		});
		cancelButton.setBounds(10, 638, 235, 35);
		bodyPanel.add(cancelButton);

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
		checkoutLabel.setText("Add a Show");
		headerPanel.setLayout(null);
		headerPanel.add(checkoutLabel);
	}

	private void enableLanguage(boolean value) {
		languageTextfield.setEnabled(value);
		if (value == false) {
			languageTextfield.setText("");
		}
	}

	private void enableLiveMusicBox(boolean value) {
		liveComboBox.setEnabled(value);
		if (liveMusic) {
			enablePerformers(true);
		} else {
			liveComboBox.setSelectedIndex(1);
		}
	}

	private void enablePerformers(boolean value) {
		performersTextfield.setEnabled(value);
		if (value == false) {
			performersTextfield.setText("");
		}

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
