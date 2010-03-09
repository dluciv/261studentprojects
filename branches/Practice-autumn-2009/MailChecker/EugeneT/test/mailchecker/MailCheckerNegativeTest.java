package mailchecker;

import org.junit.Test;
import static org.junit.Assert.*;

public class MailCheckerNegativeTest {

    public static String email;

    public MailCheckerNegativeTest() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullEmail() {
        email = null;
        MailChecker.isEmail(email);
    }

    @Test
    public void testEmptyEmail() {
        email = "";
        assertFalse(MailChecker.isEmail(email));
    }

    @Test
    public void testFirstDigits() {
        email = "123eugene@ya.ru";
        assertFalse(MailChecker.isEmail(email));
    }

    @Test
    public void testFirstDash() {
        email = "-eugene@ya.ru";
        assertFalse(MailChecker.isEmail(email));
    }

    @Test
    public void testOneLetterInDomain() {
        email = "a@b.c";
        assertFalse(MailChecker.isEmail(email));
    }

    @Test
    public void testTwoDotsTogetherInUsername() {
        email = "a..b@mail.ru";
        assertFalse(MailChecker.isEmail(email));
    }

    public void testTwoDotsBetweenDomainAndSubdomain() {
        email = "todoruk@mail..ru";
        assertFalse(MailChecker.isEmail(email));
    }

    @Test
    public void testFirstDot() {
        email = ".a@mail.ru";
        assertFalse(MailChecker.isEmail(email));
    }

    @Test
    public void testNonexistentDomain() {
        email = "yo@domain.domain";
        assertFalse(MailChecker.isEmail(email));
    }

    @Test
    public void testSingleDigitInUsername() {
        email = "1@mail.ru";
        assertFalse(MailChecker.isEmail(email));
    }

    @Test
    public void testFirstDigitInSubdomain() {
        email = "eugene@161group.ru";
        assertFalse(MailChecker.isEmail(email));
    }

    @Test
    public void testBadCharacterInUsername() {
        email = "eugene#todoruk@yandex.ru";
        assertFalse(MailChecker.isEmail(email));
    }
}