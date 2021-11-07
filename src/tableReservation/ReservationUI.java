package tableReservation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class ReservationUI {
	
	private ReservationController reservationController = ReservationController.getInstance();
	private final String DATE_FORMAT = "dd/MM/yyyy";
	private final String TIME_FORMAT = "HH:mm";
	private static ReservationUI reservationUI = null;
	private static Scanner in = new Scanner(System.in);
	
	public ReservationUI() {
		 
	}
	
	public static ReservationUI getInstance()
	{
		if (reservationUI == null)
			reservationUI = new ReservationUI();
		return reservationUI;
	}
	
	public void run()
	{
		String dateStr;
		String timeStr;
		String name;
		String contact;
		int numberOfPax;

		int choice = this.displayOptions();
		while (choice != 0) {
			switch (choice) {
				case 1:
					boolean validDateFormat;
					boolean validDate;
					LocalDate appointmentDate;
					do {
						do {
							System.out.println("Please enter date of reservation: dd/mm/yyyy");
							dateStr = in.next();
							validDateFormat = isValidDateFormat(dateStr, DATE_FORMAT);
						} while (!validDateFormat);
						DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						appointmentDate = LocalDate.parse(dateStr,dateFormatter);
						validDate = isValidDate(appointmentDate);
					}while(!validDate);


					do {
						System.out.println("Please enter time of reservation: HH:mm");
						timeStr = in.next();
						validDateFormat = isValidDateFormat(timeStr, TIME_FORMAT);
					}while(!validDateFormat);

					DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
					LocalTime appointmentTime = LocalTime.parse(timeStr,timeFormatter);

					System.out.println("Please enter the number of person(s): ");
					numberOfPax = in.nextInt();
					in.nextLine();

					System.out.println("Please enter your name: ");
					name = in.nextLine();

					System.out.println("Please enter your contact: ");
					contact = in.nextLine();

					reservationController.createReservation(name, contact, numberOfPax, appointmentDate, appointmentTime);
					break;
				case 2:
					System.out.println("Please enter your contact: ");
					contact = in.nextLine();
					reservationController.checkReservation(contact);
					break;
				case 3:
					System.out.println("Please enter your contact: ");
					contact = in.nextLine();
					reservationController.removeReservation(contact);

					break;
				case 4:
					reservationController.displayAllReservations();
					break;

			}

			choice = this.displayOptions();
		}


	}

	private boolean isValidDateFormat(String dateTime, String FORMAT){
		try {
			DateFormat dateFormat = new SimpleDateFormat(FORMAT);
			dateFormat.setLenient(false);
			dateFormat.parse(dateTime);

			return true;
		} catch (ParseException ex) {
			return false;
		}
	}
	 /** assume that can only make reservation at least one day in advance */
	private boolean isValidDate(LocalDate appointmentDate){
		if(appointmentDate.isAfter(LocalDate.now())) return false;
		return true;
	}


	
	
	 private int displayOptions() {
	        System.out.println("--------Reservation System--------");
		 	System.out.println("0. Go back to MainUI");
	        System.out.println("1. Create a new reservation");
	        System.out.println("2. Check reservation");
	        System.out.println("3. Remove reservation");
	        System.out.println("4. Display all reservations");
	        System.out.println("Your choice: ");
	        int choice = in.nextInt();
	        in.nextLine();
	        return choice;
	    }
}
