
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class Booking {

	private ArrayList<Ticket> basket;
	private InputReader reader;
	private Schedule schedule;
	private double postageFee;
	

	public Booking(Schedule schedule) {
		basket = new ArrayList<>();
		this.schedule = schedule;
		reader = new InputReader();
	}

//	public void printOptionMenu() {
//		// option screen
//
//		int choice = 0;
//		System.out.println("\n\nWould you like to: ");
//		System.out.println("1:	Browse through performances");
//		// log in?
//		// register?
//		System.out.println("2:	Search for a specific show");
//		System.out.println("3:	Buy a ticket");
//		System.out.println("4:	Display basket");
//		System.out.println("5:	Remove a ticket from basket");
//		System.out.println("6:	Go to checkout and complete purchase");
//		while (choice < 1 || choice > 6) {
//			choice = reader.getNumber("\nEnter the number of the function you wish to perform");
//		}
//		if (choice == 1) {
//			browse();
//			printOptionMenu();
//		} else if (choice == 2) {
//			search();
//			printOptionMenu();
//		} else if (choice == 3) {
//			placeOrder();
//			printOptionMenu();
//		} else if (choice == 4) {
//			displayBasket();
//			printOptionMenu();
//		} else if (choice == 5) {
//			displayBasket();
//			int ticketToDelete = -9999;
//// FIXME add exception
//			while (ticketToDelete > basket.size() || ticketToDelete < 0) {
//				ticketToDelete = reader.getNumber("Enter ticket number:");
//			}
//			Ticket ticket = basket.get(ticketToDelete + 1);
//			removeTicket(ticket);
//			System.out.println("Ticket removed from basket");
//			System.out.println("New basket total: " + getBasketTotal());
//			printOptionMenu();
//		} else if (choice == 6) {
//			if (basket.size() > 0) {
//				checkout();
//				// next customer
//				startBookingProcess();
//			} else {
//				System.out.println("\n\nYour basket is empty!");
//				printOptionMenu();
//			}
//		}
//
//	}

//	public void browse() {
//		schedule.printSchedule();
//	}
//
//	public void search() {
//		// query the database
//	}

	public void placeOrder() {
		String date = reader.getText("Enter the performance date in the format YYMMDD");
		while (date.length() < 6 || date.length() > 6) {
			date = reader.getText("Enter the performance date in the format YYMMDD");
		}
		String stageTime = reader.getText("Enter the performance stage time ('E' for evening, 'M' for matinee)");
		if (!schedule.getSchedule().containsKey(date + stageTime)) {
			// doesnt contain key = back to the start
			System.out.println("Performance not found at specified date and stage time");
			placeOrder();
		}
		Performance performance = schedule.getPerformance(date + stageTime);

		String seatType = reader.getText("What type of seat do you want? ('S' for stall, 'C' for circle");
		if (seatType.equals("S")) {
			int availableSeats = performance.getSeatList().getAvailableStallSeats();
			if (availableSeats > 0) {
				System.out.println("There are " + availableSeats + " stall seats available");
				int quantity = reader.getNumber("How many stall seats do you want to book?");
				if (quantity <= availableSeats) {
					for (int i = 0; i < quantity; i++) {
						String ticketType = reader.getText(
								"\nWill this ticket be for standard or concession entry?('S' for standard, 'C' for concession)");
						double price = performance.getSeatList().getStallPrice();
						if (ticketType.equals("C")) {
							System.out.println("Ticket type set to concession");
							price = (price / 4) * 3;
						} else {
							System.out.println("Ticket type set to standard");
							ticketType = "S";
						}
						Ticket ticket = new Ticket(performance, "STA", ticketType, price);
						basket.add(ticket);
						performance.getSeatList().bookStallSeat();
						System.out.println("\n Ticket was successfully added to basket");
					}
				}

			}
		} else if (seatType.equals("C")) {
			int availableSeats = performance.getSeatList().getAvailableCircleSeats();
			if (availableSeats > 0) {
				System.out.println("There are " + availableSeats + " circle seats available");
				int quantity = reader.getNumber("How many circle seats do you want to book?");
				if (quantity <= availableSeats) {
					for (int i = 0; i < quantity; i++) {
						String ticketType = reader.getText(
								"\nWill this ticket be for standard or concession entry?('S' for standard, 'C' for concession)");
						double price = performance.getSeatList().getStallPrice();
						if (ticketType.equals("C")) {
							System.out.println("Ticket type set to concession");
							price = (price / 4) * 3;
						} else {
							System.out.println("Ticket type set to standard");
							ticketType = "S";
						}
						Ticket ticket = new Ticket(performance, "CIR", ticketType, price);
						basket.add(ticket);
						performance.getSeatList().bookCircleSeat();
						System.out.println("\n Ticket was successfully added to basket");
					}
				}
			}
		} else {
			System.out.println("\nSeat type entered incorrectly, please try again");

		}

	}

	public void removeTicket(Ticket ticket) {
		basket.remove(ticket);
		String type = ticket.getTicketType();
		if (type == "STA") {
			ticket.getPerformance().getSeatList().unbookStallSeat();
		} else {
			ticket.getPerformance().getSeatList().unbookCircleSeat();
		}

	}

	public void displayBasket() {
		int counter = 1;
		for (Ticket ticket : basket) {
			System.out.println("Ticket: " + counter);
			ticket.printTicketDetails();
			System.out.println("-----------------------------------------------");
			counter++;
		}
		System.out.println("Basket total: " + getBasketTotal());
	}

	public void checkout() {
		// ask for their details - name, address, card number
		String name = reader.getText("Please enter your first and last name");
		String address = reader.getText("Please enter your full address");
		String cardNo = reader.getText("Please enter your 16 digit card number");
		while (cardNo.length() < 16) {
			cardNo = reader.getText("Please enter your 16 digit card number");
		}
		cardNo = Encryption.encrypt("card", cardNo);

		System.out.println("Your basket total is " + getBasketTotal());
		String collect = reader.getText(
				"Would you like to collect your tickets or have them posted (Type 'C' to collect or 'P' to deliver)");
		if (collect.equals("P") || collect.equals("p")) {
			System.out.println("You have opted to have your tickets posted");
			// go through every ticket in basket
			postageFee = 0;
			int concessionCount = 0;
			for (Ticket t : basket) {
				// if ticket type is c, increment counter
				if (t.getTicketType().equals("C")) {
					concessionCount++;
				}
			}
			if (concessionCount == basket.size()) {
				// if c = basket size, posting is free
				postageFee = 0;
			} else if (concessionCount > 0) {
				// else if c is > 0, posting is only £1
				postageFee = 1;
			} else {
				// else posting = £1 x basket size
				postageFee = basket.size();
			}
		}

		// ask to confirm purchase
		System.out.println("Your basket total is " + getBasketTotal());
		String confirm = reader.getText("Are you happy to confirm your order? ('y' for yes or 'n' for no)");
		// if happy to complete purchase
		if (confirm.equals("y") || confirm.equals("Y")) {
			// complete - generate order file and clear basket.
			System.out.println("\nOrder confirmed");
			orderNo++;
			String filename = "order0000" + orderNo + ".txt";
			try {
				FileWriter myWriter = new FileWriter("C:\\Users\\Joe\\Downloads\\" + filename);
				myWriter.write("Order0000" + orderNo);
				myWriter.write("\n Name: " + name);
				myWriter.write("\n Address: " + address);
				myWriter.write("\nCard Number: " + cardNo);
				int counter = 1;
				for (Ticket ticket : basket) {
					myWriter.write("\nTicket " + counter + ":");
					String show = ticket.getPerformance().getShow().getTitle();
					myWriter.write("\nTitle: " + show);
					String ticketType = ticket.getTicketType();
					myWriter.write("\nTicket type: " + ticketType);
					String seatType = ticket.getSeatType();
					myWriter.write("\nSeat type: " + seatType);
					String price = "" + ticket.getPrice();
					myWriter.write("\nPrice: " + price);
					myWriter.write("\n-------------------------------------");
					counter++;
				}
				myWriter.close();
				System.out.println("Order file successfully generated. Check filepath folder");
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
			basket.clear();
			postageFee = 0;
		} else {
			// else not happy to complete purchase take back to main menu
			printOptionMenu();
		}

	}

	public double getBasketTotal() {
		double total = 0;
		for (Ticket ticket : basket) {
			total += ticket.getPrice();
		}
		total = total + postageFee;
		return total;
	}

}
