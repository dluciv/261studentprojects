/*
 * some tests for converter;
 * (c) Yaskov Sergey, 2009;
 */

package convertertests;

import converter.*;
import org.junit.*;
import org.junit.Assert;

public class ConverterTests {
    private final double LENGTH_IN_CM = 19.0;
    private final double LENGTH_IN_INCHES = 7.48;
    private final String RIGHT_INPUT = "34";
    private final double EXP_RES = 34;
    private final String WRONG_INPUT = "lol";

    @Test
    public void rightConversionTest() {
        Assert.assertEquals(LENGTH_IN_INCHES,
                CentimetreToInch.centimetreToInch(LENGTH_IN_CM), 0.01);
    }

    @Test
    public void rightInputTest() {
        Assert.assertEquals(Menu.inputedTextToNumber(RIGHT_INPUT),
                EXP_RES, 0.01);
    }

    @Test
    public void wrongInputTest() {
        Assert.assertEquals(Menu.inputedTextToNumber(WRONG_INPUT), 0, 0.01);
    }
}
