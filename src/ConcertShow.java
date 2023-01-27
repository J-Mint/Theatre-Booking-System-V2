


public class ConcertShow extends Show{
	
	public ConcertShow(String title, int duration, String description, String performers) {
		super(title, description, duration, "CON");
		//  Where live music is a part of a show, the name(s) of the principle performers or performing group should be displayed prominently within the details of the show.
		updateDescription(performers + "; " + description);

	}

}

