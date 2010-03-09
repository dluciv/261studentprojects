/*
 * EMailChecker class for Email checker program by Korshakov Stepan
 */
package hotheart.study.emailchecker;

import java.util.regex.Pattern;

/**
 * @author Korshakov Stepan
 */
public class EMailChecker {

    private static final String login = "[a-zA-Z_]([a-zA-Z_0-9]*)(\\.[a-zA-Z0-9_]+)*";
    private static final String domain = "([a-zA-Z0-9-]+\\.)+";
    private static final String zone = "(([a-zA-Z]{2,4})|museum|travel)";
    private static Pattern emailPattern = Pattern.compile(login + "@" + domain + zone);

    public static boolean checkEmail(String data) {
        return emailPattern.matcher(data).matches();
    }
}
