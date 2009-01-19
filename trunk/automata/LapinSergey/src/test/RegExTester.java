/**
 *
 * Lapin Sergey 261 group mat-mex
 * Regular expression analysis
 * 19.01.2009
 */

package test;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import parser.PatternParser;
import parser.ParserException;
import graph.Graph;
import graph.GraphWorker;
import table.Table;
import statistic.*;

public class RegExTester {
    private ArrayList<Character> symbols;
            
    public RegExTester()
    {                                
    }    
    
    public ArrayList<Character> getSymbols()
    {        
        return symbols;
    }
    
    public void setSymbols(String str)
    {
        symbols = new ArrayList<Character>();
        for(int i = 0; i < str.length(); i++)
            if(Character.isLetterOrDigit(str.charAt(i)))
                symbols.add(str.charAt(i));                        
    }
        
    private double CalcNetworkCapasity (long time, String test, int numiter)
    {
        return (double)test.length() * 10 * numiter / ((double)time / 1000000000);
    }

    private StatEntry CalcEntry(MathModel model, String test, int num)
    {
        long checkIn, checkOut;
        boolean answer = true;
        double capasity;
        checkIn = System.nanoTime();
        for(int j = 0; j <= num; j++)
        {
            answer = model.Match(test);
        }
        checkOut = System.nanoTime();
        long unDefGraphTime = checkOut - checkIn;
        capasity = CalcNetworkCapasity(unDefGraphTime, test, num);
        StatEntry entry = new StatEntry(capasity, answer);
        return entry;
    }
    
    public Statistic Match(String regExp, String test, int num)
    {
        if(num < 1)
            return null;
                
        Statistic stat = new Statistic(regExp, test);                                
        PatternParser p = new PatternParser(regExp);
        setSymbols(regExp);
        try {                   
            Graph unDefGraph = p.parse();            
            GraphWorker.fileForGraphviz(unDefGraph, "unDefGraph.txt");
            
            Table unDefTable = new Table(unDefGraph);
            unDefTable.fileForGraphviz("unDefTable.txt");
            
            p = new PatternParser(regExp);
            Graph defGraph = p.parse();
            defGraph = GraphWorker.makeDeterministic(defGraph);
            GraphWorker.fileForGraphviz(defGraph, "defGraph.txt");
            
            Table defTable = new Table(defGraph);
            defTable.fileForGraphviz("defTable.txt");
            
            p = new PatternParser(regExp);
            Graph minGraph = p.parse();            
            minGraph = GraphWorker.makeDeterministic(minGraph);
            minGraph = GraphWorker.makeMinimal(minGraph);
            GraphWorker.fileForGraphviz(minGraph, "minGraph.txt");
            
            Table minTable = new Table(minGraph);
            defTable.fileForGraphviz("table_b.txt");

            stat.addEntry("UndefinedGraphMatcher", CalcEntry(unDefGraph, test, num));
            stat.addEntry("DefinedGraphMatcher",   CalcEntry(defGraph,   test, num));
            stat.addEntry("MinimalGraphMatcher",   CalcEntry(minGraph,   test, num));
            stat.addEntry("UndefinedTableMatcher", CalcEntry(unDefTable, test, num));
            stat.addEntry("DefinedTableMatcher",   CalcEntry(defTable,   test, num));
            stat.addEntry("MinimalTableMatcher",   CalcEntry(minTable,   test, num));
        } catch (ParserException ex) {
            Logger.getLogger(PatternParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stat;
    }
}
