/*
 * Clouds
 * Daylight
 * Dmitriy Zabranskiy g261 (c)2009
 */
package clouds;

import java.util.Random;

public class Daylight implements IDaylight {

    private static final Random random = new Random();

    public DaylightEnum current() {
        switch (random.nextInt(DaylightEnum.values().length)) {
            case 0:
                return DaylightEnum.MORNING;
            case 1:
                return DaylightEnum.NOON;
            case 2:
                return DaylightEnum.EVENING;
            case 3:
                return DaylightEnum.NIGHT;
            default:
                return DaylightEnum.MORNING;
        }
    }
}
