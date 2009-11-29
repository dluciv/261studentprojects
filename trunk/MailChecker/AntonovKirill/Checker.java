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
      String USERNAME = "[a-z_]+(\\.)?([a-z0-9_-]+(\\.)?)*";
      String SUBDOMAIN = "[a-z_]+((\\.)|(([a-z0-9_-]+(\\.))+))";
      String DOMAIN = "([a-z]{2,4}||ru||com||org||museum)";
      String Regex = USERNAME + "@" + SUBDOMAIN + DOMAIN;
      Pattern p = Pattern.compile(Regex);
      //Pattern p = Pattern.compile(UserName + "@" + SUBDOMAIN +  DOMAIN);
      Matcher m = p.matcher(Mail);
      return m.matches();
      }
      public static void main(String[] args) throws Exception {
        System.out.println(Checker.checkEmail(null));
      }
 }




