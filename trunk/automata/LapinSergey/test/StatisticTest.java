/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import static org.junit.Assert.*;
import test.RegExTester;
import statistic.Statistic;
import statistic.StatEntry;

/**
 *
 * @author lapin
 */
public class StatisticTest {

    public StatisticTest() {
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void KliniTest()
    {
        RegExTester a = new RegExTester();
        Statistic stat1 = a.Match("a*", "aaaa", 5000);
        assertTrue(stat1.checkAnswersEquality());
        assertEquals(true, stat1.getAnswer());
        Statistic stat2 = a.Match("a*", "", 5000);
        assertTrue(stat2.checkAnswersEquality());
        assertEquals(true, stat2.getAnswer());
        RegExTester b = new RegExTester();
        Statistic stat3 = b.Match("a*", "aaaab", 5000);
        assertTrue(stat3.checkAnswersEquality());
        assertEquals(false, stat3.getAnswer());
    }
    
    @Test
    public void QuestionTest()
    {
        RegExTester a = new RegExTester();
        Statistic stat1 = a.Match("a?", "a", 5000);
        assertTrue(stat1.checkAnswersEquality());
        assertEquals(true, stat1.getAnswer());
        Statistic stat2 = a.Match("a?", "", 5000);
        assertTrue(stat2.checkAnswersEquality());
        assertEquals(true, stat2.getAnswer());
        Statistic stat3 = a.Match("a?", "aaa", 5000);
        assertTrue(stat3.checkAnswersEquality());
        assertEquals(false, stat3.getAnswer());
    }
    
    @Test
    public void AdditionTest()
    {
        RegExTester a = new RegExTester();
        Statistic stat1 = a.Match("(a|b)", "a", 5000);
        assertTrue(stat1.checkAnswersEquality());
        assertEquals(true, stat1.getAnswer());
        Statistic stat2 = a.Match("(a|b)", "b", 5000);
        assertTrue(stat2.checkAnswersEquality());
        assertEquals(true, stat2.getAnswer());        
        Statistic stat3 = a.Match("(a|b)", "ab", 5000);
        assertTrue(stat3.checkAnswersEquality());
        assertEquals(false, stat3.getAnswer());
    }
    
    @Test
    public void ProductionTest()
    {
        RegExTester a = new RegExTester();
        Statistic stat1 = a.Match("vjreijviorejirjvijirjeivjlkfdvjrietu", "vjreijviorejirjvijirjeivjlkfdvjrietu", 5000);
        assertTrue(stat1.checkAnswersEquality());
        assertEquals(true, stat1.getAnswer());
        Statistic stat2 = a.Match("aaaaaaaaaabababbababababauwhudwhuahdwuabbab", "aaaaaaaaaababab", 5000);
        assertTrue(stat2.checkAnswersEquality());
        assertEquals(false, stat2.getAnswer());        
    }
    
    @Test
    public void RevertionTest()
    {
        RegExTester a = new RegExTester();
        Statistic stat = a.Match("(abcd)|(abcdd)|(abcddd)|(abcdddd)|(abcddddd)|(abcdddddd)", "abcdddddd", 5000);
        assertTrue(stat.checkAnswersEquality());
        assertEquals(true, stat.getAnswer());                
    }
    
    @Test
    public void ClassicTest()
    {
        RegExTester a = new RegExTester();
        Statistic stat = a.Match("(a|b)*abb", "abbbbbbbbbbbbabb", 5000);
        assertTrue(stat.checkAnswersEquality());
        assertEquals(true, stat.getAnswer());                
    }
}