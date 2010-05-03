//Dmitriy Zabranskiy g261 (c) 2009
//Exception
package exception;

import math.library.*;

public class LibraryChecker {

    public static void Check(MathLibrary lib) throws IllegalArgumentException {
        if (lib == null) {
            throw new IllegalArgumentException("Argument can not be null");
        }
    }
}