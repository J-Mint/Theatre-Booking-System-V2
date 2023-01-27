

public class OperaShow extends Show{
	private String language;
	private boolean hasLiveMusic;
	
	public OperaShow(String title, int duration, String language, String description, boolean hasLiveMusic, String performers) {
		super(title, description, duration, "OPE");
		this.language = language;
		this.hasLiveMusic = hasLiveMusic;
		if (hasLiveMusic) {
			//  Where live music is a part of a show, the name(s) of the principle performers or performing group should be displayed prominently within the details of the show.
			updateDescription(performers + "; " + description);
		}
		
	}

	public String getLanguage() {
		return language;
	}
	
	public boolean getHasLiveMusic() {
		return hasLiveMusic;
	}
	

}