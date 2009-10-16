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
public class EmailCheckerNegativeTest {

    public EmailCheckerNegativeTest() {
    }

  
    /**
     * Test of isEmail method, of class EmailChecker.
     */
    @Test
    public void testDomainFirstLevenOnOneSymbol() {

        String eMail = "a@b.c" ;
        assertFalse(EmailChecker.isEmail(eMail));

    }

    @Test
    public void testLoginOnZeroSymbol() {

        String eMail = "@mail.ru" ;
        assertFalse(EmailChecker.isEmail(eMail));

    }
    @Test
    public void testLoginOnBeginDot() {

        String eMail = ".е@mail.ru" ;
        assertFalse(EmailChecker.isEmail(eMail));

    }
    @Test
    public void testDomainFirstLevelOnFalseName() {

        String eMail = "bdbе@mail.rugbgn" ;
        assertFalse(EmailChecker.isEmail(eMail));

    }
    @Test
    public void testLoginOnBeginWithNamber() {

        String eMail = "1@mail.ru" ;
        assertFalse(EmailChecker.isEmail(eMail));

    }
}