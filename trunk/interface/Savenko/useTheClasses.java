/* Example Of Using Interfaces
 * Here is the selfCheck
 * Savenko Maria ©2009*/

package msavenko.Interf;

public class useTheClasses {
	public static void main(String[] args) {
		AskAnswerInterface conversation1 = new AskName();
		AskAnswerInterface conversation2 = new AskForHelp();
		conversation1.replyTheUser();
		conversation2.replyTheUser();
	}

}
