package MailChecker;

/*
 * Negative tests for MailChecker
 * (c)Pasha_Zolnikov, 2009
 */

import MailChecker.MailChecker;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Assert.*;

public class MailCheckerNegativeTests {

    @Test
    public void testMailShortDomain() {
        Assert.assertFalse(MailChecker.mailCheck("a@b.c"));
    }
    
    @Test
    public void testMailTwoDotsInLogin() {
        Assert.assertFalse(MailChecker.mailCheck("a..b@mail.ru"));
    }
    
    @Test
    public void testMailLoginStartsFromDot() {
        Assert.assertFalse(MailChecker.mailCheck(".a@mail.ru"));
    }
    
    @Test
    public void testMailIncorrectDomain() {
        Assert.assertFalse(MailChecker.mailCheck("yo@domain.domain"));
    }
    
    @Test
    public void testMailLoginStartsFromDigit() {
        Assert.assertFalse(MailChecker.mailCheck("1@mail.ru"));
    }
}