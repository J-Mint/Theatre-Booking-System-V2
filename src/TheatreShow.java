

public class TheatreShow extends Show {

	private String language;

	public TheatreShow(String title, int duration, String language, String description) {
		super(title, description, duration, "THE");
		this.language = language;
	}

	public String getLanguage() {
		return language;
	}
}