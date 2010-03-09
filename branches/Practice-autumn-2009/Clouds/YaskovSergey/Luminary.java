/*
 * some mock tests;
 * (c) Yaskov Sergey, 261; 2009
 */

package clouds;

import java.util.Random;

public class Luminary implements ILuminary {
    public LuminaryType luminaryType;

    public Luminary () {
        luminaryType = setLuminaryType();
    }
    
    public LuminaryType setLuminaryType() {
        Random random = new Random();

        if (random.nextBoolean()) {
            return LuminaryType.ISSHINING;
        }
        else {
            return LuminaryType.ISNOTSHINING;
        }
    }

    public LuminaryType getLuminaryType() {
        return luminaryType;
    }
}