/**
 *
 * @author Alekseev Ilya
 */

import EduAlekseev.EmailChecker;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lii
 */
public class EmailCheckerPositiveTest {

    public EmailCheckerPositiveTest() {
    }

    /**
     * Test of isEmail method, of class EmailChecker.
     */
    @Test
    public void testOnStandardMail() {

        String eMail = "mail@mail.org" ;
        assertTrue(EmailChecker.isEmail(eMail));
    }

    @Test
    public void testLoginOnOneSymbol() {

        String eMail = "a@bry.tc" ;
        assertTrue(EmailChecker.isEmail(eMail));
    }
    @Test
    public void testDomainSecomdLevelOnOneSymbol() {

        String eMail = "argt@b.cc" ;
        assertTrue(EmailChecker.isEmail(eMail));
    }

    @Test
    public void testOnDotInLogin() {

        String eMail = "yuri.gubanov@mail.ru" ;
        assertTrue(EmailChecker.isEmail(eMail));
    }

    @Test
    public void testDomainSecondLevelOnDot() {

        String eMail = "yuri.gubanov@ma.il.ru" ;
        assertTrue(EmailChecker.isEmail(eMail));
    }

    @Test
    public void testDomainFirstLevelOnFourSumble() {

        String eMail = "my@domain.info" ;
        assertTrue(EmailChecker.isEmail(eMail));
    }

    @Test
    public void testLoginOnUnderline() {

        String eMail = "a_d@msthsrthjprj.com" ;
        assertTrue(EmailChecker.isEmail(eMail));
    }

    @Test
    public void testDomainFirstLevelOnFiveSumble() {

        String eMail = "yurik@hermitage.museum" ;
        assertTrue(EmailChecker.isEmail(eMail));
    }
    @Test
    public void testLoginOnLongName() {

        String eMail = "seevilm6342345z@hermitage.mu" ;
        assertTrue(EmailChecker.isEmail(eMail));
    }
   
}

