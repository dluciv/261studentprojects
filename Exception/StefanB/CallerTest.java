package exception_checking;

import junit.framework.*;

public class CallerTest extends TestCase {
	
	public void testCallerNullArgument() throws IllegalArgumentException {
		Caller some = new Caller();
		try{
			some.call(null);
			fail ("IllegalArgumentException expected");
		}
		catch (IllegalArgumentException expected){}
	}
	
	public void testCallerValid() throws IllegalArgumentException {
		Caller some = new Caller();
		MobilePhone nokia = new MobilePhone();
		nokia.available = true;
		assertTrue(some.call(nokia));
	}
}
	
