//(c), 2009 Antonov Kirill
//Test for correcting Mail Addresses
//Positive Test


import mailchecker.Checker;
import org.junit.Test;
import static org.junit.Assert.*;

public class PositiveTest {
    @Test
    //тест с одной буквой до @
    public void CheckerTestSimple() throws Exception  {
       String mail = "a@b.cc";
       assertTrue(Checker.checkEmail(mail));
    }
    //тест с точкой в логине
    @Test
    public void CheckerTestWithPointInLogin() throws Exception  {
       String mail = "yuri.gubanov@mail.ru";
       assertTrue(Checker.checkEmail(mail));
    }
    //тест на Domain
    @Test
    public void CheckerTestDomain() throws Exception  {
       String mail = "my@domain.info";
       assertTrue(Checker.checkEmail(mail));
    }
    //тест с подчеркивание и точкой до @
    @Test
    public void CheckerTestWithUnderLine() throws Exception  {
       String mail = "_.1@mail.com";
       assertTrue(Checker.checkEmail(mail));
    }
    //тест с museum
    @Test
      public void CheckerTestWithMuseum() throws Exception  {
       String mail = "yurik@hermitage.museum";
       assertTrue(Checker.checkEmail(mail));
    }
    // тест на правильнось моего мыла1
    @Test
      public void CheckerTestWithMyMailOne() throws Exception  {
       String mail = "v1st@list.ru";
       assertTrue(Checker.checkEmail(mail));
    }
    //тест на правильнось моего мыла2
    @Test
      public void CheckerTestWithMyMailTwo() throws Exception  {
       String mail = "m08aks@gmail.com";
       assertTrue(Checker.checkEmail(mail));
    }

}