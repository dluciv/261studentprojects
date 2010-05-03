// Dmitriy Zabranskiy g261 (c)2009
// Converter's Tests
import org.junit.Test;
import static org.junit.Assert.*;

public class ConverterTest {

    @Test(expected = IllegalArgumentException.class)
    public void negativNumberTest() {
        Converter.check("-1");
    }

    @Test(expected = NullPointerException.class)
    public void nullNumberTest() {
        Converter.check(null);
    }

    @Test(expected = NumberFormatException.class)
    public void wrongNumberTest() {
        Converter.check("aaaa");
    }

    @Test
    public void someTest() {
        String result = Converter.check("0");
        assertEquals(result, "0");
    }
}