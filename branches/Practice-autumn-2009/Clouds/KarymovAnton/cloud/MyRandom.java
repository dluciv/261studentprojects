package cloud;

import java.util.Random;

public final class MyRandom {
    private static Random rnd = new Random();

    private MyRandom(){}
    public static Random getRandom() {
        return rnd;
    }
}