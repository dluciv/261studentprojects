/* JUnit Test for EmailChecker
 * By Savenko Maria (c)2009 */

package msavenko.EmailChecker;

import static org.junit.Assert.*;
import org.junit.Test;

public class EmailChekerTest {

	@Test
	public void testCheckTheEmailMatching() {
		assertTrue(EmailChecker.checkTheEmailMatching("a@b.cc"));
		assertTrue(EmailChecker.checkTheEmailMatching("yuri.gubanov@mail.ru"));
		assertTrue(EmailChecker.checkTheEmailMatching("my@domain.info"));
		assertTrue(EmailChecker.checkTheEmailMatching("_.1@mail.com"));
		assertTrue(EmailChecker.checkTheEmailMatching("yurik@hermitage.museum"));
         
		assertTrue(EmailChecker.checkTheEmailMatching("a..b@mail.ru"));
		assertTrue(EmailChecker.checkTheEmailMatching("a@b.c"));
		assertTrue(EmailChecker.checkTheEmailMatching("yo@domain.domain"));
		assertTrue(EmailChecker.checkTheEmailMatching(".a@mail.ru"));
		assertTrue(EmailChecker.checkTheEmailMatching("1@mail.ru"));
	}
	
	@Test
	public void testCheckTheZipMatching(){
		assertTrue(EmailChecker.checkTheZipMatching("198504"));
		assertTrue(EmailChecker.checkTheZipMatching("456780"));
		assertTrue(EmailChecker.checkTheZipMatching("A 198504"));
		assertTrue(EmailChecker.checkTheZipMatching("CA 198504"));
		
		assertTrue(EmailChecker.checkTheZipMatching("22"));
	}

}
