package Interface;

/**
 * Class Cars;
 * @author Zubrilin Andrey (c)2009
 */
public class Cars implements IPrice {

    public int numModel;

    public Cars(int num){
        numModel = num;
    }

    public int seePrice(){
        int price;
        System.out.println("ID of Car is "+numModel+".");
        switch(numModel){
            case 1:
                price = 700;
                break;
            case 2:
                price = 500;
                break;
            case 3:
                price = 900;
                break;
            default:
                return 0;
        }
        return price;
    }
}
