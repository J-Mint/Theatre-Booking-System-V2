import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Panel;
import java.awt.TextField;
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

public class BrowseFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtKeywords;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrowseFrame frame = new BrowseFrame();
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
	public BrowseFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(000, 000, 800, 800);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 800, 75);
		contentPane.add(panel);

		JLabel welcomeLabel = new JLabel();
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setBounds(0, 0, 777, 75);
		welcomeLabel.setBackground(SystemColor.menu);
		welcomeLabel.setFont(new Font("Arial", Font.BOLD, 46));
		welcomeLabel.setText("Find a Show");
		panel.setLayout(null);
		panel.add(welcomeLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 77, 784, 684);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 42, 760, 577);
		panel_1.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		String cols[] = new String[] { "Performance_ID", "Title", "Type",
				"Description", "Date", "Stage Time", "Duration", "Circle", "Stall"};
		DefaultTableModel model = (DefaultTableModel) table.getModel();
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
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setBounds(12, 13, 58, 16);
		panel_1.add(lblNewLabel);

		txtKeywords = new JTextField();
		txtKeywords.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {

			}
		});
		txtKeywords.setText("Keywords..");
		txtKeywords.setBounds(76, 11, 696, 22);
		panel_1.add(txtKeywords);
		txtKeywords.setColumns(10);

	}
}
