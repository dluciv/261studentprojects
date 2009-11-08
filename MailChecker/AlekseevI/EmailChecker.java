/**Класс обработки подаваемого Email.
 * 
 * @author Alekseev Ilya
 */
package emailchecker;

import java.util.regex.*;

public class EmailChecker {

    private static final String login = "[a-zA-Z_]([a-zA-Z_0-9]*)(\\.[a-zA-Z0-9_]+)*";
    private static final String domainSecondLevel = "([a-zA-Z0-9-]+\\.)+";
    private static final String domainFirstLevel = "(([a-zA-Z]{2,4})|museum|travel)";
    private static final String isEmail = login + "@" + domainSecondLevel +
            domainFirstLevel;
	// сопоставление с возможным образцом
    public static boolean isEmail(String email) {

        if (email == null) {
            throw new IllegalArgumentException("email can not be null");
        }
        Pattern pattern = Pattern.compile(isEmail);
        Matcher matcher = pattern.matcher(email);

        return matcher.find();

    }
}
