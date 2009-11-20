/*
 * (c) Stefan Bojarovski 2009
 * 
 * An e-mail checker that uses java's RegEx library
 * */
package mail_checker;

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String name = "^[a-zA-Z_]([a-zA-Z_0-9]*)(\\.[a-zA-Z0-9_]+)*";
		String domain = "([a-zA-Z0-9-]+\\.)+(([a-zA-Z]{2,4})|museum|travel)$";
		String regex = name+"@"+domain;
		
		MailChecker mCheck = new MailChecker(regex);
		
		String candidate;
		
		while (true){
			
			System.out.print("Please enter a candidate string: ");
			candidate = in.readLine();
			
			if (candidate.isEmpty()){
				System.out.print("Empty candidate string entered. Exiting.");
				break;
			}
			
			else{
				System.out.print("Analyzing candidate string...");
				System.out.println(mCheck.check(candidate));
			}			
		}		
	}
}