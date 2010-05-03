//Lebedev Dmitry g261 2009 (c)
//This task include throwing exception
package calculations;

public class Main {

    public static int calculate(Calculator calculator, int firstArgument, int secondArgument) {
        if (calculator == null) {
            throw new IllegalArgumentException ("Illegal argument");
        }
        
        return calculator.calculate(firstArgument, secondArgument);        
    }
 }
