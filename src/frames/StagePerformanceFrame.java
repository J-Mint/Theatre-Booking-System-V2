package frames;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import DBConnector.DBConnector;
import DateFormatter.DateLabelFormatter;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class StagePerformanceFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtKeywords;
	private DefaultTableModel model;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StagePerformanceFrame frame = new StagePerformanceFrame();
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
	public StagePerformanceFrame() {
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
		headerLabel.setText("Stage a performance");
		panel.setLayout(null);
		panel.add(headerLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 77, 784, 684);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 42, 760, 354);
		panel_1.add(scrollPane);

		table = new JTable();
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		String cols[] = new String[] { "Show ID", "Title", "Type", "Description", "Duration", "Language",
				"Live Music", "Performers"};
		model = (DefaultTableModel) table.getModel();
		DBConnector DBC = new DBConnector();
		DBC.connect();
		String query = "SELECT * FROM shows";
		ResultSet rs = DBC.runQuery(query);
		model.setColumnIdentifiers(cols);
		try {
			while (rs.next()) {
				String row[] = new String[8];
				for (int i = 0; i < 8; i++) {
					row[i] = rs.getString(i + 1);
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
		lblNewLabel.setBounds(12, 1, 58, 29);
		panel_1.add(lblNewLabel);

		txtKeywords = new JTextField();
		txtKeywords.setToolTipText("Type in a keyword (such as the title) to filter the results.");
		txtKeywords.setFont(new Font("Arial", Font.PLAIN, 14));
		txtKeywords.setBounds(76, 0, 591, 33);
		panel_1.add(txtKeywords);
		txtKeywords.setColumns(10);

		JButton btnNewButton_1 = new JButton("Search");
		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 14));
		btnNewButton_1.setBounds(677, 0, 95, 33);
		btnNewButton_1.addActionListener((ActionEvent e) -> {
			try {
				model.setRowCount(0);
				DBC.connect();
				String query1 = "SELECT * FROM shows WHERE show_name LIKE '%"
						+ txtKeywords.getText() + "%' OR type LIKE '%" + txtKeywords.getText()
						+ "%' OR description LIKE '%" + txtKeywords.getText() + "%' OR show_id LIKE '%"
						+ txtKeywords.getText() + "%' OR language LIKE '%" + txtKeywords.getText()
						+ "%' OR duration LIKE '%" + txtKeywords.getText() + "%' OR performers LIKE '%" + txtKeywords.getText() + "%'";
				ResultSet rs1 = DBC.runQuery(query1);
				model.setColumnIdentifiers(cols);
				try {
					while (rs1.next()) {
						String row[] = new String[8];
						for (int i = 0; i < 8; i++) {
							row[i] = rs1.getString(i + 1);
						}
						model.addRow(row);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				DBC.close();
			} catch (ArrayIndexOutOfBoundsException e2) {
				JOptionPane.showMessageDialog(null,
						"No row selected. Please select a row in the table to find tickets for that show.",
						"Error: No row selected", JOptionPane.ERROR_MESSAGE);
			}
		});
		panel_1.add(btnNewButton_1);

		JButton btnNewButton = new JButton("Stage this performance");
		
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 16));
		btnNewButton.setBounds(290, 623, 482, 50);
		panel_1.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("Date");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(302, 451, 87, 33);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Stage time");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(302, 497, 87, 29);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Circle price");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(302, 580, 87, 29);
		panel_1.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Stall price");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(300, 537, 89, 29);
		panel_1.add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setFont(new Font("Arial", Font.PLAIN, 16));
		textField.setBounds(401, 451, 373, 32);
		panel_1.add(textField);
		textField.setColumns(10);
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		panel.setLayout(null);
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePanel.setBounds(12, 407, 268, 167);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

		panel_1.add(datePanel);

		JButton selectDateButton = new JButton("Select date");
		selectDateButton.setBounds(12, 583, 268, 29);
		panel_1.add(selectDateButton);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Arial", Font.PLAIN, 16));
		textField_2.setColumns(10);
		textField_2.setBounds(401, 537, 373, 32);
		panel_1.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Arial", Font.PLAIN, 16));
		textField_3.setColumns(10);
		textField_3.setBounds(401, 580, 373, 32);
		panel_1.add(textField_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Arial", Font.PLAIN, 16));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Evening", "Matinee"}));
		comboBox.setBounds(401, 497, 373, 29);
		panel_1.add(comboBox);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Arial", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(399, 407, 373, 32);
		panel_1.add(textField_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Show ID");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(300, 407, 87, 33);
		panel_1.add(lblNewLabel_1_1);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				AdminMenuFrame aframe = new AdminMenuFrame(1);
				aframe.setVisible(true);
			}
		});
		btnCancel.setFont(new Font("Arial", Font.PLAIN, 16));
		btnCancel.setBounds(12, 623, 268, 50);
		panel_1.add(btnCancel);
		selectDateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int year = datePicker.getModel().getYear();
				int month = datePicker.getModel().getMonth() + 1;
				int day = datePicker.getModel().getDay();
				
				textField.setText(year + "-"+(month)+"-"+ day + "");
				

			}
		});

		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DBC.connect();
				String query = "CALL stagePerformance("+textField_1.getText()+", '"+textField.getText()+"', '"+comboBox.getSelectedItem().toString()+"', "+ textField_2.getText() +", "+textField_3.getText()+")";
				DBC.runQuery(query);
				query = "SELECT performance_id FROM performances WHERE date='"+textField.getText()+"' AND stage_time='"+comboBox.getSelectedItem().toString()+"'";
				ResultSet rs = DBC.runQuery(query);
				String performanceID = "";
				try {
					while (rs.next()) {
					performanceID = rs.getString(1);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for (int i = 1; i <= 200; i++) {
					int seat_id = i;
					String seatType;
					if (i <=120) {
						seatType = "stall";
					} else {
						seatType = "circle";
					}
				int seatBooked = 0;
				query = "INSERT INTO seats (seat_id, performance_id, seat_type, seat_booked) VALUES ("+seat_id+", "+performanceID +", '"+seatType+"', '"+seatBooked+"')";
					DBC.runQuery(query);
				}
				DBC.close();
				//success
				//
				
			}
		});

	}
}
