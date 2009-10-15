/*Anton Karymov,gr261,2009,
 NegativeTest for EmailChecker
*/

package Tests;

import emailchecker.Main;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Assert.*;

public class NegativeTest {
    @Test
    public void testCheckEmail6() {
        String email="a@b.c";
        Assert.assertFalse(Main.checkEmail(email));//
    }
    @Test
    public void testCheckEmail7() {
        String email="a..b@mail.ru";
        Assert.assertFalse(Main.checkEmail(email));//
    }
    @Test
    public void testCheckEmail8() {
        String email=".a@mail.ru";
        Assert.assertFalse(Main.checkEmail(email));//
    }
    @Test
    public void testCheckEmail9() {
        String email="yo@domain.domain";
        Assert.assertFalse(Main.checkEmail(email));//
    }
    @Test
    public void testCheckEmail10() {
        String email="1@mail.ru";
        Assert.assertFalse(Main.checkEmail(email));//
    }
}