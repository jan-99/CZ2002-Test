import java.util.ArrayList;

public class Set extends MenuItem{

    private int setID;
    ArrayList<AlaCarte> item;

    public Set(String name, String description, double price)
    {
        super(name, description,price);
    }

    public void addAlaCarte(MenuItem fooditem1, MenuItem fooditem2, MenuItem fooditem3)
    {
        item.add((AlaCarte) fooditem1);
        item.add((AlaCarte) fooditem2);
        item.add((AlaCarte) fooditem3);
    }

    public int getSetID(){
        return setID;
    }

    public void getSetItems(){
        int j=1;
        for(AlaCarte i: item){
            System.out.println("Item " + j + ": " +i.getName());
            j++;
        }
    }


}
