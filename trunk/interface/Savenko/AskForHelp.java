/* Example Of Using Interfaces
 * Here is the some Class2 created from Interface
 * Savenko Maria ©2009*/

package msavenko;

import java.io.*;

public class AskForHelp implements IAskAnswer {

	@Override
	public String questionTheUser() {
		BufferedReader br = new BufferedReader(new
                InputStreamReader(System.in));
        String reply=null;
		System.out.print("O, just remembered, would you help me? (y/n): ");
		try {
			reply = br.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return reply;
	}

	@Override
	public void replyTheUser() {

		if (questionTheUser().equals("y")) 
			System.out.println("Very kind of you! Thanks ");
		else System.out.println("Sorry, for troubling. Goodby! ");
	}

}
