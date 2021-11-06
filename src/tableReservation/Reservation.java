package tableReservation;

import java.time.LocalDateTime;    

public class Reservation {

	private int reservationId;
	private LocalDateTime reservationTime; // system clock time
	private LocalDateTime appointmentDateTime; //"11-02, 1900"
	//private LocalDateTime expiryTime = // find code to plus 30min to appointmentDateTime; 	
	private String name;
	private String contact; 
	private int numberOfPax;
	private int tableId;
	
	public Reservation(LocalDateTime appointmentDateTime, String name, String contact, int numberOfPax, int tableId)
	{
		//this.reservationId = reservationId;
		
		this.reservationTime = LocalDateTime.now(); 
		this.appointmentDateTime = appointmentDateTime;
		this.name = name;
		this.contact = contact;
		this.numberOfPax = numberOfPax;
		this.tableId = tableId;
	}
	
	public String getAppointmentDateTime() // CANONOT CHANGE APPOINTMENT DATE TIME
	{
		return appointmentDateTime; 
	}
	
	public int getReservationId()
	{
		return reservationId;
	}
	
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
}
