package tableReservation;

import java.time.LocalDateTime;    

public class Reservation {

	private int reservationId;
	//private LocalDateTime reservationTime; // system clock time
	private String appointmentDateTime; //"11-02, 1900"
	
	private String name;
	private String contact; 
	private int numberOfPax;
	private int tableId;
	
	public Reservation(int reservationId, String appointmentDateTime, String name, String contact, int numberOfPax, int tableId)
	{
		this.reservationId = reservationId;
		
		//LocalDateTime now = LocalDateTime.now(); 
		//this.reservationTime = now;
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
