//Lebedev Dmitry g261 2009 (c)
//This task include throwing exception
package calculations;

public class Division implements Calculator {

    public int calculate(int firstArgument, int secondArgument) {
        if (secondArgument == 0) {
            throw new ArithmeticException ("Division by zero inhibited");
        }
        return firstArgument / secondArgument;
    }
}

