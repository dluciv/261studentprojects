package mailchecker;

/* Class MailChecker
 * @author
 * Eugene Todoruk
 * group 261
 */
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MailChecker {

    private static final String USERNAME = "[a-z_]+(\\.)?([a-z0-9_-]+(\\.)?)*";
    private static final String SUBDOMAIN = "[a-z_]+((\\.)|(([a-z0-9_-]+(\\.))+))";
    private static final String DOMAIN = "([a-z]{2,4}||museum||travel)";
    public static final String EMAIL_CHECK = "^" + USERNAME + "@" +
            SUBDOMAIN + DOMAIN + "$";
    
    public static boolean isEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("email can not be null");
        }
        Pattern pattern = Pattern.compile(EMAIL_CHECK);
        Matcher matcher = pattern.matcher(email);

        return matcher.find();
    }
}