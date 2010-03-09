/*
  (c) Antonov Kirill 2009
 program Convertor RUB to USD
 */


package converter;

public class Exchanger {
    private static double USD_TO_RUB = 29.8179;

    public static double convert(double rub) {
        return rub / USD_TO_RUB;
    }

}
