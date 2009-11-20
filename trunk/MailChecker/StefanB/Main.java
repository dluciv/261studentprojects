/*
 * (c) Stefan Bojarovski 2009
 * 
 * An e-mail checker that uses java's RegEx library
 * 
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

package mail_checker;

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		//creating a reader, that we use to get input from the user
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		//these strings are used to create the regex pattern
		String name = "^[a-zA-Z_]([a-zA-Z_0-9]*)(\\.[a-zA-Z0-9_]+)*";
		String domain = "([a-zA-Z0-9-]+\\.)+(([a-zA-Z]{2,4})|museum|travel)$";
		String regex = name+"@"+domain;
		
		//the object that analyzes our candidate string
		MailChecker mCheck = new MailChecker(regex);
		
		String candidate;//used to store the user's input
		
		while (true){
			
			System.out.print("Please enter a candidate string: ");
			candidate = in.readLine();//getting input
			
			if (candidate.isEmpty()){
				//got an empty string, exiting
				System.out.print("Empty candidate string entered. Exiting.");
				break;
			}
			
			else{
				System.out.print("Analyzing candidate string...");
				System.out.println(mCheck.check(candidate));//checking for a match
			}			
		}		
	}
}