/*
 * (c) Stefan Bojarovski 2009
 * */
package mail_checker;

import junit.framework.*;
import mail_checker.MailChecker;

public class mailCheckerTest extends TestCase {
	private MailChecker tested = 
		new MailChecker("^[a-zA-Z_]([a-zA-Z_0-9]*)(\\.[a-zA-Z0-9_]+)*" +
						"@([a-zA-Z0-9-]+\\.)+(([a-zA-Z]{2,4})|museum|travel)$");
		
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