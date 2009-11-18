/*
 * (c) Stefan Bojarovski 2009
 * 
 * An e-mail checker that uses java's RegEx library
 * */
package EmailChecker;

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		mailChecker mCheck = new mailChecker();
		mCheck.init();
		String candidate;
		
		while (true){
			
			System.out.print("Please enter a candidate string: ");
			candidate = in.readLine();
			
			if (candidate.isEmpty()){
				System.out.print("Empty candidate string entered. Exiting...");
				break;
			}
			
			else{
				System.out.print("Analyzing candidate string...");
				System.out.println(mCheck.check(candidate));
			}			
		}		
	}
}