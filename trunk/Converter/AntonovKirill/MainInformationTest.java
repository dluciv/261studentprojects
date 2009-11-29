/*
 (c) Antonov Kirill 2009
  Test for converter
 */

package converter;

import org.junit.Test;

/**
 *
 * @author Tiesto
 */
public class MainInformationTest {
    @Test(expected = NullPointerException.class)
    public void testNullNumber() {
        MainInformation.parseAndConvert(null);
    }

    @Test(expected = NumberFormatException.class)
    public void testWrongNumber() {
       MainInformation.parseAndConvert("abd");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativNumber() {
       MainInformation.parseAndConvert("-25");
    }
}