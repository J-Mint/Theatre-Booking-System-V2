

public class Performance {

	private Show show;
	private SeatList seatList;
	private double price;

	public Performance(Show show, double price) {
		this.show = show;
		this.price = price;
		seatList = new SeatList(price, price);
	}
	
	public double getPrice() {
		return price;
	}
	
	public SeatList getSeatList() {
		return seatList;
	}
	
	public Show getShow() {
		return show;
	}
	
}
