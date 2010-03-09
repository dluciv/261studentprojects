//(c) Кривых Алексей 2009г.
//Swing converter
package converter;

import java.text.NumberFormat;

public class Converter {

    String convert(String input) {
        NumberFormat nf = NumberFormat.getInstance();
        //устанавливаем максимальное кол-во символов после запятой
        nf.setMaximumFractionDigits(5);
        try {
            return nf.format((Double.valueOf(input) * 1.8) + 32);
        } catch (NumberFormatException e) {
            return "Is not a Numb";
        }
    }
}
