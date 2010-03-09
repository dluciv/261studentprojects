package emailchecker;
/**
 * Main (EMailChecker).
 * @author Zubrilin Andrey (c)2009
 */
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("Null e-mail address to exit the program.");

        while(true){
            String address;
            System.out.println("\nWaiting for e-mail address..");

            BufferedReader bReader = new BufferedReader (new InputStreamReader(System.in));
            address = bReader.readLine();
            
            if(address.equals("")){
                 System.out.println("You have left the program.('null' result)");
                 return;
            }
            
            if(EMailChecker.Checker(address))
                System.out.println("E-mail address is correct.");
            else
                System.out.println("E-mail address is incorrect.");
        }
    }
}
