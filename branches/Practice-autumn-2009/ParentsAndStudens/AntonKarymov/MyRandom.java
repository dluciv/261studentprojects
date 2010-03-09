/*Anton Karymov,2009,261gr.
  Random
 */
package generic;

import java.util.Random;

public final class MyRandom {
    private static Random rnd = new Random();

    public static Random getRandom() {
        return rnd;
    }
}

