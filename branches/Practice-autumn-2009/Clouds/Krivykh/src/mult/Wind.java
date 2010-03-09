//(с) Кривых Алексей 2009г.
//Cloud
package mult;

import java.util.Random;

public class Wind implements IWind {

    //Метод возвращает текущую силу ветра, используя рандом
    public EnumWind current() {

        int random = new Random().nextInt(EnumWind.values().length);

        switch (random) {
            case 0:
                return EnumWind.LITTLE;
            case 1:
                return EnumWind.MIDDLE;
            case 2:
                return EnumWind.STRONG;
            case 3:
                return EnumWind.VERYSTRONG;
            default:
                return EnumWind.LITTLE;
        }
    }
}
