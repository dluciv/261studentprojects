/*
    (c) Stefan Bojarovski 2009
    These tests ask for the usage of
    exceptions and exception handling in the code.
*/
package exception_checking;

import junit.framework.*;

public class CallerTest extends TestCase {
    
    //Expects the method call to trow an exception
    //when called with argument null
    public void testCallerNullArgument() throws IllegalArgumentException {
        Caller some = new Caller();
        try{
            some.call(null);
            fail ("IllegalArgumentException expected");
        }
        catch (IllegalArgumentException expected){}
    }
	
    //Verifies that a call was succesful 
    //when a proper and available phone is called
    public void testCallerValid() throws IllegalArgumentException {
        Caller some = new Caller();
        MobilePhone nokia = new MobilePhone();
        nokia.available = true;
        assertTrue(some.call(nokia));
    }
}
	
