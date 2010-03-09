/* Program Exception
 * @author
 * Eugene Todoruk
 * group 261
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Summation {

    public static int sum(int firstNum, int secondNum) {
        return firstNum + secondNum;
    }

    static int parseNum(String number) throws NumberFormatException, IllegalArgumentException {
        return Integer.parseInt(number);
    }

    public static int getNum() throws IOException {

        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
        boolean isNumberFormatException;
        int number = 0;

        do {
            try {
                number = parseNum(bReader.readLine());
                isNumberFormatException = false;
            } catch (NumberFormatException e) {
                System.out.println("Число введено не верно. Попробуйте ещё раз.");
                isNumberFormatException = true;
            }
        } while (isNumberFormatException);

        return number;
    }
}
