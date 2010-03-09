package emailchecker;
/**
 *Test Part I.
 * @author Zubrilin Andrey (c)2009
 */
import org.junit.Test;
import static org.junit.Assert.*;

public class emailCheckerPositivTests {

    public emailCheckerPositivTests() {
    }

    public static String email;

    /**
     * Testing Checker method(EMailChecker class).
     */
    @Test
    public void TheFirstPositivTest() {
        email = "a@a.ru";
        assertTrue(EMailChecker.Checker(email));
    }
    @Test
    public void TheSecondPositivTest() {
        email = "_.1@hey.org";
        assertTrue(EMailChecker.Checker(email));
    }
    @Test
    public void TheThirdPositivTest() {
        email = "__aaa___bc1245@mail.ru";
        assertTrue(EMailChecker.Checker(email));
    }
    @Test
    public void TheFourthPositivTest() {
        email = "veryveryveryverylongadddressname@name.museum";
        assertTrue(EMailChecker.Checker(email));
    }
    @Test
    public void TheFivesPositivTest() {
        email = "yurik@hermitage.museum";
        assertTrue(EMailChecker.Checker(email));
    }
    @Test
    public void TheSixthPositivTest() {
        email = "my@domain.info";
        assertTrue(EMailChecker.Checker(email));
    }
}
