/*
 * MailChecker
 * (c)Pasha_Zolnikov, 2009
 * */

package mailchecker;

import java.util.regex.*;

public class MailChecker {
	String login = "[a-z_][a-z0-9]*(\\.\\w+)*";
	String subdomain = "([a-z]+\\.)*";
	String domain = "(([a-z]{2})|(com)|(net)|(org)|(biz)|(edu)|(int)|(gov)|(mil)|(info)|" +
					"(name)|(aero)|(arpa)|(coop)|(mobi)|(museum)|(travel))";
	String mailregexp = String.format("%s@%s%s", login, subdomain, domain);
		
	Pattern pattern = Pattern.compile(mailregexp);
    
	public static boolean mailCheck(String mail){
		Matcher matcher = pattern.matcher(mail);
		return matcher.matches();
    }
}