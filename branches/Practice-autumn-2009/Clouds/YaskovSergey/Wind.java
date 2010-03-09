/*
 * some mock tests;
 * (c) Yaskov Sergey, 261; 2009
 */

package clouds;

import java.util.Random;


public class Wind implements IWind {
    private final int MAX_WIND = 11;
    public int windPower;
    private Random rnd = new Random();

    public Wind() {
        windPower = rnd.nextInt(MAX_WIND);
    }

    public int getWindPower() {
        return windPower;
    }
}
