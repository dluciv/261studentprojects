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
    Result table;
    Result NFA;
    Result DFA;
    Result minGraph;

    public TestResultItem(String pattern, String string, boolean matches) {
        this.pattern = pattern;
        this.string = string;
        this.matches = matches;
    }

    public Result getDFA() {
        return DFA;
    }

    public Result getNFA() {
        return NFA;
    }


    public boolean isMatches() {
        return matches;
    }

    public Result getMinGraph() {
        return minGraph;
    }

    public String getPattern() {
        return pattern;
    }

    public String getString() {
        return string;
    }

    public Result getTable() {
        return table;
    }

    public void setDFA(Result DFA) {
        this.DFA = DFA;
    }

    public void setNFA(Result NFA) {
        this.NFA = NFA;
    }


    public void setMinGraph(Result minGraph) {
        this.minGraph = minGraph;
    }

    public void setTable(Result table) {
        this.table = table;
    }

    
}
