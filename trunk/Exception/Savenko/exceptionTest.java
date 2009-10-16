/* JUnit test for Exception
 * By Savenko Maria (c)2009 */

package msavenko.learnToThrowAnException;

//import msavenko.EmailChecker.EmailChecker;
import msavenko.Interf.AskName;

import org.junit.Test;

public class exceptionTest {
	
	@Test(expected = Exception.class)
	public void testCheckTheInterface() throws Exception{
		 exception.checkTheInterface(AskName.class);
	}

}
