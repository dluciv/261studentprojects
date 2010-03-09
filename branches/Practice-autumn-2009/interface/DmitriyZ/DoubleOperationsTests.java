// Dmitriy Zabranskiy g261 (c)2009
// Tests of DoubleOperations
package tests;

import math.library.MathLibrary;
import myinterface.DoubleOperations;
import org.junit.Test;
import static org.junit.Assert.*;

public class DoubleOperationsTests {

    MathLibrary<Double> lib = new DoubleOperations();

    @Test
    public void addTest() {
        double result = lib.add(5.8, 3.0);
        double expResult = 8.8;
        assertTrue(result == expResult);
    }

    @Test
    public void subTest() {
        double result = lib.sub(-2.0, 3.7);
        double expResult = -5.7;
        assertTrue(result == expResult);
    }

    @Test
    public void nextTest() {
        double result = lib.next(-2.5);
        double expResult = -1.5;
        assertTrue(result == expResult);
    }

    @Test
    public void someTest() {
        double result = lib.next(lib.add(0.8, 3.0));
        double expResult = 4.8;
        assertTrue(result == expResult);
    }
}