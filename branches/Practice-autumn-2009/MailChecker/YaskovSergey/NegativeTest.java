/*
 * (c) Yaskov Sergey, 2009
 * negative tests for e-mail checker
 */

package tests;

import org.junit.Test;
import emailchecker.EmailChecker;
import static org.junit.Assert.*;

public class NegativeTest {
    @Test
    public void isAddressNull() {
        String address = "";
        assertFalse(EmailChecker.checkEmail(address));
    }

    @Test
    public void AddressExample1() {
        String address = "a@b.c";
        assertFalse(EmailChecker.checkEmail(address));
    }

    @Test
    public void AddressExample2() {
        String address = "a..b@mail.ru";
        assertFalse(EmailChecker.checkEmail(address));
    }

    @Test
    public void AddressExample3() {
        String address = ".a@mail.ru";
        assertFalse(EmailChecker.checkEmail(address));
    }

    @Test
    public void AddressExample4() {
        String address = "yo@domain.domain";
        assertFalse(EmailChecker.checkEmail(address));
    }

    @Test
    public void AddressExample5() {
        String address = "1@mail.ru";
        assertFalse(EmailChecker.checkEmail(address));
    }
}