/*
 * (c) Stefan Bojarovski 2009
 * Explanation of the regex pattern
 * ^ 								- the beginning of a line.
 * [a-zA-Z_]						- followed by any letter or an underscore.
 * ([a-zA-Z_0-9]*)					- followed by a group, consisting of any letter, digit 
 * 									  or an underscore, which may repeat zero
 * 									  or more times.
 * (\\.[a-zA-Z0-9_]+)*				- followed by a group, consisting of
 * 									  a dot followed by any letter, digit or an underscore
 * 									  which may repeat one or more times,
 * 									  and the whole group may repeat zero or more times.
 * @								- followed by a @.
 * ([a-zA-Z0-9-]+\\.)+				- followed by a group consisting of any letter, digit
 * 									  or "-" which may repeat one or more times and a dot.
 * 									  The group may repeat one or more times. 
 * (([a-zA-Z]{2,4})|museum|travel)	- followed by a group consisting of either 
 * 									  a group consisting of any letter which may repeat
 * 									  at least 2 but no more than 4 times
 * 									  or
 * 									  either of the words "museum" or "travel"
 * $								- followed by an end of line
 * */

package EmailChecker;

import java.util.regex.*;

public class mailChecker {
	
	private String Regex;
	public Pattern p;
	public Matcher m;
	
	public void init(){
		String name = "^[a-zA-Z_]([a-zA-Z_0-9]*)(\\.[a-zA-Z0-9_]+)*";
		String domain = "([a-zA-Z0-9-]+\\.)+(([a-zA-Z]{2,4})|museum|travel)$";
		setRegex(name+"@"+domain);
	}
	
	public void setRegex(String newRegex) {
		/*TODO: write a regular to "java-type" regex transformer
		 * 		so that the regex pattern could be changed if necessary
		 * */
		this.Regex = newRegex;
		p = Pattern.compile(Regex);
	}
	
	public String getRegex() {
		return Regex;
	}
	
	public boolean check (String candidate){
		m = p.matcher(candidate);
		return m.find();
	}
}
