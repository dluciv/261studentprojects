package test;
import statistic.StatEntry;
import statistic.Statistic;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author lapin
 */
public class Test {        
    public static void main(String[] args) {
        RegExTester a = new RegExTester();
        Statistic stat = a.Match("(a|b)*abb", "abbbbbbbbbbbbabb", 5000);
        
        for(StatEntry tmp : stat.SortByCapasity())
        {
            System.out.println(stat.findByEntry(tmp) + tmp);
        }        
        System.out.println(stat.checkAnswersEquality());
        System.out.println(stat.getAnswer());
    }
}