/*
 * Tests (Mail Checker)
 * Dmitriy Zabranskiy g261 (c)2009
 */
package mailchecker;

import org.junit.Test;
import static org.junit.Assert.*;

public class NegativeMailCheckerTest {

    @Test
    public void isMailNull() {
        String email = "";
        assertFalse(MailChecker.isMail(email));
    }

    @Test
    public void example1() {
        String email = "a@b.c";
        assertFalse(MailChecker.isMail(email));
    }

    @Test
    public void example2() {
        String email = "a..b@mail.ru";
        assertFalse(MailChecker.isMail(email));
    }

    @Test
    public void example3() {
        String email = ".a@mail.ru";
        assertFalse(MailChecker.isMail(email));
    }

    @Test
    public void example4() {
        String email = "yo@domain.domain";
        assertFalse(MailChecker.isMail(email));
    }

    @Test
    public void example5() {
        String email = "1@mail.ru";
        assertFalse(MailChecker.isMail(email));
    }

    @Test
    public void doublePoint1() {
        String email = "swom@po..chta.ru";
        assertFalse(MailChecker.isMail(email));
    }

    @Test
    public void doublePoint2() {
        String email = "swo..mm@ppochta.ru";
        assertFalse(MailChecker.isMail(email));
    }

    @Test
    public void isLoginFalse1() {
        String email = "1swom@pochta.ru";
        assertFalse(MailChecker.isMail(email));
    }

    @Test
    public void isLoginFalse2() {
        String email = "s&&wom@pochta.ru";
        assertFalse(MailChecker.isMail(email));
    }

    @Test
    public void isDomainFalse1() {
        String email = "swom@pochta.r";
        assertFalse(MailChecker.isMail(email));
    }

    @Test
    public void isDomainFalse2() {
        String email = "swom@pochta.rurururu";
        assertFalse(MailChecker.isMail(email));
    }

    @Test
    public void isSubDomainFalse1() {
        String email = "swom@123p-$ochta.org";
        assertFalse(MailChecker.isMail(email));
    }

    @Test
    public void isSubDomainFalse2() {
        String email = "swom@Pochta.ru";
        assertFalse(MailChecker.isMail(email));
    }
}