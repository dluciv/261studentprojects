/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package statistic;

/**
 *
 * @author lapin
 */
public class StatEntry {
    private String checking, checked;
    private long timeofcheck;
    private boolean answer;
    
    public StatEntry(String checking, String checked, long timeofcheck, boolean answer)
    {
        this.checked = checked;
        this.checking = checking;
        this.timeofcheck = timeofcheck;
        this.answer = answer;
    }
    
    public String getChecking()
    {
        return checking;
    }
    
    public String getChecked()
    {
        return checked;
    }
    
    public long getTimeofCheck()
    {
        return timeofcheck;
    }
    
    public boolean getAnswer()
    {
        return answer;
    }
    
    @Override
    public String toString() {    
        String s = new String();
        s += "StatEntry: checking string is " + checking + "\n";
        s += "checked string is " + checked + "\n";
        s += "timeofcheck is " + timeofcheck + "\n";
        s += "with answer: " + answer;        
        return s;
    }
}
