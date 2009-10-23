//

import mailchecker.Checker;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tiesto
 */
public class NegativeTest {
    @Test
    public void CheckEmailTooShortDomain() throws Exception {
       String email = "a@b.c";
       assertFalse(Checker.checkEmail(email));
    }
    @Test
    public void CheckEmailPointMailTest() throws Exception {
       String email = "a..b@mail.ru";
       assertFalse(Checker.checkEmail(email));
    }
    @Test
    public void CheckEmailWithPointAtTheBegining() throws Exception {
       String email = ".a@mail.ru";
       assertFalse(Checker.checkEmail(email));
    }
    @Test
    public void CheckEmailWithYoAnddomain() throws Exception {
       String email = "yo@domain.domain";
       assertFalse(Checker.checkEmail(email));
    }
    @Test
    public void CheckEmailWithNumberOneAtTheBegining() throws Exception {
       String email = "1@mail.ru";
       assertFalse(Checker.checkEmail(email));
    }
    @Test
    public void CheckEmailWithnumberOneAtTheDomain() throws Exception {
       String email = "kirill@mail.1";
       assertFalse(Checker.checkEmail(email));
     }
    @Test
    public void CheckEmailWithDoubleLiteral() throws Exception {
       String email = "kirill@@mail.ru";
       assertFalse(Checker.checkEmail(email));
     }
}


   