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
        String firstpart = "[a-z||\\_]"; //имя делится на две части, т.к. должно
        //начинаться с буквы или подчеркивания
        String mainpart = "(" + symbol + "*|(\\.|\\_|\\-)" + symbol + ")*";
        String domain2 = symbol + "*";
        String domain1 = "((" + symbol + "{2,3})|" +
                         "(info)|(name)|(aero)|(arpa)|" +
                         "(coop)|(museum)|(mobi)|(travel))" ; //считается, что
        //любой 2-, 3-буквеный домен верен, более длинные перечисленны вручную
        Pattern regexp = Pattern.compile(firstpart + mainpart + "@" +
                                    domain2 + "." + domain1);
        Matcher m = regexp.matcher(mail);
        return m.matches();
              
    }

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your eMail addres:");
        
        while (true){ //завершается, если ввести пустую строку
            String mail = scan.nextLine();
            if (mail.length() == 0) break;
            if (isMail(mail)) {
                System.out.println("Your eMail is correct!");
            }
            else{
                System.out.println("Your eMail is wrong!");
            }
        }
    }

}
