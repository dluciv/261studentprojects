/*
 * Mail Checker
 * Dmitriy Zabranskiy g261 (c)2009
 */
package mailchecker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailChecker {
    //"\\u002E" = ".", "\\u005F" = "_"

    private static String POINT = "^(?!(.*\\.\\.))"; // look for ".." in a line
    private static String LOGIN = "[a-z\\u005F]{1}[a-z\\d\\u005F\\u002E-]*";
    private static String SUBDOMAIN = "[a-z\\u005F]{1}[a-z0-9\\u005F\\u002E-]*";
    private static String DOMAIN = "([a-z]{2,4}||travel||museum)";
    private static String REGEX = "^" + LOGIN + "@" + SUBDOMAIN + "\\." + DOMAIN + "$";

    public static boolean isMail(String email) {
        Pattern pattern = Pattern.compile(POINT);
        Matcher matcher = pattern.matcher(email);

        if (matcher.find()) {
            pattern = Pattern.compile(REGEX);
            matcher = pattern.matcher(email);
            return matcher.find();
        } else {
            return false;
        }

    }
}