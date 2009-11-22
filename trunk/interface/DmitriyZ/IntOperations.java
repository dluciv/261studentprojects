// Dmitriy Zabranskiy g261 (c)2009
// Interface
package myinterface;

import math.library.*;

public class IntOperations implements MathLibrary<Integer> {

    public Integer sub(Integer num1, Integer num2) {
        return num1 - num2;
    }

    public Integer add(Integer num1, Integer num2) {
        return num1 + num2;
    }

    public Integer next(Integer num) {
        return num + 1;
    }
}