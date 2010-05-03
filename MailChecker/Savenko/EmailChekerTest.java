/*
 * JUnit Test for EmailChecker By Savenko Maria (c)2009
 */

package msavenko;

import static org.junit.Assert.*;
import org.junit.Test;

public class EmailChekerTest {
    
    @Test
    public void testCheckTheEmailMatching1() {
        assertTrue(EmailChecker.checkTheEmailMatching("a@b.cc"));
    }
    
    @Test
    public void testCheckTheEmailMatching2() {
        assertTrue(EmailChecker.checkTheEmailMatching("yuri.gubanov@mail.ru"));
    }
    
    @Test
    public void testCheckTheEmailMatching3() {
        assertTrue(EmailChecker.checkTheEmailMatching("my@domain.info"));
    }
    
    @Test
    public void testCheckTheEmailMatching4() {
        assertTrue(EmailChecker.checkTheEmailMatching("_.1@mail.com"));
    }
    
    @Test
    public void testCheckTheEmailMatching5() {
        assertTrue(EmailChecker.checkTheEmailMatching("yurik@hermitage.museum"));
    }
    
    @Test
    public void testCheckTheEmailFAILMatching1() {
        assertTrue(!EmailChecker.checkTheEmailMatching(".a@mail.ru"));
    }
    
    @Test
    public void testCheckTheEmailFAILMatching2() {
        assertTrue(!EmailChecker.checkTheEmailMatching("1@mail.ru"));
    }
    
    @Test
    public void testCheckTheEmailFAILMatching3() {
        assertTrue(!EmailChecker.checkTheEmailMatching("yo@domain.domain"));
    }
    
    @Test
    public void testCheckTheEmailFAILMatching4() {
        assertTrue(!EmailChecker.checkTheEmailMatching("a@b.c"));
    }
    
    @Test
    public void testCheckTheEmailFAILMatching5() {
        assertTrue(!EmailChecker.checkTheEmailMatching("a..b@mail.ru"));
    }
    
    @Test
    public void testCheckTheZipMatching1() {
        assertTrue(EmailChecker.checkTheZipMatching("198504"));
    }
    
    @Test
    public void testCheckTheZipMatching2() {
        assertTrue(EmailChecker.checkTheZipMatching("456780"));
    }
    
    @Test
    public void testCheckTheZipMatching3() {
        assertTrue(EmailChecker.checkTheZipMatching("A 198504"));
    }
    
    @Test
    public void testCheckTheZipMatching4() {
        assertTrue(EmailChecker.checkTheZipMatching("CA 198504"));
    }
    
    @Test
    public void testCheckTheZipFAILMatching() {
        assertTrue(!EmailChecker.checkTheZipMatching("22"));
    }
    
}
