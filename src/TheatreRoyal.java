

public class TheatreRoyal {
	private Manager manager;
	
	public TheatreRoyal() {
		manager = new Manager();
	}
	
	public Manager getManager() {
		return manager;
	}
	
	public void start() {
		manager.getBooking().startBookingProcess();
	}

}
