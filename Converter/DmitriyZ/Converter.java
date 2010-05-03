// Dmitriy Zabranskiy g261 (c)2009
// Converter (km to yards)
import javax.swing.JOptionPane;

public class Converter {

    final static int KM_TO_YARD = 1094;

    public static int convert(int km) {
        return km * KM_TO_YARD;
    }

    public static String check(String km) {

        if (km == null) {
            JOptionPane.showMessageDialog(null, "Argument can not be null");
            throw new NullPointerException("Argument can not be null");
        }

        try {
            if (convert(Integer.parseInt(km)) > 0) {
                return Integer.toString(convert(Integer.parseInt(km)));
            } else if (Integer.parseInt(km) == 0) {
                return Integer.toString(0);
            } else {
                JOptionPane.showMessageDialog(null, "You can't enter a negative value");
                throw new IllegalArgumentException("You can't enter a negative value");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The number is written incorrectly");
            throw new NumberFormatException("The number is written incorrectly");
        }
    }
}