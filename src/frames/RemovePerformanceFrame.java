package frames;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

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

import DBConnector.DBConnector;

public class RemovePerformanceFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtKeywords;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemovePerformanceFrame frame = new RemovePerformanceFrame();
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
	public RemovePerformanceFrame() {
		int userID = 1;
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
		headerLabel.setText("Remove Performance");
		panel.setLayout(null);
		panel.add(headerLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 77, 784, 684);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 42, 760, 508);
		panel_1.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		String cols[] = new String[] { "Performance ID", "Show ID", "Title", "Date", "Stage Time", "Duration",
				"Stall Price", "Circle Price" };
		model = (DefaultTableModel) table.getModel();
		DBConnector DBC = new DBConnector();
		DBC.connect();
		String query = "SELECT performance_id, performances.show_id, show_name, date, stage_time, duration, stall_price, circle_price from performances JOIN shows ON performances.show_id = shows.show_id;";
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
		lblNewLabel.setBounds(12, 0, 58, 29);
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
				String searchText = txtKeywords.getText();
				String query1 = "SELECT * FROM shows WHERE show_name LIKE '%" + searchText + "%' OR type LIKE '%"
						+ searchText + "%' OR description LIKE '%" + searchText + "%' OR show_id LIKE '%" + searchText
						+ "%' OR language LIKE '%" + searchText + "%' OR duration LIKE '%" + searchText + "%'";
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
						"No row selected. Please select a row in the table to remove a performance.",
						"Error: No row selected", JOptionPane.ERROR_MESSAGE);
			}
		});
		panel_1.add(btnNewButton_1);

		JButton btnNewButton = new JButton("Remove Performance");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 16));
		btnNewButton.setBounds(12, 562, 760, 50);
		btnNewButton.addActionListener((ActionEvent e) -> {
			try {
				int index = table.getSelectedRow();
				int performanceID = Integer.parseInt((String) model.getValueAt(index, 0));
				int confirm = JOptionPane.showConfirmDialog(null,
						"You are about to remove performance with ID: " + performanceID + ". Is this correct?");
				if (confirm == 0) {
					DBC.connect();
					String query2 = "SET FOREIGN_KEY_CHECKS=0";
					DBC.runQuery(query2);
					query2 = "DELETE FROM basket where performance_id = "+performanceID;
					query2 = "DELETE FROM seats WHERE performance_id =" + performanceID;
					DBC.runQuery(query2);
					query2 = "DELETE FROM performances WHERE performance_id=" + performanceID;
					DBC.runQuery(query2);
					query2 = "SET FOREIGN_KEY_CHECKS=1";
					DBC.runQuery(query2);
					model.setRowCount(0);
					model.setColumnIdentifiers(cols);
					ResultSet rs2 = DBC.runQuery(query);
					try {
						while (rs2.next()) {
							String row[] = new String[8];
							for (int i = 0; i < 8; i++) {
								row[i] = rs2.getString(i + 1);
							}
							model.addRow(row);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			} catch (ArrayIndexOutOfBoundsException e1) {
				JOptionPane.showMessageDialog(null,
						"No row selected. Please select a row in the table to remove a performance.",
						"Error: No row selected", JOptionPane.ERROR_MESSAGE);
			}
			DBC.close();
		});
		panel_1.add(btnNewButton);

		JButton btnBackToAdmin = new JButton("Back to Admin Menu");
		btnBackToAdmin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				AdminMenuFrame aframe = new AdminMenuFrame(1);
				aframe.setVisible(true);
			}
		});
		btnBackToAdmin.setFont(new Font("Arial", Font.PLAIN, 16));
		btnBackToAdmin.setBounds(12, 623, 760, 50);
		panel_1.add(btnBackToAdmin);

	}

}
