

import java.util.ArrayList;

public class SeatList {

	private ArrayList<Seat> seatList;
	private double priceStall, priceCircle;
	
	public SeatList(double priceStall, double priceCircle) {
		this.priceStall= priceStall;
		this.priceCircle = priceCircle;
		seatList = new ArrayList<>();
		// create 80 circle seats and add to seat array
		for (int i = 1; i <= 80; i++) {
			CircleSeat seat = new CircleSeat(priceCircle);
			seatList.add(seat);
		}
		// create 120 stallseats and add to seat array
		for (int i = 1; i <= 120; i++) {
			StallSeat seat = new StallSeat(priceStall);
			seatList.add(seat);
		}
	}
	
	public double getCirclePrice() {
		return priceStall;
	}
	
	public double getStallPrice() {
		return priceCircle;
	}

	public int getAllAvailableSeats() {
		// create an int variable to keep track of the number of avaiable seats
		int seatsAvailable = 0;
		// go through each seat in the seatlist array
		for (Seat seat : seatList) {
			// if the seat is available, increment seatsAvailable variable
			if (seat.getAvailablity()) {
				seatsAvailable++;
			}
		}
		// after going through every seat in the array return the variable we created to
		// keep track of the number of available seats
		return seatsAvailable;
	}

	public void bookStallSeat() {
		// go through each seat in the seatlist array
		for (Seat seat : seatList) {
			// check to see if the seat is of type stall ("STA")
			if (seat.getType().equals("STA")) {
				// check to see if seat is available
				if (seat.getAvailablity()) {
					// set the first available seat to unavailable
					seat.setUnavailable();
					// stop running this method. seat has now been booked.
					break;
				}
			}
		}
	}
	public void unbookStallSeat() {
		// go through each seat in the seatlist array
				for (Seat seat : seatList) {
					// check to see if the seat is of type circle ("CIR")
					if (seat.getType().equals("STA")) {
						// check to see if seat is unavailable
						if (!seat.getAvailablity()) {
							// set the first unavailable seat to available
							seat.setAvailable();
							// stop running this method. seat has now been unbooked.
							break;
						}
					}
				}
	}

	public void bookCircleSeat() {
		// go through each seat in the seatlist array
		for (Seat seat : seatList) {
			// check to see if the seat is of type circle ("CIR")
			if (seat.getType().equals("CIR")) {
				// check to see if seat is available
				if (seat.getAvailablity()) {
					// set the first available seat to unavailable
					seat.setUnavailable();
					// stop running this method. seat has now been booked.
					break;
				}
			}
		}
	}

	public void unbookCircleSeat() {
		// go through each seat in the seatlist array
				for (Seat seat : seatList) {
					// check to see if the seat is of type circle ("CIR")
					if (seat.getType().equals("CIR")) {
						// check to see if seat is unavailable
						if (!seat.getAvailablity()) {
							// set the first unavailable seat to available
							seat.setAvailable();
							// stop running this method. seat has now been unbooked.
							break;
						}
					}
				}
	}

	public int getAvailableStallSeats() {
		// create an int variable to keep track of the number of avaiable seats
		int seatsAvailable = 0;
		// go through each seat in the seatlist array
		for (Seat seat : seatList) {
			// check to see if the seat is of type stall ("STA")
			if (seat.getType().equals("STA")) {
				// if the seat is available, increment seatsAvailable variable
				if (seat.getAvailablity()) {
					seatsAvailable++;
				}
			}
		}
		// after going through every seat in the array return the variable we created to
		// keep track of the number of available seats
		return seatsAvailable;
	}

	public int getAvailableCircleSeats() {
		// create an int variable to keep track of the number of avaiable seats
		int seatsAvailable = 0;
		// go through each seat in the seatlist array
		for (Seat seat : seatList) {
			// check to see if the seat is of type circle ("CIR")
			if (seat.getType().equals("CIR")) {
				// if the seat is available, increment seatsAvailable variable
				if (seat.getAvailablity()) {
					seatsAvailable++;
				}
			}
		}
		// after going through every seat in the array return the variable we created to
		// keep track of the number of available seats
		return seatsAvailable;
	}

}
