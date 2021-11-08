package tableReservation;

import java.io.File;
import java.io.IOException;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.sound.sampled.SourceDataLine;

public class ReservationController extends AbstractController {
	
	private static Scanner in = new Scanner(System.in);
	private final int EXPIRE_PERIOD = 30;
	private ArrayList<Reservation> reservationList;
	private TableController tableController = TableController.getInstance(); 
	
	private static ReservationController reservationController = null;
	private static final String dir = "src/data/reservation.txt";

	private static int reservationId = 0;
	

	public ReservationController()
	{
		File file = new File(dir);
        if (file.exists()) {
            System.out.println("file exist");
            reservationList = load(dir);
        } else {
            System.out.println("not exist");
            file.getParentFile().mkdir();
            file.createNewFile();
            reservationList = new ArrayList<Reservation>();
            save(dir, reservationList);
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
		clearReservation();
		for (Reservation r: reservationList) {
			System.out.println(r.toString());			
		}
	}
	
	public Reservation getReservationById(int reservationId) {
		clearReservation();
		for (Reservation res : reservationList){
			if( res.getReservationId() == reservationId)
				return res;
		}
		System.out.println("Reservation not found");
		return null;
	}

	//clearReservation() is to remove the expired Reservations from reservation list
	private void clearReservation() {
		LocalDate today = LocalDate.now();
		LocalTime curTime = LocalTime.now();

		ArrayList<Reservation> toRemove = new ArrayList<>();
		for(Reservation reservation : reservationList){
			LocalTime expireTime = reservation.getAppointmentTime().plusMinutes(EXPIRE_PERIOD);
			if(reservation.getAppointmentDate().equals(today) && curTime.isAfter(expireTime)){
				toRemove.add(reservation);
			}
		}
		reservationList.removeAll(toRemove);
	};


	public void createReservation(String name, String contact, int numberOfPax, LocalDate date, LocalTime time)
	{
		clearReservation();
		// get all table of specific pax
		ArrayList<Integer> tableList = tableController.getTableByPax(numberOfPax);

		// check time to remove the reserved table from table list
		for(Reservation reservation : reservationList){
			LocalTime otherTime = reservation.getAppointmentTime().plusMinutes(119);
			if(reservation.getAppointmentDate().equals(date) && otherTime.isAfter(time)){
				if(tableList.contains(reservation.getTableId())) {
					System.out.println(reservation.getTableId());
					int idx = tableList.indexOf(reservation.getTableId()); 
    				tableList.remove(idx);
				}
			}
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
		
		reservationId = reservationId + 1;

		// create the reservation
		Reservation reservation = new Reservation(reservationId, name, contact, numberOfPax, tableId, date, time);
		reservationList.add(reservation);
	}

	// check reservation method: clearReservation method + search by contact no.
	public boolean checkReservation (String contact) {
		clearReservation();		
		boolean found = false;
		try {
			if (contact.length() != 8)
				throw new Exception("Invalid contact number!");
			for (Reservation r : reservationList) {
				if (r.getContact().contains(contact)) {
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
				int Id = in.nextInt();
				in.nextLine();
				System.out.println("Do you wish to remove this reservation? Y/N");
				if (in.nextLine().charAt(0) == 'Y')
				{
					reservationList.removeIf(reservation -> reservation.getReservationId() == Id);
					System.out.println("Reservation removed!");
				}					
			}			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	//   load method will be different between different controller
    public ArrayList load(String filename) throws IOException {
        ArrayList stringArray = (ArrayList) read(filename);
        ArrayList alr = new ArrayList();  // to store invoices data

        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            StringTokenizer star = new StringTokenizer(st, "|");


            int reservationId = Integer.parseInt(star.nextToken().trim());
			String name = star.nextToken().trim();
			String contact = star.nextToken().trim();
			int numOfPax = Integer.parseInt(star.nextToken().trim());
			int tableId = Integer.parseInt(star.nextToken().trim());
			LocalDate date = LocalDate.parse(star.nextToken().trim());
			LocalTime time = LocalTime.parse(star.nextToken().trim());

            // create Invoice object from file data
            Reservation reservation = new Reservation(reservationId,name,contact,numOfPax,tableId,date,time);

            //add to Invoice List
            alr.add(reservation);
        }
        return alr;
    }



    /**
     * save method
     * save method will be different with different controlelr
     */

    public static void save(String filename, List al) throws IOException {
        List alw = new ArrayList();  //to store data

        for (int i = 0; i < al.size(); i++) {
            Reservation reservation = (Reservation) al.get(i);
            StringBuilder st = new StringBuilder();
            st.append(reservation.getReservationId()); // trim() ??
            st.append("|");
            st.append(reservation.getName());
            st.append("|");
            st.append(reservation.getContact());  // ke yi ma?
            st.append("|");
            st.append(reservation.getNumberOfPax());
            st.append("|");
            st.append(reservation.getTableId());
            st.append("|");
            st.append(reservation.getAppointmentDate());
            st.append("|");
            st.append(reservation.getAppointmentTime());
            alw.add(st.toString());

            write(filename, alw);
        }
    }
	
}
