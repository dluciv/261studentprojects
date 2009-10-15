//Lebedev Dmitry g261 2009 (c)

package emailchecker;

import java.util.regex.*;

public class Main { 
    static String Login = "(([a-zA-Z_])|([a-zA-Z_]+([.]{0,1}[a-zA-Z0-9_])+))";
    static String TopLevelDomain = "(((travel)|(museum)|([a-z]{2,4})))";
    static String LowLevelDomain = "[a-z]+";
    public static boolean checkEmail (String Email) {
        Pattern pattern = Pattern.compile (Login + "@" + LowLevelDomain
                                                 + "(.)" + TopLevelDomain);
        Matcher matcher = pattern.matcher(Email);
        boolean flag = matcher.matches();
        String output = Email;
        if (flag) {
            output += " is CORRECT email adress\n";
        }
        else 
            output += " isn't correct email adress\n";
        System.out.println(output);
        return flag;
}
        public static void main(String[] args) {
          /*  checkEmail("a@b.cc");
            checkEmail("yuri.gubanov@mail.ru");
            checkEmail("my@domain.info");
            checkEmail("a.b..f.s@mail.com");
            checkEmail("yurik@hermitage.museum");
            checkEmail("Name.Sur_name@gmail.com");

            checkEmail("a@b.c");
            checkEmail("a..b@mail.ru");
            checkEmail(".a@mail.ru");
            checkEmail("yo@domain.domain");
            checkEmail("1@mail.ru");
            */
         }
}


