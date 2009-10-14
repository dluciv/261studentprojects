/*
 *Program check is input string a mail
 *soldatov dmitry ©, 2009
 */

package mailchecker;
import java.util.regex.*;
import java.util.Scanner;

public class MailChecker {
    public static boolean isMail(String mail){
        String symbol = "[a-z||0-9]";
        String firstpart = "[a-z||\\_]";
        String mainpart = "(" + symbol + "*|(\\.|\\_|\\-)" + symbol + ")*";
        String domain2 = symbol + "*";
        String domain1 = "((" + symbol + "{2,3})|" +
                         "(info)|(name)|(aero)|(arpa)|" +
                         "(coop)|(museum)|(mobi)|(travel))" ;
        Pattern p = Pattern.compile(firstpart + mainpart + "@" +
                                    domain2 + "." + domain1);
        Matcher m = p.matcher(mail);
        return m.matches();
              
    }

    public static void main(String[] args) {
       // Scanner scan = new Scanner(System.in);
       // String mail = scan.nextLine();
        String mail = "vbanzay@mail.ru";
        System.out.println (isMail(mail));
    }

}
