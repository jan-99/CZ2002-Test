package tableReservation;

import java.util.Scanner;

public class TableUI {
	
	Scanner in = new Scanner(System.in);
	private static TableController single_instance = null;
	private TableController tableController = TableController.getInstance();
	public TableUI() {}
	
	public static TableController getInstance() {
		if(single_instance == null) {
			single_instance = new TableController();
		}
		return single_instance;
	}
		
	public void run() {
		
		int choice = this.displayOptions();
		do {	        
	        switch(choice) {
			case 1:
				tableController.displayAllTables();
				break;
			case 2:
				tableController.displayUnoccupiedTables();
				break;
			case 3:
				tableController.displayOccupiedTables();
				break;
//			case 4:
//				Scanner sc = new Scanner(System.in);
//				int tableId = sc.nextInt();
//				tableController.getTableByID(tableId);
//				break;
	        }
	        
	        choice = this.displayOptions();
	        
		} while (choice != 0);
		
	}

//	private void getTableByID(int tableId) {
//		// TODO Auto-generated method stub
//		
//	}

	private void displayOccupiedTables() {
		// TODO Auto-generated method stub		
	}

	private void displayUnoccupiedTables() {
		// TODO Auto-generated method stub		
	}

	private void displayAllTables() {
		// TODO Auto-generated method stub
		
	}
	
	private int displayOptions() {
		System.out.println("--------TableUI--------");
        System.out.println("0. Go back to MainUI" +
        		"\n1. displayAllTables"+
                "\n2. displayUnoccupiedTables"+
                "\n3. displayOccupiedTables"+
                "\n4. getTableByID");
        int choice = in.nextInt();
        in.nextLine();
        return choice;
    }
}

