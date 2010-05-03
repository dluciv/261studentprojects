
/**Тесты
 *
 * @author Алексеев Илья(с)
 */
import convector.ConvectorMilsToKilometers;
import org.junit.Test;

public class TestConvectorMilsToKilometers {

    @Test(expected = NullPointerException.class)
    public void testNullNumber() {
        ConvectorMilsToKilometers.parseAndConvert(null);
    }

    @Test(expected = NumberFormatException.class)
    public void testWrongNumber() {
        ConvectorMilsToKilometers.parseAndConvert("белеберда");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativNumber() {
        ConvectorMilsToKilometers.parseAndConvert("-43");
    }
}
