package tableReservation;

import java.time.LocalDateTime;    
import java.time.format.DateTimeFormatter;

public class Reservation {

	private LocalDateTime reservationTime; // system clock time
	//private LocalDateTime appointmentDateTime; //"11-02, 1900"
	//private LocalDateTime expiryTime = // find code to plus 30min to appointmentDateTime; 	
	private String name;
	private String contact; 
	private int numberOfPax;
	private int tableId;
	
	public Reservation(String name, String contact, int numberOfPax, int tableId)
	{	
		this.reservationTime = LocalDateTime.now(); 
		//this.appointmentDateTime = appointmentDateTime;
		this.name = name;
		this.contact = contact;
		this.numberOfPax = numberOfPax;
		this.tableId = tableId;
	}
	
	/* public LocalDateTime getAppointmentDateTime() // CANNOT CHANGE APPOINTMENT DATE TIME
	{
		return appointmentDateTime; 
	} */
	
	
	public String getName()
	{
		return name;
	}
	
	public String getContact()
	{
		return contact;
	}
	
	public int getNumberOfPax()
	{
		return numberOfPax;
	}
	
	public int getTableId()
	{
		return tableId;
	}

	public String toString() {
		return "Reservation made for " + numberOfPax +
		" under the name, " + name +
		"\nThe table number is " + tableId +
		".\nThe appointment date time is " + reservationTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + "\n";
	}
}
