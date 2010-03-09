//(с) Кривых Алексей 2009г.
//Cloud
package mult;

import java.util.Random;

public class Luminary implements ILuminary {

    //Метод возвращает текущее освещение, используя рандом
    public EnumLuminary current() {
        if (new Random().nextBoolean()) {
            return EnumLuminary.ISSHINING;
        } else {
            return EnumLuminary.NOTSHINING;
        }
    }
}
