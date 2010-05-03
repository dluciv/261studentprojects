// Dmitriy Zabranskiy g261 (c)2009
// Interface
package exception;

import math.library.*;

public class IntOperations implements MathLibrary {

    public double sub(double num1, double num2) {
        return (int) (num1 - num2);
    }

    public double add(double num1, double num2) {
        return (int) (num1 + num2);
    }

    public double next(double num) {
        return (int) (num + 1);
    }
}