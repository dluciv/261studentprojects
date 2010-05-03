/**
 * Testing MoneyConverter
 * @author Zubrilin Andrey (c) 2009
 */
package moneyconverter;

import org.junit.Test;
import static org.junit.Assert.*;

public class testconverter {

    public testconverter(){

    }
    private MoneyConverter c = new MoneyConverter();

    @Test(expected = NumberFormatException.class)
    public void IncorrectNumberTest() {
        c.convert("Hello world");
    }

    @Test(expected = NullPointerException.class)
    public void NullTest() {
        c.convert(null);
    }

    @Test()
    public void NegativeNumberTest() {
        assertTrue(c.convert("-5") == 0 );
    }

    @Test()
    public void NegativeExchange(){
        c.Exchange = -5;
        assertTrue(c.Exchange == 0);
    }
}
