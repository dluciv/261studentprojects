/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package j2se.g261.eda.automator.tests;

import java.io.Serializable;

/**
 *
 * @author nastya
 */
public class TestResultItem implements Serializable{
    String pattern;
    String string;
    boolean matches;
    boolean matchesTable = false;
    boolean matchesNFA = false;
    boolean matchesDFA = false;
    boolean matchesMinGraph = false;
    long timeTable = 0l;
    long timeNFA = 0l;
    long timeDFA = 0l;
    long timeMinGraph = 0l;
    int cicle = 1;

    public TestResultItem(String pattern, String string, boolean matches) {
        this.pattern = pattern;
        this.string = string;
        this.matches = matches;
    }
    
    TestResultItem(TestItem item){
        this(item.getPattern(), item.getString(), item.isMatches());
    }

    public int getCicle() {
        return cicle;
    }

    public boolean isMatches() {
        return matches;
    }

    public boolean isMatchesDFA() {
        return matchesDFA;
    }

    public boolean isMatchesMinGraph() {
        return matchesMinGraph;
    }

    public boolean isMatchesNFA() {
        return matchesNFA;
    }

    public boolean isMatchesTable() {
        return matchesTable;
    }

    public String getPattern() {
        return pattern;
    }

    public String getString() {
        return string;
    }

    public long getTimeDFA() {
        return timeDFA;
    }

    public long getTimeMinGraph() {
        return timeMinGraph;
    }

    public long getTimeNFA() {
        return timeNFA;
    }

    public long getTimeTable() {
        return timeTable;
    }

    public void setCicle(int cicle) {
        this.cicle = cicle;
    }

    public void setMatches(boolean matches) {
        this.matches = matches;
    }

    public void setMatchesDFA(boolean matchesDFA) {
        this.matchesDFA = matchesDFA;
    }

    public void setMatchesMinGraph(boolean matchesMinGraph) {
        this.matchesMinGraph = matchesMinGraph;
    }

    public void setMatchesNFA(boolean matchesNFA) {
        this.matchesNFA = matchesNFA;
    }

    public void setMatchesTable(boolean matchesTable) {
        this.matchesTable = matchesTable;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public void setString(String string) {
        this.string = string;
    }

    public void setTimeDFA(long timeDFA) {
        this.timeDFA = timeDFA;
    }

    public void setTimeMinGraph(long timeMinGraph) {
        this.timeMinGraph = timeMinGraph;
    }

    public void setTimeNFA(long timeNFA) {
        this.timeNFA = timeNFA;
    }

    public void setTimeTable(long timeTable) {
        this.timeTable = timeTable;
    }
    
    
}
