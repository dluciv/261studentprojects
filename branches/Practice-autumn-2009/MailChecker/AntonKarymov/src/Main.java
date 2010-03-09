/*Anton Karymov,gr261,2009,
 * EmailChecker
 */
package emailchecker;

import java.util.regex.*;

public class Main {

    static final String mailBoxName = "[a-zA-Z_]" + "([.]?[a-zA-Z1-9_]+)*";
    static final String subDomain = mailBoxName;
    static final String domainFirstLevel = "([a-z]{2,3}||museum||travel||info||name||aero||arpa||coop||mobi)";
    static final String at = "@";
    static final String dot = "[.]";
    static final Pattern pattern = Pattern.compile(String.format("%s%s%s%s%s", mailBoxName, at, subDomain, at, domainFirstLevel));

    public static boolean checkEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("email");
        }
        Matcher compare = pattern.matcher(email);
        return compare.matches();
    }
}

