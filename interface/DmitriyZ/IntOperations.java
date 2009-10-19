// Dmitriy Zabranskiy g261 (c)2009
// Interface

import math.library.*;

public class IntOperations implements MathLibrary {

    public float sub(float num1, float num2) {
        return (int) (num1 - num2);
    }

    public float add(float num1, float num2) {
        return (int) (num1 + num2);
    }

    public float next(float num) {
        return (int) (num++);
    }
}