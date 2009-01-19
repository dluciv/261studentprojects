package test;
import statistic.StatEntry;
import statistic.Statistic;

/**
 *
 * Lapin Sergey 261 group mat-mex
 * Regular expression analysis
 * 19.01.2009
 */
public class Test {        
    public static void main(String[] args) {
        RegExTester a = new RegExTester();
        Statistic stat = a.Match("(a|b)*abb", "abbbabbbbbbbbbbbbbbbbbbbbbbbbbbbbbabb", 5000);
        
        for(StatEntry tmp : stat.SortByCapasity())
        {
            System.out.println(stat.findByEntry(tmp) + tmp);
        }        
        System.out.println(stat.checkAnswersEquality());
        System.out.println(stat.getAnswer());
    }
}