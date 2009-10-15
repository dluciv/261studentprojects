package emailchecker;


/**
 *
 * @author Lii
 */
import java.util.regex.*;

public class EmailChacker {
    
    public static boolean isEmail (String email){
        
        String DomenThirdLevel = "(([a-zA-Z_])|([a-zA-Z_]+([.]{0,1}[a-zA-Z0-9_])+))";
        String DomenSecondLevel = "(([a-zA-Z_])|([a-zA-Z_]+([.]{0,1}[a-zA-Z0-9_])+))";
        String DomenFirstLevel = "([a-z]){2,4}|museum|travel";
        
        String REGEXPeMail = DomenThirdLevel + "@" + DomenSecondLevel+"."+DomenFirstLevel;
        Pattern pattern = Pattern.compile(REGEXPeMail);
        Matcher matcher = pattern.matcher(email);
      
        return matcher.matches();
    }


}
