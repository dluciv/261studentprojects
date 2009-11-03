// Dmitriy Zabranskiy g261 (c)2009
// Tests of IntOperations

import math.library.MathLibrary;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntOperationsTests {

    MathLibrary lib = new IntOperations();

    @Test
    public void addTest() {
        double result = lib.add(2, 3);
        double expResult = 5;
        assertTrue(result == expResult);
    }

    @Test
    public void subTest() {
        double result = lib.sub(-2, 3);
        double expResult = -5;
        assertTrue(result == expResult);
    }

    @Test
    public void nextTest() {
        double result = lib.next(2);
        double expResult = 3;
        assertTrue(result == expResult);
    }

    @Test
    public void someTest() {
        double result = lib.next(lib.add(2, 3));
        double expResult = 6;
        assertTrue(result == expResult);
    }
}