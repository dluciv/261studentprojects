/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package emailchecker;


import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lii
 */
public class emailChackerPositiveTest {

    public emailChackerPositiveTest() {
    }

    /**
     * Test of isEmail method, of class emailChacker.
     */
    @Test
    public void testOnStandardMail() {

        String eMail = "mail@mail.org" ;
        assertTrue(EmailChacker.isEmail(eMail));
    }

    @Test
    public void testOnOneSumbleInDomenThirdLevel() {

        String eMail = "a@bry.tc" ;
        assertTrue(EmailChacker.isEmail(eMail));
    }
    @Test
    public void testOnOneSumbleInDomenSecondLevel() {

        String eMail = "argt@b.cc" ;
        assertTrue(EmailChacker.isEmail(eMail));
    }

    @Test
    public void testOnPointInDomenThirdLevel() {

        String eMail = "yuri.gubanov@mail.ru" ;
        assertTrue(EmailChacker.isEmail(eMail));
    }

    @Test
    public void testOnPointInDomenSecondLevel() {

        String eMail = "yuri.gubanov@ma.il.ru" ;
        assertTrue(EmailChacker.isEmail(eMail));
    }

    @Test
    public void testDomenFirstLevelOnFourSumble() {

        String eMail = "my@domain.info" ;
        assertTrue(EmailChacker.isEmail(eMail));
    }

    @Test
    public void testDomenThirdLevelOnUnderline() {
//bdt48-_.r@vrb.com
        String eMail = "a.d@msthsrthjprj.com" ;
        assertTrue(EmailChacker.isEmail(eMail));
    }

    @Test
    public void testDomenFirstLevelOnFiveSumble() {

        String eMail = "yurik@hermitage.museum" ;
        assertTrue(EmailChacker.isEmail(eMail));
    }
    @Test
    public void testDomenThirdLevelOnLongName() {

        String eMail = "seevilm6342345z@hermitage.mu" ;
        assertTrue(EmailChacker.isEmail(eMail));
    }
    @Test
    public void testDomenSecondLevelOnUnderlineAndPoint() {

        String eMail = "yurik@hg4t.age.museum" ;
        assertTrue(EmailChacker.isEmail(eMail));
    }

}

