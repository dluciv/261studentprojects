/* Exception
 * Проверить аргументы метода, принимающего интерфейс, и 
 * кинуть исключение про инвалид параметер, если он null
 * By Savenko Maria (c)2009 */

package msavenko.learnToThrowAnException;

import java.util.*;

public class exception {
	
	public static void checkTheInterface(Class someClass) throws Exception{
		if (someClass.getInterfaces()==null) 
			throw new Exception ("This Class Implements no Interface");
	}
	
}
