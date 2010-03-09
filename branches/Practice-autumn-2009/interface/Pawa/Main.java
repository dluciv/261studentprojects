/**
 * Using converter
 * (c)Pasha_Zolnikov, 2009
 */



public class Main {
    public static double dollars = 234500;
    public static double rubles = 23567200;
    public static double kiogramms = 234.45;
    public static double pounds = 1235.56;
    
    public static CurrencyConverter currencyConverter = new CurrencyConverter();
    public static MassConverter massConverter = new MassConverter();
    
    public static void main(String[] args){
        System.out.println(currencyConverter.convertForwards(dollars));
        System.out.println(currencyConverter.convertBackwards(rubles));
        System.out.println(massConverter.convertForwards(kiogramms));
        System.out.println(massConverter.convertBackwards(pounds));
    }
}    
