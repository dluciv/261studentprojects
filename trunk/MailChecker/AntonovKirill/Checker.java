//(C), 2009 Antonov Kirill
//The EmailChecker


package mailchecker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checker {
      public static boolean checkEmail(String Mail) throws Exception {
        if(Mail == null) {
           throw new Exception("There is not such Mail Address");
        }
      String Login = "[a-z_]+(\\.)?([a-z0-9_-]+(\\.)?)*";
      String SubDomain = "[a-z_]+((\\.)|(([a-z0-9_-]+(\\.))+))";
      String Domain = "([a-z]{2,4}||ru||com||org||museum)";
      String Regex = Login + "@" + SubDomain + Domain;
      Pattern p = Pattern.compile(Regex);
      //Pattern p = Pattern.compile(UserName + "@" + SubDomain +  Domain);
      Matcher m = p.matcher(Mail);
      return m.matches();
      }
      public static void main(String[] args) throws Exception {
        System.out.println(Checker.checkEmail(null));
    }
 }




