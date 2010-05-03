/**
 * Example of using interface(Shop);
 * @author Zubrilin Andrey (c)2009
 */
package Interface;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static Chocolate chocolate = new Chocolate();
    public static Water water = new Water();

    public static void main(String[] args) throws IOException {


        System.out.println("To exit our shop push enter \"Exit\"");

        while(true){
            String choco;
            String aqua;
            System.out.println("\n-What chocolate do you want?");

            BufferedReader bReader = new BufferedReader (new InputStreamReader(System.in));
            choco = bReader.readLine();

            if (chocolate.price(choco) == null)
                throw new IllegalArgumentException("Not in stock.");
            System.out.println("Cost : " + chocolate.price(choco));

            System.out.println("\n-What water do you want?");
            aqua = bReader.readLine();

            if (water.price(aqua) == null)
                throw new IllegalArgumentException("Not in stock.");
            System.out.println("Cost : " + water.price(aqua));
        }
    }
}
