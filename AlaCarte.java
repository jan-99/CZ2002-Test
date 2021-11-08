
public class AlaCarte extends MenuItem{

    private int AlaCarteID;
    Category type;

    public enum Category {
        MAINCOURSE, DRINKS, DESSERTS
    }

    public AlaCarte(String name, String description, double price, Category type) {
        super(name, description, price);
        this.type = type;
    }

    public int getAlaCarteID(){
        return AlaCarteID;
    }


}
