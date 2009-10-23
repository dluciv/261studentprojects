//Lebedev Dmitry g261 2009 (c)
//This task include throwing exception
package calculations;

public class Main {

    public static int calculate(Calculator calculator, int x, int y) {
        if (calculator == null) {
            throw new NullPointerException("Illegal argument");
        }
        return calculator.calculate(x, y);
    }
}
