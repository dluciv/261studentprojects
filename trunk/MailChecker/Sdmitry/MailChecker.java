/*
 *Program check is input string a mail
 *soldatov dmitry ©, 2009
 */

package mailchecker;
import java.util.regex.*;
import java.util.Scanner;

public class MailChecker {
    
    private static String symbol = "[a-z0-9]";
    private static String firstPart = "[a-z_]"; //login is divided into two 
    //parts becouse one must begin with letter or "_"
    private static String mainPart = "(" + symbol + "*|(\\.|_|-)" + symbol + ")*";
    private static String login = firstPart + mainPart;
    private static String subDomain = symbol + "(" + symbol + "*|(\\.)" + symbol + ")*";
    private static String specialDomains = "(info)|(name)|(aero)|(arpa)|" +
                                           "(coop)|(museum)|(mobi)|(travel)"; 
    private static String topLevelDomain = "((" + symbol + "{2,3})|" + specialDomains + ")"; 
    //supose that every 2-, 3-letter domain is true
    private static String dot = ".";
    private static String at = "@";
    private static String mail = login + at + subDomain + dot + topLevelDomain;
    private static Pattern mailRegexp = Pattern.compile(mail);
    
    public static boolean isMail(String mail){
        
        Matcher checker = mailRegexp.matcher(mail);
        return checker.matches();
    }

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        while (true){ //cycle close when line is empty
            System.out.println("Enter your mail addres: ");
            String mail = scan.nextLine();
            if (mail.length() == 0) {
                break;
            }
            if (isMail(mail)) {
                System.out.println("Your mail is correct!\n");
            }
            else{
                System.out.println("Your mail is wrong!\n");
            }
        }
    }

}
