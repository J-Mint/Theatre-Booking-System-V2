

public class Main {

	public static void main(String[] args) {
		TheatreRoyal theatre = new TheatreRoyal();
		
		
		TheatreShow show1 = new TheatreShow("ShowTitle1", 60, "English", "this is show 1");
		ConcertShow show2 = new ConcertShow("ShowTitle2", 35, "this is show2", "performers1");
		OperaShow show3 = new OperaShow("ShowTitle3", 10, "English", "this is show 3", true, "performers2");
		MusicalShow show4 = new MusicalShow("ShowTitle4", 90, "Spanish", "this is show 4", false, null);
		
		Performance perf1 = new Performance(show1, 10);
		Performance perf2 = new Performance(show2, 20);
		Performance perf3 = new Performance(show3, 30);
		Performance perf4 = new Performance(show4, 40);
		
		theatre.getManager().getSchedule().addToSchedule("221208", "E", perf1);
		theatre.getManager().getSchedule().addToSchedule("221208", "M", perf2);
		theatre.getManager().getSchedule().addToSchedule("221209", "E", perf3);
		theatre.getManager().getSchedule().addToSchedule("221209", "M", perf4);
		
		theatre.getManager().setCurrentShow("221208", "E");
		theatre.start();
		
		
	}

}
