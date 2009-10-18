/*Anton Karymov,gr261,2009,
*PositiveTest for EmailChecker
*/

package Tests;

import emailchecker.Main;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Assert.*;

public class CheckEmailPositiveTest {
    @Test
    public void positiveTwoSymbolDomain() {
        String email="a@b.cc";
        Assert.assertTrue(Main.checkEmail(email));//
    }
    @Test
    public void positiveDotInNickname() {
        String email="yuri.gubanov@mail.ru";
        Assert.assertTrue(Main.checkEmail(email));//
    }
    @Test
    public void positiveDomainInfo() {
        String email="my@domain.info";
        Assert.assertTrue(Main.checkEmail(email));//
    }
    @Test
    public void positiveFirstUnderline() {
        String email="_.1@mail.com";
        Assert.assertTrue(Main.checkEmail(email));//
    }
    @Test
    public void positiveDomainMuseum() {
        String email="yurik@hermitage.museum";
        Assert.assertTrue(Main.checkEmail(email));//
    }

}