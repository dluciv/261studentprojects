/*
 * Clouds
 * Wind
 * Dmitriy Zabranskiy g261 (c)2009
 */
package clouds;

import java.util.Random;

public class Wind implements IWind {

    private final int MAXWIND = 11;
    private static final Random random = new Random();

    public int current() {
        return random.nextInt(MAXWIND);
    }
}