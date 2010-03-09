/**Modul conversion
 *
 * @author Alekseev Ilya
 */
package convector;

import javax.swing.JOptionPane;

public class ConvectorMilsToKilometers {

    final static double KilometersInMile = 1.609269;

    public static double conversion(double mile) {
        return mile * KilometersInMile;
    }
// перевод числа с строку

    public static String parseAndConvert(String mile) {

        double distance;

        if (mile == null) {
            JOptionPane.showMessageDialog(null, "число не может быть нулом");
            throw new NullPointerException("число не может быть нулом");
        }
        try {
            distance = conversion(Double.parseDouble(mile));
            if (distance > 0) {
                return Double.toString(distance);
            } else {
                JOptionPane.showMessageDialog(null, "Не вводите отрицательные числа");
                throw new IllegalArgumentException("не вводите отрицательные числа");
            }
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(null, "Число не верное,не пишите символы ");
            throw new NumberFormatException("Число не верное,не пишите символы ");
        }
    }
}
