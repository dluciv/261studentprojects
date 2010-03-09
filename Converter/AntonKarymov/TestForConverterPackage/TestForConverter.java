/*Anton Karymov,gr 261
Test for Converter
 */
package TestForConverterPackage;

import org.junit.Test;
import converter.Converter;
import converter.Components;
import org.junit.Assert;

public class TestForConverter {
 Converter converter = new Converter();
 Components components = new Components();

 @Test()
    public void RubleToDollarTest() {
        double helpNumberForRubleToDollarTest = 1.0;
        Assert.assertEquals(helpNumberForRubleToDollarTest, converter.rubleToDollar(29.34), 0.00001);
    }

    @Test()
    public void DollarToRubleTest() {
        double helpNumberForDollarToRubleTest = 29.34;
        Assert.assertEquals(helpNumberForDollarToRubleTest, converter.dollarToRuble(1.0), 0.00001);
    }

    @Test(expected = NumberFormatException.class)
    public void testNegativeIsUserInputCorrect() {
        String helpWord = "sfdfsdsf";
        components.prserInput(helpWord);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullIsUserInputCorrect() {
        components.prserInput(null);
    }

    @Test()
    public void testPositiveIsUserEnterCorrectForDouble() {
        double helpDoubleNumber = 43.456;
        Assert.assertEquals(helpDoubleNumber, components.prserInput("43.456"), 0.0);
    }
}
