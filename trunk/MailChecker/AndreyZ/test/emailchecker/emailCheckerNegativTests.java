package emailchecker;
/**
 *Test Part II
 * @author Zubrilin Andrey (c)2009
 */
import org.junit.Test;
import static org.junit.Assert.*;

public class emailCheckerNegativTests {

    public emailCheckerNegativTests() {
    }
    public static String email;

    /**
     * Testing Checker method(EMailChecker class).
     */
    @Test
    public void TheFirstNegativTest() {
        email = "a@a.a";
        assertFalse(EMailChecker.Checker(email));
    }
    @Test
    public void TheSecondNegativTest() {
        email = "";
        assertFalse(EMailChecker.Checker(email));
    }
    @Test
    public void TheThirdNegativTest() {
        email = ".a@mail.ru";
        assertFalse(EMailChecker.Checker(email));
    }
    @Test
    public void TheFourthNegativTest() {
        email = "12345abc_@up.to";
        assertFalse(EMailChecker.Checker(email));
    }
    @Test
    public void TheFivesNegativTest() {
        email = "yurik@domain.domain";
        assertFalse(EMailChecker.Checker(email));
    }
    @Test
    public void TheSixthNegativTest() {
        email = "abc..-..@domain.info";
        assertFalse(EMailChecker.Checker(email));
    }
}