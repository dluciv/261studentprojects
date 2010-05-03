/*
 * JUnit test for Exception By Savenko Maria (c)2009
 */

package msavenko;

import java.lang.System;
import org.junit.*;

public class exceptionTest {
    
    @Test
    public void testCheckTheClass1() {
        exception.checkTheInterface(new AskForHelp());
    }
    
    @Test
    public void testCheckTheClass2() {
        exception.checkTheInterface(new AskName());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCheckNullArgument() {
        exception.checkTheInterface(null);
    }
    
}
