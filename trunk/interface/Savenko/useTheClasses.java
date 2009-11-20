/*
 * Example Of Using Interfaces Here is the selfCheck Savenko Maria ©2009
 */

package msavenko;

public class UseTheClasses {
    
    public static void main(String[] args) {
        IAskAnswer askName    = new AskName();
        IAskAnswer askForHelp = new AskForHelp();
        askName.Dialog();
        askForHelp.Dialog();
    }
    
}
