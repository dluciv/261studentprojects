/* Example Of Using Interfaces
 * Here is the some Class1 created from Interface
 * Savenko Maria ©2009*/

package msavenko;

import java.io.*;

public class AskName implements IAskAnswer {

    @Override
    public String questionTheUser() {
        BufferedReader br = new BufferedReader(new
                InputStreamReader(System.in));
        String name=null;
        System.out.println("Hi !");
        System.out.print("What is your name mr/mrs? Tell me: ");
        try {
            name = br.readLine();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return name;
    }

    @Override
    public void replyTheUser() {
        String name=questionTheUser();
        System.out.println("Mmmmm !");
        System.out.println("Ok, Nice to meet your " + name);
    }

}
