import org.junit.Test;
import static org.junit.Assert.*;
import mailchecker.MailChecker;

public class IsMailWrongTest {

    @Test
    public void tooShortDomenMailTest() {
        assertFalse(MailChecker.isMail("a@b.c"));
    }
    
    @Test
    public void repeatingPointMailTest() {
        assertFalse(MailChecker.isMail("a..b@mail.ru"));
    }

    @Test
    public void firstPlacePointMailTest() {
        assertFalse(MailChecker.isMail(".a@mail.ru"));
    }

    @Test
    public void discorrectDomainMailTest() {
        assertFalse(MailChecker.isMail("yo@domain.domain"));
    }

    @Test
    public void firstPlaceDigitMailTest() {
        assertFalse(MailChecker.isMail("1@mail.ru"));
    }
}