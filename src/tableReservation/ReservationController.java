package tableReservation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class ReservationController {
	
	private static Scanner in = new Scanner(System.in);

	private ArrayList<Reservation> reservationList;
	private TableController tableController = TableController.getInstance(); 
	
	private static ReservationController reservationController = null;
	
	//private function to clearReservation()
	private void clearReservation() {

	};

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
	
	public int createReservation(String name, String contact, int numberOfPax)
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
