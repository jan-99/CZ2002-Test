import java.util.Scanner;

import tableReservation.ReservationUI;
import tableReservation.TableUI;

public class Main {
	
	private static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		int choice; 
		
		do {
			System.out.println("--------Main Control System--------");
			System.out.println("0. Exit");
			System.out.println("1. Reservation");
		    System.out.println("2. Table");
		    System.out.println("3. Menu");
		    System.out.println("4. Order");
		    System.out.println("5. Members");
		    System.out.println("6. Invoice");
		    System.out.println("Your choice: ");
		    choice = in.nextInt();
		    in.nextLine();
		    
		    switch(choice) {
		    	case 1:
		    		ReservationUI reservationUI = new ReservationUI();
		    		reservationUI.run();
		    		break;
		    	case 2: 
		    		TableUI tableUI = new TableUI();
		    		tableUI.run();
		    		break;
				case 5: 
					MemberUI memberUI = MemberUI.getInstance();
					memberUI.run();
		    	default:
		    		break;
		    }
		} while (choice != 0);
		
	
		
		
		
		
	}
	
	
	
}
