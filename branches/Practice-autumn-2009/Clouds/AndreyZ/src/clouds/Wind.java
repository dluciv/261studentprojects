/**
 * Wind Class
 * @author Zubrilin Andrey
 * group 261 (c) 2009
 */

package clouds;

public class Wind implements IWind {
    private int str;

    public Wind(int str) {
        this.str = str;
    }

    public int current() {
        return this.str;
    }
}
