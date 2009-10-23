/* JUnit Test for EmailChecker
 * By Savenko Maria (c)2009 */

package msavenko;

import static org.junit.Assert.*;
import org.junit.Test;

public class EmailChekerTest {

    @Test
    public void testCheckTheEmailMatching1() {
    assertTrue(EmailChecker.checkTheEmailMatching("a@b.cc"));
    }
    
    public void testCheckTheEmailMatching2() {
    assertTrue(EmailChecker.checkTheEmailMatching("yuri.gubanov@mail.ru"));
    }
    
    public void testCheckTheEmailMatching3() {
    assertTrue(EmailChecker.checkTheEmailMatching("my@domain.info"));
    }
    
    public void testCheckTheEmailMatching4() {
    assertTrue(EmailChecker.checkTheEmailMatching("_.1@mail.com"));
    }
    
    public void testCheckTheEmailMatching5() {
    assertTrue(EmailChecker.checkTheEmailMatching("yurik@hermitage.museum"));
    }
    
    public void testCheckTheEmailFAILMatching1() {
    assertTrue(!EmailChecker.checkTheEmailMatching(".a@mail.ru"));
    }
    
    public void testCheckTheEmailFAILMatching2() {
    assertTrue(!EmailChecker.checkTheEmailMatching("1@mail.ru"));
    }
    
    public void testCheckTheEmailFAILMatching3() {
    assertTrue(!EmailChecker.checkTheEmailMatching("yo@domain.domain"));
    }
    
    public void testCheckTheEmailFAILMatching4() {
    assertTrue(!EmailChecker.checkTheEmailMatching("a@b.c"));
    }
    
    public void testCheckTheEmailFAILMatching5() {
    assertTrue(!EmailChecker.checkTheEmailMatching("a..b@mail.ru"));
    }
    
    @Test
    public void testCheckTheZipMatching1(){
        assertTrue(EmailChecker.checkTheZipMatching("198504"));
    }
    
    public void testCheckTheZipMatching2(){
    assertTrue(EmailChecker.checkTheZipMatching("456780"));
    }
    
    public void testCheckTheZipMatching3(){
    assertTrue(EmailChecker.checkTheZipMatching("A 198504"));
    }
    
    public void testCheckTheZipMatching4(){
    assertTrue(EmailChecker.checkTheZipMatching("CA 198504"));
    }
    
    public void testCheckTheZipFAILMatching(){
    assertTrue(!EmailChecker.checkTheZipMatching("22"));
    }

}
