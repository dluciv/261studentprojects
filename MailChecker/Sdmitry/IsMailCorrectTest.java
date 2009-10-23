import org.junit.Test;
import static org.junit.Assert.*;
import mailchecker.MailChecker;

public class IsMailCorrectTest {

    @Test
    public void twoLetterDomainMailTest() {
        assertTrue(MailChecker.isMail("a@b.cc"));
    }

    @Test
    public void pointInNameMailTest() {
        assertTrue(MailChecker.isMail("yuri.gubanov@mail.ru"));
    }

    @Test
    public void especialDomainMailTest() {
        assertTrue(MailChecker.isMail("my@domain.info"));
    }

    @Test
    public void underscoreFirstPlaceMailTest() {
        assertTrue(MailChecker.isMail("_.1@mail.com"));
    }
    
    @Test
    public void ManyPartSubdomainMailTest() {
        assertTrue(MailChecker.isMail("abc@ab.cd.ef.g.com"));
    }
    
}