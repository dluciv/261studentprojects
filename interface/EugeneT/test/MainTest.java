/* Eugene Todoruk
 * group 261
 */

import org.junit.Test;

public class MainTest {

    @Test(expected=IllegalArgumentException.class)
    public void testNullException() {

        Main.prevedFromMedved(null);
    }

    @Test
    public void testPrevedFromRussianMedved() {

        IMedved russianMedved = new RussianMedved();
        Main.prevedFromMedved(russianMedved);
    }

    @Test
    public void testPrevedFromAmericanMedved() {

        IMedved americanMedved = new AmericanMedved();
        Main.prevedFromMedved(americanMedved);
    }
}