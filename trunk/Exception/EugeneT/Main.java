/* Program Exception
 * @author
 * Eugene Todoruk
 * group 261
 */

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        int firstNum, secondNum;

        firstNum = Summation.getNum();
        secondNum = Summation.getNum();

        System.out.println(Summation.sum(firstNum, secondNum));
    }
}
