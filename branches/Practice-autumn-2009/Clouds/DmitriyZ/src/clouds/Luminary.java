/*
 * Clouds
 * Luminary
 * Dmitriy Zabranskiy g261 (c)2009
 */
package clouds;

import java.util.Random;

public class Luminary implements ILuminary {

    private static final Random random = new Random();

    public LuminaryEnum current() {
        switch (random.nextInt(LuminaryEnum.values().length)) {
            case 0:
                return LuminaryEnum.ISSHINING;
            case 1:
                return LuminaryEnum.NOTSHINING;
            default:
                return LuminaryEnum.NOTSHINING;
        }
    }
}