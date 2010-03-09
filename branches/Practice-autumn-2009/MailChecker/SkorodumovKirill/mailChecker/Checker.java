//Skorodumov Kirill gr.: 261
//email task

package mailChecker;

import java.util.regex.Pattern;

public class Checker {
	public static String LOGIN = "[a-zA-Z_]+([a-zA-Z0-9_]*(.?[a-zA-Z0-9_]+)?)*";
	public static String HIGHLEVELDOMAIN = "([a-z]+\\.)";
	public static String LOWLEVELDOMAIN = "([a-z]{2,3}||museum||travel||info||name||aero||arpa||coop||mobi)";
	public static Pattern mailPattern = 
		Pattern.compile(LOGIN + "@" + HIGHLEVELDOMAIN + LOWLEVELDOMAIN);
	
	public static boolean check(String email){
		if (email == null) {
			throw new IllegalArgumentException("email");
		}
		
		return mailPattern.matcher(email).matches();
		
	}
	
	
	public static void main(String args[]) {
		System.out.println(check("a@b.cc"));
	}

}
