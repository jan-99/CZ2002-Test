public class OrderItem extends MenuItem{

    //public int OrderItemID; //added to identify for addOrderItem and removeOrderItem
    private MenuItem item;
    private String name;
    private String description;
    private double price;

    private int quantity;
    //String name, String description, double price
    public OrderItem(MenuItem item , int quantity){
        super(item.getName(), item.getDescription(), item.getPrice());
        //this.OrderItemID = OrderID;
        this.quantity = quantity;
    }

    public MenuItem getItem(){
        return item;
    }
    public int getQuantity(){
        return quantity;
    }

    public double getPrice(){
        return price;
    }

}
