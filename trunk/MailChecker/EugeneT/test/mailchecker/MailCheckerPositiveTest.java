package mailchecker;

import org.junit.Test;
import static org.junit.Assert.*;

public class MailCheckerPositiveTest {

    public static String email;

    public MailCheckerPositiveTest() {
    }

    @Test
    public void testIsEmail() {
        email = "nighteugene@ya.ru";
        assertTrue(MailChecker.isEmail(email));
    }

    @Test
    public void testUnderscoreInsideUsername() {
        email = "night_eugene@mail.ru";
        assertTrue(MailChecker.isEmail(email));
    }

    @Test
    public void testOneLetterInUsernameAndBeforeDomain() {
        email = "a@b.cc";
        assertTrue(MailChecker.isEmail(email));
    }

    @Test
    public void testDotInsideUsername() {
        email = "yuri.gubanov@mail.ru";
        assertTrue(MailChecker.isEmail(email));
    }

    @Test
    public void testFourLettersInDomain() {
        email = "my@domain.info";
        assertTrue(MailChecker.isEmail(email));
    }

    @Test
    public void testFirstUnderscoreWithDot() {
        email = "_.1@mail.com";
        assertTrue(MailChecker.isEmail(email));
    }

    @Test
    public void testDomainIsMuseum() {
        email = "yurik@hermitage.museum";
        assertTrue(MailChecker.isEmail(email));
    }

    @Test
    public void testDigitAndDotContractInsideUsername() {
        email = "eugene1.todoruk@gmail.com";
        assertTrue(MailChecker.isEmail(email));
    }

    @Test
    public void testDashInsideUsename() {
        email = "eugene-todoruk@gmail.com";
        assertTrue(MailChecker.isEmail(email));
    }

    @Test
    public void testTwoDotsInUsename() {
        email = "eugene.t.a@gmail.com";
        assertTrue(MailChecker.isEmail(email));
    }

    @Test
    public void testTwoDashsInsideUsername() {
        email = "t--a@gmail.com";
        assertTrue(MailChecker.isEmail(email));
    }

    @Test
    public void testSurroundedUnderlines() {
        email = "___todoruk___@mail.ru";
        assertTrue(MailChecker.isEmail(email));
    }

    @Test
    public void testManySubdomains() {
        email = "eugene@spbu.mat-mex.group261.ru";
        assertTrue(MailChecker.isEmail(email));
    }
}