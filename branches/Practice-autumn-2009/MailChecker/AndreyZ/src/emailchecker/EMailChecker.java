package emailchecker;
/**
 * E-MailChecker Class.
 * @author Zubrilin Andrey (c)2009
 */
import java.util.regex.*;

public class EMailChecker {

    public static boolean Checker(String email) {

        String AddressName = "[a-z\\_]([a-z\\_\\-\\d]*)(\\.[a-z\\-\\d\\_]+)*";
        String domain1 = "([a-z]{2,4}|museum|travel)";
        String domain2 = "[a-zA-Z\\d\\_\\-]+[.]";

        String eMail = AddressName+"@"+domain2+domain1;

        Pattern pattern = Pattern.compile(eMail);
        Matcher matcher = pattern.matcher(email);

        if(!matcher.matches())
            return false;
        return true;
    }
}
