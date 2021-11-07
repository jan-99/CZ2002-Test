package tableReservation;

public class Table{
	public enum seatNumber {
		TWO,FOUR,SIX,EIGHT,TEN
	}
	
	private int tableId;
	private boolean isOccupied;
	seatNumber numOfSeats; 
		
	
	public Table(int tableId, seatNumber numOfSeats)
	{
		this.tableId = tableId;
		this.isOccupied = false; 
		this.numOfSeats = numOfSeats;
		
	}
	
	public boolean getOccupied()
	{
		return this.isOccupied; 
	}
	
	public int getTableId()
	{
		return tableId;
	}
	
	public seatNumber getNumOfSeats()
	{
		return numOfSeats;
	}
	
	public void setOccupied()
	{
		this.isOccupied = true; 
	}

	public seatNumber getPax(){
		return numOfSeats;
	}
	public void setUnoccupied()
	{
		this.isOccupied = false;
	}
	
}
