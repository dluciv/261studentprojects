/**
 * Currency converter
 * (c)Pasha_Zolnikov, 2009
 */



public class CurrencyConverter implements Converter{
    double dollarRate = 29.08;
    
    public double convertForwards(double dollar) {
        return dollar * dollarRate;
    }

    public double convertBackwards(double ruble) {
        return ruble / dollarRate;
    }

}