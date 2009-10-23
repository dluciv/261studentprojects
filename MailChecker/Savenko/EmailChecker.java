/* EmailChecker
 * By Savenko Maria (c)2009 */

package msavenko;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailChecker {
    public static final Pattern secwrd = Pattern.compile
    ("[a-zA-Z0-9_]+");
    public static final Pattern firstword = Pattern.compile
    ("[a-zA-Z_][a-zA-Z0-9_]*");
    public static final Pattern email = Pattern.compile
    ("[a-zA-Z_][a-zA-Z0-9_]*(\\u002E[a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+\\u002E(([a-zA-Z0-9_][a-zA-Z0-9_])|(net)|(com)|(org)|(museum)|(info))");
    public static final Pattern zipcode = Pattern.compile
    ("([A-Z]{0,2} )*[0-9]{6}");

    public static boolean checkTheEmailMatching(String word) {
       Matcher matcher = email.matcher(word);
       if (matcher.matches()) {
           return true;
       }
       else {
           return false;
       }
    }
    
    public static boolean checkTheZipMatching(String word) {
        Matcher matcher = zipcode.matcher(word);
        if (matcher.matches()) {
           return true;
        }
        else {
           return false;
        }
     }

}
