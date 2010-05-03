/*
 * Mail Checker
 * Dmitriy Zabranskiy g261 (c)2009
 */
package mailchecker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailChecker {

    private static String LOGIN = "[a-z_]{1}((\\.)?[a-z0-9_-])*";
    private static String SUBDOMAIN = "[a-z_]{1}((\\.)?[a-z0-9_-])*";
    private static String DOMAIN = "([a-z]{2,4}||travel||museum)";
    private static String REGEX = "^" + LOGIN + "@" + SUBDOMAIN + "\\." + DOMAIN + "$";

    public static boolean isMail(String email) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();

    }
}