// Dmitriy Zabranskiy g261 (c)2009
// Interface

import math.library.*;

public class DoubleOperations implements MathLibrary {

    public double sub(double num1, double num2) {
        return num1 - num2;
    }

    public double add(double num1, double num2) {
        return num1 + num2;
    }

    public double next(double num) {
        return num + 1;
    }
}
