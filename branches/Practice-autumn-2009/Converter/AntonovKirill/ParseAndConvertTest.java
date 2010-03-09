/*
  (c) Antonov Kirill 2009
 program Convertor RUB to USD
 */

package converter;

import org.junit.Test;

public class ParseAndConvertTest {

    @Test(expected = NullPointerException.class)
    public void testNullNumber() {
        ParseAndConvert.parseAndConvert(null);
    }

    @Test(expected = NumberFormatException.class)
    public void testWrongNumber() {
        ParseAndConvert.parseAndConvert("assd");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativNumber() {
        ParseAndConvert.parseAndConvert("-100");
    }
}