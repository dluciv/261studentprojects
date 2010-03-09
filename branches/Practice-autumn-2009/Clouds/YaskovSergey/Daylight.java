/*
 * some mock tests;
 * (c) Yaskov Sergey, 261; 2009
 */

package clouds;

import java.util.Random;

public class Daylight implements IDaylight {
    private Random rnd = new Random();
    public DaylightType daylightType;

    public Daylight() {
        daylightType = setDaylightType();
    }

    DaylightType setDaylightType() {
        switch (rnd.nextInt(3)) {
            case 0:
                return DaylightType.DAY;

            case 1:
                return DaylightType.NIGHT;

            case 2:
                return DaylightType.MORNING;

            case 3:
                return DaylightType.NOON;

            default:
                return DaylightType.MORNING;

        }
    }

    public DaylightType getDaylightType () {
        return daylightType;
    }
}
