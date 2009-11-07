package Interface;

/**
 * Example of using interface(Pricelists of cars and socks);
 * @author Zubrilin Andrey (c)2009
 */
import java.util.Random;
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Random random = new Random();

        //Socks costs;
        IPrice modelSocks = new Socks(random.nextInt(4)+1);

        int price = modelSocks.seePrice();

        if(price != 0)
            System.out.println("The Price is "+price+" Ketse");
        else
            System.out.println("Don't have this item on the storage");

        //Cars costs;
        IPrice modelCars = new Cars(random.nextInt(4)+1);

        price = modelCars.seePrice();
        
        if(price != 0)
            System.out.println("The Price is "+price+" Ketse");
        else
            System.out.println("Don't have this item on the storage");
    }
}
