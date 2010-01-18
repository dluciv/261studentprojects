/*
 * program for some length conversion;
 * (c) Yaskov Sergey, 2009;
 */

package converter;

public class CentimetreToInch {
    static final double CENTIMETRES_IN_INCH = 2.54;

    public static double centimetreToInch(double inches) {
        return inches / CENTIMETRES_IN_INCH;
    }
}
