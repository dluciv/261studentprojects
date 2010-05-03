//(с) Кривых Алексей 2009г.
//emailcheker
package EmailCheckerTests;

import emailchecker.EmailChecker;
import org.junit.Test;
import static org.junit.Assert.*;

public class emailcheckerPositiveTest {

    @Test
    public void testOneCharInSubdAndName() {
        String mail = "a@b.cc";
        assertTrue(EmailChecker.isMail(mail));
    }

    @Test
    public void testDotInName() {
        String mail = "yuri.gubanov@mail.ru";
        assertTrue(EmailChecker.isMail(mail));
    }

    @Test
    public void testFirstUnderLine() {
        String mail = "_.1@mail.com";
        assertTrue(EmailChecker.isMail(mail));
    }

    @Test
    public void testMuseumInDomain() {
        String mail = "yurik@hermitage.museum";
        assertTrue(EmailChecker.isMail(mail));
    }

    @Test
    public void testNumbInName() {
        String mail = "a234@mail.com";
        assertTrue(EmailChecker.isMail(mail));
    }

    @Test
    public void testDotesInName() {
        String mail = "a.d.gf.hh@mail.com";
        assertTrue(EmailChecker.isMail(mail));
    }

    @Test
    public void testManyUnderL() {
        String mail = "a____h@mail.com";
        assertTrue(EmailChecker.isMail(mail));
    }

    @Test
    public void testSubSubDomain() {
        String mail = "a@spb.mail.cc";
        assertTrue(EmailChecker.isMail(mail));
    }
}