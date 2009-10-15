/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import hotheart.study.emailchecker.EMailChecker;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author HotHeart
 */
public class JUnitTests {

    public JUnitTests() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void validTest() {
        Assert.assertTrue(EMailChecker.checkEMail("ex3ndr@inbox.ru"));
        Assert.assertTrue(EMailChecker.checkEMail("korshakov.stepan@gmail.com"));
        Assert.assertTrue(EMailChecker.checkEMail("a@b.cc"));
        Assert.assertTrue(EMailChecker.checkEMail("yuri.gubanov@mail.ru"));
        Assert.assertTrue(EMailChecker.checkEMail("my@domain.info"));
        Assert.assertTrue(EMailChecker.checkEMail("my@domain.info"));
        Assert.assertTrue(EMailChecker.checkEMail("yurik@hermitage.museum"));
    }

    @Test
    public void invalidTest() {
        Assert.assertFalse(EMailChecker.checkEMail("a@b.c"));
        Assert.assertFalse(EMailChecker.checkEMail("a..b@mail.ru"));
        Assert.assertFalse(EMailChecker.checkEMail(".a@mail.ru"));
        Assert.assertFalse(EMailChecker.checkEMail("yo@domain.domain"));
        Assert.assertFalse(EMailChecker.checkEMail("1@mail.ru"));
    }
}
