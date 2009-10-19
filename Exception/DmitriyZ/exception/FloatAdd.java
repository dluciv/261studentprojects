//Dmitriy Zabranskiy g261 (c) 2009
//Exception
package exception;

public class FloatAdd {

    public static float add(String num1, String num2) {

        try {
            return Float.parseFloat(num1) + Float.parseFloat(num2);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Please, enter the numbers!");
        }

    }
}