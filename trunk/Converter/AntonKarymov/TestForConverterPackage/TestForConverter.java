/*Anton Karymov,gr 261
Test for Converter
 */
package TestForConverterPackage;

import org.junit.Test;
import converter.Converter;
import converter.Components;
import org.junit.Assert;

public class TestForConverter {

    @Test()
    public void RubleToDollarTest() {
        double helpNumberForRubleToDollarTest = 1.0;
        Assert.assertEquals(helpNumberForRubleToDollarTest, Converter.rubleToDollar(29.34), 0.00001);
    }

    @Test()
    public void DollarToRubleTest() {
        double helpNumberForDollarToRubleTest = 29.34;
        Assert.assertEquals(helpNumberForDollarToRubleTest, Converter.dollarToRuble(1.0), 0.00001);
    }

    @Test(expected = NumberFormatException.class)
    public void testNegativeIsUserEnterCorrect() {
        String helpWord = "sfdfsdsf";
        Components.isUserEnterCorrect(helpWord);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullIsUserEnterCorrect() {
        Components.isUserEnterCorrect(null);
    }

    @Test()
    public void testPositiveIsUserEnterCorrectForDouble() {
        double helpDoubleNumber = 43.456;
        Assert.assertEquals(helpDoubleNumber, Components.isUserEnterCorrect("43.456"), 0.00001);
    }
}
