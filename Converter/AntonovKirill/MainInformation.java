/*
 (c)Antonov Kirill
 program Convertor RUB to USD
 Description of exceptions and data
*/
package converter;
import javax.swing.JOptionPane;

/**
 *
 * @author Tiesto
 */
public class MainInformation {
    final static double USD_TO_RUB = 29.8179; // По данным ЦБ РФ на 27.11.2009

    public static double convert(double rub) {
        return rub / USD_TO_RUB;
    }

    //перевод числа в строку
    public static String parseAndConvert(String rub) {
        double currency;

        if (rub == null) {
            JOptionPane.showMessageDialog(null, "The number can not be null");
            throw new NullPointerException("The number can not be null");
        }

        try {
            currency = convert(Double.parseDouble(rub));
            if (currency > 0) {
                return Double.toString(currency);
            } else {
                JOptionPane.showMessageDialog(null, "Can not enter a negative value");
                throw new IllegalArgumentException("Can not enter a negative value");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The number is written is not true!");
            throw new NumberFormatException("The number is written is not true!");
        }
    }

}
