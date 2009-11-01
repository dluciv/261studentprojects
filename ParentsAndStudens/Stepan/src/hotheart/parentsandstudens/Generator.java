/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotheart.parentsandstudens;

import java.util.Random;

/**
 *
 * @author HotHeart
 */
public class Generator {

    private static final Random rnd = new Random();

    private static final String[] namesMale = new String[]
    {
        "Александр", "Степан", "Дситрий", "Илья", "Сергей", "Михаил"
    };
    
    private static final String[] namesFemale = new String[]
    {
        "Александра", "Алина", "Марина", "Анастасия", "Катерина", "Аня"
    };

    public static String generateName(Sex sex) {
        if (sex == Sex.FEMALE)
            return namesFemale[rnd.nextInt(namesFemale.length)];
        else
            return namesMale[rnd.nextInt(namesMale.length)];
    }
}
