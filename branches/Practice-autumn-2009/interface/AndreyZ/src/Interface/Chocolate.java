package Interface;

/**
 * Class Chocolate;
 * @author Zubrilin Andrey (c)2009
 */
public class Chocolate implements IShop {

    public String price(String name){
        if(name.equals("Mars"))
            return "6";
        if(name.equals("Wagon Wheels"))
            return "7";
        if(name.equals("Nuts"))
            return "8";
        return null;
    }
}
