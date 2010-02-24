/*
 * (c) Stefan Bojarovski 2009
 * */
package converter;

/*
 * 1 Macedonian denar = 0.719575287 Russian rubles
 * 1 Russian ruble = 1.38970865 Macedonian denari
 * */

public class ConverterFunctionality {
		
	public static double convert(double value, String fromCur, String toCur){
		
		Currency from = new Currency(fromCur, value);
		Currency to = new Currency(toCur, 0);
		CurrencyConverter converter = new CurrencyConverter();
		
		return converter.convert(from, to);
	}
}

/*
private static double MKD_TO_RUB = 0.719575287;
private static double RUB_TO_MKD = 1.38970865;

public static double convertToRub (double inMkd){
	return (inMkd*MKD_TO_RUB);
}
public static double convertToMkd (double inRub){
	return (inRub*RUB_TO_MKD);
*/