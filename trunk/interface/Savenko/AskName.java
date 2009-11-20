/*
 * Example Of Using Interfaces Here is the some Class1 created from Interface
 * Savenko Maria ©2009
 */

package msavenko;

import java.io.*;

public class AskName implements IAskAnswer {
	
	@Override
	public void Dialog() {
        String name = askUser("Hi! What is your name Mr/Mrs? Tell me: ");
        replyUser(name);
	}
    
    @Override
    public String askUser(String question) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = null;
        System.out.print(question);
        try{
            name = reader.readLine();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return name;
    }
    
    @Override
    public void replyUser(String name) {
        System.out.println("Ok, Nice to meet your " + name);
    }
    
}
