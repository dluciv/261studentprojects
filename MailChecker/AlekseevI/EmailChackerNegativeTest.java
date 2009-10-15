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
public class EmailChackerNegativeTest {

    public EmailChackerNegativeTest() {
    }

  
    /**
     * Test of isEmail method, of class EmailChacker.
     */
    @Test
    public void testDomenFirstLevenOnOneSymbol() {

        String eMail = "a@b.c" ;
        assertFalse(EmailChacker.isEmail(eMail));

    }

    @Test
    public void testDomenFirstLevelOnOneSymbol() {

        String eMail = "f.е@mail.ru" ;
        assertFalse(EmailChacker.isEmail(eMail));

    }
    @Test
    public void testDomenFirstLevelOnBeginPoint() {

        String eMail = ".е@mail.ru" ;
        assertFalse(EmailChacker.isEmail(eMail));

    }
    @Test
    public void testDomenFirstLevelOnFalseName() {

        String eMail = "bdbе@mail.rugbgn" ;
        assertFalse(EmailChacker.isEmail(eMail));

    }
    @Test
    public void testDomenThirdLevelOnBeginWithNamber() {

        String eMail = "1@mail.ru" ;
        assertFalse(EmailChacker.isEmail(eMail));

    }
}