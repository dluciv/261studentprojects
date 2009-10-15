//Lebedev Dmitry g261 2009 (c)

package tests;

import emailchecker.Main;
import org.junit.*;
import org.junit.Assert;


public class Tests {
    @Test
        public void Test1() {
            String email = "a@b.cc";
            Assert.assertTrue(Main.checkEmail(email));
        }

    @Test
        public void Test2() {
            String email = "yuri.gub.anov@mail.ru";
            Assert.assertTrue(Main.checkEmail(email));
        }

    @Test
        public void Test3() {
            String email = "my@domain.info";
            Assert.assertTrue(Main.checkEmail(email));
        }

    @Test
        public void Test4() {
            String email = "_.1@mail.com";
            Assert.assertTrue(Main.checkEmail(email));
        }

    @Test
        public void Test5() {
            String email = "yurik@hermitage.museum";
            Assert.assertTrue(Main.checkEmail(email));
        }
    @Test
        public void Test6() {
            String email = "a@b.c";
            Assert.assertFalse(Main.checkEmail(email));
        }

    @Test
        public void Test7() {
            String email = "a..b@mail.ru";
            Assert.assertFalse(Main.checkEmail(email));
        }

    @Test
        public void Test8() {
            String email = ".a@mail.ru";
            Assert.assertFalse(Main.checkEmail(email));
        }

    @Test
        public void Test9() {
            String email = "yo@domain.domain";
            Assert.assertFalse(Main.checkEmail(email));
        }

    @Test
        public void Test10() {
            String email = "1@mail.ru";
            Assert.assertFalse(Main.checkEmail(email));
        }
}