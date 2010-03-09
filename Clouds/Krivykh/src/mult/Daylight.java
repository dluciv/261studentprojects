//(с) Кривых Алексей 2009г.
//Cloud
package mult;

import java.util.Random;

public class Daylight implements IDaylight {

    //Метод возвращает случайное текущее время суток
    public EnumDaylight current() {
        
        int random = new Random().nextInt(EnumDaylight.values().length);
        
        switch (random) {
            case 0:
                return EnumDaylight.EVNING;
            case 1:
                return EnumDaylight.MORNING;
            case 2:
                return EnumDaylight.NIGHT;
            default:
                return EnumDaylight.NOON;
        }
    }
}