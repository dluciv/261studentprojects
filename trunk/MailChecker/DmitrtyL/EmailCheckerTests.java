//Lebedev Dmitry g261 2009 (c)
package tests;

import emailchecker.EmailChecker;
import org.junit.*;
import org.junit.Assert;

public class EmailCheckerTests {
    //Here starts positive tests

    @Test
    public void singleLetterLogin() {
        String email = "a@b.cc";
        Assert.assertTrue(EmailChecker.checkEmail(email));
    }

    @Test
    public void dotsInLogin() {
        String email = "yuri.gub.anov@mail.ru";
        Assert.assertTrue(EmailChecker.checkEmail(email));
    }

    @Test
    public void fourLetterDomain() {
        String email = "my@domain.info";
        Assert.assertTrue(EmailChecker.checkEmail(email));
    }

    @Test
    public void underscoreSymbolAtFirstPlace() {
        String email = "_.1@mail.com";
        Assert.assertTrue(EmailChecker.checkEmail(email));
    }

    @Test
    public void museumDomain() {
        String email = "yurik@hermitage.museum";
        Assert.assertTrue(EmailChecker.checkEmail(email));
    }

    @Test
    public void severalDomain() {
        String email = "vasya@mail.mail.ru";
        Assert.assertTrue(EmailChecker.checkEmail(email));
    }

    //Here starts negative tests
    @Test
    public void singleLetterDomain() {
        String email = "a@b.c";
        Assert.assertFalse(EmailChecker.checkEmail(email));
    }

    @Test
    public void twoConcecutiveDots() {
        String email = "a..b@mail.ru";
        Assert.assertFalse(EmailChecker.checkEmail(email));
    }

    @Test
    public void dotAtFirstPlace() {
        String email = ".a@mail.ru";
        Assert.assertFalse(EmailChecker.checkEmail(email));
    }

    @Test
    public void sixLetterDomain() {
        String email = "yo@domain.domain";
        Assert.assertFalse(EmailChecker.checkEmail(email));
    }

    @Test
    public void numberAtFirstPlace() {
        String email = "1@mail.ru";
        Assert.assertFalse(EmailChecker.checkEmail(email));
    }

    @Test
    public void dotAfterAt() {
        String email = "vasya@.mail.ru";
        Assert.assertFalse(EmailChecker.checkEmail(email));
    }
}
