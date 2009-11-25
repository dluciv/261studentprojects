package moneyconverter;
/**
 * Converter;
 * @author Zubrilin Andrey (c)2009
 */

public class converter {
    //Курс валюты;
    static double Exchange;
    //Изменение курса валюты на стандартный;
    public static double resetCourse(){
        return Exchange = 29.1;
    }

    //Проверяем является ли строка числом,если да,то возвращаем число,иначе 0;
    public static double convertString(String someMoney){
        try {
            return Double.parseDouble(someMoney);
        } catch (NumberFormatException e) {
        }
        return 0;
    }

    //Перевод из одних единиц в другие;
    public static double calcMoney(String someMoney){
        double val;

        val = convertString(someMoney);
        if(val>0)
            return val*Exchange;
        return 0;
    }
    
    //Перевод из одних единиц в другие;
    public static double calcBackMoney(String someMoney){
        double val;

        val = convertString(someMoney);
        if(val>0)
            return val/Exchange;
        return 0;
    }
}
