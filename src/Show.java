

public abstract class Show {
	private String title, showType, description;
	private int duration;

	public Show(String title, String description, int duration, String showType) {
		this.title = title;
		this.duration = duration;
		this.description = description;
		this.showType = showType;
	}

	public void updateDescription(String description) {
		this.description = description;
	}

	public String getShowType() {
		return showType;
	}

	public String getTitle() {
		return title;
	}

	public int getDuration() {
		return duration;
	}

	public String getDescription() {
		return description;
	}

	public void printShowDetails() {
		System.out.println("Title:		" + title);
		System.out.println("Type of show:	" + showType);
		System.out.println("Description:	" + description);
		System.out.println("Duration:	" + duration + " minutes");
		
	}
}
