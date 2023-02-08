import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.JTextPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Panel;
import java.awt.TextField;
import javax.swing.JTextArea;
import java.awt.SystemColor;

public class BrowseFrame extends JFrame {

	private JPanel contentPane;

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
		
		JTextPane welcome = new JTextPane();
		welcome.setBounds(0, 0, 777, 75);
		welcome.setBackground(SystemColor.menu);
		welcome.setFont(new Font("Arial", Font.BOLD, 46));
		welcome.setEditable(false);
		welcome.setText("Find a Show");
		StyledDocument doc = welcome.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		panel.setLayout(null);
		panel.add(welcome);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 77, 784, 684);
		
		
		JTextPane cont = new JTextPane();
		cont.setBackground(SystemColor.menu);
		cont.setFont(new Font("Arial", Font.BOLD, 35));
		cont.setEditable(false);
		cont.setText("Click anywhere\nto find shows and book tickets");
		cont.setBounds(0, 0, 784, 684);
		
		StyledDocument doc1 = cont.getStyledDocument();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc1.setParagraphAttributes(0, doc1.getLength(), center, false);
		panel_1.add(cont);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
	}
}
