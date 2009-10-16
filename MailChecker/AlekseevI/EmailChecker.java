/**
 *
 * @author Alekseev Ilya
 */
package EduAlekseev;

import java.util.regex.*;

public class EmailChecker {

    public static boolean isEmail(String email) {

        String login = "(([a-zA-Z_])|([a-zA-Z_]+([.|_|-]{0,1}[a-zA-Z0-9_])+))";
        String domainSecondLevel = "(([a-zA-Z_])|([a-zA-Z_]+([.]{0,1}[a-zA-Z0-9_])+))";
        String domainFirstLevel = "([a-z]){2,4}|museum|travel";

       if (email == null){
           throw new NullPointerException("email is null");
       }
        
                String regexpEmail = login + "@" + domainSecondLevel + "." + domainFirstLevel;
                Pattern pattern = Pattern.compile(regexpEmail);
                Matcher matcher = pattern.matcher(email);
        
                return matcher.matches();
           
       }   
}
