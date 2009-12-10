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
public class Wind implements IWind {

    public EnumWinds current() {
        int random = new Random().nextInt(EnumWinds.values().length);
        switch (random) {
            case 0:
                return EnumWinds.BREEZE;
            case 1:
                return EnumWinds.STORM;
            case 2:
                return EnumWinds.TWISTER;
            default:
                return EnumWinds.BREEZE;
        }
    }
}
