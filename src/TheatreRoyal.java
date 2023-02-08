import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TheatreRoyal implements ActionListener, MouseListener{

	private Schedule schedule;
	private Booking booking;
	private Performance currentShow;
	private JFrame frame;
	private JPanel titlePanel, bodyPanel;
	private JLabel titleLabel, bodyLabel;

	public TheatreRoyal() {
		schedule = new Schedule();
		booking = new Booking(schedule);
		start();
	}

	public void start() {
		// Welcome Screen
		frame = new JFrame();
		frame.setTitle("Theatre Booking System V3");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		ImageIcon icon = new ImageIcon("src/icon.png");
		frame.setIconImage(icon.getImage());

		titlePanel = new JPanel();
		titlePanel.setLayout(new GridBagLayout());
		titlePanel.setBackground(Color.BLACK);
		titlePanel.setPreferredSize(new Dimension(800, 100));

		titleLabel = new JLabel("Welcome to the Theatre Royal");
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
		titlePanel.add(titleLabel);

		bodyPanel = new JPanel();
		bodyPanel.setLayout(new GridBagLayout());
		bodyPanel.setBackground(Color.BLACK);
		bodyPanel.setPreferredSize(new Dimension(800, 700));

		bodyLabel = new JLabel("Click anywhere to continue!");
		bodyLabel.setForeground(Color.WHITE);
		bodyLabel.setFont(new Font("Arial", Font.BOLD, 25));
		bodyPanel.add(bodyLabel);

		frame.add(titlePanel, BorderLayout.NORTH);
		frame.add(bodyPanel, BorderLayout.SOUTH);
		frame.setSize(800, 800);
		frame.setVisible(true);
		frame.addMouseListener(this);

	}

	public Schedule getSchedule() {
		return schedule;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setCurrentShow(String dateYYMMDD, String stageTimeEorM) {
		this.currentShow = schedule.getPerformance(dateYYMMDD + stageTimeEorM);
	}

	public void clearShow() {
		currentShow = null;
	}

	// change the frame to the browse screen
	public void browse() {
		// adjust existing frame
		titleLabel.setText("Browse or Search for a Show");
		bodyLabel.setText("");
		//remove mouse listener.
		frame.removeMouseListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		browse();
		
				
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
