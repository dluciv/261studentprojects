package Interface;

/**
 * Class Socks;
 * @author Zubrilin Andrey (c)2009
 */
public class Socks implements IPrice {

    public int numModel;

    public Socks(int num){
        numModel = num;
    }

    public int seePrice(){
        int price;
        System.out.println("ID of Socks is "+numModel+".");
        switch(numModel){
            case 1:
                price = 7;
                break;
            case 2:
                price = 5;
                break;
            case 3:
                price = 9;
                break;
            default:
                return 0;
        }
        return price;
    }
}
