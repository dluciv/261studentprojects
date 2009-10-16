/* Program Exception
 * @author
 * Eugene Todoruk
 * group 261
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MyExeptions {

    public static int sum(String num1, String num2) {
        if ((num1 == null) || (num2 == null)) {
            throw new IllegalArgumentException("Numbers can not be null");
        }

        try{
            return Integer.parseInt(num1) + Integer.parseInt(num2);
        }
        catch(NumberFormatException e){       
            throw new NumberFormatException("Please, enter the numbers!");
        }
    }

    public static void main(String[] args) throws IOException {
        String num1, num2;
        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));

        num1 = bReader.readLine();
        num2 = bReader.readLine();
        
        System.out.println(sum(num1, num2));
    }
}
