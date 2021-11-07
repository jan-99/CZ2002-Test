package tableReservation;

import java.time.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ReservationController {
	
	private static Scanner in = new Scanner(System.in);
	private final int EXPIRE_PERIOD = 5;
	private ArrayList<Reservation> reservationList;
	private TableController tableController = TableController.getInstance(); 
	
	private static ReservationController reservationController = null;


	public ReservationController()
	{
		this.reservationList = new ArrayList<>();
	}
	
	public static ReservationController getInstance()
	{
		if (reservationController == null) {
			reservationController = new ReservationController();
		}
		return reservationController;
	} 
	
	
	// display all the attributes of reservation class corresponding to its reservation id
	public void displayAllReservations() 
	{
		for (Reservation r: reservationList) {
			System.out.println("Reservation no.: " + reservationList.indexOf(r));
			System.out.println(r.toString());			
		}
	}
	
	/* public void displayAllReservation(String contact)
	{
		for (Reservation i: reservationList)
		{
			if (i.getContact().equals(contact))
			{
				System.out.println("Your reservationId is " + i.getReservationId()
						+ "\nReservation made for " + 
						i.getNumberOfPax() +
						" under the name, " + i.getName() +
						"\nThe table number is " + i.getTableId() +
						".\nYour appointment date time is " + i.getAppointmentDateTime());
				System.out.println();
			}
		}
				
	} */

	/* public int createReservation(String name, String contact, int numberOfPax)
	{
		// call function to clear expired Reservations
		// return table list for numberOfPax
		// Conflict resolution: loop through reservations for all tables with numberOfPax and check if appointDateTime >= endTimeOfAppoint
		// -> if have reservation for table, delete from available table list

		tableController.displayUnoccupiedTables(numberOfPax); //display unoccupied tables should try to return an array of tableid
		System.out.println("Please enter the table number you would like to reserve: ");

		int tableId = in.nextInt();
		tableController.setOccupied(tableId);

		int reservationId = reservationList.size();
		// change appointDateTime from String to LocateDate
		Reservation reservation = new Reservation(name, contact, numberOfPax, tableId);
		reservationList.add(reservation);
		return reservationId;
	} */

/**
 * clearReservation() is to remove the expired Reservations from reservation list
 */

	private void clearReservation() {
		LocalDate today = LocalDate.now();
		LocalTime curTime = LocalTime.now();
		for(Reservation reservation : reservationList){
			LocalTime expireTime = reservation.getAppointmentTime().plusMinutes(EXPIRE_PERIOD);
			if(reservation.getAppointmentDate().equals(today) && curTime.isAfter(expireTime)){
				reservationList.remove(reservation);
			}
			
		}
	};

	public void createReservation(String name, String contact, int numberOfPax, LocalDate date, LocalTime time)
	{
		clearReservation();
		// get all table of specific pax
		ArrayList<Integer> tableList = tableController.getTableByPax(numberOfPax);

		// check time to remove the reserved table from table list
		for(Reservation reservation : reservationList){
			LocalTime otherTime = reservation.getAppointmentTime().plusMinutes(119);
			System.out.println("Before if time");
			if(reservation.getAppointmentDate().equals(date) && otherTime.isAfter(time)){
				System.out.println("After if time");
				if(tableList.contains(reservation.getTableId())) {
					System.out.println(reservation.getTableId());
					//tableList.remove(reservation.getTableId());
					int idx = tableList.indexOf(reservation.getTableId()); 
    				tableList.remove(idx);
				}
			}
			//tableList.removeIf(tableList -> reservation.getTableId() == reservation.getTableId());
		}

		// display available table id and ask user to choose the id
		System.out.println("unreserved table: " + tableList.toString());
		System.out.println("enter the table id to reserve the table");
		int tableId = in.nextInt();
		do {
			try {			
			if (!tableList.contains(tableId)) {
				throw new Exception("Invalid table number!");
			}
			} 	catch (Exception e) {
				System.out.println(e.getMessage());				
				System.out.println("enter the table id to reserve the table");
				tableId = in.nextInt();
				continue;
			}
			break;
		} while (true);
		
		

		// create the reservation
		Reservation reservation = new Reservation(name, contact, numberOfPax, tableId, date, time);
		reservationList.add(reservation);
	}
	
	/* public void showReservation(int reservationId)
	{
		System.out.println("\nReservation made for " + 
							reservationList.get(reservationId).getNumberOfPax() +
							" under the name, " + reservationList.get(reservationId).getName() +
							"\nThe table number is " + reservationList.get(reservationId).getTableId() +
							".\nYour appointment date time is " + reservationList.get(reservationId).getAppointmentDateTime());
		System.out.println();
	} */
	
	/* public void removeReservation(int reservationID)
	{
		
		int tableId = reservationList.get(reservationID).getTableId();
		tableController.setUnoccupied(tableId);		
		reservationList.remove(reservationID);
		System.out.println("Reservation has been removed");

	} */

	// check reservation method: clearReservation method + search by contact no.

	public boolean checkReservation(String contact) {
		clearReservation();		
		boolean found = false;
		try {
			if (contact.length() != 8)
				throw new Exception("Invalid contact number!");
			for (Reservation r : reservationList) {
				if (r.getContact().contains(contact)) {
					System.out.println("\nReservation no.: " + reservationList.indexOf(r));
					System.out.println(r.toString());
					found = true;
				}				
			}
			if (!found) {
				System.out.println("No reservation found!");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return found;
	}
	// method to remove expired Reservations: clearReservation method + loop through all reservations and return index of reservation(s) 
	
	public void removeReservation(String contact) {
		clearReservation();
		try {
			if (contact.length() != 8)
				throw new Exception("Invalid contact number!");

			boolean found = checkReservation(contact);
			if (found) {
				System.out.println("Enter reservation no. to delete: ");
				int index = in.nextInt();
				in.nextLine();
				System.out.println("Do you wish to remove this reservation? Y/N");
				if (in.nextLine().charAt(0) == 'Y')
				{
					reservationList.remove(index);
					System.out.println("Reservation removed!");
				}					
			}			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	
	
}
