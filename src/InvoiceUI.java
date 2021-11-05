import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.time.LocalDate;
public class InvoiceUI {
    private static InvoiceUI single_instance = null;
    private InvoiceController invoiceController = InvoiceController.getInstance();
    private static Scanner in = new Scanner(System.in);
    private InvoiceUI() throws IOException {}

    public static InvoiceUI getInstance() throws IOException {
        if (single_instance == null)
            single_instance = new InvoiceUI();
        return single_instance;
    }

    public void run(){
        Scanner sc = new Scanner(System.in);

        System.out.println("--------Invoice and Report Panel--------");
        System.out.println("1. printInvoice"+
                "\n2. print daily revenue report"+
                "\n3. print monthly revenue report"+
                "\n4. back to main panel");

        int choice = sc.nextInt();
        while(true) {
            switch (choice) {
                case 1:
                    createInvoice();
                    break;
                case 2:
                    printRevenueReportByDay();
                    break;
                case 3:
                    printRevenueReportByMonth();
                    break;
                case 4:
                    System.out.println("back to main panel...");
                    return;
                default:
                    System.out.println("invalid input!");
            }
            System.out.println("enter option:");
            choice = sc.nextInt();
        }
    }

    private void createInvoice(){
        Scanner sc = new Scanner(System.in);
        int orderId;
        System.out.print("enter order ID:");                                // handle invalid input  1. in the orderList
        //                                                                                            2. not been checked out
        orderId = sc.nextInt();

        System.out.println("enter phoneNumber to check membership");

        String number = sc.next();
        invoiceController.printInvoice(orderId, number);

    }

    private void printRevenueReportByDay(){
        Scanner sc= new Scanner(System.in);
        System.out.println("enter the date: (dd/mm/yyyy)");
        String dateString = sc.next();

    }
    private void printRevenueReportByMonth(){
        Scanner sc= new Scanner(System.in);
        System.out.println("Please enter the month(MM/YYYY)");
        String dateStr = sc.next();
        invoiceController.printMonthlyReport(dateStr);
    }
}
