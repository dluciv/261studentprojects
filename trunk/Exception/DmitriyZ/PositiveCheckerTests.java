// Dmitriy Zabranskiy g261 (c)2009
// PositiveTests
package exception;

import org.junit.Test;

public class PositiveCheckerTests {

    @Test
    public void FirstInterfaceTest() {
        LibraryChecker.Check(new IntOperations());
    }

    @Test
    public void SecondInterfaceTest() {
        LibraryChecker.Check(new FloatOperations());
    }
}