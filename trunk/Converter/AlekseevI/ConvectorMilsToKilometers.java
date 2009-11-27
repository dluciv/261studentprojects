/**
 *Modul conversion
 * @author Alekseev Ilya (c) 2009
 */
package convector;

import javax.swing.JOptionPane;

public class ConvectorMilsToKilometers {

    final static double KilometersInMile = 1.609269;

    public static double conversion(double mile) {
        return mile * KilometersInMile;

    }
// перевод чи�?ла �? �?троку

    public static String parseAndConvert(String mile) {

        double distance;


        distance = conversion(Double.parseDouble(mile));
        return Double.toString(distance);


    }
}
