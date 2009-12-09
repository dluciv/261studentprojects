package Interface;

/**
 * Class Water;
 * @author Zubrilin Andrey (c)2009
 */
public class Water implements IShop {

    public String price(String name){
        if(name.equals("7up"))
            return "10";
        if(name.equals("Coca Cola"))
            return "11";
        if(name.equals("Pepsi"))
            return "12";
        return null;
    }
}
