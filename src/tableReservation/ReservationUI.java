package tableReservation;

import java.io.*;
import java.util.List;
import java.util.Scanner;

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
		int date;
		int time; 
		String name; 
		String contact; 
		int numberOfPax;
		
		int choice = this.displayOptions();
		while (choice != 0)
		{
			switch(choice) {
				case 1: 
					System.out.println("Please enter date of reservation: ");
					date = in.nextInt();
					System.out.println("Please enter time of reservation (24 hour): ");
					time = in.nextInt();
					System.out.println("Please enter the number of person(s): ");
					numberOfPax = in.nextInt();
					in.nextLine();
					String appointmentDateTime = String.format("%d Nov %dH", date, time);
					
					System.out.println("Please enter your name: ");
					name = in.nextLine();
					
					System.out.println("Please enter your contact: ");
					contact = in.nextLine();
					
					
					int reservationId = reservationController.createReservation(appointmentDateTime, name, contact, numberOfPax);
					reservationController.showReservation(reservationId);
					break;
				case 2:
				case 3: 
					System.out.println("Please enter your reservationId: ");
					int resID = in.nextInt();
					in.nextLine();
					reservationController.showReservation(resID);
					
					System.out.println("Do you wish to remove this reservation? Y/N");
					char confirmation = in.nextLine().charAt(0);
						if (confirmation == 'Y')
						{
							reservationController.removeReservation(resID);
						}					
					break;
				case 4:
					System.out.println("Please enter your contact: ");
					contact = in.nextLine();
					
					reservationController.displayAllReservation(contact);
					break;
				
			}
			
			choice = this.displayOptions();
		}
		

	}
	
	
	 private int displayOptions() {
	        System.out.println("--------Reservation System--------");
		 	System.out.println("0. Go back to MainUI");
	        System.out.println("1. Create a new reservation");
	        System.out.println("2. Update reservation details");
	        System.out.println("3. Remove a reservation");
	        System.out.println("4. Print all reservations");
	        System.out.println("Your choice: ");
	        int choice = in.nextInt();
	        in.nextLine();
	        return choice;
	    }
}
