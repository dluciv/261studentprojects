/**
 *
 * Lapin Sergey 261 group mat-mex
 * Regular expression analysis
 * 19.01.2009
 */

package statistic;

import java.util.Comparator;

public class StatEntry {    
    private double networkcapasity;
    private boolean answer;
    static CapasityComparator capasitycomparator = new CapasityComparator();
    
    public static class CapasityComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            return (int)((StatEntry)o1).networkcapasity - (int)((StatEntry)o2).networkcapasity;
        }       
    }
    
    public static CapasityComparator getCapasityComparator()
    {
        return capasitycomparator;
    }
    
    public StatEntry(double networkcapasity, boolean answer)
    {       
        this.networkcapasity = networkcapasity;
        this.answer = answer;
    }
    
    public int compareTo(Object o) {
        StatEntry tmp = (StatEntry)o;
        if(this.networkcapasity > tmp.networkcapasity)
            return 1;
        if(this.networkcapasity < tmp.networkcapasity)
            return -1;
        return 0;
    }  
    
    
    public double getNetworkCapasity()
    {
        return networkcapasity;
    }
    
    public boolean getAnswer()
    {
        return answer;
    }
    
    @Override
    public String toString() {    
        String s = new String();
        s += "capasity is " + networkcapasity + "\n";
        s += "with answer: " + answer;        
        return s;
    }
}
