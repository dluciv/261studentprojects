/**
 * Converter;
 * @author Zubrilin Andrey (c)2009
 */
package moneyconverter;

import javax.swing.*;

public class MoneyConverter {
    //Курс валюты;
    public double Exchange;

    public double NegativeNumberChecker(double num){
        num = num > 0 ? num : 0;
        return num;
    }
    //Проверяем является ли строка числом,если да,то возвращаем число,иначе 0;
    public double stringToNum(String someMoney){
        try {
            return Double.parseDouble(someMoney);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Incorrect value!");
            throw new NumberFormatException("Incorrect value!");
        }
    }

    //Перевод из одних единиц в другие;
    public double convert(String someMoney){
        double val;

        val = stringToNum(someMoney);
        return NegativeNumberChecker(val*Exchange);
    }
    
    //Перевод из одних единиц в другие;
    public double convertBack(String someMoney){
        double val;

        val = stringToNum(someMoney);
        return NegativeNumberChecker(val/Exchange);
    }
}
