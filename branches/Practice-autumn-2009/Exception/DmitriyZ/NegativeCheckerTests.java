// Dmitriy Zabranskiy g261 (c)2009
// Negative Tests of Library Checker
package exception;

import math.library.MathLibrary;
import org.junit.Test;

public class NegativeCheckerTests {

    @Test(expected = IllegalArgumentException.class)
    public void nullArgumentTest() {
        LibraryChecker.Check(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyLibraryTest() {
        MathLibrary lib = null;
        LibraryChecker.Check(lib);
    }
}