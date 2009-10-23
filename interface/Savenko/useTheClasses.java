/* Example Of Using Interfaces
 * Here is the selfCheck
 * Savenko Maria ©2009*/

package msavenko;

public class UseTheClasses {
	public static void main(String[] args) {
		IAskAnswer conversation1 = new AskName();
		IAskAnswer conversation2 = new AskForHelp();
		conversation1.replyTheUser();
		conversation2.replyTheUser();
	}

}
