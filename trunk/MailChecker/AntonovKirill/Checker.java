//(C), 2009 Antonov Kirill
//The EmailChecker


package mailchecker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checker {
      public static boolean checkEmail(String eMail) throws Exception {
         //Exception mailIsNull = new Exception();
                 if(eMail == null) {
                   throw new Exception("There is not such eMail Address");
       }
         String UserName = "[a-z_]+(\\.)?([a-z0-9_-]+(\\.)?)*";
         String SubDomain = "[a-z_]+((\\.)|(([a-z0-9_-]+(\\.))+))";
         String Domain = "([a-z]{2,4}||ru||com||org||museum)";
     	 // String sDomen1 = "[a-z]([a-z[0-9]\u002E\u005F\u002D]*[a-z||0-9])*";
    	 //String sDomen2 = "(([a-z]){2,4}|net||org||ru||info|museum)";
    	 //Pattern p = Pattern.compile(sDomen1 + "@" + sDomen1 + sDomen2);
         Pattern p = Pattern.compile(UserName + "@" + SubDomain +  Domain);
    	 Matcher m = p.matcher(eMail);
         return m.matches();
    }
      public static void main(String[] args) throws Exception {
        System.out.println(Checker.checkEmail(null));
    }
 }




