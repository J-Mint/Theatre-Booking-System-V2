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

public class TheatreRoyal {

	private Schedule schedule;
	private Performance currentShow;
	private JFrame frame;
	private JPanel titlePanel, bodyPanel;
	private JLabel titleLabel, bodyLabel;

	public TheatreRoyal() {
		schedule = new Schedule();
		start();
	}

	public void start() {
		
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


	
}
