

public class Manager {
	
	private Schedule schedule;
	private Booking booking;
	private Performance currentShow;
	
	public Manager() {
		schedule = new Schedule();
		booking = new Booking(schedule);
	}
	
	public Schedule getSchedule() {
		return schedule;
	}
	
	public Booking getBooking() {
		return booking;
	}
	
	public void setCurrentShow(String dateYYMMDD, String stageTimeEorM) {
		currentShow = schedule.getPerformance(dateYYMMDD+stageTimeEorM);
	}
	
	public void clearShow() {
		currentShow = null;
	}
	
	
}
