package tableReservation;

import java.io.*;
import java.util.List;
import java.util.Scanner;

import java.time.LocalDateTime;    
import java.time.format.DateTimeFormatter;

public class ReservationUI {
	
	private ReservationController reservationController = ReservationController.getInstance();
	
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
		String date;
		int time; 
		String name; 
		String contact; 
		int numberOfPax;
		String appointmentDateTime = null;
		
		int choice = this.displayOptions();
		while (choice != 0)
		{
			switch(choice) {
				case 1: 
					System.out.println("Please enter date of reservation: ");
					date = in.nextLine();
					if (date.length() == 1)
						date = '0' + date;

					System.out.println("Please pick a reservation time");
					System.out.println("1. 7pm - 9pm");
					System.out.println("2. 9pm - 11pm");
					time = in.nextInt();

					System.out.println("Please enter the number of person(s): ");
					numberOfPax = in.nextInt();
					in.nextLine();

					switch (time)
					{
						case 1:
							appointmentDateTime = String.format("2019-11-%s 19:00", date);
							break;
						case 2:
							appointmentDateTime = String.format("2019-11-%s 21:00", date);
							break;
					}

					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
					LocalDateTime dateTime = LocalDateTime.parse(appointmentDateTime, formatter);

					
					System.out.println("Please enter your name: ");
					name = in.nextLine();
					
					System.out.println("Please enter your contact: ");
					contact = in.nextLine();
					while (true)
					{
						try {
							if (contact.length() != 8)
								throw new Exception("Invalid contact number!");
						} catch (Exception e) {
							System.out.println(e.getMessage());
							System.out.println("Please enter your contact: ");
							contact = in.nextLine();
							continue;
						}
						break;
					}
					
					int reservationId = reservationController.createReservation(name, contact, numberOfPax, dateTime);
					reservationController.checkReservation(contact);
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
