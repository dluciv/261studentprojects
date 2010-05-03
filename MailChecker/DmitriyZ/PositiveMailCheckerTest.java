/*
 * Tests (Mail Checker)
 * Dmitriy Zabranskiy g261 (c)2009
 */
package mailchecker;

import org.junit.Test;
import static org.junit.Assert.*;

public class PositiveMailCheckerTest {

    @Test
    public void isPositiveMail() {
        String email = "swom@pochta.ru";
        assertTrue(MailChecker.isMail(email));
    }

    @Test
    public void isDomainTrue1() {
        String email = "swom@pochta.travel";
        assertTrue(MailChecker.isMail(email));
    }

    @Test
    public void isLoginTrue1() {
        String email = "s1w1o1m1@pochta.ru";
        assertTrue(MailChecker.isMail(email));
    }

    @Test
    public void isSubDomainTrue1() {
        String email = "swom@p.o_c-hta.ru";
        assertTrue(MailChecker.isMail(email));
    }

    @Test
    public void isDomainTrue2() {
        String email = "swom@pochta.org";
        assertTrue(MailChecker.isMail(email));
    }

    @Test
    public void isLoginTrue2() {
        String email = "s.-.w.-.o.-.m@pochta.ru";
        assertTrue(MailChecker.isMail(email));
    }

    @Test
    public void isSubDomainTrue2() {
        String email = "swom@po345345ch9t-awfg6796fbgd.ru";
        assertTrue(MailChecker.isMail(email));
    }

    @Test
    public void isLoginTrue3() {
        String email = "swom_1990@pochta.ru";
        assertTrue(MailChecker.isMail(email));
    }

    @Test
    public void isDomainTrue3() {
        String email = "swom@pochta.tutu";
        assertTrue(MailChecker.isMail(email));
    }

    @Test
    public void isSubDomainTrue3() {
        String email = "swom@pp.ru";
        assertTrue(MailChecker.isMail(email));
    }

    @Test
    public void example1() {
        String email = "a@b.cc";
        assertTrue(MailChecker.isMail(email));
    }

    @Test
    public void example2() {
        String email = "yuri.gubanov@mail.ru";
        assertTrue(MailChecker.isMail(email));
    }

    @Test
    public void example3() {
        String email = "my@domain.info";
        assertTrue(MailChecker.isMail(email));
    }

    @Test
    public void example4() {
        String email = "_.1@mail.com";
        assertTrue(MailChecker.isMail(email));
    }

    @Test
    public void example5() {
        String email = "yurik@hermitage.museum";
        assertTrue(MailChecker.isMail(email));
    }

    @Test
    public void example6() {
        String email = "yu-rik@hermitage.museum";
        assertTrue(MailChecker.isMail(email));
    }

    @Test
    public void example7() {
        String email = "yu-rik@her---mitage.museum";
        assertTrue(MailChecker.isMail(email));
    }

    @Test
    public void example8() {
        String email = "yu-rik@mail.google.com";
        assertTrue(MailChecker.isMail(email));
    }
}
