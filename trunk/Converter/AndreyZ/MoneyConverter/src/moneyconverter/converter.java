package moneyconverter;
/**
 * Converter;
 * @author Zubrilin Andrey (c)2009
 */

public class converter {

    //Изменение курса валюты на стандартный;
    public static double resetCourse(){
        return MakeMainMenu.exCourse = 29.1;
    }

    //Проверяем является ли строка числом,если да,то возвращаем число,иначе 0;
    public static double ifNum(String someMoney){
        try {
            return Double.parseDouble(someMoney);
        } catch (NumberFormatException e) {
        }
        return 0;
    }

    //Перевод из одних единиц в другие;
    public static double calcMoney(String someMoney,double Course,boolean whatEx){
        double val;

        val = ifNum(someMoney);
        if(val>0)
            if(whatEx)
                return val*Course;
            else
                return val/Course;
        return 0;
    }

}
