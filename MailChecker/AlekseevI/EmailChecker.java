/**
 *
 * @author Alekseev Ilya
 */
package EduAlekseev;

import java.util.regex.*;

public class EmailChecker {

     private static String login = "(([a-zA-Z_])|([a-zA-Z_]+([.|_|-]{0,1}[a-zA-Z0-9_])+))";
     private static String domainSecondLevel = "(([a-zA-Z_])|([a-zA-Z_]+([.]{0,1}[a-zA-Z0-9_])+))";
     private static String domainFirstLevel = "([a-z]){2,4}|museum|travel";


     public static boolean isEmail(String email) {
       if (email == null)
           throw new NullPointerException("email is null");
       Pattern pattern = Pattern.compile(String.format("%s%s%s%s", login,
                 domainSecondLevel, domainFirstLevel));
       Matcher matcher = pattern.matcher(email);
        
                return matcher.matches();
           
       }   
}
