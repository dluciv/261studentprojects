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


        distance = conversion(Double.parseDouble(mile));
        return Double.toString(distance);


    }
}
