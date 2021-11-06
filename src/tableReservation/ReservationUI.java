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
					
					
					int reservationId = reservationController.createReservation(name, contact, numberOfPax);
					//reservationController.showReservation(reservationId);
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
