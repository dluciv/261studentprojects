/*
 * MailChecker
 * (c)Pasha_Zolnikov, 2009
 * */

package MailChecker;

import java.util.regex.*;

public class MailChecker {
    public static boolean mailCheck(String mail){
	String login = "[a-z_][a-z0-9]*(\\.\\w+)*";
	String subdomain = "([a-z]+\\.)*";
	String domain = "(([a-z]{2})|(com)|(net)|(org)|(biz)|(edu)|(int)|(gov)|(mil)|(info)|" +
		 	"(name)|(aero)|(arpa)|(coop)|(mobi)|(museum)|(travel))";
	String mailregexp = String.format("%s@%s%s", login, subdomain, domain);
		
	Pattern pattern = Pattern.compile(mailregexp);
	Matcher matcher = pattern.matcher(mail);
	return matcher.matches();
    }
}