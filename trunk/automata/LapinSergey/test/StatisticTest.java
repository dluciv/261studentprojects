/**
 *
 * Lapin Sergey 261 group mat-mex
 * Regular expression analysis
 * 19.01.2009
 */

import org.junit.Test;
import static org.junit.Assert.*;
import test.RegExTester;
import statistic.Statistic;
import test.ConsoleStrings;

public class StatisticTest {

    public StatisticTest() {
    }
    
    private void AnswerFormTrue(String str, String regverb, String teststr, int num)
    {
        System.out.println(str);
        RegExTester a = new RegExTester();
        Statistic stat = a.Match(regverb, teststr, num);
        System.out.println(stat.PrintWinners());
        assertTrue(stat.checkAnswersEquality());
        assertEquals(true, stat.getAnswer());
    }

    private void AnswerFormFalse(String str, String regverb, String teststr, int num)
    {
        System.out.println(str);
        RegExTester a = new RegExTester();
        Statistic stat = a.Match(regverb, teststr, num);
        System.out.println(stat.PrintWinners());
        assertTrue(stat.checkAnswersEquality());
        assertEquals(false, stat.getAnswer());
    }

    @Test
    public void TestOne()
    {
        AnswerFormTrue("TestOne\n", "(b|((ab*)a*)|a*|ab)*", "b", 5000);
        ConsoleStrings.PrintBorder();
    }
    
    @Test
    public void KliniTest()
    {
        AnswerFormTrue("KliniTest\n", "a*", "aaaa", 5000);
        AnswerFormTrue("", "a*", "", 5000);
        AnswerFormFalse("", "a*", "aaaab", 5000);
        ConsoleStrings.PrintBorder();
    }
    
    @Test
    public void QuestionTest()
    {
        AnswerFormTrue("QuestionTest\n", "a?", "a", 5000);
        AnswerFormTrue("", "a?", "", 5000);
        AnswerFormFalse("", "a?", "aaa", 5000);
        ConsoleStrings.PrintBorder();
    }
    
    @Test
    public void AdditionTest()
    {
        AnswerFormTrue("AdditionTest\n", "(a|b)", "a", 5000);
        AnswerFormTrue("", "(a|b)", "b", 5000);
        AnswerFormFalse("", "(a|b)", "ab", 5000);
        ConsoleStrings.PrintBorder();
    }

    @Test
    public void ProductionTest()
    {
        AnswerFormTrue("ProductionTest\n", "vjreijviorejirjvijirjeivjlkfdvjrietu", "vjreijviorejirjvijirjeivjlkfdvjrietu", 5000);
        AnswerFormFalse("", "aaaaaaaaaabababbababababauwhudwhuahdwuabbab", "aaaaaaaaaababab", 5000);
        ConsoleStrings.PrintBorder();
    }
    
    @Test
    public void RevertionTest()
    {
        AnswerFormTrue("RevertionTest\n", "(abcd)|(abcdd)|(abcddd)|(abcdddd)|(abcddddd)|(abcdddddd)", "abcdddddd", 5000);
        ConsoleStrings.PrintBorder();
    }

    @Test
    public void ClassicTest()
    {
        AnswerFormFalse("ClassicTest\n", "(a|b)*abb", "abababababbabababababbabababababababababababababababababbbbb", 5000);
        AnswerFormTrue("ClassicTest\n", "(a|b)*abb", "aaaaaaaaaaaaabb", 5000);
        ConsoleStrings.PrintBorder();
    }
}