// Dmitriy Zabranskiy g261 (c)2009
// Positive Tests of Library Checker
package exception;

import org.junit.Test;

public class PositiveCheckerTests {

    @Test
    public void firstInterfaceTest() {
        LibraryChecker.Check(new IntOperations());
    }

    @Test
    public void secondInterfaceTest() {
        LibraryChecker.Check(new DoubleOperations());
    }
}