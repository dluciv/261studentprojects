/*Anton Karymov,gr261,2009,
 NegativeTest for EmailChecker
*/

package Tests;

import emailchecker.Main;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Assert.*;

public class CheckEmailNegativeTest {
    @Test
    public void negativeSmallDomain() {
        String email="a@b.c";
        Assert.assertFalse(Main.checkEmail(email));//
    }
    @Test
    public void negativeTwoDot () {
        String email="a..b@mail.ru";
        Assert.assertFalse(Main.checkEmail(email));//
    }
    @Test
    public void  negativeFirstDot() {
        String email=".a@mail.ru";
        Assert.assertFalse(Main.checkEmail(email));//
    }
    @Test
    public void  negativeUnknownDomain() {
        String email="yo@domain.domain";
        Assert.assertFalse(Main.checkEmail(email));//
    }
    @Test
    public void  negativeFirstNumber() {
        String email="1@mail.ru";
        Assert.assertFalse(Main.checkEmail(email));//
    }
}