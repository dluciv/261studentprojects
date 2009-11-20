/*
 * (c) Stefan Bojarovski 2009
 * */

package mail_checker;

import java.util.regex.*;

public class MailChecker {
	
	private String regex; //stores the regex pattern
	private Pattern p;
	private Matcher m;
	
	//constructor
	public MailChecker(String expr) {
		this.regex = expr;
		p = Pattern.compile(expr);
	}

	//allows to change the regex pattern
	public void setRegex(String newRegex) {
		/*TODO: write a regular to "java-type" regex transformer
		 * 		so that the regex pattern could be changed if necessary
		 * */
		this.regex = newRegex;
		p = Pattern.compile(regex);
	}
	
	//returns the momentary regex pattern
	public String getRegex() {
		return regex;
	}
	
	//analyzes the candidate string and returns result
	public boolean check (String candidate){
		m = p.matcher(candidate);
		return m.matches();
	}
}
