/* JUnit test for Exception
 * By Savenko Maria (c)2009 */

public class TryThrowingExceptions {

	public static void checkTheInterface(AskAnswerInterface askAnswerInterface)
			throws IllegalArgumentException {
		if (askAnswerInterface == null)
			throw new IllegalArgumentException("Argument can not be null");
	}

}
