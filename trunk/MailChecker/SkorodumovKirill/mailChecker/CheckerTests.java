//Skorodumov Kirill gr.: 261
//tests for email task 


package mailChecker;

import org.junit.Assert;
import org.junit.Test;

public class CheckerTests {
	
	//positive tests
	@Test
	public void simpleTest()
	{
		Assert.assertTrue(Checker.check("a@b.cc"));
	}
	
	@Test
	public void dotInLoginTest()
	{
		Assert.assertTrue(Checker.check("yuri.gubanov@mail.ru"));
	}
	
    @Test
    public void emailDomainInfoTest() {
        Assert.assertTrue(Checker.check("my@domain.info"));
    }
    @Test
    public void emailLoginStartsFromDashTest() {
        Assert.assertTrue(Checker.check("_.1@mail.com"));
    }
    @Test
    public void emailMuseumDomainTest() {
        Assert.assertTrue(Checker.check("yurik@hermitage.museum"));
    }
    
    //negative tests
    @Test
    public void shortDomainTest() {
        Assert.assertFalse(Checker.check("a@b.c"));
    }
    
    @Test
    public void twoDotsInLoginTest() {
        Assert.assertFalse(Checker.check("a..b@mail.ru"));
    }
    
    @Test
    public void loginStartsFromDotTest() {
        Assert.assertFalse(Checker.check(".a@mail.ru"));
    }
    
    @Test
    public void icorrectDomainTest() {
        Assert.assertFalse(Checker.check("yo@domain.domain"));
    }
    
    @Test
    public void testMailLoginStartsFromDigit() {
        Assert.assertFalse(Checker.check("1@mail.ru"));
    }
}
