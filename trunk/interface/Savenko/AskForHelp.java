/*
 * Example Of Using Interfaces Here is the some Class2 created from Interface
 * Savenko Maria ©2009
 */

package msavenko;

import java.io.*;

public class AskForHelp implements IAskAnswer {
	
	@Override
	public void Dialog() {
		String reply=askUser("O, just remembered, would you help me? (y/n): ");		
		replyUser(reply);
	}
    
    @Override
    public String askUser(String question) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String reply = null;
        System.out.print(question);
        try{
            reply = reader.readLine();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        
		return reply;
    }
    
    @Override
    public void replyUser(String reply) {        
        if (reply.equals("y")||reply.equals("Y")){
        	System.out.println("Very kind of you! Thanks ");
        } else {
        	System.out.println("Sorry, for troubling. Goodbye! ");
        	throw new NullPointerException("You said NO!!!");
        }
    }
    
}
