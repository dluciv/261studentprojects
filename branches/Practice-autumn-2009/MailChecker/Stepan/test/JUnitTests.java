/*
 * Unit Tests for Email checker by Korshakov Stepan
 */

import hotheart.study.emailchecker.EMailChecker;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Korshakov Stepan
 */
public class JUnitTests {

    @Test
    public void validTest() {
        Assert.assertTrue(EMailChecker.checkEmail("ex3ndr@inbox.ru"));
        Assert.assertTrue(EMailChecker.checkEmail("korshakov.stepan@gmail.com"));
        Assert.assertTrue(EMailChecker.checkEmail("a@b.cc"));
        Assert.assertTrue(EMailChecker.checkEmail("yuri.gubanov@mail.ru"));
        Assert.assertTrue(EMailChecker.checkEmail("my@domain.info"));
        Assert.assertTrue(EMailChecker.checkEmail("my@domain.info"));
        Assert.assertTrue(EMailChecker.checkEmail("yurik@hermitage.museum"));
    }

    @Test
    public void invalidTest() {
        Assert.assertFalse(EMailChecker.checkEmail("a@b.c"));
        Assert.assertFalse(EMailChecker.checkEmail("a..b@mail.ru"));
        Assert.assertFalse(EMailChecker.checkEmail(".a@mail.ru"));
        Assert.assertFalse(EMailChecker.checkEmail("yo@domain.domain"));
        Assert.assertFalse(EMailChecker.checkEmail("1@mail.ru"));
    }
}
