/* Program Exception
 * @author
 * Eugene Todoruk
 * group 261
 */

import org.junit.Test;

public class SummationTest {

    @Test(expected = NumberFormatException.class)
    public void testNumberFormatException() {
        Summation.parseNum("one");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentException() {
        Summation.parseNum(null);
    }
}