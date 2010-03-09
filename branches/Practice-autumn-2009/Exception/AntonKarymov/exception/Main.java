/*Anton Karymov,gr261,
Exception.
 */

package exception;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void division(String divide, String divider) {

        if ((divide == null) || (divider == null)) {
            throw new IllegalArgumentException("Argument can't be null!");

        }
        if (Double.parseDouble(divider) == 0) {
            throw new NullPointerException("Divide by zero - impossible!");
        }

        try {
            System.out.println(Double.parseDouble(divide) / Double.parseDouble(divider));
        } catch (NumberFormatException e) {
            throw new NumberFormatException("We can do operation only with number!");
        }

    }

    public static void main(String[] args) throws IOException {
        String divide, divider;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        divide = bufferedReader.readLine();
        divider = bufferedReader.readLine();

        division(divide, divider);
    }
}
