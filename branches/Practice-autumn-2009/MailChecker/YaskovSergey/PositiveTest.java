/*
 * (c) Yaskov Sergey, 2009
 * positive tests for e-mail checker
 */

package tests;

import org.junit.Test;
import emailchecker.EmailChecker;
import static org.junit.Assert.*;

public class PositiveTest {
    @Test
    public void AddressExample1 () {
        String address = "a@b.cc";
        assertTrue(EmailChecker.checkEmail(address));
    }

    @Test
    public void AddressExample2 () {
        String address = "yuri.gubanov@mail.ru";
        assertTrue(EmailChecker.checkEmail(address));
    }

    @Test
    public void AddressExample3 () {
        String address = "my@domain.info";
        assertTrue(EmailChecker.checkEmail(address));
    }

    @Test
    public void AddressExample4 () {
        String address = "_.1@mail.com";
        assertTrue(EmailChecker.checkEmail(address));
    }

    @Test
    public void AddressExample5 () {
        String address = "yurik@hermitage.museum";
        assertTrue(EmailChecker.checkEmail(address));
    }
}