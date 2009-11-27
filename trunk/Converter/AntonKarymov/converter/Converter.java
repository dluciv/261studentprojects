/*Anton Karymov,gr 261
immediate Converter
 */
package converter;

import java.math.*;

public final class Converter {

    private static double course = 29.34;

    public static double rubleToDollar(double nominalRub) {
        double nominalBaks = nominalRub / course;
        return (BigDecimal.valueOf(nominalBaks).setScale(2, RoundingMode.UP)).doubleValue();
    }

    public static double dollarToRuble(double nominalDollar) {
        double nominalRub = nominalDollar * course;
        return (BigDecimal.valueOf(nominalRub).setScale(2, RoundingMode.UP)).doubleValue();
    }
}
