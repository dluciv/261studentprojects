// Dmitriy Zabranskiy g261 (c)2009
// Interface
package myinterface;

import math.library.*;

public class DoubleOperations implements MathLibrary<Double> {

    public Double sub(Double num1, Double num2) {
        return num1 - num2;
    }

    public Double add(Double num1, Double num2) {
        return num1 + num2;
    }

    public Double next(Double num) {
        return num + 1;
    }
}
