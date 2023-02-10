package Frames;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import DBConnector.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class BasketFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private JLabel lblNewLabel_1;
	private String price, concessionCount, ticketCount, soonestDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BasketFrame frame = new BasketFrame();
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
	public BasketFrame() {
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
		headerLabel.setText("Basket");
		panel.setLayout(null);
		panel.add(headerLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 77, 784, 684);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 42, 760, 540);
		panel_1.add(scrollPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Add more");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 16));
		btnNewButton.setBounds(10, 623, 166, 50);
		btnNewButton.addActionListener((ActionEvent e) -> {
			dispose();
			BrowseFrame bframe = new BrowseFrame();
			bframe.setVisible(true);
		});	
		panel_1.add(btnNewButton);
		
		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				CheckoutFrame cframe = new CheckoutFrame(price, concessionCount, ticketCount, soonestDate);
				cframe.setVisible(true);
			}
		});
		btnCheckout.setFont(new Font("Arial", Font.PLAIN, 16));
		btnCheckout.setBounds(362, 623, 410, 50);
		panel_1.add(btnCheckout);
		
		JButton btnRemoveTicket = new JButton("Remove ticket");
		btnRemoveTicket.setFont(new Font("Arial", Font.PLAIN, 16));
		btnRemoveTicket.setBounds(186, 623, 166, 50);
		btnRemoveTicket.addActionListener((ActionEvent e) -> {
			try {
				int index = table.getSelectedRow();
				int performanceID = Integer.parseInt((String) model.getValueAt(index, 0));
				int seatID = Integer.parseInt((String) model.getValueAt(index, 1));
				// delete record from basket
				String query = "DELETE FROM basket WHERE performance_id="+performanceID+" AND seat_id="+seatID;
				DBConnector DBC = new DBConnector();
				DBC.connect();
				DBC.runQuery(query);
				
				//make the seat available again.
				query = "UPDATE seats SET seat_booked=false WHERE performance_id="+performanceID+" AND seat_id="+seatID;
				DBC.runQuery(query);
				DBC.close();
				
				//clear table
				model.setRowCount(0);
				//load table again.
				loadTable();
				
			} catch (ArrayIndexOutOfBoundsException e1) {
				JOptionPane.showMessageDialog(null, "No row selected. Please select a row in the table to remove ticket.", "Error: No row selected",JOptionPane.ERROR_MESSAGE);
		}	
		});		
		panel_1.add(btnRemoveTicket);
		
		JLabel lblNewLabel = new JLabel("Total:    £");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(362, 598, 60, 14);
		panel_1.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(424, 598, 46, 14);
		panel_1.add(lblNewLabel_1);
		
		loadTable();
		
		

	}
	
	public void loadTable() {
		String cols[] = new String[] { "Performance ID", "Seat No", "Seat Type", "Price",
				"Title", "Show Type", "Date", "Stage Time", "Duration", "Description"};
		model = (DefaultTableModel) table.getModel();
		DBConnector DBC = new DBConnector();
		DBC.connect();
		String query = "SELECT performances.performance_id, seat_id, standard_or_concession,  price, show_name, type , date, stage_time, duration, description  FROM basket JOIN performances ON basket.performance_id  JOIN shows ON performances.show_id WHERE basket.performance_id = performances.performance_id AND shows.show_id = performances.show_id";
		ResultSet rs = DBC.runQuery(query);
		model.setColumnIdentifiers(cols);
		try {
			while (rs.next()) {
				String row[] = new String[10];
				for (int i = 0; i < 10; i++) {
					row[i]=rs.getString(i+1);  
				}
				model.addRow(row);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		query = "SELECT SUM(price) FROM basket";
		rs = DBC.runQuery(query);
		try {
			while (rs.next()) {
				price = rs.getString(1);
				lblNewLabel_1.setText(price);
					 
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query = "SELECT COUNT(*) FROM basket WHERE standard_or_concession='Concession'";
		rs = DBC.runQuery(query);
		try {
			while (rs.next()) {
				concessionCount = rs.getString(1);					 
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		query = "SELECT COUNT(*) FROM basket";
		rs = DBC.runQuery(query);
		try {
			while (rs.next()) {
				ticketCount = rs.getString(1);					 
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		query = "SELECT MIN(date) FROM basket JOIN performances ON basket.performance_id  JOIN shows ON performances.show_id WHERE basket.performance_id = performances.performance_id AND shows.show_id = performances.show_id";
		rs = DBC.runQuery(query);
		try {
			while (rs.next()) {
				soonestDate = rs.getString(1);					 
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DBC.close();
	}
}
