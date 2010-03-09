/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clouds;

import java.util.Random;

/**
 *
 * @author Lii
 */
public class Daylight implements IDaylight {

    public EnumDaylight current() {
        int random = new Random().nextInt(EnumDaylight.values().length);
        switch (random) {
            case 0:
                return EnumDaylight.EVENING;
            case 1:
                return EnumDaylight.MORNING;
            case 2:
                return EnumDaylight.NIGHT;
            case 3:
                return EnumDaylight.NOON;
            default:
                return EnumDaylight.MORNING;
        }
    }
}
