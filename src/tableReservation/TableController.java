package tableReservation;

import tableReservation.Table.seatNumber;

public class TableController {
	
	private Table[] listOfTables;
	final int SIZE=12;
	private static TableController tableController = null;
	
	public TableController()
	{
		
		listOfTables = new Table[SIZE];
		Table table1 = new Table(1, Table.seatNumber.TWO);
		Table table2 = new Table(2, Table.seatNumber.TWO);
		Table table3 = new Table(3, Table.seatNumber.TWO);
		Table table4 = new Table(4, Table.seatNumber.TWO);
		Table table5 = new Table(5, Table.seatNumber.FOUR);
		Table table6 = new Table(6, Table.seatNumber.FOUR);
		Table table7 = new Table(7, Table.seatNumber.FOUR);
		Table table8 = new Table(8, Table.seatNumber.SIX);
		Table table9 = new Table(9, Table.seatNumber.SIX);
		Table table10 = new Table(10, Table.seatNumber.SIX);
		Table table11 = new Table(11, Table.seatNumber.EIGHT);
		Table table12 = new Table(12, Table.seatNumber.TEN);
		
		listOfTables[0] = table1;
		listOfTables[1] = table2;
		listOfTables[2] = table3;
		listOfTables[3] = table4;
		listOfTables[4] = table5;
		listOfTables[5] = table6;
		listOfTables[6] = table7;
		listOfTables[7] = table8;
		listOfTables[8] = table9;
		listOfTables[9] = table10;
		listOfTables[10] = table11;
		listOfTables[11] = table12;

	}
	
	public static TableController getInstance()
	{
		if(tableController == null) {
			tableController = new TableController();
		}
		return tableController;
	}
	
	public void setOccupied(int tableId)
	{
		listOfTables[tableId-1].setOccupied(); 
	}
	
	public void setUnoccupied(int tableId)
	{
		listOfTables[tableId-1].setUnoccupied(); 
	}
	
	public void displayAllTables()
	{
		for (Table i: listOfTables )
		{
			int tableId = i.getTableId();
			boolean status = i.getOccupied();
			
			System.out.println("Table " + tableId + " | " +" is Occupied: " + status + " | " + "Number of Seats: " + i.getNumOfSeats());
		}
	}
	
	// Showing in general all unoccupied tables 
	public void displayUnoccupiedTables()
	{
		System.out.println("Showing all unoccupied tables: ");
		int j = 1;
		for (Table i: listOfTables)
		{
			if (i.getOccupied() == false)
			{
				
				System.out.println("Table " + i.getTableId());
			}
			
			j++;
			if(j >= 13) {
				break;
			}
			
		}
	}
	
	
	// Showing all unoccupied tables corresponding to the number of people 
	public void displayUnoccupiedTables(int numberOfPax)
	{	
		seatNumber tablePax = null;
		
		switch (numberOfPax)
		{
			case 1:
			case 2:
				tablePax = Table.seatNumber.TWO;
				break;
			case 3:
			case 4: 
				tablePax = Table.seatNumber.FOUR;
				break;
			case 5:
			case 6:
				tablePax = Table.seatNumber.SIX;
				break;
			case 7:
			case 8: 
				tablePax = Table.seatNumber.EIGHT;
				break;
			case 9:
			case 10:
				tablePax = Table.seatNumber.TEN;
				break;
		}
	
		System.out.println("Showing all unoccupied tables: ");
		int j = 1;
		for (Table i: listOfTables)
		{
			if (i.getOccupied() == false && i.getNumOfSeats() == tablePax)
			{
				
				System.out.println("Table " + i.getTableId());
			}
			
			j++;
			if(j >= 13) {
				break;
			}
			
		}
	}
	
	public void displayOccupiedTables()
	{
		for (Table i: listOfTables)
		{
			if (i.getOccupied() == true)
			{
				System.out.println("Table " + i.getTableId());
			}
		}
	}
	
	public void getTableByID(int tableId)
	{
		System.out.println("Table " + tableId + "Occupied: " + listOfTables[tableId].getOccupied() + " Number of Seats: " + listOfTables[tableId].numOfSeats);
	}
}