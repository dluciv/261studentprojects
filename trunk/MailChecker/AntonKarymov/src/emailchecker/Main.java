/*Anton Karymov,gr261,2009,
* EmailChecker
*/

package emailchecker;

import java.util.regex.*;

public class Main {
    public static boolean checkEmail(String email){
        String nickName = "[a-zA-Z_]" + "([.]{0,1}[a-zA-Z1-9_]+)*" ;
        String domainFirstLevel = "([a-z]{2,3}||museum||travel||info||name||aero||arpa||coop||mobi)";
        String domainSecondLevel = "[a-z]+";

        Pattern pattern = Pattern.compile (nickName+"@"+domainSecondLevel+"."+domainFirstLevel);
        String result = "This Email: " + email;
        Matcher compare = pattern.matcher(email);

        if (compare.matches()){
            result += " - passed";
        } else {
            result += " - not passed";
            }

        System.out.println(result);
        return  compare.matches();
        }

    public static void main(String[] args){
        checkEmail ("AntonKarymov@yandex.ru");
        }
}

