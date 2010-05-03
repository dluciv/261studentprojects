/**
 * @author Eugene Todoruk
 * group 261
 */

package clouds;

public class Wind implements IWind {
    private int strength;

    public Wind(int strength) {
        this.strength = strength;
    }

    public int current() {
        return this.strength;
    }
}
