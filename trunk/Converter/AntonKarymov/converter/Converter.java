/*Anton Karymov,gr 261
immediate Converter
 */
package converter;

import java.math.*;

public final class Converter {

    private double course = 29.34;

    public double rubleToDollar(double nominalRub) {
        double nominalBaks = nominalRub / course;
        return (BigDecimal.valueOf(nominalBaks).setScale(2, RoundingMode.HALF_EVEN)).doubleValue();
    }

    public double dollarToRuble(double nominalDollar) {
        double nominalRub = nominalDollar * course;
        return (BigDecimal.valueOf(nominalRub).setScale(2, RoundingMode.HALF_EVEN)).doubleValue();
    }
}
