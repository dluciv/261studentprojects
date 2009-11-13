//(c), 2009 Antonov kirill
//TEST for correcting Mail Addresses
//Negative Test

import mailchecker.Checker;
import org.junit.Test;
import static org.junit.Assert.*;

public class NegativeTest {
    //тест на длинну
    @Test
    public void CheckEmailTooShortDomain() throws Exception {
       String mail = "a@b.c";
       assertFalse(Checker.checkEmail(mail));
    }
    //тест на наличие множества точек по среди 
    @Test
    public void CheckEmailPointMailTest() throws Exception {
       String mail = "a..b@mail.ru";
       assertFalse(Checker.checkEmail(mail));
    }
    //тест на невозможность точки в начале ввода
    @Test
    public void CheckEmailWithPointAtTheBegining() throws Exception {
       String mail = ".a@mail.ru";
       assertFalse(Checker.checkEmail(mail));
    }
    //тест на неправильность повтора 
    @Test
    public void CheckEmailWithYoAnddomain() throws Exception {
       String mail = "yo@domain.domain";
       assertFalse(Checker.checkEmail(mail));
    }
    //тест на множество чисел в начале
    @Test
    public void CheckEmailWithNumberOneAtTheBegining() throws Exception {
       String mail = "1@mail.ru";
       assertFalse(Checker.checkEmail(mail));
    }
    //тест на наличие множества чисел в domain'е
    @Test
    public void CheckEmailWithnumberOneAtTheDomain() throws Exception {
       String mail = "kirill@mail.1";
       assertFalse(Checker.checkEmail(mail));
     }
    //тест на случайный ввод двойнойго @@
    @Test
    public void CheckEmailWithDoubleLiteral() throws Exception {
       String mail = "kirill@@mail.ru";
       assertFalse(Checker.checkEmail(mail));
     }
}


   