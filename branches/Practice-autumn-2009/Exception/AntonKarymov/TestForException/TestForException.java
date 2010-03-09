/*Anton Karymov,gr261,
Tests for Exception.
 */

package TestForException;

import org.junit.Test;
import exception.Main;

public class TestForException {

    String zero = "0";
    String checkNumber = "8";
    String checkString = "fdfdf";

    @Test(expected = IllegalArgumentException.class)
    public void IllegalArgumentDivideTest() {
        Main.division(null, checkNumber);
    }

    @Test(expected = IllegalArgumentException.class)
    public void IllegalArgumentDividerTest() {
        Main.division(checkNumber, null);
    }

    @Test(expected = NullPointerException.class)
    public void DividByZeroTest() {
        Main.division(checkNumber, zero);
    }

    @Test(expected = NumberFormatException.class)
    public void DivideByStringTest() {
        Main.division(checkNumber, checkString);
    }
}
