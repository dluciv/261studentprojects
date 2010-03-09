/*
 * program for testing e-mail adress
 * (c) Yaskov Sergey, 261; 2009
 * */

package emailchecker;

import java.util.regex.*;

public class EmailChecker {
    private static String toCompare = 
            "([_a-zA-Z]+(\\.?[_0-9a-zA-Z])*" +
            "@(([a-zA-Z0-9]\\.?)+[.])+" +
            "((travel)|(museum)|([a-z]{2,4})))";

    public static boolean checkEmail(String address) {
        if (address == null) {
            throw new IllegalArgumentException("email");
        }
        
        Pattern pattern = Pattern.compile(toCompare);
        Matcher matcher = pattern.matcher(address);
        
        return matcher.matches();
    }
}