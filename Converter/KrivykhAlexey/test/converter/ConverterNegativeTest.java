//(c) Кривых Алексей 2009г.
//Swing converter
package converter;

import org.junit.Test;
import static org.junit.Assert.*;

public class ConverterNegativeTest {

    @Test
    public void testCharInputConvert() {
        Converter converter = new Converter();
        String input = "asd";
        assertTrue("Is not a Numb" == converter.convert(input));
    }

    @Test
    public void testCharInNumbConvert() {
        Converter converter = new Converter();
        String input = "21asd2343";
        assertTrue("Is not a Numb" == converter.convert(input));
    }

    @Test
    public void testDoubleDotConvert() {
        Converter converter = new Converter();
        String input = "21..323";
        assertTrue("Is not a Numb" == converter.convert(input));
    }

    @Test
    public void testTwoDotInNumbConvert() {
        Converter converter = new Converter();
        String input = "23.32.32";
        assertTrue("Is not a Numb" == converter.convert(input));
    }

    @Test
    public void testEmptyStringConvert() {
        Converter converter = new Converter();
        String input = "";
        assertTrue("Is not a Numb" == converter.convert(input));
    }
}