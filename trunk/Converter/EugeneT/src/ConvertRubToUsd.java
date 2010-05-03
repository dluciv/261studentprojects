/**
 * Класс конвертирования рублей в доллары
 * @author Eugene Todoruk
 * group 261
 */

import javax.swing.JOptionPane;

public class ConvertRubToUsd {

    final static double USD_TO_RUB = 29.1641;  // Данные на 22.10.09

    public static double convert(double rub) {
        return rub / USD_TO_RUB;
    }

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
                JOptionPane.showMessageDialog(null, "Нельзя вводить отрицательные значения");
                throw new IllegalArgumentException("Нельзя вводить отрицательные значения");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Число записано не верно!");
            throw new NumberFormatException("Число записано не верно!");
        }
    }
}
