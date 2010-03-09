// Dmitriy Zabranskiy g261 (c)2009
// Interface
package myinterface;

import math.library.*;

public class Main {

    public static <U> U Addition(MathLibrary<U> lib, U num1, U num2) {
        if (lib == null) {
            throw new IllegalArgumentException("Argument can not be null");
        }

        return lib.add(num1, num2);
    }
}