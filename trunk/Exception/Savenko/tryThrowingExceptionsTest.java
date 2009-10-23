/* JUnit test for Exception
 * By Savenko Maria (c)2009 */

package msavenko;

import java.lang.System;
import org.junit.*;

public class tryThrowingExceptionsTest {

    @Test
    public void testCheckTheClass1() {
        TryThrowingExceptions.checkTheInterface(new AskForHelp());
    }

    @Test
    public void testCheckTheClass2() {
        TryThrowingExceptions.checkTheInterface(new AskName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckNullArgument() {
        TryThrowingExceptions.checkTheInterface(null);
    }

}
