

import java.util.HashMap;

public class Schedule {

	HashMap<String, Performance> schedule;
	
	public Schedule() {
		schedule = new HashMap<>();
		
	}
	
	public HashMap<String, Performance> getSchedule() {
		return schedule;
	}
	
	public void addToSchedule(String dateYYMMDD, String stageTimeEorM, Performance performance) {
		schedule.put(dateYYMMDD+stageTimeEorM, performance);
	}
	
	public Performance getPerformance(String key) {
		return schedule.get(key);
	}

	public void printSchedule() {
		//return the schedule in some usable format
		//print out every key
		for (String i : schedule.keySet()) {
			  System.out.println("\nDate+Stagetime: " + i + " (YYMMDD + M = Matinee, E = Evening)");
			  schedule.get(i).getShow().printShowDetails();
			  int seatAvailability = schedule.get(i).getSeatList().getAllAvailableSeats();
			  double seatPrice = schedule.get(i).getPrice();
			  System.out.println("Seat Price: 	£" +seatPrice);
			  System.out.println("Seats available:"+seatAvailability );
			}
	}
	
}
