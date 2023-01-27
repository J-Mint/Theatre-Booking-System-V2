

public abstract class Seat {

	private double price;
	private boolean available;
	private String type;

	public Seat(double price, String type) {
		this.price = price;
		available = true;
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public void setUnavailable() {
		available = false;
	}
	
	public void setAvailable() {
		available = true;
	}
	
	public boolean getAvailablity() {
		return available;
	}

	public double getPrice() {
		return price;
	}
	

}
