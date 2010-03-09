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
public class Luminary implements ILuminary {

    public EnumLuminary current() {
        int random = new Random().nextInt(EnumLuminary.values().length);
        switch (random) {
            case 0:
                return EnumLuminary.CLOUDY;
            case 1:
                return EnumLuminary.ISSHINY;
            default:
                return EnumLuminary.CLOUDY;
        }
    }
}
