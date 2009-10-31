// Dmitriy Zabranskiy g261 (c)2009
// NegativeTests
package exception;

import math.library.MathLibrary;
import org.junit.Test;

public class NegativeCheckerTests {

    @Test(expected = IllegalArgumentException.class)
    public void NullArgumentTest() {
        LibraryChecker.Check(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void EmptyLibraryTest() {
        MathLibrary abc = null;
        LibraryChecker.Check(abc);
    }
}