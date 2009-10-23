
import mailchecker.Checker;
import org.junit.Test;
import static org.junit.Assert.*;

public class PositiveTest {
    @Test
    public void CheckerTestSimple() throws Exception  {
       String email = "a@b.cc";
       assertTrue(Checker.checkEmail(email));
    }
    @Test
    public void CheckerTestWithPointInLogin() throws Exception  {
       String email = "yuri.gubanov@mail.ru";
       assertTrue(Checker.checkEmail(email));
    }
    @Test
    public void CheckerTestDomain() throws Exception  {
       String email = "my@domain.info";
       assertTrue(Checker.checkEmail(email));
    }
    @Test
    public void CheckerTestWithUnderLine() throws Exception  {
       String email = "_.1@mail.com";
       assertTrue(Checker.checkEmail(email));
    }
    @Test
      public void CheckerTestWithMuseum() throws Exception  {
       String email = "yurik@hermitage.museum";
       assertTrue(Checker.checkEmail(email));
    }
    @Test
      public void CheckerTestWithMyMailOne() throws Exception  {
       String email = "v1st@list.ru";
       assertTrue(Checker.checkEmail(email));
    }
    @Test
      public void CheckerTestWithMyMailTwo() throws Exception  {
       String email = "m08aks@gmail.com";
       assertTrue(Checker.checkEmail(email));
    }

}