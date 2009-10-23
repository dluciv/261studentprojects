//Lebedev Dmitry g261 2009 (c)
//This task include throwing exception
package calculations;

public class Division implements Calculator {

    public int calculate(int x, int y) {
        if (y == 0) {
            throw new UnsupportedOperationException("Division by zero inhibited");
        }
        return x / y;
    }
}

