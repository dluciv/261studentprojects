// Dmitriy Zabranskiy g261 (c)2009
// Tests of IntOperations
package tests;

import math.library.MathLibrary;
import myinterface.IntOperations;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntOperationsTests {

    MathLibrary<Integer> lib1 = new IntOperations();

    @Test
    public void addTest() {
        int result = lib1.add(2, 3);
        int expResult = 5;
        assertTrue(result == expResult);
    }

    @Test
    public void subTest() {
        int result = lib1.sub(-2, 3);
        int expResult = -5;
        assertTrue(result == expResult);
    }

    @Test
    public void nextTest() {
        int result = lib1.next(2);
        int expResult = 3;
        assertTrue(result == expResult);
    }

    @Test
    public void someTest() {
        int result = lib1.next(lib1.add(2, 3));
        int expResult = 6;
        assertTrue(result == expResult);
    }
}