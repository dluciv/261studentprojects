/*Anton Karymov,gr 261
Test for Converter
 */
package TestForConverterPackage;

import org.junit.Test;
import converter.Converter;

public class TestForConverter {

    @Test(expected = NullPointerException.class)
    public void nullNumberForRubleToDollarTest() {
        Converter.rubleToDollar(null);
    }

    @Test(expected = NumberFormatException.class)
    public void wrongNumberForRubleToDollarTest() {
        Converter.rubleToDollar("hvhbn gvcf");
    }

    @Test(expected = NullPointerException.class)
    public void nullNumberForDollarToRubleTest() {
        Converter.dollarToRuble(null);
    }

    @Test(expected = NumberFormatException.class)
    public void wrongNumberForDollarToRubleTest() {
        Converter.dollarToRuble("sdfdsfsdf");
    }
}
