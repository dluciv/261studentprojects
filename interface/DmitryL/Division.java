//Lebedev Dmitry g261 2009 (c)

package calculations;

public class Division implements Calculating{

    public int calculate(int x, int y) {
        if (y == 0)
            throw new UnsupportedOperationException("Division by zero inhibited");
        else return x/y;
    }
    
}

