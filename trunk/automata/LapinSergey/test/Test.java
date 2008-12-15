package test;
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
        Statistic stat = a.Match("(a|b)*abb", "b", 100000);
        System.out.println(stat);
        System.out.println("UndefinedGraphMatcher " + 
                stat.calcSummaryTimeOnEntry("UndefinedGraphMatcher"));
        System.out.println("DefinedGraphMatcher " + 
                stat.calcSummaryTimeOnEntry("DefinedGraphMatcher"));
        System.out.println("UndefinedTableMatcher " + 
                stat.calcSummaryTimeOnEntry("UndefinedTableMatcher"));
        System.out.println("DefinedTableMatcher " + 
                stat.calcSummaryTimeOnEntry("DefinedTableMatcher"));
    }
}