// Dmitriy Zabranskiy g261 (c)2009
// Interface

import math.library.*;

public class IntOperations implements MathLibrary {

    public float sub(float a, float b) {
        return (int) (a - b);
    }

    public float add(float a, float b) {
        return (int) (a + b);
    }

    public float next(float a) {
        return (int) (a++);
    }
}