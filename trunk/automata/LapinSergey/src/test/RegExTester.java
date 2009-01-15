/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Calendar;
import java.util.TimeZone;
import parser.PatternParser;
import parser.ParserMatcher;
import parser.ParserException;
import graph.Graph;
import graph.GraphWorker;
import table.Table;
import statistic.Statistic;
import statistic.StatEntry;


/**
 *
 * @author lapin
 */
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
    
    public String generateTest(int sizeofstr)
    {
        String res = new String();
        Random r = new Random();
        for(int i = 0; i < sizeofstr; i++)
        {
            int rand = r.nextInt(symbols.size());        
            res += symbols.get(rand); 
        }        
        return res;
    }  
    
    private double CalcNetworkCapasity (long time, String test, int numiter)
    {
        return (double)test.length() * numiter / ((double)time / 1000000000); 
    }
    
    public Statistic Match(String regExp, String test, int num)
    {
        if(num < 1)
            return null;
        long checkIn, checkOut;
        boolean answer = true;        
        double capasity;
        
        StatEntry entry;
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
            
            checkIn = System.nanoTime();            
            for(int j = 0; j <= num; j++)
            {
                answer = unDefGraph.Match(test);
            }                
            checkOut = System.nanoTime();            
            long unDefGraphTime = checkOut - checkIn;
            capasity = CalcNetworkCapasity(unDefGraphTime, test, num);
            entry = new StatEntry(capasity, answer);
            stat.addEntry("UndefinedGraphMatcher", entry);            

            checkIn = System.nanoTime();                            
            for(int j = 0; j <= num; j++)
            {
                answer = defGraph.Match(test);
            }
            checkOut = System.nanoTime();            
            long defGraphTime = checkOut - checkIn;
            capasity = CalcNetworkCapasity(defGraphTime, test, num);
            entry = new StatEntry(capasity, answer);
            stat.addEntry("DefinedGraphMatcher", entry);                            

            checkIn = System.nanoTime();                            
            for(int j = 0; j <= num; j++)
            {
                answer = minGraph.Match(test);
            }
            checkOut = System.nanoTime();            
            long minGraphTime = checkOut - checkIn;
            capasity = CalcNetworkCapasity(minGraphTime, test, num);
            entry = new StatEntry(capasity, answer);
            stat.addEntry("MinimalGraphMatcher", entry);                            

            checkIn = System.nanoTime();                            
            for(int j = 0; j <= num; j++)
            {
                answer = unDefTable.Match(test);
            }
            checkOut = System.nanoTime();            
            long unDefTableTime = checkOut - checkIn;
            capasity = CalcNetworkCapasity(unDefTableTime, test, num);
            entry = new StatEntry(capasity, answer);
            stat.addEntry("UndefinedTableMatcher", entry);                            

            checkIn = System.nanoTime();                            
            for(int j = 0; j <= num; j++)
            {
                answer = defTable.Match(test);
            }
            checkOut = System.nanoTime();            
            long defTableTime = checkOut - checkIn;
            capasity = CalcNetworkCapasity(defTableTime, test, num);
            entry = new StatEntry(capasity, answer);
            stat.addEntry("DefinedTableMatcher", entry);            

            checkIn = System.nanoTime();                            
            for(int j = 0; j <= num; j++)
            {
                answer = minTable.Match(test);
            }
            checkOut = System.nanoTime();            
            long minTableTime = checkOut - checkIn;
            capasity = CalcNetworkCapasity(defGraphTime, test, num);
            entry = new StatEntry(minTableTime, answer);
            stat.addEntry("MinimalTableMatcher", entry);                                                
        } catch (ParserException ex) {
            Logger.getLogger(PatternParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stat;
    }
}
