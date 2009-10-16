/*
 *Program check is input string a mail
 *soldatov dmitry В©, 2009
 */

package mailchecker;
import java.util.regex.*;
import java.util.Scanner;

public class MailChecker {
    public static boolean isMail(String mail){
        String symbol = "[a-z0-9]";
        String firstpart = "[a-z_]"; //login is divided into two parts
        //becouse one must begin with letter or "_"
        String mainpart = "(" + symbol + "*|(\\.|_|-)" + symbol + ")*";
        String domain2 = symbol + symbol + "*";
        String domain1 = "((" + symbol + "{2,3})|" +
                         "(info)|(name)|(aero)|(arpa)|" +
                         "(coop)|(museum)|(mobi)|(travel))" ; //supose that
        //every 2-, 3-letter domain is true
        Pattern regexp = Pattern.compile(firstpart + mainpart + "@" + 
                                         domain2 + "." + domain1);
        Matcher m = regexp.matcher(mail);
        return m.matches();
              
    }

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        
        while (true){ //cycle close when line is empty
            System.out.println("Enter your mail addres: ");
            String mail = scan.nextLine();
            if (mail.length() == 0) break;
            if (isMail(mail)) {
                System.out.println("Your mail is correct!\n");
            }
            else{
                System.out.println("Your mail is wrong!\n");
            }
        }
    }

}
