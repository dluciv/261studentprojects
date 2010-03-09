//(с) Кривых Алексей 2009г.
//emailcheker
package EmailCheckerTests;

import emailchecker.EmailChecker;
import org.junit.Test;
import static org.junit.Assert.*;

public class EmailCheckerNegativeTest {

    @Test
    public void testOneCharInDomain() {
        String mail = "a@b.c";
        assertFalse(EmailChecker.isMail(mail));
    }

    @Test
    public void testDoubleDotInName() {
        String mail = "a..b@mail.ru";
        assertFalse(EmailChecker.isMail(mail));
    }

    @Test
    public void testFirstDot() {
        String mail = ".a@mail.ru";
        assertFalse(EmailChecker.isMail(mail));
    }

    @Test
    public void testLongDomain() {
        String mail = "yo@domain.domain";
        assertFalse(EmailChecker.isMail(mail));
    }

    @Test
    public void testFirstNumInName() {
        String mail = "1@b.cc";
        assertFalse(EmailChecker.isMail(mail));
    }

    @Test
    public void testDoubleDotInEndSubDomain() {
        String mail = "a@b..cc";
        assertFalse(EmailChecker.isMail(mail));
    }

    @Test
    public void testFirstNumInSubDomain() {
        String mail = "a@1b.cc";
        assertFalse(EmailChecker.isMail(mail));
    }
}