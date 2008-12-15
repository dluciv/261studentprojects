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
    
    public Statistic Match(String regExp, String test, int num)
    {
        if(num < 1)
            return null;
        Calendar checkIn, checkOut;
        boolean answer = true;
        long time;
        StatEntry entry;
        TimeZone tz = TimeZone.getDefault();
        Statistic stat = new Statistic();                                
        PatternParser p = new PatternParser(regExp);
        setSymbols(regExp);
        try {                   
            Graph unDefGraph = p.parse();            
            Table unDefTable = new Table(unDefGraph);            
            p = new PatternParser(regExp);
            Graph defGraph = p.parse();
            defGraph = GraphWorker.makeDeterministic(defGraph);            
            Table defTable = new Table(defGraph);            
            
            checkIn = Calendar.getInstance(tz);
            for(int j = 0; j <= num; j++)
            {
                answer = ParserMatcher.GraphMatch(unDefGraph, test);
            }
            checkOut = Calendar.getInstance(tz);            
            time = checkOut.getTimeInMillis() - checkIn.getTimeInMillis();
            entry = new StatEntry(test, regExp, time, answer);
            stat.addEntry("UndefinedGraphMatcher", entry);            

            checkIn = Calendar.getInstance(tz);
            for(int j = 0; j < num; j++)
            {
                answer = ParserMatcher.TableMatch(unDefTable, test);
            }
            checkOut = Calendar.getInstance(tz);
            time = checkOut.getTimeInMillis() - checkIn.getTimeInMillis();
            entry = new StatEntry(test, regExp, time, answer);
            stat.addEntry("UndefinedTableMatcher", entry);

            checkIn = Calendar.getInstance(tz);
            for(int j = 0; j < num; j++)
            {
                answer = ParserMatcher.GraphMatch(defGraph, test);
            }
            checkOut = Calendar.getInstance(tz);
            time = checkOut.getTimeInMillis() - checkIn.getTimeInMillis();
            entry = new StatEntry(test, regExp, time, answer);
            stat.addEntry("DefinedGraphMatcher", entry);                                      

            checkIn = Calendar.getInstance(tz);
            for(int j = 0; j < num; j++)            
            {
                answer = ParserMatcher.TableMatch(defTable, test);
            }
            checkOut = Calendar.getInstance(tz);
            time = checkOut.getTimeInMillis() - checkIn.getTimeInMillis();
            entry = new StatEntry(test, regExp, time, answer);
            stat.addEntry("DefinedTableMatcher", entry);                      
        } catch (ParserException ex) {
            Logger.getLogger(PatternParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stat;
    }
}
