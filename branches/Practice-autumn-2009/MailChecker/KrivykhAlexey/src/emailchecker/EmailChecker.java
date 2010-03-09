//(с) Кривых Алексей 2009г.
//Emailchecker
package emailchecker;

import java.util.regex.*;

public class EmailChecker {

    private static final String Name = "[a-z_]+([\\.]?[a-z0-9-_]+)*";
    private static final String SubDomain = "[a-z_]+(\\.)?([a-z0-9-_]+\\.)*";
    private static final String Domain = "([a-z]{2,4}|travel|museum)";
    private static final String reg4mail = Name + "@" + SubDomain + Domain;

    public static boolean isMail(String email) {
        Pattern pattern = Pattern.compile(reg4mail);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}