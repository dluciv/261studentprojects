/*
 * (c) Stefan Bojarovski 2009
 * */
package EmailChecker;

import junit.framework.*;
import EmailChecker.mailChecker;

public class mailCheckerTest extends TestCase {
	mailChecker tested = new mailChecker();
	{
		tested.init();
	}
	
	//Valid e-mail addresses
	public void testCheckSingleChar(){
		assertTrue(tested.check("a@b.cc"));
	}
	public void testCheckDot(){
		assertTrue(tested.check("yuri.gubanov@mail.ru"));
	}
	public void testCheckDomainInfo(){
		assertTrue(tested.check("my@domain.info"));
	}
	public void testCheckUnderscore(){
		assertTrue(tested.check("_.1@mail.com"));
	}
	public void testCheckDomainMuseum(){
		assertTrue(tested.check("yurik@hermitage.museum"));
	}
	
	//Invalid e-mail addresses
	public void testCheckDomainShort(){
		assertFalse(tested.check("a@b.c"));
	}
	public void testCheckDoubleDot(){
		assertFalse(tested.check("a..b@mail.ru"));
	}
	public void testCheckStartingDot(){
		assertFalse(tested.check(".a@mail.ru"));
	}
	public void testCheckInvalidDomain(){
		assertFalse(tested.check("yo@domain.domain"));
	}
	public void testCheckStartingDigit(){
		assertFalse(tested.check("1@mail.ru"));
	}
}