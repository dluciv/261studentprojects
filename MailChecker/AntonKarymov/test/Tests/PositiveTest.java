/*Anton Karymov,gr261,2009,
*PositiveTest for EmailChecker
*/

package Tests;

import emailchecker.Main;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Assert.*;

public class PositiveTest {
    @Test
    public void testCheckEmail1() {
        String email="a@b.cc";
        Assert.assertTrue(Main.checkEmail(email));//
    }
    @Test
    public void testCheckEmail2() {
        String email="yuri.gubanov@mail.ru";
        Assert.assertTrue(Main.checkEmail(email));//
    }
    @Test
    public void testCheckEmail3() {
        String email="my@domain.info";
        Assert.assertTrue(Main.checkEmail(email));//
    }
    @Test
    public void testCheckEmail4() {
        String email="_.1@mail.com";
        Assert.assertTrue(Main.checkEmail(email));//
    }
    @Test
    public void testCheckEmail5() {
        String email="yurik@hermitage.museum";
        Assert.assertTrue(Main.checkEmail(email));//
    }

}