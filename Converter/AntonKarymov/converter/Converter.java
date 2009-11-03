/*Anton Karymov,gr 261
immediate Converter
 */
package converter;

import java.math.*;
import javax.swing.JOptionPane;

public class Converter {

    private static double course = 29.34;

    public static String rubleToDollar(String nominalRub) {

        if (nominalRub == null) {
            JOptionPane.showMessageDialog(null, "Null-это не число", "Error", JOptionPane.ERROR_MESSAGE);
            throw new NullPointerException("Null-это не число");
        }

        try {
            double nominalBaks = Double.parseDouble(nominalRub) * course;
            return String.valueOf(BigDecimal.valueOf(nominalBaks).setScale(2, RoundingMode.UP));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Введите число,а не билеберду", "Error", JOptionPane.ERROR_MESSAGE);
            throw new NumberFormatException("Введите число,а не билеберду");
        }

    }

    public static String dollarToRuble(String nominalDollar) {

        if (nominalDollar == null) {
            JOptionPane.showMessageDialog(null, "Null-это не число", "Error", JOptionPane.ERROR_MESSAGE);
            throw new NullPointerException("Null-это не число");
        }

        try {
            double nominalRub = Double.parseDouble(nominalDollar) / course;
            return String.valueOf(BigDecimal.valueOf(nominalRub).setScale(2, RoundingMode.UP));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Введите число,а не билеберду", "Error", JOptionPane.ERROR_MESSAGE);
            throw new NumberFormatException("Введите число,а не билеберду");
        }
    }
}
