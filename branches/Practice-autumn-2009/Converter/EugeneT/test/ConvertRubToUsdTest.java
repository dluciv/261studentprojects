/**
 * Класс тестов для класса конвертера валют
 * @author Eugene Todoruk
 * group 261
 */

import org.junit.Test;

public class ConvertRubToUsdTest {

    @Test(expected = NullPointerException.class)
    public void testNullNumber() {
        ConvertRubToUsd.parseAndConvert(null);
    }

    @Test(expected = NumberFormatException.class)
    public void testWrongNumber() {
        ConvertRubToUsd.parseAndConvert("assd");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativNumber() {
        ConvertRubToUsd.parseAndConvert("-100");
    }
}