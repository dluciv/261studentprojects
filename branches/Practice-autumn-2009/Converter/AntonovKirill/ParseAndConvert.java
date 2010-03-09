/*
  (c) Antonov Kirill 2009
 program Convertor RUB to USD
 */

package converter;

import javax.swing.JOptionPane;

public class ParseAndConvert extends Exchanger {

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
