package frames;
import DBConnector.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.beans.PropertyChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;

public class BrowseFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtKeywords;
	private DefaultTableModel model;
	private int userID;

	
	public BrowseFrame(int userID) {
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

		JScrollPane scrollPane = new JScrollPane();
		if (this.userID == 1) {
			scrollPane.setBounds(12, 42, 760, 500);
			JButton btnNewButton_2 = new JButton("Back to Admin Menu");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					AdminMenuFrame aframe = new AdminMenuFrame(1);
					aframe.setVisible(true);
				}
			});
			btnNewButton_2.setFont(new Font("Arial", Font.PLAIN, 16));
			btnNewButton_2.setBounds(12, 562, 760, 50);
			bodyPanel.add(btnNewButton_2);
		} else {
		scrollPane.setBounds(12, 42, 760, 570);
		}
		bodyPanel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		String cols[] = new String[] { "Performance_ID", "Title", "Type",
				"Description", "Date", "Stage Time", "Duration", "Circle", "Stall"};
		model = (DefaultTableModel) table.getModel();
		DBConnector DBC = new DBConnector();
		DBC.connect();
		String query = "SELECT performance_ID, show_name, type, description, date, stage_time, duration, circle_price, stall_price FROM performances JOIN shows on performances.show_id = shows.show_id";
		ResultSet rs = DBC.runQuery(query);
		model.setColumnIdentifiers(cols);
		try {
			while (rs.next()) {
				String row[] = new String[9];
				for (int i = 0; i < 9; i++) {
					row[i]=rs.getString(i+1);  
				}
				model.addRow(row);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBC.close();
		
		
		JLabel lblNewLabel = new JLabel("Search");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setBounds(12, 0, 58, 29);
		bodyPanel.add(lblNewLabel);

		txtKeywords = new JTextField();
		txtKeywords.setToolTipText("Type in a keyword (such as the title) to filter the results.");
		txtKeywords.setFont(new Font("Arial", Font.PLAIN, 14));
		txtKeywords.setBounds(76, 0, 591, 33);
		bodyPanel.add(txtKeywords);
		txtKeywords.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Search");
		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 14));
		btnNewButton_1.setBounds(677, 0, 95, 33);
		btnNewButton_1.addActionListener((ActionEvent e) -> {
			try {
				model.setRowCount(0);
				DBC.connect();
				String query1 = "SELECT performance_ID, show_name, type, description, date, stage_time, duration, circle_price, stall_price FROM performances JOIN shows on performances.show_id = shows.show_id WHERE show_name LIKE '%"+txtKeywords.getText()+"%' OR type LIKE '%"+txtKeywords.getText()+"%' OR description LIKE '%"+txtKeywords.getText()+"%' OR date LIKE '%"+txtKeywords.getText()+"%' OR stage_time LIKE '%"+txtKeywords.getText()+"%' OR duration LIKE '%"+txtKeywords.getText()+"%'";
						//"SELECT performance_ID, show_name, type, description, date, stage_time, duration, circle_price, stall_price FROM performances JOIN shows on performances.show_id = shows.show_id WHERE show_name LIKE '%"+txtKeywords.getText()+"%'";
				ResultSet rs1 = DBC.runQuery(query1);
				model.setColumnIdentifiers(cols);
				try {
					while (rs1.next()) {
						String row[] = new String[9];
						for (int i = 0; i < 9; i++) {
							row[i]=rs1.getString(i+1);  
						}
						model.addRow(row);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				DBC.close();
			} catch (ArrayIndexOutOfBoundsException e2) {
				JOptionPane.showMessageDialog(null, "No row selected. Please select a row in the table to find tickets for that show.", "Error: No row selected",JOptionPane.ERROR_MESSAGE);
		}	
		});
		bodyPanel.add(btnNewButton_1);
		
		
		JButton btnNewButton = new JButton("Find tickets");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 16));
		btnNewButton.setBounds(12, 623, 760, 50);
		btnNewButton.addActionListener((ActionEvent e) -> {
			try {
				int index = table.getSelectedRow();
				int performanceID = Integer.parseInt((String) model.getValueAt(index, 0));
				dispose();
				ShowDetailedInfoFrame dframe = new ShowDetailedInfoFrame(performanceID, userID);
				dframe.setVisible(true);
				
				
			} catch (ArrayIndexOutOfBoundsException e1) {
				JOptionPane.showMessageDialog(null, "No row selected. Please select a row in the table to find tickets for that show.", "Error: No row selected",JOptionPane.ERROR_MESSAGE);
		}	
		});
		bodyPanel.add(btnNewButton);

	}

	private void configureHeaderPanel() {
		JPanel headerPanel = new JPanel();
		headerPanel.setBounds(0, 0, 800, 75);
		contentPane.add(headerPanel);

		JLabel headerLabel = new JLabel();
		headerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (userID == 0 ) {
				dispose();
				LoginFrame lframe = new LoginFrame(userID);
				lframe.setVisible(true);
				}
			}
		});
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerLabel.setBounds(0, 0, 777, 75);
		headerLabel.setBackground(SystemColor.menu);
		headerLabel.setFont(new Font("Arial", Font.BOLD, 46));
		headerLabel.setText("Find a Show");
		headerPanel.setLayout(null);
		headerPanel.add(headerLabel);
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
