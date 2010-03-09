// Dmitriy Zabranskiy g261 (c)2009
// Tests of Main
package tests;

import myinterface.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class MainTest {

    @Test(expected = IllegalArgumentException.class)
    public void nullPointer() {
        Main.Addition(null, 3.5, 4);
    }

    @Test
    public void additionTest1() {
        double result = Main.<Double>Addition(new DoubleOperations(), 4.8, 5.3);
        double expResult = 10.1;
        assertTrue(result == expResult);
    }

    @Test
    public void additionTest2() {
        double result = Main.<Double>Addition(new DoubleOperations(), 3.4, 5.2);
        double expResult = 8.6;
        assertTrue(result == expResult);
    }

    @Test
    public void additionTest3() {
        int result = Main.<Integer>Addition(new IntOperations(), 4, 5);
        double expResult = 9;
        assertTrue(result == expResult);
    }

    @Test
    public void additionTest4() {
        int result = Main.<Integer>Addition(new IntOperations(), 3, 5);
        int expResult = 8;
        assertTrue(result == expResult);
    }
}