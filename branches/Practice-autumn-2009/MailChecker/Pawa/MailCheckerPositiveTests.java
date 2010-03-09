/*
 * Positive unit tests for MailChecker
 * (c)Pasha_Zolnikov, 
 */

package MailChecker;

import org.junit.Assert;
import org.junit.Test;

public class MailCheckerPositiveTests {

    @Test
    public void testSimpleEmail() {
        Assert.assertTrue(MailChecker.mailCheck("a@b.cc"));
    }
    
    @Test
    public void testEmailDotInLogin() {
        Assert.assertTrue(MailChecker.mailCheck("yuri.gubanov@mail.ru"));
    }
    
    @Test
    public void testEmailDomainInfo() {
        Assert.assertTrue(MailChecker.mailCheck("my@domain.info"));
    }
    @Test
    public void testEmailLoginStartsFromDash() {
        Assert.assertTrue(MailChecker.mailCheck("_.1@mail.com"));
    }
    @Test
    public void testEmailMuseumDomain() {
        Assert.assertTrue(MailChecker.mailCheck("yurik@hermitage.museum"));
    }
    @Test
    public void testEmailComplexSubdomain() {
        Assert.assertTrue(MailChecker.mailCheck("pawa@math.spbu.ru"));
    }
    
    @Test
    public void testEmailVasya2009() {
        Assert.assertTrue(MailChecker.mailCheck("vasya2009@mail.ru"));
    }
}